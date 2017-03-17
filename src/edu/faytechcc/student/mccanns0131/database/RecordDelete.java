/**
    A deletion of a record from the database
    CSC-289- Group 4
    @author Timothy Burns, Shane McCann
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;

public class RecordDelete
{
    // Fields
    private String sql;
    
    /**
        Constructor
    */
    
    public RecordDelete()
    {
        sql = "";
    }
    
    /**
        DeleteLocation - Remove a record of a location with the given name
        from the database
    
        @param locationName The name of the location to remove from the database
        @throws SQLException Error removing record from database
    */
    
    private void deleteLocation(String locationName) throws SQLException
    {
        sql = "DELETE FROM Locations " +
              "WHERE Locations.LocationName = '" + locationName + "'";
        
        ReserveDB.getInstance().deleteRecord(this);
    }
    
    /**
        DeleteReservable - Remove a record of a reservable from the database
    
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
        Query query = new Query();
        if (!query.queryIfReservableExists(reservable.getName()))
            deleteLocation(reservable.getName());
        
        // Check if we should delete a record of a timeframe with the same
        // timeframe as the reservable
        if (!query.queryIfReservableExists(reservable.getTimeframe()))
            deleteTimeframe(reservable.getTimeframe());
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