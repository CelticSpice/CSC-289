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
        QueryLocationNames - Return the names of every location in the database
    
        @throws SQLException Error running the query
        @return names The names of every location in the database
    */
    
    public String[] queryLocationNames() throws SQLException
    {
        sql = "SELECT Locations.LocationName " +
              "FROM Locations " +
              "ORDER BY LocationName";
        
        return ResultSetParser.parseLocationNames(ReserveDB
                                                  .getInstance()
                                                  .runQuery(this));
    }
    
    /**
        QueryLocationTimeframes - Query the reservable timeframes allocated to
        a location
    
        @param name The name of the location to query the allocated timeframes
                    of
        @throws SQLException Error running the query
        @return timeframes The reservable timeframes allocated to the location
    */
    
    public TimeframeList queryLocationTimeframes(String name)
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
                                                         .getInstance()
                                                         .runQuery(this));
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
        sql = "SELECT Locations.LocationName, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime " +
              "FROM Reservables " +
              "INNER JOIN Locations " +
              "ON Reservables.LocationName = Locations.LocationName " +
              "INNER JOIN Timeframes " +
              "ON Reservables.TimeframeID = Timeframes.TimeframeID " +
              "WHERE Locations.LocationName = '" + reservable.getName()+ "' " +
              "AND StartDate = '" + reservable.getStartDate() + "' " +
              "AND StartTime = '" + reservable.getStartTime() + "' " +
              "AND EndDate = '" + reservable.getEndDate() + "' " +
              "AND EndTime = '" + reservable.getEndTime() + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryIfReservationExists - Query if a record of a reservation exists
        in the database
    
        @param reservation The reservation to query if exists in the database
        @throws SQLException Error querying the database
        @return Whether the record of the reservation exists in the database
    */
    
    public boolean queryIfReservationExists(Reservation reservation)
            throws SQLException
    {
        sql = "SELECT Locations.LocationName, Timeframes.StartDate, " +
              "Timeframes.StartTime, Timeframes.EndDate, Timeframes.EndTime, " +
              "FROM Reservations " +
              "INNER JOIN Reservables " +
              "ON Reservations.LocationName = Reservables.LocationName " +
              "AND Reservations.TimeframeID = Reservables.TimeframeID " +
              "INNER JOIN Locations " +
              "ON Reservables.LocationName = Locations.LocationName " +
              "INNER JOIN Timeframes " +
              "ON Reservables.TimeframeID = Timeframes.TimeframeID " +
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
    
        @param timeframe The timeframe to query if exists in the database
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
        QueryIfReserverExists - Query if the reserver exists in the database
        
        @param r The reserver being checked
        @return If the reserver exists
        @throws SQLException SQLException Error running the query
     */
    
    public boolean queryIfReserverExists(Reserver r) throws SQLException
    {
        // Store reserver info as Strings
        String firstName = r.getContactInfo().getFirstName();
        String lastName = r.getContactInfo().getLastName();
        String email = r.getContactInfo().getEmail();
        String phone = r.getContactInfo().getPhoneNumber();
        
        // Build SQL statement
        sql = "SELECT Reservers.firstName, Reservers.lastName, " +
              "Reservers.email, Reservers.phone" +
              "FROM Reservers" +
              "WHERE firstName = '" + firstName + "'" +
              "AND lastName = '" + lastName + "'" +
              "AND email = '" + email + "'" +
              "AND phone = '" + phone + "'";
        
        return !ResultSetParser.isEmpty(ReserveDB.getInstance().runQuery(this));
    }
    
    /**
        QueryLocationReservations - Query for reservations that have been made
        at the location with the given name
    
        @param locationName Name of location to query reservations made at
        @return A list of reservations that have been made at the location with
                the given name
    */
    
    public ReservationList queryLocationReservations(String locationName)
    {
        return null;
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