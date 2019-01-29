package PO62.Suslov.wdad.learn.rmi;

import PO62.Suslov.wdad.data.managers.DataManager;
import PO62.Suslov.wdad.data.managers.PreferencesManager;
import PO62.Suslov.wdad.utils.PreferencesManagerConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();

        System.setProperty("java.security.policy", preferencesManager.getProperty(PreferencesManagerConstants.POLICYPATH));
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            DataManager xmlDataManager = new XmlDataManagerImpl("src/PO62/Suslov/wdad/learn/xml/test.xml");
            DataManager stub = (DataManager) UnicastRemoteObject.exportObject(xmlDataManager, 0);
            Registry registry;
            if (preferencesManager.getProperty(PreferencesManagerConstants.CREATEREGISTRY).equals("yes")) {
                registry = LocateRegistry.createRegistry(Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRYPORT)));
            } else {
                registry = LocateRegistry.getRegistry(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRYADDRESS), Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRYPORT)));
            }

            registry.bind("XmlDataManager", stub);
        } catch (AlreadyBoundException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
