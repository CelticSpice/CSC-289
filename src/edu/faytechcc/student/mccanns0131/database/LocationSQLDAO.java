/**
    DAO (Data Access Object) for accessing location data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import java.sql.SQLException;
import java.util.List;

public class LocationSQLDAO
{
    private DBConnection connection;
    private DBDataSource source;
    
    /**
        Constructs a new LocationSQLDAO
    */
    
    public LocationSQLDAO()
    {
        source = DBDataSource.getInstance();
        connection = null;
    }
    
    /**
        Constructs a new LocationSQLDAO initialized with the given connection
    
        @param conn DBConnection
    */
    
    public LocationSQLDAO(DBConnection conn)
    {
        source = null;
        connection = conn;
    }
    
    /**
        Adds a record of a location to the database
    
        @param loc Location to add
        @throws SQLException Error adding record
    */
    
    public void addLocation(Location loc) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        ResultSetParser parser = new ResultSetParser();
        parser.setResultSet((new RecordAdd(connection).addLocation(loc)));
        int id = parser.parseID();
        loc.setID(id);
    }
    
    /**
        Closes this SQLDAO resource
    
        @throws SQLException Error closing the SQLDAO connection
    */
    
    public void close() throws SQLException
    {
        connection.close();
    }
    
     /**
        Retrieves all locations from the database
    
        @throws SQLException Error retrieving locations
        @return All locations in the database
    */
    
    public List<Location> getAll() throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        LocationQuery query = new LocationQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryAll();
        parser.setResultSet(connection.runQuery(query));
        
        return parser.parseLocations();
    }
    
    /**
        Removes a record of a location from the database
    
        @param loc Location to remove
        @throws SQLException Error removing record
    */
    
    public void removeLocation(Location loc) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        new RecordDelete(connection).deleteLocation(loc);
    }
    
    /**
        Updates a record of a location in the database
    
        @param loc Updated location
        @throws SQLException Error updating record
    */
    
    public void updateLocation(Location loc) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        new RecordUpdate(connection).updateLocation(loc);
    }
}