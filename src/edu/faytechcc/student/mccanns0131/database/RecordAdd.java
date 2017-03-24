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
    private String sql;
    
    /**
        Constructs a new RecordAdd
    */
    
    public RecordAdd()
    {
        sql = "";
    }
    
    /**
        Adds a record of a location to the database
    
        @param location The location to add
        @throws SQLException Error adding record to the database
    */
    
    private void addLocation(Location location) throws SQLException
    {
        sql = "INSERT INTO Locations " +
              "VALUES ('" + location.getName() + "', " +
                            location.getCapacity() + ")";
        
        ReserveDB.getInstance().addRecord(this);
    }
    
    /**
        Adds a record of a reservable to the database
    
        @param reservable The reservable to add
        @throws SQLException Error adding record to the database
    */
    
    public void addReservable(Reservable reservable) throws SQLException
    {
        // Check if a record of a location should be added
        ResultSetParser parser = new ResultSetParser();
        ReservableQuery q = new ReservableQuery();
        parser.setResultSet(q.queryReservableName(reservable.getName()));
        
        if (parser.isEmpty())
            addLocation(reservable.getLocation());
        
        // Check if a record of a timeframe should be added
        parser.setResultSet(q.queryReservableTimeframe(
                reservable.getTimeframe()));
        
        if (parser.isEmpty())
            addTimeframe(reservable.getTimeframe());
        
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


        ReserveDB.getInstance().addRecord(this);
    }
    
    /**
        Adds a record of a reservation to the database
    
        @param reservation The reservation to add
        @throws SQLException Error adding record to the database
    */
    
    public void addReservation(Reservation reservation) throws SQLException
    {
        // Check if a record of a reserver should be added
        ResultSetParser parser = new ResultSetParser();
        ReservationQuery q = new ReservationQuery();
        parser.setResultSet(q.queryReservationReserver(
                reservation.getReserver()));
        
        if (parser.isEmpty())
            addReserver(reservation.getReserver());
        
        String reserverID = "(SELECT Reservers.ReserverID " +
                            "FROM Reservers " +
                            "WHERE Reservers,FirstName = '" +
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

        ReserveDB.getInstance().addRecord(this);
    }
    
    /**
       Adds a record of a reserver to the database
       
       @param reserver The reserver to add
       @throws SQLException Error adding record to the database
     */
    
    private void addReserver(Reserver reserver) throws SQLException
    {
        String fields = "(FirstName, LastName, Email, Phone)";
        
        sql = "INSERT INTO Reservers " + fields + " "     +
              "VALUES ('" + reserver.getFirstName()     + "', '" +
                            reserver.getLastName()      + "', '" +
                            reserver.getEmailAddress()  + "', '" +
                            reserver.getPhoneNumber()   + "')";
            
        ReserveDB.getInstance().addRecord(this);
    }
    
    /**
        Adds a record of a timeframe to the database
    
        @param timeframe The timeframe to add
        @throws SQLException Error adding record to database
    */
    
    private void addTimeframe(Timeframe timeframe) throws SQLException
    {
        String fields = "(StartDate, StartTime, EndDate, EndTime)";
        
        sql = "INSERT INTO Timeframes " + fields + " " +
              "VALUES ('" + timeframe.getStartDate() + "', '" +
                            timeframe.getStartTime() + "', '" +
                            timeframe.getEndDate()   + "', '" +
                            timeframe.getEndTime() + "')";
        
        ReserveDB.getInstance().addRecord(this);
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
