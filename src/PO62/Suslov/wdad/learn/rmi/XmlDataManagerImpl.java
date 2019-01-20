package PO62.Suslov.wdad.learn.rmi;

import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;
import PO62.Suslov.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager {
    XmlTask xmlTask;
    public XmlDataManagerImpl() throws ParserConfigurationException, SAXException, IOException {
        xmlTask = new XmlTask();
    }
    @Override
    public Note getNote(User owner, String title) throws RemoteException {
        return xmlTask.getNote(owner, title);
    }

    @Override
    public void updateNote(User owner, String title, StringBuilder newText) throws IOException, RemoteException {
        xmlTask.updateNote(owner, title, newText);

    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws IOException, RemoteException {
        xmlTask.setPrivileges(noteTitle, user, newRights);

    }

    @Override
    public List<Note> getNotes(User owner) throws RemoteException{

        return xmlTask.getNotes(owner);
    }
}