package PO62.Suslov.wdad.learn.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlTask {
    Document document;

    public XmlTask(String path) throws IOException, SAXException, ParserConfigurationException {
        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(inputFile);

        DOMBuilder domBuilder = new DOMBuilder();
        document = domBuilder.build(doc);
    }

    public String getNoteText (User owner, String title)
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
//
//    public void updateNote (User owner, String title, String newText)
//    {
//
//    }
//    public void setPrivileges (String noteTitle, User user, int newRights)
//    {
//
//    }
}
