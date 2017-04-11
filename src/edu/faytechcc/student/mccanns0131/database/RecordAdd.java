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
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordAdd
{
    // Fields
    private DBConnection connection;
    private String sql;
    
    /**
        Constructs a new RecordAdd initialized with the given connection to
        the database
    
        @param conn Connection to database
    */
    
    public RecordAdd(DBConnection conn)
    {
        connection = conn;
        sql = "";
    }
    
    /**
        Adds a record of a location to the database, returning the generated
        ID
    
        @param location The location to add
        @throws SQLException Error adding record to database
        @return The generated ID
    */
    
    public ResultSet addLocation(Location location) throws SQLException
    {
        String fields = "(LocationName, Capacity)";
        
        sql = "INSERT INTO Locations " + fields + " " +
              "VALUES ('" + location.getName() + "', " +
                            location.getCapacity() + ")";
        
        return connection.addRecord(this);
    }
    
    /**
        Adds a record of a reservable to the database
    
        @param reservable The reservable to add
        @throws SQLException Error adding record to database
    */
    
    public void addReservable(Reservable reservable) throws SQLException
    {        
        sql = "INSERT INTO Reservables " +
              "VALUES ('" + reservable.getLocationID() + "', " +
                            reservable.getTimeframeID() + ", " +
                            reservable.getCost() + ")";
        
        connection.addRecord(this);
    }
    
    /**
        Adds a record of a reservation to the database
    
        @param reservation The reservation to add
        @throws SQLException Error adding record to database
    */
    
    public void addReservation(Reservation reservation) throws SQLException
    {
        sql = "INSERT INTO Reservations " +
              "VALUES ('" + reservation.getLocationID() + "', " +
                            reservation.getTimeframeID() + ", " +
                            reservation.getReserverID() + ", '" +
                            reservation.getEventType() + "', " +
                            reservation.getNumberAttending() + ", " +
                            false + ")";
        
        connection.addRecord(this);
    }
    
    /**
        Adds a record of a reserver to the database, returning the generated
        ID
       
        @param reserver The reserver to add
        @throws SQLException Error adding record to database
        @return The generated ID
     */
    
    public ResultSet addReserver(Reserver reserver) throws SQLException
    {
        String fields = "(FirstName, LastName, Email, Phone)";
        
        sql = "INSERT INTO Reservers " + fields + " "     +
              "VALUES ('" + reserver.getFirstName()     + "', '" +
                            reserver.getLastName()      + "', '" +
                            reserver.getEmailAddress()  + "', '" +
                            reserver.getPhoneNumber()   + "')";
        
        return connection.addRecord(this);
    }
    
    /**
        Adds a record of a timeframe to the database, returning the generated
        ID
    
        @param timeframe The timeframe to add
        @throws SQLException Error adding record to database
        @return The generated ID
    */
    
    public ResultSet addTimeframe(Timeframe timeframe) throws SQLException
    {
        String fields = "(StartDate, StartTime, EndDate, EndTime)";
        
        sql = "INSERT INTO Timeframes " + fields + " " +
              "VALUES ('" + timeframe.getStartDate() + "', '" +
                            timeframe.getStartTime() + "', '" +
                            timeframe.getEndDate()   + "', '" +
                            timeframe.getEndTime() + "')";
        
        return connection.addRecord(this);
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