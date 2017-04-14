/**
    DAO (Data Access Object) for accessing reservable data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Reservable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservableSQLDAO
{
    private Connection connection;
    private DBDataSource source;
    
    /**
        Constructs a new ReservableSQLDAO
    */
    
    public ReservableSQLDAO()
    {
        source = DBDataSource.getInstance();
        connection = null;
    }
    
    /**
        Constructs a new ReservableSQLDAO initialized with the given connection
    
        @param conn Connection
    */
    
    public ReservableSQLDAO(Connection conn)
    {
        source = null;
        connection = conn;
    }
    
    /**
        Adds a record of a reservable to the database
    
        @param reservable Reservable to add
        @throws SQLException Error adding record
    */
    
    public void addReservable(Reservable reservable) throws SQLException
    {        
        if (connection == null)
            connection = source.getConnection();
                
        // Check if a record of a location should be added
        String sql = "SELECT Locations.LocationID " +
                      "FROM Locations " +
                      "WHERE Locations.LocationID = " +
                       reservable.getLocationID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeQuery(sql);
            
            if (!stmt.getResultSet().isBeforeFirst())
            {
                LocationSQLDAO locationDAO = new LocationSQLDAO(connection);
                locationDAO.addLocation(reservable.getLocation());
            }
            
            // Check if a record of a timeframe should be added
            sql =   "SELECT Timeframes.TimeframeID " +
                    "FROM Timeframes " +
                    "WHERE Timeframes.TimeframeID = " +
                    reservable.getTimeframeID();
            
            stmt.executeQuery(sql);
            
            if (!stmt.getResultSet().isBeforeFirst())
            {
                TimeframeSQLDAO timeframeDAO = new TimeframeSQLDAO(connection);
                timeframeDAO.addTimeframe(reservable.getTimeframe());
            }
            
            sql = "INSERT INTO Reservables " +
                  "VALUES ('" + reservable.getLocationID() + "', " +
                                reservable.getTimeframeID() + ", " +
                                reservable.getCost() + ")";
            
            stmt.executeUpdate(sql);
        }
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
        Removes a record of a reservable from the database
    
        @param reservable Reservable to remove
        @throws SQLException Error removing record
    */
    
    public void removeReservable(Reservable reservable) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "DELETE FROM Reservables " +
                     "WHERE Reservables.LocationID = " +
                        reservable.getLocationID() + " " +
                     "AND Reservables.TimeframeID = " +
                        reservable.getTimeframeID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            
            // Check if a record of a location should also be removed
            sql = "SELECT Reservables.LocationID " +
                   "FROM Reservables " +
                   "WHERE Reservables.LocationID = " +
                    reservable.getLocationID();
            
            stmt.executeQuery(sql);
            
            if (!stmt.getResultSet().isBeforeFirst())
            {
                LocationSQLDAO locationDAO = new LocationSQLDAO(connection);
                locationDAO.removeLocation(reservable.getLocation());
            }
            
            // Check if a record of a timeframe should also be removed
            sql = "SELECT Reservables.TimeframeID " +
                  "FROM Reservables " +
                  "WHERE Reservables.TimeframeID = " +
                  reservable.getTimeframeID();
            
            stmt.executeQuery(sql);
            
            if (!stmt.getResultSet().isBeforeFirst())
            {
                TimeframeSQLDAO timeframeDAO = new TimeframeSQLDAO(connection);
                timeframeDAO.removeTimeframe(reservable.getTimeframe());
            }
        }
    }
    
    /**
        Updates a record of a reservable on the database
    
        @param reservable Updated reservable
        @throws SQLException Error updating record
    */
    
    public void updateReservable(Reservable reservable) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "UPDATE Reservables " +
                     "SET Reservables.Cost = " +
                        reservable.getCost() + " " +
                     "WHERE Reservables.LocationID = " +
                        reservable.getLocationID() + " " +
                     "AND Reservables.TimeframeID = " +
                        reservable.getTimeframeID();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
        
        // Update location
        LocationSQLDAO locDAO = new LocationSQLDAO(connection);
        locDAO.updateLocation(reservable.getLocation());
    }
}