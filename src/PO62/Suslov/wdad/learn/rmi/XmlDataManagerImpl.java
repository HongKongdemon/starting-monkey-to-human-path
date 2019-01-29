package PO62.Suslov.wdad.learn.rmi;

import PO62.Suslov.wdad.data.managers.DataManager;
import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;
import PO62.Suslov.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class XmlDataManagerImpl implements DataManager {
    private XmlTask xmlTask;

    public XmlDataManagerImpl(String path) throws ParserConfigurationException, SAXException, IOException {
        xmlTask = new XmlTask(path);
    }

    @Override
    public String getNote(User owner, String title) throws RemoteException {
        return xmlTask.getNoteText(owner, title);
    }

    @Override
    public void updateNote(User owner, String title, String newText) throws RemoteException {
        try {
            xmlTask.updateNote(owner, title, newText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPrivileges(User user, String noteTitle, int newRights) throws RemoteException {
        try {
            xmlTask.setPrivileges(user, noteTitle, newRights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> getNotes(User owner) throws RemoteException {
        return xmlTask.getNotes(owner);
    }
}
