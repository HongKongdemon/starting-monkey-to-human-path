package PO62.Suslov.wdad.data.managers;

import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DataManager extends Remote
{
    public Note getNote(User owner, String title) throws RemoteException, IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException;
    public void updateNote(User owner, String title, String newText) throws IOException, RemoteException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException;
    public void setPrivileges(String noteTitle, User user, int newRights) throws IOException, RemoteException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException;
    public List<Note> getNotes(User owner) throws IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException;
}