package PO62.Suslov.wdad.data.managers;

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

public class PreferencesManager {
    private static final String path = "src/PO62/Suslov/wdad/resources/configuration/appconfig.xml";

    private Document document;

    private static PreferencesManager ourInstance = new PreferencesManager();

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(inputFile);

            DOMBuilder domBuilder = new DOMBuilder();
            document = domBuilder.build(doc);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public String getCreateRegistry() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").getText();
    }

    public void setCreateRegistry(String createRegistry) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").setText(createRegistry);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public String getRegistryAddress() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").getText();
    }

    public void setRegistryAddress(String registryAddress) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").setText(registryAddress);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public String getRegistryPort() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").getText();
    }

    public void setRegistryPort(String registryPort) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").setText(registryPort);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public String getPolicyPath() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("client").getChild("policypath").getText();
    }

    public void setPolicyPath(String policyPath) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("client").getChild("policypath").setText(policyPath);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public String getUseCodeBaseOnly()
    {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("client").getChild("usecodebaseonly").getText();
    }

    public void setUseCodeBaseOnly(String useCodeBaseOnly) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("client").getChild("usecodebaseonly").setText(useCodeBaseOnly);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    public String getClassProvider() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("classprovider").getText();
    }

    public void setClassProvider(String classProvider) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("classprovider").setText(classProvider);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }
}
