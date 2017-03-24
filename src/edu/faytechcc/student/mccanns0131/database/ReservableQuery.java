/**
    A query of the database for reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        Queries reservables for a distinct name
    
        @param name Name of reservable
        @throws SQLException Error Querying the database
        @return The results of the query
    */
    
    public ResultSet queryReservableName(String name) throws SQLException
    {
        sql = "SELECT DISTINCT Reservables.LocationName " +
              "FROM Reservables " +
              "WHERE Reservables.LocationName = '" + name + "'";
        
        return ReserveDB.getInstance().runQuery(this);
    }
    
    /**
        Queries reservables for a distinct timeframe
    
        @param timeframe The timeframe of the reservable
        @throws SQLException Error Querying the database
        @return The results of the query
    */
    
    public ResultSet queryReservableTimeframe(Timeframe timeframe)
            throws SQLException
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
        
        return ReserveDB.getInstance().runQuery(this);
    }
}