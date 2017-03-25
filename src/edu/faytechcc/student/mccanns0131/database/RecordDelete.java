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
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordDelete
{
    // Fields
    private String sql;
    
    /**
        Constructs a new RecordDelete
    */
    
    public RecordDelete()
    {
        sql = "";
    }
    
    /**
        Removes a record of a location from the database
    
        @param loc The location to remove
        @throws SQLException Error removing record from database
    */
    
    private void deleteLocation(Location loc) throws SQLException
    {
        sql = "DELETE FROM Locations " +
              "WHERE Locations.LocationName = '" + loc.getName() + "'";
        
        ReserveDB.getInstance().deleteRecord(this);
    }
    
    /**
        Removes a record of a reservable from the database
    
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
        
        ReserveDB.getInstance().deleteRecord(this);
        
        // Check if we should delete a record of a location with the same name
        // as the reservable
        ReservableQuery q = new ReservableQuery();
        ResultSet rs = q.queryReservableName(reservable.getName());
        ResultSetParser parser = new ResultSetParser(rs);
        
        if (parser.isEmpty())
            deleteLocation(reservable.getLocation());
        
        // Check if we should delete a record of a timeframe with the same
        // timeframe as the reservable
        parser.setResultSet(
                q.queryReservableTimeframe(reservable.getTimeframe()));
        if (parser.isEmpty())
            deleteTimeframe(reservable.getTimeframe());
    }
    
    /**
        Removes a record of a reservation from the database
    
        @param reservation Reservation to remove
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
        
        ReserveDB.getInstance().deleteRecord(this);
        
        // Check if a record of a reserver should also be deleted
        ReservationQuery q = new ReservationQuery();
        ResultSetParser parser = new ResultSetParser();
        parser.setResultSet(q.queryReservationReserver(
                reservation.getReserver()));
        
        if (parser.isEmpty())
            deleteReserver(reservation.getReserver());
    }
    
    /**
        Removes a record of a reserver from the database
    
        @param reserver Reserver to remove from the database
        @throws SQLException Error removing record from database
    */
    
    private void deleteReserver(Reserver reserver) throws SQLException
    {
        String reserverID = "(SELECT Reservers.ReserverID " +
                            "FROM Reservers " +
                            "WHERE Reservers.FirstName = '" +
                              reserver.getFirstName() + "' " +
                            "AND Reservers.LastName = '" +
                              reserver.getLastName() + "' " +
                            "AND Reservers.Email = '" +
                              reserver.getEmailAddress() + "' " +
                            "AND Reservers.Phone = '" +
                              reserver.getPhoneNumber() + "')";
        
        sql = "DELETE FROM Reservers " +
              "WHERE Reservers.ReserverID = " + reserverID;
        
        ReserveDB.getInstance().deleteRecord(this);
    }
    
    /**
        DeleteTimeframe - Remove a record of a timeframe from the database
    
        @param timeframe The timeframe to remove from the database
        @throws SQLException Error removing record from database
    */
    
    private void deleteTimeframe(Timeframe timeframe) throws SQLException
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
        
        ReserveDB.getInstance().deleteRecord(this);
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