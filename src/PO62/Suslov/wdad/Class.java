package PO62.Suslov.wdad;

import PO62.Suslov.wdad.data.managers.JDBCDataManager;
import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;

import java.rmi.RemoteException;
import java.util.List;

class Class {
    public static void main(String[] args) throws RemoteException {
        JDBCDataManager jdbcDataManager = new JDBCDataManager();

        User user = new User("test@yandex.ru", "test");

        jdbcDataManager.updateNote(user, "Еда", "Еда 2.1");

        jdbcDataManager.setPrivileges(user, "Еда", 1);

        List<Note> notes = jdbcDataManager.getNotes(user);

        System.out.println(jdbcDataManager.getNote(user, "Еда"));
    }
}