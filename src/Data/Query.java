/**
    A query of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.sql.SQLException;

public class Query
{
    // Fields
    private String sql;
    
    /**
        Constructor
    */
    
    public Query()
    {
        sql = "";
    }
    
    /**
        QueryIfLocationExists - Return if the location with the specified name
        exists in the database
    
        @param name The location name
        @throws SQLException Error running the query
        @return If the location with the specified name exists in the database
    */
    
    public boolean queryIfLocationExists(String name) throws SQLException
    {
        sql = "SELECT Locations.LocationName " +
              "FROM Locations " +
              "WHERE LocationName = '" + name + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.runQuery(this));
    }
    
    /**
        QueryLocationNames - Return the names of every location in the database
    
        @throws SQLException Error running the query
        @return names The names of every location in the database
    */
    
    public String[] queryLocationNames() throws SQLException
    {
        sql = "SELECT Locations.LocationName " +
              "FROM Locations " +
              "ORDER BY LocationName";
        
        return ResultSetParser.parseLocationNames(ReserveDB.runQuery(this));
    }
    
    /**
        QueryLocationTimeframes - Query the reservable timeframes allocated to
        a location
    
        @param name The name of the location to query the allocated timeframes
                    of
        @throws SQLException Error running the query
        @return timeframes The reservable timeframes allocated to the location
    */
    
    public ReservableTimeframeList queryLocationTimeframes(String name)
            throws SQLException
    {
        sql = "SELECT Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, " +
              "LocationTimeframes.Cost " +
              "FROM LocationTimeframes " +
              "INNER JOIN Timeframes " +
              "ON LocationTimeframes.TimeframeID = Timeframes.TimeframeID " +
              "WHERE LocationTimeframes.LocationName = '" + name + "' " +
              "ORDER BY StartDate, StartTime, EndDate, EndTime, Cost";
        
        return ResultSetParser.parseReservableTimeframes(ReserveDB
                                                            .runQuery(this));
    }
    
    /**
        QueryReservedTimeframes - Query the database for timeframes that have
        been reserved at the location specified by the given name
    
        @param name The name of the location to query the reserved timeframes
                    of
        @throws SQLException Error running the query
        @return timeframes The reservable timeframes the location is reserved
                           for
    */
    
    /**
        QueryIfReservableExists - Query if a reservable matching the arguments
        exists in the database
    
        @param location Location of the reservable
        @param timeframe Timeframe of the reservable
        @throws SQLException Error running the query
        @return If a reservable matching the arguments exists
    */
    
    public boolean queryIfReservableExists(Location location, 
                                           Timeframe timeframe)
            throws SQLException
    {
        sql = "SELECT Locations.LocationName, Locations.Capacity, " +
              "Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, " +
              "LocationTimeframes.Cost " +
              "FROM LocationTimeframes " +
              "INNER JOIN Locations " +
              "ON LocationTimeframes.LocationName = Locations.LocationName " +
              "INNER JOIN Timeframes " +
              "ON LocationTimeframes.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Locations.LocationName = '" + location.getName() + "' " +
              "AND StartDate = '" + timeframe.getStartDate() + "' " +
              "AND StartTime = '" + timeframe.getStartTime() + "' " +
              "AND EndDate = '" + timeframe.getEndDate() + "' " +
              "AND EndTime = '" + timeframe.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.runQuery(this));
    }
    
    /**
        QueryIfTimeframeExists - Query if the timeframe specified exists in the
        database
    
        @param t Timeframe to check if exists in the database
        @throws SQLException Error running the query
        @return If the specified timeframe exists in the database
    */
    
    public boolean queryIfTimeframeExists(Timeframe t)
            throws SQLException
    {
        sql = "SELECT Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime " +
              "FROM Timeframes " +
              "WHERE StartDate = '" + t.getStartDate() + "' " +
              "AND StartTime = '" + t.getStartTime() + "' " +
              "AND EndDate = '" + t.getEndDate() + "' " +
              "AND EndTime = '" + t.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.runQuery(this));
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