package PO62.Suslov.wdad.learn.rmi;

import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface XmlDataManager extends Remote
{
    public Note getNote(User owner, String title) throws RemoteException;
    public void updateNote(User owner, String title, StringBuilder newText) throws IOException, RemoteException;
    public void setPrivileges(String noteTitle, User user, int newRights) throws IOException, RemoteException;
    public List<Note> getNotes(User owner) throws RemoteException;
}