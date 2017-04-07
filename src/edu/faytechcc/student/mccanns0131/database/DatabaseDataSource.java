/**
    Data source backed by the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import java.sql.Connection;
import java.sql.SQLException;
import org.mariadb.jdbc.MariaDbDataSource;

public class DatabaseDataSource
{
    private static final String DB_NAME = "ReserveDB";
    
    private MariaDbDataSource source;
    
    /**
        Constructs a new DatabaseDataSource
    */
    
    public DatabaseDataSource()
    {
        source = new MariaDbDataSource();
        
        DatabaseSettings settings = SystemPreferences.getDBSettings();
        source.setServerName(settings.getDBHost());
        source.setPort(settings.getDBPort());
        source.setUserName(settings.getDBUser());
        source.setPassword(settings.getDBPass());
    }
    
    /**
        Returns a connection to the DBMS
    
        @throws SQLException DBMS access error
        @return Connection to the DBMS
    */
    
    public Connection getDBMSConnection() throws SQLException
    {
        source.setDatabaseName(null);
        return source.getConnection();
    }
    
    /**
        Returns a connection to the database
    
        @throws SQLException Database access error
        @return Connection to the database
    */
    
    public DatabaseConnection getDBConnection() throws SQLException
    {
        source.setDatabaseName(DB_NAME);
        return new DatabaseConnection(source.getConnection());
    }
}