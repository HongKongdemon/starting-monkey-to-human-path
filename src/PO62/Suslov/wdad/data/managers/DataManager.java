package PO62.Suslov.wdad.data.managers;

import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DataManager extends Remote {
    public String getNote (User owner, String title) throws RemoteException;
    public void updateNote (User owner, String title, String newText) throws RemoteException;
    public void setPrivileges (User user, String noteTitle, int newRights) throws RemoteException;
    public List<Note> getNotes (User owner) throws RemoteException;
}
