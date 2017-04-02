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
        Prepares the query to query all locations
    */
    
    public void queryAll()
    {
        sql = "SELECT Locations.LocationName, Locations.Capacity, " +
              "Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost, " +
              "Reservations.TimeframeID AS 'ReservedTimeframeID' " +
              "FROM Locations " +
              "INNER JOIN Reservables " +
              "ON Locations.LocationName = Reservables.LocationName " +
              "INNER JOIN Timeframes " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "LEFT JOIN Reservations " +
              "ON Reservables.LocationName = Reservations.LocationName " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "ORDER BY Locations.LocationName, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "Reservables.Cost";
    }
}