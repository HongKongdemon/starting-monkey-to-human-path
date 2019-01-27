package PO62.Suslov.wdad.data.managers;

import PO62.Suslov.wdad.data.storage.DataSourceFactory;
import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;
import org.xml.sax.SAXException;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.List;


public class JDBCDataManager implements DataManager
{
    @Override
    public Note getNote(User owner, String title) throws IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        Statement stmt = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Note note = new Note();
        User user = new User();

        try {
            con = ds.getConnection();

            statement = con.prepareStatement("SELECT title, creation_date, text, users.name " +
                    "FROM note INNER JOIN users ON note.author_id = users.id " +
                    "WHERE note.title = ? AND users.name = ?");

            statement.setString(1, title);
            statement.setString(2, owner.getName());

            rs = statement.executeQuery();

            while(rs.next()){
                note.setTitle(rs.getString("title"));
                note.setCdate(rs.getString("creation_date"));
                note.setText(rs.getString("text"));
                user.setName(rs.getString("name"));
                note.setOwner(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(statement != null) statement.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return note;
    }

    @Override
    public void updateNote(User owner, String title, String newText) throws RemoteException, IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        Statement stmt = null;
        PreparedStatement statement = null;
        Note note = new Note();
        User user = new User();

        try {
            con = ds.getConnection();

            statement = con.prepareStatement("UPDATE note " +
                    "SET text = ? " +
                    "WHERE title = ? AND " +
                    "author_id = (SELECT id FROM users WHERE name = ?)");

            statement.setString(1, newText);
            statement.setString(2, title);
            statement.setString(3, owner.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement != null) statement.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        Statement stmt = null;
        PreparedStatement statement = null;

        try {
            con = ds.getConnection();

            statement = con.prepareStatement("UPDATE users_privileges " +
                    "SET privilege = ? " +
                    "WHERE notes_id = (SELECT id FROM note WHERE title = ?) AND " +
                    "users_id = (SELECT id FROM users WHERE name = ?)");

            statement.setString(1, String.valueOf(newRights));
            statement.setString(2, noteTitle);
            statement.setString(3, user.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement != null) statement.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Note> getNotes(User owner) throws IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        return null;
    }

}