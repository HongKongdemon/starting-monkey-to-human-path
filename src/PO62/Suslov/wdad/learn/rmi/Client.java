package PO62.Suslov.wdad.learn.rmi;

import PO62.Suslov.wdad.data.managers.PreferencesManager;
import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;
import PO62.Suslov.wdad.utils.PreferencesManagerConststs;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    static private String policyPath;
    static private String codebaseUrl;
    static private int registryPort;
    static final private String EXECUTOR_NAME = "xmlDataManager";

    static private PreferencesManager preferencesManager;
    static private String registryAddres;
    static private String useCodebaseOnly;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, URISyntaxException, IOException, SAXException {
        preferencesManager = PreferencesManager.getInstance();
        registryPort = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConststs.REGISTRY_PORT));
        registryAddres = preferencesManager.getProperty(PreferencesManagerConststs.REGISTRY_ADDRESS);
        codebaseUrl = "file://".concat(Client.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

        policyPath = preferencesManager.getProperty(PreferencesManagerConststs.POLICY_PATH);
        useCodebaseOnly = preferencesManager.getProperty(PreferencesManagerConststs.USE_CODE_BASE_ONLY);

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodebaseOnly", useCodebaseOnly);
        System.setProperty("java.security.policy", policyPath);

        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(registryAddres, registryPort);
        } catch (RemoteException re) {
            System.err.println("cant locate registry");
            re.printStackTrace();

        }

        if (registry != null) {
            try {
                Remote remote = registry.lookup(EXECUTOR_NAME);
                test((XmlDataManager) remote);
            } catch (RemoteException re) {
                System.err.println("something unbelievable happens");
                re.printStackTrace();
            } catch (Exception e) {
                System.err.println("user input err");
                e.printStackTrace();
            }
        }
    }



    public static void test(XmlDataManager xmlDataManager) throws RemoteException {
        User owner = new User();
        owner.setName("Name1");
        owner.setMail("Mail1");
        Note note = xmlDataManager.getNote(owner, "Title1");

        System.out.println("Text: " + note.getText() + '\n'
                + "Title: " + note.getTitle());
    }
}