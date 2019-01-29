package PO62.Suslov.wdad.learn.rmi;

import PO62.Suslov.wdad.data.managers.PreferencesManager;
import PO62.Suslov.wdad.learn.xml.User;
import PO62.Suslov.wdad.utils.PreferencesManagerConstants;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();

        System.setProperty("java.security.policy", preferencesManager.getProperty(PreferencesManagerConstants.POLICYPATH));
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.getRegistry(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRYADDRESS), Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRYPORT)));

            XmlDataManager xmlDataManager = (XmlDataManager) registry.lookup("XmlDataManager");

            User user = new User("test@yandex.ru", "test");

            System.out.println(xmlDataManager.getNote(user, "Еда"));
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
