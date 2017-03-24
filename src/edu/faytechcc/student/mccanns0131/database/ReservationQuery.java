/**
    A query of the database for reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reserver;
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
        Queries for a reservation with a distinct reserver
    
        @param reserver Reserver to query reservations with
        @throws SQLException Error querying the database
        @return A ResultSet containing the results of the query
    */
    
    public ResultSet queryReservationReserver(Reserver reserver)
            throws SQLException
    {
        String reserverID = "(SELECT Reservers.ReserverID " +
                            "FROM Reservers " +
                            "WHERE Reservers,FirstName = '" +
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
        
        return ReserveDB.getInstance().runQuery(this);
    }
    
    /**
        Queries for & returns reservations made at the given location
    
        @param loc The location
        @throws SQLException Error querying the database
        @return A ResultSet containing the results of the query
    */
    
    public ResultSet queryReservations(Location loc) throws SQLException
    {
        sql = "SELECT Reservers.FirstName, Reservers.LastName, " +
              "Reservers.Email, Reservers.Phone, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "Reservations.EventType, Reservations.NumberAttending, " +
              "Reservations.Approved " +
              "FROM Reservers " +
              "INNER JOIN Reservations " +
              "ON Reservers.ReserverID = Reservations.ReserverID " +
              "INNER JOIN Timeframes " +
              "ON Reservations.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Reservations.LocationName = '" + loc.getName() + "'";
        
        return ReserveDB.getInstance().runQuery(this);
    }
}