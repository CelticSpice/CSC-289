/**
    A query of the database for reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reserver;

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
        Prepares the query to query for a distinct reservation made by the
        specified reserver
        
        @param reserver Reserver to query distinct reservation made by
    */
    
    public void queryDistinctByReserver(Reserver reserver)
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
        Prepares the query to query reservations made at the specified location
    
        @param loc The location to query reservations of
    */
    
    public void queryByLocation(Location loc)
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
              "WHERE Reservations.LocationName = '" + loc.getName() + "'";        
    }
}