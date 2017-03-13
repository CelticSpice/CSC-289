/**
    An addition of a record to the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import Exception.RecordExistsException;
import java.sql.SQLException;

public class RecordAdd
{
    // Fields
    private String sql;
    
    /**
        Constructor
    */
    
    public RecordAdd()
    {
        sql = "";
    }
    
    /**
        AddReservable - Add a record of a reservable to the database
    
        @param reservable The reservable to add
        @throws SQLException Error adding record to the database
        @throws RecordExistsException Identical record exists in the database
    */
    
    public void addReservable(Reservable reservable)
            throws SQLException, RecordExistsException
    {
        // Ensure that the record does not already exist
        Query query = new Query();
        
        if (!query.queryIfReservableExists(reservable))
        {
            ReserveDB db = ReserveDB.getInstance();
            
            // Avoid duplicating a record of a location
            if (!query.queryIfLocationExists(reservable.getName()))
            {
                sql = "INSERT INTO Locations " +
                      "VALUES ('" + reservable.getName() + "', " +
                                    reservable.getCapacity() + ")";
                
                db.addRecord(this);
            }
            
            // Avoid duplicating a record of a timeframe
            if (!query.queryIfTimeframeExists(reservable.getTimeframe()))
            {
                sql = "INSERT INTO Timeframes " +
                      "(StartDate, StartTime, EndDate, EndTime) " +
                      "VALUES ('" + reservable.getStartDate() + "', '" +
                                    reservable.getStartTime() + "', '" +
                                    reservable.getEndDate()   + "', '" +
                                    reservable.getEndTime() + "')";
                
                db.addRecord(this);
            }
            
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
                                reservable.getCost().doubleValue() + ")";
          
            db.addRecord(this);
        }
        else
            throw new RecordExistsException();
    }
    
    /**
       AddReserver - Add a record of a reserver to the database
       
       @param r The reserver to be added
       @throws SQLException Error adding record to the database
       @throws RecordExistsException Identical record exists in the database
     */
    
    private void addReserver(Reserver r)
            throws SQLException, RecordExistsException
    {
        // Ensure that the record does not already exist
        Query query = new Query();
        
        if (!query.queryIfReserverExists(r))
        {
            sql = "INSERT INTO Reservers (firstName, lastName, email, phone" +
                  "VALUES ('" + r.getContactInfo().getFirstName() + "', " +
                  "'" + r.getContactInfo().getLastName() + "', " +
                  "'" + r.getContactInfo().getEmail() + "', " +
                  "'" + r.getContactInfo().getPhoneNumber() + "')";
            
            ReserveDB.getInstance().addRecord(this);
        }
        else
            throw new RecordExistsException();
    }
    
    /**
        AddReservation - Add a record of a reservation to the database
    
        @param reservation The reservation to add
        @throws SQLException Error adding record to the database
        @throws RecordExistsException Identical record exists in the database
    */
    
    public void addReservation(Reservation reservation)
            throws SQLException, RecordExistsException
    {
        // Ensure that the record does not already exist
        Query query = new Query();
        
        if (!query.queryIfReservationExists(reservation))
        {   
            // Check valid Reservable????
            
            // Make sure Reserver is not a duplicate
            Reserver reserver = reservation.getReserver();
            
            if (!query.queryIfReserverExists(reserver))
            {
                    addReserver(reserver);
            }
            
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
            
            sql = "INSERT INTO Reservations" +
                  "VALUES ('" + reservation.getLocationName() + "', " +
                  "(SELECT DISTINCT Reservables.timeframeID" +
                  "FROM Reservables" +
                  "INNER JOIN Timeframes" +
                  "ON Reservables.timeframeID = Timeframes.timeframeID" +
                  "WHERE timeframeID = '" + timeframeID +
                  "(SELECT reserverID" +
                  "FROM Reservers" +
                  "WHERE firstName = '" + reserver.getFirstName() + "'" +
                  "AND lastName = '" + reserver.getLastName() + "'" +
                  "AND email = '" + reserver.getEmailAddress() + "'" +
                  "AND phone = '" + reserver.getPhoneNumer() + "')," + 
                  reservation.getEventType() + ", " +
                  reservation.getNumberAttending() + ")";
            
            ReserveDB.getInstance().addRecord(this);
        }
        else
            throw new RecordExistsException();         
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
