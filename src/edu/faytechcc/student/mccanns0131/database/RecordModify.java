/**
 * A modification of a record in the database
 * CSC-289 - Group 4
 * @author Shane McCann
 */
package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.exception.RecordNotExistsException;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RecordModify
{
    // Fields
    private String sql;
    
    /**
     * Constructor
     */
    public RecordModify()
    {
        sql = "";
    }
    
    /**
     * ModifyLocation - Modify an existing location's name and capacity.
     * 
     * @param location The location being modified
     * @param newLocationName The new location name
     * @param newCapacity The new location capacity
     * @throws SQLException Error modifying record in database
     * @throws RecordNotExistsException No existing record was found
     */
    public void modifyLocation(Location location, String newLocationName,
            int newCapacity) throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservableQuery q = new ReservableQuery();
        
        ResultSetParser rsp = new ResultSetParser(q.queryReservableName(
                location.getName()));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Locations" +
                  "SET locationName = '" + newLocationName + "'," +
                  "capacity = " + newCapacity +
                  "WHERE locationName = '" + location.getName() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyLocationCapacity - Modify an existing location's capacity.
     * 
     * @param location The location being modified
     * @param newCapacity The new capacity of the location
     * @throws SQLException Error modifying record in database
     * @throws RecordNotExistsException No existing record was found
     */
    public void modifyLocationCapacity(Location location, int newCapacity)
            throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        LocationQuery q = new LocationQuery();
        
        ResultSetParser rsp = new ResultSetParser(q.queryLocationCapacity(
                location.getName()));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Locations" +
                  "SET capacity = " + newCapacity +
                  "WHERE locationName = '" + location.getName() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyLocationName - Modify an existing location's name
     * 
     * @param location The location being modified
     * @param newLocationName The new location name
     * @throws SQLException Error modifying record in database
     * @throws RecordNotExistsException No existing record was found
     */
    public void modifyLocationName(Location location, String newLocationName)
            throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservableQuery q = new ReservableQuery();
        
        ResultSetParser rsp = new ResultSetParser(q.queryReservableName(
                location.getName()));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Locations" +
                  "SET locationName = '" + newLocationName + "'" +
                  "WHERE locationName = '" + location.getName() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReservableCost - Modify the cost of an existing reservable.
     * 
     * @param reservable The reservable being modified
     * @param newCost The new cost of the reservable
     * @throws SQLException Error modifying record in database
     * @throws RecordNotExistsException No existing record was found
     */
    public void modifyReservableLocationCost(Reservable reservable,
            BigDecimal newCost) throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservableQuery q = new ReservableQuery();
        
        ResultSetParser rsp = new ResultSetParser(q.queryReservableName(
                reservable.getName()));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservables" +
                  "SET cost = " + newCost +
                  "WHERE locationName = '" + reservable.getName() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReservableCost - Modify the cost of a reservable
     * 
     * @param reservable The reservable being modified
     * @param newCost The new cost
     * @throws SQLException Error modifying cost of the reservable
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReservableTimeframeCost(Reservable reservable,
            BigDecimal newCost) throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservableQuery q = new ReservableQuery();
        
        ResultSetParser rsp = new ResultSetParser(q.queryReservableName(
                reservable.getName()));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservables" +
                  "SET cost = " + newCost +
                  "WHERE locationName = '" + reservable.getName() + "'" +
                  "AND Reservables.timeframeID = Timeframes.timeframeID" +
                  "AND Timeframes.startDate = '" + reservable.getStartDate() + "'" +
                  "AND Timeframes.endDate = '" + reservable.getEndDate() + "'" +
                  "AND Timeframes.startTime = '" + reservable.getStartTime() + "'" +
                  "AND Timeframes.endTime = '" + reservable.getEndTime() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReservationReviewed - Modify the reviewed status of a reservation
     * 
     * @param reservation The reservation being modified
     * @param reviewed Whether or not the reservation is reviewed
     * @throws SQLException Error modifying the record
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReservationReviewed(Reservation reservation,
            boolean reviewed) throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservationQuery q = new ReservationQuery();
        
        LocalDateTime s = LocalDateTime.of(reservation.getStartDate(),
                reservation.getStartTime());
        LocalDateTime e = LocalDateTime.of(reservation.getEndDate(),
                reservation.getEndTime());
        
        Timeframe time = new Timeframe(s, e);
        
        ResultSetParser rsp = new ResultSetParser(q.queryReservation(
                reservation.getLocationName(), time));
        
        if (!rsp.isEmpty())
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
            
            sql = "UPDATE Reservations " +
                  "SET Reservations.Reviewed = " + reviewed + " " +
                  "WHERE Reservations.LocationName = '" + reservation.getLocationName() + "' " +
                  "AND Reservations.TimeframeID = " + timeframeID;
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReservationNumberAttending - Modify the number of attendees at a
     * reservation
     * 
     * @param reservation The reservation being modified
     * @param newNumAttending The new amount of attendees
     * @throws SQLException Error modifying number of attendees
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReservationNumberAttending(Reservation reservation,
            int newNumAttending) throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservationQuery q = new ReservationQuery();
        
        LocalDateTime s = LocalDateTime.of(reservation.getStartDate(),
                reservation.getStartTime());
        LocalDateTime e = LocalDateTime.of(reservation.getEndDate(),
                reservation.getEndTime());
        
        Timeframe time = new Timeframe(s, e);
        
        ResultSetParser rsp = new ResultSetParser(q.queryReservation(
                reservation.getLocationName(), time));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservations" +
                  "SET numberAttending = " + newNumAttending +
                  "WHERE locationName = '" + reservation.getLocationName() + "'" +
                  "AND Reservations.timeframeID = Timeframes.timeframeID" +
                  "AND Timeframes.startDate = '" + reservation.getStartDate() + "'" +
                  "AND Timeframes.endDate = '" + reservation.getEndDate() + "'" +
                  "AND Timeframes.startTime = '" + reservation.getStartTime() + "'" +
                  "AND Timeframes.endTime = '" + reservation.getEndTime() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReserverEmailAddress - modify the email of a reserver
     * 
     * @param reserver The reserver being modified
     * @param newEmail The email the current email will be changed to
     * @throws SQLException Error modifying reserver email
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReserverEmailAddress(Reserver reserver, String newEmail)
            throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservationQuery q = new ReservationQuery();
        
        ResultSetParser rsp = new ResultSetParser(
                q.queryReservationReserver(reserver));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservers" +
                  "SET email = '" + newEmail + "'" +
                  "WHERE email = '" + reserver.getEmailAddress() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReserverFirstName - Modify the first name of a reserver
     * 
     * @param reserver The reserver being modified
     * @param newFirstName The new first name
     * @throws SQLException Error modifying reserver first name
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReserverFirstName(Reserver reserver, String newFirstName)
            throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservationQuery q = new ReservationQuery();
        
        ResultSetParser rsp = new ResultSetParser(
                q.queryReservationReserver(reserver));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservers" +
                  "SET firstName = '" + newFirstName + "'" +
                  "WHERE email = '" + reserver.getEmailAddress() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReserverLastName - modify the last name of a reserver
     * 
     * @param reserver The reserver being modified
     * @param newLastName The new last name
     * @throws SQLException Error modifying reserver last name
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReserverLastName(Reserver reserver, String newLastName)
            throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservationQuery q = new ReservationQuery();
        
        ResultSetParser rsp = new ResultSetParser(
                q.queryReservationReserver(reserver));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservers" +
                  "SET lastName = '" + newLastName + "'" +
                  "WHERE email = '" + reserver.getEmailAddress() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ModifyReserverPhoneNumber - modify the phone number of a reserver
     * 
     * @param reserver The reserver being modified
     * @param newPhone The new phone number
     * @throws SQLException Error modifying reserver phone number
     * @throws RecordNotExistsException The record does not exist
     */
    public void modifyReserverPhoneNumber(Reserver reserver, String newPhone)
            throws SQLException, RecordNotExistsException
    {
        // Ensure that the record exists.
        ReservationQuery q = new ReservationQuery();
        
        ResultSetParser rsp = new ResultSetParser(
                q.queryReservationReserver(reserver));
        
        if (!rsp.isEmpty())
        {
            sql = "UPDATE Reservers" +
                  "SET phoneNumber = '" + newPhone + "'" +
                  "WHERE email = '" + reserver.getEmailAddress() + "'";
            
            ReserveDB.getInstance().modifyRecord(this);
        }
        else
            throw new RecordNotExistsException();
    }
    
    /**
     * ToString - Return a string representation of the object
     * 
     * @return A string representation of the object
     */
    @Override
    public String toString()
    {
        return sql;
    }
}