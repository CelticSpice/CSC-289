/**
    A deletion of a record from the database
    CSC-289- Group 4
    @author Timothy Burns, Shane McCann
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;

public class RecordDelete
{
    // Fields
    private DatabaseConnection connection;
    private String sql;
    
    /**
        Constructs a new RecordDelete initialized with the given connection
        to a database
    
        @param conn Connection to a database
    */
    
    public RecordDelete(DatabaseConnection conn)
    {
        connection = conn;
        sql = "";
    }
    
    /**
        Removes a record of a location from the database
    
        @param loc The location to remove
        @throws SQLException Error removing record from database
    */
    
    public void deleteLocation(Location loc) throws SQLException
    {
        sql = "DELETE FROM Locations " +
              "WHERE Locations.LocationID = " + loc.getID();
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a reservable from the database
    
        @param reservable The reservable to remove
        @throws SQLException Error removing record from database
    */
    
    public void deleteReservable(Reservable reservable) throws SQLException
    {
        sql = "DELETE FROM Reservables " +
              "WHERE Reservables.LocationID = " +
                reservable.getLocationID() + " " +
              "AND Reservables.TimeframeID = " +
                reservable.getTimeframeID();
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a reservation from the database
    
        @param reservation The reservation to remove from a database
        @throws SQLException Error removing record from database
    */
    
    public void deleteReservation(Reservation reservation) throws SQLException
    {
        sql = "DELETE FROM Reservations " +
              "WHERE Reservations.LocationID = " +
                reservation.getLocationID() + " " +
              "AND Reservations.TimeframeID = " +
                reservation.getTimeframeID();
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a reserver from the database
    
        @param reserver Reserver to remove from a database
        @throws SQLException Error removing record from database
    */
    
    public void deleteReserver(Reserver reserver) throws SQLException
    {
        sql = "DELETE FROM Reservers " +
              "WHERE Reservers.ReserverID = " + reserver.getID();
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a timeframe from the database
    
        @param timeframe The timeframe to remove from the database
        @throws SQLException Error removing record from database
    */
    
    public void deleteTimeframe(Timeframe timeframe) throws SQLException
    {
        sql = "DELETE FROM Timeframes " +
              "WHERE Timeframes.TimeframeID = " + timeframe.getID();
        
        connection.deleteRecord(this);
    }
    
    /**
        Returns a string representation of the object
    
        @return A string representation of the object
    */
    
    @Override
    public String toString()
    {
        return sql;
    }
}