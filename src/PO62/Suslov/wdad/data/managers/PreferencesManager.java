package PO62.Suslov.wdad.data.managers;

import PO62.Suslov.wdad.utils.PreferencesManagerConstants;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PreferencesManager {
    private static final String path = "src/PO62/Suslov/wdad/resources/configuration/appconfig.xml";

    private Document document;
    private org.w3c.dom.Document doc;
    private XPath xPath;

    private static PreferencesManager ourInstance = new PreferencesManager();

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);

            xPath = XPathFactory.newInstance().newXPath();

            DOMBuilder domBuilder = new DOMBuilder();
            document = domBuilder.build(doc);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        try {
            return (String) xPath.evaluate(key + "/text()", doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProperty(String key, String value) {
        try {
            ((Node) xPath.evaluate(key, doc, XPathConstants.NODE)).getFirstChild().setNodeValue(value);

            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());
            xmlOutputter.output(new DOMBuilder().build(doc), new FileWriter(path));
        } catch (XPathExpressionException | IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.put(PreferencesManagerConstants.CREATEREGISTRY, getProperty(PreferencesManagerConstants.CREATEREGISTRY));
        props.put(PreferencesManagerConstants.REGISTRYADDRESS, getProperty(PreferencesManagerConstants.REGISTRYADDRESS));
        props.put(PreferencesManagerConstants.REGISTRYPORT, getProperty(PreferencesManagerConstants.REGISTRYPORT));
        props.put(PreferencesManagerConstants.POLICYPATH, getProperty(PreferencesManagerConstants.POLICYPATH));
        props.put(PreferencesManagerConstants.USECODEBASEONLY, getProperty(PreferencesManagerConstants.USECODEBASEONLY));
        props.put(PreferencesManagerConstants.CLASSPROVIDER, getProperty(PreferencesManagerConstants.CLASSPROVIDER));

        return props;
    }

    public void setProperties(Properties prop) {
        for (Object key : prop.keySet()) {
            setProperty(key.toString(), prop.getProperty(key.toString()));
        }
    }

    @Deprecated
    public String getCreateRegistry() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").getText();
    }

    @Deprecated
    public void setCreateRegistry(String createRegistry) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").setText(createRegistry);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    @Deprecated
    public String getRegistryAddress() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").getText();
    }

    @Deprecated
    public void setRegistryAddress(String registryAddress) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").setText(registryAddress);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    @Deprecated
    public String getRegistryPort() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").getText();
    }

    @Deprecated
    public void setRegistryPort(String registryPort) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").setText(registryPort);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    @Deprecated
    public String getPolicyPath() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("client").getChild("policypath").getText();
    }

    @Deprecated
    public void setPolicyPath(String policyPath) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("client").getChild("policypath").setText(policyPath);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    @Deprecated
    public String getUseCodeBaseOnly()
    {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("client").getChild("usecodebaseonly").getText();
    }

    @Deprecated
    public void setUseCodeBaseOnly(String useCodeBaseOnly) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("client").getChild("usecodebaseonly").setText(useCodeBaseOnly);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }

    @Deprecated
    public String getClassProvider() {
        Element root = document.getRootElement();

        return root.getChild("rmi").getChild("classprovider").getText();
    }

    @Deprecated
    public void setClassProvider(String classProvider) throws IOException {
        Element root = document.getRootElement();

        root.getChild("rmi").getChild("classprovider").setText(classProvider);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter(path));
    }
}
