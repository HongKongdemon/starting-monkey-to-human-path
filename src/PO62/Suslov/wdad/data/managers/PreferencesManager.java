package PO62.Suslov.wdad.data.managers;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class PreferencesManager {
    private static PreferencesManager instance;
    Element root;
    List<Element> nodeListElements;
    XMLOutputter xmlOut;
    org.jdom2.Document jdomDocument;
    String fileName = "";

    public PreferencesManager() throws IOException, SAXException, ParserConfigurationException {
        fileName = "src/PO62/Suslov/wdad/resources/configuration/appconfig.xml";
        jdomDocument = createJDOMusingDOMParser(fileName);
        xmlOut = new XMLOutputter();
        root = jdomDocument.getRootElement();
    }
    public static PreferencesManager getInstance() throws ParserConfigurationException, SAXException, IOException {
        if (instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }
    private static org.jdom2.Document createJDOMusingDOMParser(String fileName)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        documentBuilder = dbFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(fileName));
        DOMBuilder domBuilder = new DOMBuilder();
        return domBuilder.build(doc);
    }
    public String getCreateRegistry()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("createregistry");
    }
    public void setCreateRegistry(String createregistry) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").setText(createregistry);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    public String getRegistryAddress()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("registryaddress");
    }
    public void setRegistryAddress(String registryaddress) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").setText(registryaddress);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    public String getRegistryPort()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("registryport");
    }
    public void setRegistryPort(String registryport) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").setText(registryport);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    public String getPolicyPath()
    {
        return root.getChild("rmi").getChild("client").getChildText("policypath");
    }
    public void setPolicyPath(String policypath) throws IOException {
        root.getChild("rmi").getChild("client").getChild("policypath").setText(policypath);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    public String getUseCodeBaseOnly()
    {
        return root.getChild("rmi").getChild("client").getChildText("usecodebaseonly");
    }
    public void setUseCodeBaseOnly(String usecodebaseonly) throws IOException {
        root.getChild("rmi").getChild("client").getChild("usecodebaseonly").setText(usecodebaseonly);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    public String getClassProvider()
    {
        return root.getChild("rmi").getChildText("classprovider");
    }
    public void setClassProvider(String classprovider) throws IOException {
        root.getChild("rmi").getChild("classprovider").setText(classprovider);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
}
