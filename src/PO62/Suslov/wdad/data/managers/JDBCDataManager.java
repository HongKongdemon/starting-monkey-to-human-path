package PO62.Suslov.wdad.data.managers;

import PO62.Suslov.wdad.data.storage.DataSourceFactory;
import PO62.Suslov.wdad.learn.xml.Note;
import PO62.Suslov.wdad.learn.xml.User;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            try (PreparedStatement stmt = connection.prepareStatement("SELECT text FROM notes LEFT JOIN users ON users.id = notes.author_id WHERE users.name = ?")) {
                stmt.setString(1, owner.getName());
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
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPrivileges(User user, String noteTitle, int newRights) throws RemoteException {
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> getNotes(User owner) throws RemoteException {
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
