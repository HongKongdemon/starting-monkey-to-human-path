package PO62.Suslov.wdad;

import PO62.Suslov.wdad.data.managers.JDBCDataManager;
import PO62.Suslov.wdad.learn.xml.User;

import java.rmi.RemoteException;

class Class {
    public static void main(String[] args) throws RemoteException {
        JDBCDataManager jdbcDataManager = new JDBCDataManager();

        User user = new User("test@yandex.ru", "test");

        System.out.println(jdbcDataManager.getNote(user, "Еда"));
    }
}