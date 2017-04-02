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
        Removes a record of a location from a database
    
        @param loc The location to remove
        @throws SQLException Error removing record from database
    */
    
    public void deleteLocation(Location loc) throws SQLException
    {
        sql = "DELETE FROM Locations " +
              "WHERE Locations.LocationName = '" + loc.getName() + "'";
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a reservable from a database
    
        @param reservable The reservable to remove
        @throws SQLException Error removing record from database
    */
    
    public void deleteReservable(Reservable reservable) throws SQLException
    {
        String timeframeID = "(SELECT Timeframes.TimeframeID " +
                             "FROM Timeframes " +
                             "WHERE Timeframes.StartDate = '" +
                                reservable.getStartDate() + "' " +
                             "AND Timeframes.StartTime = '" +
                                reservable.getStartTime() + "' " +
                             "AND Timeframes.EndDate = '" +
                                reservable.getEndDate() + "' " +
                             "AND Timeframes.EndTime = '" +
                                reservable.getEndTime() + "')";
        
        sql = "DELETE FROM Reservables " +
              "WHERE Reservables.LocationName = '" + 
                reservable.getName() + "' " +
              "AND Reservables.TimeframeID = " +
                timeframeID;
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a reservation from a database
    
        @param reservation The reservation to remove from a database
        @throws SQLException Error removing record from database
    */
    
    public void deleteReservation(Reservation reservation) throws SQLException
    {
        String timeframeID = "(SELECT Timeframes.TimeframeID " +
                             "FROM Timeframes " +
                             "WHERE Timeframes.StartDate = '" +
                                reservation.getStartDate() + "' " +
                             "AND Timeframes.StartTime = '" +
                                reservation.getStartTime() + "' " +
                             "AND Timeframes.EndDate = '" +
                                reservation.getEndDate() + "' " +
                             "AND Timeframes.EndTime = '" +
                                reservation.getEndTime() + "')";
        
        sql = "DELETE FROM Reservations " +
              "WHERE Reservations.LocationName = '" +
                reservation.getLocationName() + "' " +
              "AND Reservations.TimeframeID = " +
                timeframeID;
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a reserver from a database
    
        @param reserver Reserver to remove from a database
        @throws SQLException Error removing record from database
    */
    
    public void deleteReserver(Reserver reserver) throws SQLException
    {
        sql = "DELETE FROM Reservers " +
              "WHERE Reservers.FirstName = '" + reserver.getFirstName() + "' " +
              "AND Reservers.LastName = '" + reserver.getLastName() + "' " +
              "AND Reservers.Email = '" + reserver.getEmailAddress() + "' " +
              "AND Reservers.Phone = '" + reserver.getPhoneNumber() + "'";
        
        connection.deleteRecord(this);
    }
    
    /**
        Removes a record of a timeframe from a database
    
        @param timeframe The timeframe to remove from a database
        @throws SQLException Error removing record from database
    */
    
    public void deleteTimeframe(Timeframe timeframe) throws SQLException
    {
        sql = "DELETE FROM Timeframes " +
              "WHERE Timeframes.StartDate = '" +
                timeframe.getStartDate() + "' " +
              "AND Timeframes.StartTime = '" +
                timeframe.getStartTime() + "' " +
              "AND Timeframes.EndDate = '" +
                timeframe.getEndDate() + "' " +
              "AND Timeframes.EndTime = '" +
                timeframe.getEndTime() + "'";
        
        connection.deleteRecord(this);
    }
    
    /**
        ToString - Return a string representation of the object
    
        @return A string representation of the object
    */
    
    @Override
    public String toString()
    {
        return sql;
    }
}