/**
    DAO (Data Access Object) for accessing reservation data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReservationSQLDAO
{
    private Connection connection;
    private DBDataSource source;
    
    /**
        Constructs a new ReservationSQLDAO
    */
    
    public ReservationSQLDAO()
    {
        source = DBDataSource.getInstance();
        connection = null;
    }
    
    /**
        Constructs a new ReservationSQLDAO initialized with the given connection
    
        @param conn Connection
    */
    
    public ReservationSQLDAO(Connection conn)
    {
        source = null;
        connection = conn;
    }
    
    /**
        Adds a record of a reservation to the database
    
        @param reservation Reservation to add
        @throws SQLException Error adding record
    */
    
    public void addReservation(Reservation reservation) throws SQLException
    {        
        if (connection == null)
            connection = source.getConnection();
        
        // Check if a record of a reserver should be added
        String sql = "SELECT Reservers.ReserverID " +
                     "FROM Reservers " +
                     "WHERE Reservers.ReserverID = " +
                     reservation.getReserverID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeQuery(sql);
            
            if (!stmt.getResultSet().isBeforeFirst())
            {
                ReserverSQLDAO reserverDAO = new ReserverSQLDAO(connection);
                reserverDAO.addReserver(reservation.getReserver());
            }
            
            sql = "INSERT INTO Reservations " +
                  "VALUES ('" + reservation.getLocationID() + "', " +
                                reservation.getTimeframeID() + ", " +
                                reservation.getReserverID() + ", '" +
                                reservation.getEventType() + "', " +
                                reservation.getNumberAttending() + ", " +
                                false + ")";
            
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
        Retrieves reservations made at the specified location from the database
    
        @param loc The location to retrieve reservations made at
        @throws SQLException Error retrieving reservations
        @return List of reservations made at the location
    */
    
    public List<Reservation> getByLocation(Location loc) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "SELECT Reservers.ReserverID, Reservers.FirstName, " +
                     "Reservers.LastName, Reservers.Email, Reservers.Phone, " +
                     "Timeframes.TimeframeID, Timeframes.StartDate, " +
                     "Timeframes.StartTime, Timeframes.EndDate, " +
                     "Timeframes.EndTime, Reservables.Cost, " +
                     "Reservations.EventType, Reservations.NumberAttending, " +
                     "Reservations.Reviewed " +
                     "FROM Reservers " +
                     "INNER JOIN Reservations " +
                     "ON Reservers.ReserverID = Reservations.ReserverID " +
                     "INNER JOIN Reservables " +
                     "ON Reservations.LocationID = Reservables.LocationID " +
                     "AND Reservations.TimeframeID = Reservables.TimeframeID " +
                     "INNER JOIN Timeframes " +
                     "ON Reservables.TimeframeID = Timeframes.TimeframeID " +
                     "WHERE Reservations.LocationID = " + loc.getID(); 
        
        ResultSetParser parser;
        try (Statement stmt = connection.createStatement()) {
            parser = new ResultSetParser(stmt.executeQuery(sql));
        }
        
        return parser.parseReservations(loc);
    }
    
    /**
        Removes a record of a reservation from the database
    
        @param reservation Reservation to remove
        @throws SQLException Error removing record
    */
    
    public void removeReservation(Reservation reservation) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "DELETE FROM Reservations " +
                     "WHERE Reservations.LocationID = " +
                        reservation.getLocationID() + " " +
                     "AND Reservations.TimeframeID = " +
                        reservation.getTimeframeID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            
            // Check if a record of a reserver should also be removed
            sql = "SELECT Reservations.ReserverID " +
                   "FROM Reservations " +
                   "WHERE Reservations.ReserverID = " +
                   reservation.getReserverID() + ")";
            
            stmt.executeQuery(sql);
            
            if (!stmt.getResultSet().isBeforeFirst())
            {
                ReserverSQLDAO reserverDAO = new ReserverSQLDAO(connection);
                reserverDAO.removeReserver(reservation.getReserver());
            }
        }
    }
    
    /**
        Updates a record of a reservation in the database
    
        @param reservation Reservation to update
        @throws SQLException Error removing record
    */
    
    public void updateReservation(Reservation reservation) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "UPDATE Reservations " +
                     "SET Reservations.EventType = '" +
                        reservation.getEventType() + "', " +
                     "Reservations.NumberAttending = " +
                        reservation.getNumberAttending() + ", " +
                     "Reservations.Reviewed = " +
                        reservation.isReviewed() + " " +
                     "WHERE Reservations.LocationID = " +
                        reservation.getLocationID() + " " +
                     "AND Reservations.TimeframeID = " +
                        reservation.getTimeframeID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}