/**
    An update of a record in the database
    CSC-289 - Group 4
    @author Shane McCann
 */

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import java.sql.SQLException;

public class RecordUpdate
{
    // Fields
    private DBConnection connection;
    private String sql;
    
    /**
        Constructs a new RecordUpdate initialized with the given database
        connection
    
        @param conn Connection to a database
    */
    
    public RecordUpdate(DBConnection conn)
    {
        connection = conn;
        sql = "";
    }
    
    /**
        Updates a record of a location in the database
    
        @param loc The updated location
        @throws SQLException Error updating record
    */
    
    public void updateLocation(Location loc) throws SQLException
    {
        sql = "UPDATE Locations " +
              "SET Locations.LocationName = '" +
                loc.getName() + "', " +
              "Locations.Capacity = " +
                loc.getCapacity() + " " +
              "WHERE Locations.LocationID = " +
                loc.getID();
        
        connection.updateRecord(this);
    }
    
    /**
        Updates a record of a reservable in the database
    
        @param reservable The updated reservable
        @throws SQLException Error updating record
    */
    
    public void updateReservable(Reservable reservable) throws SQLException
    {
        sql = "UPDATE Reservables " +
              "SET Reservables.Cost = " +
                reservable.getCost() + " " +
              "WHERE Reservables.LocationID = " +
                reservable.getLocationID() + " " +
              "AND Reservables.TimeframeID = " +
                reservable.getTimeframeID();
        
        connection.updateRecord(this);
    }
    
    /**
        Updates a record of a reservation in the database
    
        @param reservation The updated reservation
        @throws SQLException Error updating record
    */
    
    public void updateReservation(Reservation reservation) throws SQLException
    {
        sql = "UPDATE Reservations " +
              "SET Reservations.EventType = '" +
                reservation.getEventType() + "', " +
              "Reservations.NumberAttending = " +
                reservation.getNumberAttending() + ", " +
              "Reservations.Reviewed = " +
                reservation.isReviewed() + " " +
              "WHERE Reservations.LocationID = " +
                reservation.getLocationID() + " " +
              "AND Reservations.TimeframeID = " +
                reservation.getTimeframeID();
        
        connection.updateRecord(this);
    }
    
    /**
        Updates a record of a reserver in the database
    
        @param reserver The updated reserver
        @throws SQLException Error updating record
    */
    
    public void updateReserver(Reserver reserver) throws SQLException
    {
        sql = "UPDATE Reservers " +
              "SET Reservers.FirstName = '" +
                reserver.getFirstName() + "', " +
              "Reservers.LastName = '" +
                reserver.getLastName() + "', " +
              "Reservers.Email = '" +
                reserver.getEmailAddress() + "', " +
              "Reservers.Phone = '" +
                reserver.getPhoneNumber() + "' " +
              "WHERE Reservers.ReserverID = " +
                reserver.getID();
        
        connection.updateRecord(this);
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