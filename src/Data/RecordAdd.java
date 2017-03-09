/**
    An addition of a record to the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import Exception.RecordExistsException;
import java.math.BigDecimal;
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
        AddReservable - Adds a reservable (location & timeframe) to the
        database
    
        @param location Reservable location to add
        @param timeframe Reservable timeframe to add
        @param cost The cost to reserve the reservable
        @throws SQLException There was an error adding the record
        @throws RecordExistsException Record already exists in database
    */
    
    public void addReservable(Location location, Timeframe timeframe,
                              BigDecimal cost)
            throws SQLException, RecordExistsException
    {
        // Ensure that the record does not already exist
        Query query = new Query();
        
        if (!query.queryIfReservableExists(location, timeframe))
        {
            // Get dates & times of timeframe as strings
            String startDate = timeframe.getStartDate().toString();
            String startTime = timeframe.getStartTime().toString();
            String endDate = timeframe.getEndDate().toString();
            String endTime = timeframe.getEndTime().toString();
            
            // Avoid duplicating a record of a location
            if (!query.queryIfLocationExists(location.getName()))
            {
                sql = "INSERT INTO Locations " +
                      "VALUES ('" + location.getName() + "', " +
                                    location.getCapacity() + ")";
                
                ReserveDB.addRecord(this);
            }
            
            // Avoid duplicating a record of a timeframe
            if (!query.queryIfTimeframeExists(timeframe))
            {
                sql = "INSERT INTO Timeframes " +
                      "(StartDate, StartTime, EndDate, EndTime) " +
                      "VALUES ('" + startDate + "', '" + startTime + "', '" +
                                    endDate   + "', '" + endTime + "')";
                
                ReserveDB.addRecord(this);
            }
            
            String subSelect = "(SELECT Timeframes.TimeframeID " +
                                "FROM Timeframes T " +
                                "WHERE T.StartDate = '" + startDate + "' " +
                                "AND T.StartTime = '" + startTime + "' " +
                                "AND T.EndDate = '" + endDate + "' " +
                                "AND T.EndTime = '" + endTime + "')";
            
            sql = "INSERT INTO LocationTimeframes " +
                  "VALUES ('" + location.getName() + "', " + subSelect + ", " +
                                cost.doubleValue() + ")";
          
            ReserveDB.addRecord(this);
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