/**
    A query of the database for locations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

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
        Prepares the query to return data on locations
    
        @return This query, prepared
    */
    
    public LocationQuery queryLocations()
    {
        sql = "SELECT Locations.LocationName, Locations.Capacity, " +
              "Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost, " +
              "Reservations.LocationName AS 'ReservedLocationName' " +
              "FROM Locations " +
              "INNER JOIN Reservables " +
              "ON Locations.LocationName = Reservables.LocationName " +
              "INNER JOIN Timeframes " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "LEFT JOIN Reservations " +
              "ON Reservables.LocationName = Reservations.LocationName " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "ORDER BY Locations.LocationName";
        
        return this;
    }
    
    /**
        Prepares the query to return the capacity of the named location
    
        @param locName Name of the location to get the capacity of
    */
    
    public void queryLocationCapacity(String locName)
    {
        sql = "SELECT Locations.Capacity " +
              "FROM Locations " +
              "WHERE Locations.LocationName = '" + locName + "'";        
    }
}