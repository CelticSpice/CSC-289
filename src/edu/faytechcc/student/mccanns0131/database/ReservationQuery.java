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
        sql = "SELECT DISTINCT Reservations.ReserverID " +
              "FROM Reservations " +
              "WHERE Reservations.ReserverID = " + reserver.getID();
    }
    
    /**
        Prepares the query to query for reservations made at the
        specified location
    
        @param loc The location to query reservations made at
    */
    
    public void queryByLocation(Location loc)
    {
        sql = "SELECT Reservers.ReserverID, Reservers.FirstName, " +
              "Reservers.LastName, Reservers.Email, Reservers.Phone, " +
              "Timeframes.TimeframeID, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "Reservables.Cost, Reservations.EventType, " +
              "Reservations.NumberAttending, Reservations.Reviewed " +
              "FROM Reservers " +
              "INNER JOIN Reservations " +
              "ON Reservers.ReserverID = Reservations.ReserverID " +
              "INNER JOIN Reservables " +
              "ON Reservations.LocationID = Reservables.LocationID " +
              "AND Reservations.TimeframeID = Reservables.TimeframeID " +
              "INNER JOIN Timeframes " +
              "ON Reservables.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Reservations.LocationID = " + loc.getID(); 
    }
}