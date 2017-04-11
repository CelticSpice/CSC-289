/**
    The database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import java.sql.SQLException;
import org.mariadb.jdbc.MariaDbDataSource;

public class DBDataSource
{
    private static final String DB_NAME = "ReserveDB";
    
    private static DBDataSource dbDataSource;
    
    private MariaDbDataSource source;
    
    /**
        Constructs a new DBDataSource
    */
    
    private DBDataSource()
    {
        source = new MariaDbDataSource();
        
        DatabaseSettings settings = SystemPreferences.getDBSettings();
        source.setDatabaseName(DB_NAME);
        source.setServerName(settings.getDBHost());
        source.setPort(settings.getDBPort());
        source.setUserName(settings.getDBUser());
        source.setPassword(settings.getDBPass());
    }
    
    /**
        Returns a connection to the database
    
        @throws SQLException Database access error
        @return Connection to the database
    */
    
    public DBConnection getConnection() throws SQLException
    {
        return new DBConnection(source.getConnection());
    }
    
    /**
        Returns an instance of the data source
    
        @return Instance of the data source
    */
    
    public static DBDataSource getInstance()
    {
        if (dbDataSource == null)
            dbDataSource = new DBDataSource();
        return dbDataSource;
    }
    
    /**
        Updates the database properties
    
        @param settings Database settings
    */
    
    public void updateProperties(DatabaseSettings settings)
    {
        source.setServerName(settings.getDBHost());
        source.setPort(settings.getDBPort());
        source.setUserName(settings.getDBUser());
        source.setPassword(settings.getDBPass());
    }
}