/**
    A query of the database for locations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationQuery extends Query
{
    /**
        Constructs a new LocationQuery
    */
    
    public LocationQuery()
    {
        super();
    }
    
    /**
        Queries for & returns data on the timeframes allocated to the named
        location
    
        @param locName Name of location to get timeframes of
        @throws SQLException Error querying the database
        @return ResultSet containing data on timeframes allocated to named
                location
    */
    
    public ResultSet queryLocationTimeframes(String locName) throws SQLException
    {
        sql = "SELECT Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost, " +
              "Reservations.LocationName AS 'ReservedLocationName'" +
              "FROM Timeframes " +
              "INNER JOIN Reservables " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "LEFT JOIN Reservations " +
              "ON Reservables.LocationName = Reservations.LocationName " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "WHERE Reservables.LocationName = '" + locName + "'";
        
        return ReserveDB.getInstance().runQuery(this);
    }
    
    /**
        Queries for & returns the capacity of the named location
    
        @param locName Name of the location to get the capacity of
        @throws SQLException Error querying the database
        @return ResultSet containing the capacity of the named location
    */
    
    public ResultSet queryLocationCapacity(String locName) throws SQLException
    {
        sql = "SELECT Locations.Capacity " +
              "FROM Locations " +
              "WHERE Locations.LocationName = '" + locName + "'";
        
        return ReserveDB.getInstance().runQuery(this);
    }
    
    /**
        Queries for & returns the names of locations
    
        @throws SQLException Error querying the database
        @return ResultSet containing location names
    */
    
    public ResultSet queryLocationNames() throws SQLException
    {
        sql = "SELECT Locations.LocationName " +
              "FROM Locations " +
              "ORDER BY Locations.LocationName";
        
        return ReserveDB.getInstance().runQuery(this);
    }
}