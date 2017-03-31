/**
    DAO (Data Access Object) for accessing location data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import java.sql.SQLException;

public class LocationSQLDAO
{
    private DatabaseConnection conn;
    
    /**
        Constructs a new LocationSQLDAO
    
        @throws SQLException Error connection to database
    */
    
    public LocationSQLDAO() throws SQLException
    {
        SystemPreferences prefs = SystemPreferences.getInstance();
        String user = prefs.getDBUser();
        String pass = prefs.getDBPass();
        conn = DatabaseConnection.getConnection(user, pass);
    }
}