/**
    A query of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        Query for & return a list of available timeframes at the location
        specified by the given name
    
        @param locationName Name of location to get available timeframes of
        @throws SQLException Error querying the database
        @return A list of available timeframes at the specified location
    */
    
    private List<Timeframe> queryAvailableLocationTimeframes(
            String locationName) throws SQLException
    {
        sql = "SELECT Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost " +
              "FROM Timeframes " +
              "INNER JOIN Reservables " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "LEFT JOIN Reservations " +
              "ON Reservables.LocationName = Reservations.LocationName " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "WHERE Reservables.LocationName = '" + locationName + "' " +
              "AND Reservations.LocationName IS NULL " +
              "ORDER BY Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost";
        
        return ResultSetParser.parseTimeframes
            (ReserveDB.getInstance().runQuery(this), false);
    }
    
    /**
        QueryIfLocationExists - Query if a record of a location exists in
        the database
    
        @param locationName The name of the location to query if exists in the
                            database
        @throws SQLException Error querying the database
        @return Whether the record of the location with the specified name
                exists in the database
    */
    
    public boolean queryIfLocationExists(String locationName) 
            throws SQLException
    {
        sql = "SELECT Locations.LocationName " +
              "FROM Locations " +
              "WHERE LocationName = '" + locationName + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        Query for & return every location in the database
    
        @throws SQLException Error querying the database
        @return Every location in the database
    */
    
    public List<Location> queryLocations() throws SQLException
    {
        List<Location> locations = new ArrayList<>();
        
        String[] names = queryLocationNames();
        
        int capacity;
        Location location;
        List<Timeframe> timeframes;
        
        for (String name : names)
        {
            capacity = queryLocationCapacity(name);
            timeframes = queryLocationTimeframes(name);
            
            location = new Location(name, capacity, timeframes);
            
            locations.add(location);
        }
        
        return locations;
    }
    
    /**
        Query for & return the capacity of the location with the specified name
    
        @param locationName The name of the location to get the capacity of
        @throws SQLException Error querying the database
        @return The capacity of the location with the specified name
    */
    
    private int queryLocationCapacity(String locationName) throws SQLException
    {
        sql = "SELECT Locations.Capacity " +
              "FROM Locations " +
              "WHERE Locations.LocationName = '" + locationName + "'";
        
        return ResultSetParser.parseLocationCapacity(
                ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        Query for & return the names of every location in the database
    
        @throws SQLException Error querying the database
        @return The names of every location in the database
    */
    
    private String[] queryLocationNames() throws SQLException
    {
        sql = "SELECT Locations.LocationName " +
              "FROM Locations " +
              "ORDER BY Locations.LocationName";
        
        return ResultSetParser.parseLocationNames(
                ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        Query for & return a list of timeframes at the location specified by the
        given name
    
        @param locationName Name of location to get timeframes of
        @throws SQLException Error querying the database
        @return A list of timeframes at the specified location
    */
    
    private List<Timeframe> queryLocationTimeframes(String locationName)
            throws SQLException
    {
        List<Timeframe> reservedTimeframes =
                queryReservedLocationTimeframes(locationName);
        
        List<Timeframe> availableTimeframes =
                queryAvailableLocationTimeframes(locationName);
        
        reservedTimeframes.addAll(availableTimeframes);
        
        return reservedTimeframes;
    }
    
    /**
        QueryIfReservableExists - Query if a record of a reservable exists in
        the database
    
        @param reservable The reservable to query if exists in the database
        @throws SQLException Error querying the database
        @return Whether the record of the reservable exists in the database
    */
    
    public boolean queryIfReservableExists(Reservable reservable)
            throws SQLException
    {
        sql = "SELECT Reservables.LocationName, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime " +
              "FROM Reservables " +
              "INNER JOIN Timeframes " +
              "ON Reservables.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Reservables.LocationName = '" +
                reservable.getName() + "' " +
              "AND StartDate = '" +
                reservable.getStartDate() + "' " +
              "AND StartTime = '" +
                reservable.getStartTime() + "' " +
              "AND EndDate = '" +
                reservable.getEndDate() + "' " +
              "AND EndTime = '" +
                reservable.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryIfReservableExists - Query if a record of a reservable with the
        given name exists in the database
    
        @param reservableName Name of reservable to query if exists in the
                              database
        @throws SQLException Error querying the database
        @return Whether a record of a reservable with the given name exists
                in the database
    */
    
    public boolean queryIfReservableExists(String reservableName)
            throws SQLException
    {
        sql = "SELECT Reservables.LocationName " +
              "FROM Reservables " +
              "WHERE Reservables.LocationName = '" + reservableName + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryIfReservableExists - Query if a record of a reservable with the
        given timeframe exists in the database
    
        @param timeframe Timeframe of reservable to query if exists in the
                         database
        @throws SQLException Error querying the database
        @return Whether a record of a reservable with the given timeframe exists
                in the database
    */
    
    public boolean queryIfReservableExists(Timeframe timeframe)
            throws SQLException
    {  
        sql = "SELECT Reservables.TimeframeID " +
              "FROM Reservables " +
              "INNER JOIN Timeframes " +
              "ON Reservables.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Timeframes.StartDate = '" +
                timeframe.getStartDate() + "' " +
              "AND Timeframes.StartTime = '" +
                timeframe.getStartTime() + "' " +
              "AND Timeframes.EndDate = '" +
                timeframe.getEndDate() + "' " +
              "AND Timeframes.EndTime = '" +
                timeframe.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryIfReservationExists - Query if a record of a reservation exists
        in the database
    
        @param reservation Reservation to query if exists in the database
        @throws SQLException Error querying the database
        @return Whether a record of a reservation exists in the database
    */
    
    public boolean queryIfReservationExists(Reservation reservation)
            throws SQLException
    {
        sql = "SELECT Reservations.LocationName, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "FROM Reservations " +
              "INNER JOIN Timeframes " +
              "ON Reservations.TimeframeID = Timeframes.TimeframeID " +
              "WHERE LocationName = '" + reservation.getLocationName() + "' " +
              "AND StartDate = '" + reservation.getStartDate() + "' " +
              "AND StartTime = '" + reservation.getStartTime() + "' " +
              "AND EndDate = '" + reservation.getEndDate() + "' " +
              "AND EndTime = '" + reservation.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryIfTimeframeExists - Query if a record of a timeframe exists
        in the database
    
        @param timeframe Timeframe to query if exists in the database
        @throws SQLException Error querying the database
        @return Whether the record of the timeframe exists in the database
    */
    
    public boolean queryIfTimeframeExists(Timeframe timeframe)
            throws SQLException
    {
        sql = "SELECT Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime " +
              "FROM Timeframes " +
              "WHERE StartDate = '" + timeframe.getStartDate() + "' " +
              "AND StartTime = '" + timeframe.getStartTime() + "' " +
              "AND EndDate = '" + timeframe.getEndDate() + "' " +
              "AND EndTime = '" + timeframe.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryIfReserverExists - Query if a record of a reserver exists in the
        database
        
        @param r Reserver to query if exists in the database
        @throws SQLException Error querying the database
        @return Whether the record of a reserver exists in the database
     */
    
    public boolean queryIfReserverExists(Reserver r) throws SQLException
    {
        sql = "SELECT Reservers.FirstName, Reservers.LastName, " +
              "Reservers.Email, Reservers.Phone " +
              "FROM Reservers " +
              "WHERE FirstName = '" + r.getFirstName() + "' " +
              "AND LastName = '" + r.getLastName() + "' " +
              "AND Email = '" + r.getEmailAddress() + "' " +
              "AND Phone = '" + r.getPhoneNumber() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        Query for & return a list of reserved timeframes at the location
        specified by the given name
    
        @param locationName Name of location to get reserved timeframes of
        @throws SQLException Error querying the database
        @return A list of reserved timeframes at the specified location
    */
    
    private List<Timeframe> queryReservedLocationTimeframes(String locationName)
            throws SQLException
    {          
        sql = "SELECT Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost " +
              "FROM Timeframes " +
              "INNER JOIN Reservables " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "INNER JOIN Reservations " +
              "ON Reservables.LocationName = Reservations.LocationName " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "WHERE Reservations.LocationName = '" + locationName + "' " +
              "ORDER BY Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, Reservables.Cost";
        
        return ResultSetParser.parseTimeframes
            (ReserveDB.getInstance().runQuery(this), true);
    }
    
    /**
        Queries for & returns a list of reservations made
    
        @throws SQLException Error querying the database
        @return List of reservations
    */
    
    public List<Reservation> queryReservations() throws SQLException
    {
        sql = "SELECT Locations.LocationName, Locations.Capacity, " +
              "Timeframes.StartDate, Timeframes.StartTime, " +
              "Timeframes.EndDate, Timeframes.EndTime, " +
              "Reservables.Cost, Reservers.FirstName, Reservers.LastName, " +
              "Reservers.Email, Reservers.Phone, Reservations.EventType, " +
              "Reservations.NumberAttending, Reservations.Approved " +
              "FROM Locations " +
              "INNER JOIN Reservables " +
              "ON Locations.LocationName = Reservables.LocationName " +
              "INNER JOIN Timeframes " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "INNER JOIN Reservations " +
              "ON Reservables.LocationName = Reservations.LocationName " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "INNER JOIN Reservers " +
              "ON Reservers.ReserverID = Reservations.ReserverID " +
              "ORDER BY Reservations.Approved, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, " +
              "Timeframes.EndTime, Reservers.FirstName, Reservers.LastName, " +
              "Reservations.NumberAttending";
        
        return ResultSetParser.parseReservations
            (ReserveDB.getInstance().runQuery(this));
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
    
    /**
     * QueryReservables - Query reservables from the database based on search
     * filters
     * 
     * @return A ResultSet of reservables meeting search criteria
     * @throws SQLException Error querying the database
     */
    public List<Reservable> queryReservables() throws SQLException
    {
        /**
         * List<Location> locations = new ArrayList<>();
        
        String[] names = queryLocationNames();
        
        int capacity;
        Location location;
        List<Timeframe> timeframes;
        
        for (String name : names)
        {
            capacity = queryLocationCapacity(name);
            timeframes = queryLocationTimeframes(name);
            
            location = new Location(name, capacity, timeframes);
            
            locations.add(location);
        }
        
        return locations.toArray(new Location[locations.size()]);
         */
        
        List<Location> locations = new ArrayList();
        List<Reservable> reservables = new ArrayList();
        
        String[] names = queryLocationNames();
        
        int capacity;
        Location location;
        List<Timeframe> timeframes;
        
        for (String name : names)
        {
            capacity = queryLocationCapacity(name);
            timeframes = queryLocationTimeframes(name);
            
            location = new Location(name, capacity, timeframes);
            
            for (Timeframe t : timeframes)
            {
                reservables.add(new Reservable(location, t));
            }
            
            locations.add(location);
        }
        return reservables;
    }
}