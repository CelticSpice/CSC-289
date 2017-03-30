/**
    A query of the database for reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationQuery extends Query
{
    /**
        Constructs a new ReservationQuery
    */
    
    public ReservationQuery()
    {
        super();
    }
    
    /**
        Prepares the query to query reservations made for the ID of the given
        reserver
    
        @param reserver Reserver to query ID of
    */
    
    public void queryReservationReserverID(Reserver reserver)
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
        
        sql = "SELECT DISTINCT Reservations.ReserverID " +
              "FROM Reservations " +
              "WHERE Reservations.ReserverID = " + reserverID;
    }
    
    /**
        Prepares the query to return data on reservations made at the named
        location
    
        @param locName The location name
    */
    
    public void queryReservations(String locName)
    {
        sql = "SELECT Reservers.FirstName, Reservers.LastName, " +
              "Reservers.Email, Reservers.Phone, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "Reservations.EventType, Reservations.NumberAttending, " +
              "Reservations.Reviewed " +
              "FROM Reservers " +
              "INNER JOIN Reservations " +
              "ON Reservers.ReserverID = Reservations.ReserverID " +
              "INNER JOIN Timeframes " +
              "ON Reservations.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Reservations.LocationName = '" + locName + "'";        
    }
    
    /**
     * QueryReservation - Returns a reservation that matches the given location
     * name and timeframe
     * 
     * @param loc The location name
     * @param time The timeframe of the reservation
     * @return A ResultSet containing the matching record
     * @throws SQLException Error querying the database
     */
    public ResultSet queryReservation(String loc, Timeframe time)
            throws SQLException
    {
        sql = "SELECT Reservers.FirstName, Reservers.LastName, " +
              "Reservers.Email, Reservers.Phone, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "Reservations.EventType, Reservations.NumberAttending, " +
              "Reservations.Reviewed " +
              "FROM Reservers " +
              "INNER JOIN Reservations " +
              "ON Reservers.ReserverID = Reservations.ReserverID " +
              "INNER JOIN Timeframes " +
              "ON Reservations.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Reservations.LocationName = '" + loc + "'" +
              "AND Timeframes.StartDate = '" + time.getStartDate() + "'" +
              "AND Timeframes.StartTime = '" + time.getStartTime() + "'" +
              "AND Timeframes.EndDate = '" + time.getEndDate() + "'" +
              "AND Timeframes.EndTime = '" + time.getEndTime() + "'";
        
        return ReserveDB.getInstance().runQuery(this);
    }
}