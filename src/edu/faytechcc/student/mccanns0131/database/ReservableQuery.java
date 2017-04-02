/**
    A query of the database for reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Timeframe;

public class ReservableQuery extends Query
{
    /**
        Constructs a new ReservableQuery
    */
    
    public ReservableQuery()
    {
        super();
    }
    
    /**
        Prepares the query to query for a distinct reservable at the specified
        location
    
        @param loc The location to query a distinct reservable at
    */
    
    public void queryDistinctByLocation(Location loc)
    {
        sql = "SELECT DISTINCT Reservables.LocationName " +
              "FROM Reservables " +
              "WHERE Reservables.LocationName = '" + loc.getName() + "'";
    }
    
    /**
        Prepares the query to query for a distinct reservable at the specified
        timeframe
    
        @param timeframe The timeframe to query a distinct reservable at
    */
    
    public void queryDistinctByTimeframe(Timeframe timeframe)
    {
        String timeframeID = "(SELECT Timeframes.TimeframeID " +
                             "FROM Timeframes " +
                             "WHERE Timeframes.StartDate = '" +
                                timeframe.getStartDate() + "' " +
                             "AND Timeframes.StartTime = '" +
                                timeframe.getStartTime() + "' " +
                             "AND Timeframes.EndDate = '" +
                                timeframe.getEndDate() + "' " +
                             "AND Timeframes.EndTime = '" +
                                timeframe.getEndTime() + "')";
        
        sql = "SELECT DISTINCT Reservables.TimeframeID " +
              "FROM Reservables " +
              "WHERE Reservables.TimeframeID = " + timeframeID;
    }
}