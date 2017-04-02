/**
    DAO (Data Access Object) for accessing location data on a database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.Location;
import java.sql.SQLException;
import java.util.List;

public class LocationSQLDAO
{
    private DatabaseConnection connection;
    
    /**
        Constructs a new LocationSQLDAO & attempts to establish a connection to
        a database using the given database settings
    
        @param settings Database settings to connect to database with
        @throws SQLException Error connecting to database
    */
    
    public LocationSQLDAO(DatabaseSettings settings) throws SQLException
    {
        connection = DatabaseConnection.getConnection(settings);
    }
    
    /**
        Constructs a new LocationSQLDAO initialized with the given connection
        to a database
    
        @param conn Connection to database
    */
    
    public LocationSQLDAO(DatabaseConnection conn)
    {
        connection = conn;
    }
    
    /**
        Adds a record of a location to a database
    
        @param loc Location to add
        @throws SQLException Error adding record
    */
    
    public void addLocation(Location loc) throws SQLException
    {
        new RecordAdd(connection).addLocation(loc);
    }
    
    /**
        Closes the DAO's connection to the database
    
        @throws SQLException Error closing connection
    */
    
    public void close() throws SQLException
    {
        connection.close();
    }
    
     /**
        Retrieves all locations from a database
    
        @throws SQLException Error retrieving locations
        @return All locations in a database
    */
    
    public List<Location> getAll() throws SQLException
    {
        LocationQuery query = new LocationQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryAll();
        parser.setResultSet(connection.runQuery(query));
        
        return parser.parseLocations();
    }
    
    /**
        Removes a record of a location from a database
    
        @param loc Location to remove
        @throws SQLException Error removing record
    */
    
    public void removeLocation(Location loc) throws SQLException
    {
        new RecordDelete(connection).deleteLocation(loc);
    }
}