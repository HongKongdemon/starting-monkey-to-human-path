package PO62.Suslov.wdad.learn.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTask {
    private Document document;
    private String path;

    public XmlTask(String path) throws IOException, SAXException, ParserConfigurationException {
        this.path = path;

        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(inputFile);

        DOMBuilder domBuilder = new DOMBuilder();
        document = domBuilder.build(doc);
    }

    public String getNoteText(User owner, String title)
    {
        Element root = document.getRootElement();

        List<Element> childs = root.getChildren("note");
        for (Element child: childs) {
            String childTitle = child.getChild("title").getText();

            if (childTitle.equals(title)) {
                Element childOwner = child.getChild("owner");
                String email = childOwner.getAttribute("mail").getValue();
                String name = childOwner.getAttribute("name").getValue();

                if (email.equals(owner.getMail()) && name.equals(owner.getName())) {
                    return child.getChild("text").getText();
                }
            }
        }

        return null;
    }

    public void updateNote(User owner, String title, String newText) throws IOException {
        Element root = document.getRootElement();

        List<Element> childs = root.getChildren("note");
        for (Element child: childs) {
            String childTitle = child.getChild("title").getText();

            if (childTitle.equals(title)) {
                Element childOwner = child.getChild("owner");
                String email = childOwner.getAttribute("mail").getValue();
                String name = childOwner.getAttribute("name").getValue();

                if (email.equals(owner.getMail()) && name.equals(owner.getName())) {
                    child.getChild("text").setText(newText);
                }
            }
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public void setPrivileges(User owner, String title, int newRights) throws IOException {
        Element root = document.getRootElement();

        List<Element> childs = root.getChildren("note");
        for (Element child: childs) {
            String childTitle = child.getChild("title").getText();

            if (childTitle.equals(title)) {
                List<Element> childPrivileges = child.getChildren("privileges");

                for (Element privilegeElement: childPrivileges) {
                    List<Element> privileges = privilegeElement.getChildren();

                    for (Element privilege: privileges) {
                        if (privilege.getName().equals("user")) {
                            String email = privilege.getAttribute("mail").getValue();
                            String name = privilege.getAttribute("name").getValue();

                            if (email.equals(owner.getMail()) && name.equals(owner.getName())) {
                                switch (newRights) {
                                    case 0:
                                        privilege.detach();
                                        break;
                                    case 1:
                                        privilege.getAttribute("rights").setValue("R");
                                        break;
                                    case 3:
                                        privilege.getAttribute("rights").setValue("RW");
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public List<Note> getNotes(User owner) {
        Element root = document.getRootElement();

        List<Note> notes = new ArrayList<>();
        List<Element> childs = root.getChildren("note");
        for (Element child: childs) {
            Element childOwner = child.getChild("owner");
            String email = childOwner.getAttribute("mail").getValue();
            String name = childOwner.getAttribute("name").getValue();

            if (email.equals(owner.getMail()) && name.equals(owner.getName())) {
                String title = child.getChild("title").getText();
                String text = child.getChild("text").getText();

                notes.add(new Note(title, text));
            }
        }

        return notes;
    }
}
