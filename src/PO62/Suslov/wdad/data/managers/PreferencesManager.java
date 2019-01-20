package PO62.Suslov.wdad.data.managers;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import PO62.Suslov.wdad.utils.PreferencesManagerConststs;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public final class PreferencesManager {
    private static PreferencesManager instance;
    Element root;
    List<Element> nodeListElements;
    XMLOutputter xmlOut;
    org.jdom2.Document jdomDocument;
    String fileName = "";
    String a = "";

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
    @Deprecated
    public String getCreateRegistry()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("createregistry");
    }
    @Deprecated
    public void setCreateRegistry(String createregistry) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").setText(createregistry);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String getRegistryAddress()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("registryaddress");
    }
    @Deprecated
    public void setRegistryAddress(String registryaddress) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").setText(registryaddress);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String getRegistryPort()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("registryport");
    }
    @Deprecated
    public void setRegistryPort(String registryport) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").setText(registryport);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String getPolicyPath()
    {
        return root.getChild("rmi").getChild("client").getChildText("policypath");
    }
    @Deprecated
    public void setPolicyPath(String policypath) throws IOException {
        root.getChild("rmi").getChild("client").getChild("policypath").setText(policypath);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String getUseCodeBaseOnly()
    {
        return root.getChild("rmi").getChild("client").getChildText("usecodebaseonly");
    }
    @Deprecated
    public void setUseCodeBaseOnly(String usecodebaseonly) throws IOException {
        root.getChild("rmi").getChild("client").getChild("usecodebaseonly").setText(usecodebaseonly);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String getClassProvider()
    {
        return root.getChild("rmi").getChildText("classprovider");
    }
    @Deprecated
    public void setClassProvider(String classprovider) throws IOException {
        root.getChild("rmi").getChild("classprovider").setText(classprovider);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    public void setProperty(String key, String value) throws IOException {
        String[] tags = key.split("\\.");
        Element element = null;
        for(String s : tags)
        {
            element = root.getChild(s);
        }
        element.setText(value);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }



    public String getProperty(String key)
    {
        String[] tags = key.split("\\.");
        Element element = root;
        for(String child : tags)
        {
            element = element.getChild(child);
        }

        if(element != null)
        {
            return element.getText();
        }

        return "";

    }

    public void setProperties(Properties prop)
    {
        prop.stringPropertyNames().forEach(s -> {
            try {
                setProperty(s,prop.getProperty(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public Properties getProperties()
    {

        Properties properties = new Properties();
        String[] keys = {PreferencesManagerConststs.CLASS_PROVIDER,PreferencesManagerConststs.CREATE_REGISTRY,
                PreferencesManagerConststs.POLICY_PATH, PreferencesManagerConststs.REGISTRY_ADDRESS,
                PreferencesManagerConststs.USE_CODE_BASE_ONLY, PreferencesManagerConststs.REGISTRY_PORT};

        for(String s : keys){
            properties.setProperty(s,getProperty(s));
        }
        return properties;

    }

    public void addBindedObject(String name, String className)
    {

    }

    public void removeBindedObject(String name)
    {

    }
}