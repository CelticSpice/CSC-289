/**
    A query of the database for reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

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
        Prepares the query to query reservables for the given location name
    
        @param name Name of reservable
        @return This query, prepared
    */
    
    public ReservableQuery queryReservableName(String name)
    {
        sql = "SELECT DISTINCT Reservables.LocationName " +
              "FROM Reservables " +
              "WHERE Reservables.LocationName = '" + name + "'";
        
        return this;
    }
    
    /**
        Prepares the query to query reservables for the ID of the given
        timeframe
    
        @param timeframe The timeframe to query ID of
    */
    
    public void queryReservableTimeframeID(Timeframe timeframe)
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