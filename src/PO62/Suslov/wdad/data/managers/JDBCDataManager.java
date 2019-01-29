package PO62.Suslov.wdad.data.managers;

import PO62.Suslov.wdad.data.storage.DataSourceFactory;
import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class JDBCDataManager implements DataManager {
    DataSource dataSource;

    public JDBCDataManager() {
        dataSource = DataSourceFactory.createDataSource();
    }

    @Override
    public String getNote(User owner, String title) throws RemoteException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("SELECT text FROM notes LEFT JOIN users ON users.id = notes.author_id WHERE users.name = ? AND notes.title = ?")) {
                stmt.setString(1, owner.getName());
                stmt.setString(2, title);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("text");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateNote(User owner, String title, String newText) throws RemoteException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("UPDATE notes LEFT JOIN users ON users.id = notes.author_id SET text = ? WHERE users.name = ? AND notes.title = ?")) {
                stmt.setString(2, owner.getName());
                stmt.setString(1, newText);
                stmt.setString(3, title);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPrivileges(User user, String noteTitle, int newRights) throws RemoteException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("UPDATE user_privileges SET privilege = ? WHERE notes_id = (SELECT id FROM notes WHERE title = ?) AND users_id = (SELECT id FROM users WHERE name = ?)")) {
                stmt.setInt(1, newRights);
                stmt.setString(2, noteTitle);
                stmt.setString(3, user.getName());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> getNotes(User owner) throws RemoteException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM notes WHERE author_id = (SELECT id FROM users WHERE name = ?)")) {
                stmt.setString(1, owner.getName());
                ResultSet resultSet = stmt.executeQuery();

                List<Note> notes = new ArrayList<>();

                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String text = resultSet.getString("text");

                    notes.add(new Note(title, text));
                }

                return notes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
