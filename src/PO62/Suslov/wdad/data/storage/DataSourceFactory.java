package PO62.Suslov.wdad.data.storage;

import PO62.Suslov.wdad.data.managers.PreferencesManager;
import PO62.Suslov.wdad.utils.PreferencesManagerConstants;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public static DataSource createDataSource() {
        PreferencesManager pm = PreferencesManager.getInstance();

        MysqlDataSource mysql = new MysqlDataSource();
        mysql.setServerName(pm.getProperty(PreferencesManagerConstants.HOSTNAME));
        mysql.setPortNumber(Integer.parseInt(pm.getProperty(PreferencesManagerConstants.PORT)));
        mysql.setDatabaseName(pm.getProperty(PreferencesManagerConstants.DBNAME));
        mysql.setUser(pm.getProperty(PreferencesManagerConstants.USER));
        mysql.setPassword(pm.getProperty(PreferencesManagerConstants.PASS));

        return mysql;
    }

    public static DataSource createDataSource(String host, int port, String dbName, String user, String password) {
        MysqlDataSource mysql = new MysqlDataSource();
        mysql.setServerName(host);
        mysql.setPortNumber(port);
        mysql.setDatabaseName(dbName);
        mysql.setUser(user);
        mysql.setPassword(password);

        return mysql;
    }
}