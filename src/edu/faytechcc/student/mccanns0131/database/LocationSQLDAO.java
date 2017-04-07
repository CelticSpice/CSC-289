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
    private DatabaseDataSource source;
    
    /**
        Constructs a new LocationSQLDAO
    */
    
    public LocationSQLDAO()
    {
        source = new DatabaseDataSource();
    }
    
    /**
        Adds a record of a location to the database
    
        @param loc Location to add
        @throws SQLException Error adding record
    */
    
    public void addLocation(Location loc) throws SQLException
    {
        ResultSetParser parser = new ResultSetParser();
        DatabaseConnection connection = source.getDBConnection();
        parser.setResultSet((new RecordAdd(connection).addLocation(loc)));
        connection.close();
        int id = parser.parseID();
        loc.setID(id);
    }
    
     /**
        Retrieves all locations from the database
    
        @throws SQLException Error retrieving locations
        @return All locations in the database
    */
    
    public List<Location> getAll() throws SQLException
    {
        LocationQuery query = new LocationQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryAll();
        DatabaseConnection connection = source.getDBConnection();
        parser.setResultSet(connection.runQuery(query));
        connection.close();
        
        return parser.parseLocations();
    }
    
    /**
        Removes a record of a location from the database
    
        @param loc Location to remove
        @throws SQLException Error removing record
    */
    
    public void removeLocation(Location loc) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        new RecordDelete(connection).deleteLocation(loc);
        connection.close();
    }
    
    /**
        Updates a record of a location in the database
    
        @param loc Updated location
        @throws SQLException Error updating record
    */
    
    public void updateLocation(Location loc) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        new RecordUpdate(connection).updateLocation(loc);
        connection.close();
    }
}