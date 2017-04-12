/**
    DAO (Data Access Object) for accessing location data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LocationSQLDAO
{
    private Connection connection;
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
    
        @param conn Connection
    */
    
    public LocationSQLDAO(Connection conn)
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
        
        String fields = "(LocationName, Capacity)";
        
        String sql = "INSERT INTO Locations " + fields + " " +
                     "VALUES ('" + loc.getName() + "', " +
                                   loc.getCapacity() + ")";
        
        ResultSetParser parser;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            parser = new ResultSetParser(stmt.getGeneratedKeys());
        }
        
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
    
    public List<ReservableLocation> getAll() throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "SELECT Locations.LocationID, Locations.LocationName, " +
                     "Locations.Capacity, Timeframes.TimeframeID, " +
                     "Timeframes.StartDate, Timeframes.StartTime, " +
                     "Timeframes.EndDate, Timeframes.EndTime, " +
                     "Reservables.Cost, " +
                     "Reservations.TimeframeID AS 'ReservedTimeframeID' " +
                     "FROM Locations " +
                     "INNER JOIN Reservables " +
                     "ON Locations.LocationID = Reservables.LocationID " +
                     "INNER JOIN Timeframes " +
                     "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
                     "LEFT JOIN Reservations " +
                     "ON Reservables.LocationID = Reservations.LocationID " +
                     "AND Reservables.TimeframeID = Reservations.TimeframeID " +
                     "ORDER BY Locations.LocationName, Timeframes.StartDate, " +
                     "Timeframes.StartTime, Timeframes.EndDate, " +
                     "Timeframes.EndTime, Reservables.Cost";
        
        ResultSetParser parser;
        try (Statement stmt = connection.createStatement()) {
            parser = new ResultSetParser(stmt.executeQuery(sql));
        }
        
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
        
        String sql = "DELETE FROM Locations " +
                     "WHERE Locations.LocationID = " + loc.getID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
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
        
        String sql = "UPDATE Locations " +
                     "SET Locations.LocationName = '" +
                        loc.getName() + "', " +
                     "Locations.Capacity = " +
                        loc.getCapacity() + " " +
                     "WHERE Locations.LocationID = " +
                        loc.getID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}