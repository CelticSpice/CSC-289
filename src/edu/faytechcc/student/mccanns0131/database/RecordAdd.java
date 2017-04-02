/**
    An addition of a record to the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;

public class RecordAdd
{
    // Fields
    private DatabaseConnection connection;
    private String sql;
    
    /**
        Constructs a new RecordAdd initialized with the given connection to
        a database
    
        @param conn Connection to database
    */
    
    public RecordAdd(DatabaseConnection conn)
    {
        connection = conn;
        sql = "";
    }
    
    /**
        Adds a record of a location to a database
    
        @param location The location to add
        @throws SQLException Error adding record to database
    */
    
    public void addLocation(Location location) throws SQLException
    {
        sql = "INSERT INTO Locations " +
              "VALUES ('" + location.getName() + "', " +
                            location.getCapacity() + ")";
        
        connection.addRecord(this);
    }
    
    /**
        Adds a record of a reservable to a database
    
        @param reservable The reservable to add
        @throws SQLException Error adding record to database
    */
    
    public void addReservable(Reservable reservable) throws SQLException
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

        sql = "INSERT INTO Reservables " +
              "VALUES ('" + reservable.getName() + "', " +
                            timeframeID + ", " +
                            reservable.getCost() + ")";
        
        connection.addRecord(this);
    }
    
    /**
        Adds a record of a reservation to a database
    
        @param reservation The reservation to add
        @throws SQLException Error adding record to database
    */
    
    public void addReservation(Reservation reservation) throws SQLException
    {
        String reserverID = "(SELECT Reservers.ReserverID " +
                            "FROM Reservers " +
                            "WHERE Reservers.FirstName = '" +
                                reservation.getReserverFirstName() + "' " +
                            "AND Reservers.LastName = '" +
                                reservation.getReserverLastName() + "' " +
                            "AND Reservers.Email = '" +
                                reservation.getReserverEmail() + "' " +
                            "AND Reservers.Phone = '" +
                                reservation.getReserverPhone() + "')";
            
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

        sql = "INSERT INTO Reservations " +
              "VALUES ('" + reservation.getLocationName() + "', " +
                            timeframeID + ", " +
                            reserverID + ", '" +
                            reservation.getEventType() + "', " +
                            reservation.getNumberAttending() + ", " +
                            false + ")";
        
        connection.addRecord(this);
    }
    
    /**
        Adds a record of a reserver to a database
       
        @param reserver The reserver to add
        @throws SQLException Error adding record to database
     */
    
    public void addReserver(Reserver reserver) throws SQLException
    {
        String fields = "(FirstName, LastName, Email, Phone)";
        
        sql = "INSERT INTO Reservers " + fields + " "     +
              "VALUES ('" + reserver.getFirstName()     + "', '" +
                            reserver.getLastName()      + "', '" +
                            reserver.getEmailAddress()  + "', '" +
                            reserver.getPhoneNumber()   + "')";
        
        connection.addRecord(this);
    }
    
    /**
        Adds a record of a timeframe to a database
    
        @param timeframe The timeframe to add
        @throws SQLException Error adding record to database
    */
    
    public void addTimeframe(Timeframe timeframe) throws SQLException
    {
        String fields = "(StartDate, StartTime, EndDate, EndTime)";
        
        sql = "INSERT INTO Timeframes " + fields + " " +
              "VALUES ('" + timeframe.getStartDate() + "', '" +
                            timeframe.getStartTime() + "', '" +
                            timeframe.getEndDate()   + "', '" +
                            timeframe.getEndTime() + "')";
        
        connection.addRecord(this);
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
