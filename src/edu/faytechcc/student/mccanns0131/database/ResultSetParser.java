/**
    A parser for ResultSet objects
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultSetParser
{  
    // Fields
    private ResultSet rs;
    
    /**
        Constructs a new ResultSetParser
    */
    
    public ResultSetParser()
    {
        rs = null;
    }
    
    /**
        Constructs a new ResultSetParser initialized with the given ResultSet
    
        @param resultSet The ResultSet
    */
    
    public ResultSetParser(ResultSet resultSet)
    {
        rs = resultSet;
    }
    
    /**
        Returns whether the parser has a ResultSet
    
        @return If the parser has a ResultSet
    */
    
    public boolean hasResultSet()
    {
        return rs != null;
    }
    
    /**
        Returns whether the parser's ResultSet is empty
    
        @throws SQLException Error parsing the result set
        @return If the parser's ResultSet is empty
    */
    
    public boolean isEmpty() throws SQLException
    {
        return !rs.isBeforeFirst();
    }
    
    /**
        Parses data on a location's capacity
    
        @throws SQLException Error parsing the result set
        @return The location capacity
    */
    
    public int parseLocationCapacity() throws SQLException
    {
        rs.next();
        return rs.getInt(1);
    }
    
    /**
        Parses data on locations
    
        @throws SQLException Error parsing the result set
        @return List of locations
    */
    
    public List<Location> parseLocations() throws SQLException
    {
        List<Location> locations = new ArrayList<>();
        HashMap<String, Location> locationMap = new HashMap<>();
        
        String name;
        int capacity;
        Location loc;
        Timeframe timeframe;
        BigDecimal cost;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        LocalDateTime sDateTime, eDateTime;
        
        while (rs.next())
        {
            // Build location
            name = rs.getString("LocationName");
            capacity = rs.getInt("Capacity");
            
            if (locationMap.containsKey(name))
                loc = locationMap.get(name);
            else
            {
                loc = new Location(name, capacity);
                locationMap.put(name, loc);
                locations.add(loc);
            }
            
            // Build timeframe
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            cost = rs.getBigDecimal("Cost");
            
            sDateTime = LocalDateTime.of(startDate, startTime);
            eDateTime = LocalDateTime.of(endDate, endTime);
            
            // Check if timeframe is reserved
            rs.getString("ReservedLocationName");
            if (rs.wasNull())
                timeframe = new Timeframe(sDateTime, eDateTime, cost);
            else
                timeframe = new Timeframe(sDateTime, eDateTime, cost, true);
            
            loc.addTimeframe(timeframe);            
        }
        
        return locations;
    }
    
    /**
        Parses data on location names
    
        @throws SQLException Error parsing the result set
        @return List of location names
    */
    
    public List<String> parseLocationNames() throws SQLException
    {
        List<String> names = new ArrayList<>();
        while (rs.next())
            names.add(rs.getString(1));
        return names;
    }
    
    /**
        Parses data on reservations made at the given location
    
        @param loc The location
        @throws SQLException Error parsing the result set
        @return List of reservations
    */
    
    public List<Reservation> parseReservations(Location loc) throws SQLException
    {
        List<Reservation> reservations = new ArrayList<>();
        
        Reservable reservable;
        Reserver reserver;
        Reservation reservation;
        
        boolean approved;
        String firstName, lastName, email, phone, eventType;
        int numAttending;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        
        while (rs.next())
        {  
            // Build Reserver
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            email = rs.getString("Email");
            phone = rs.getString("Phone");
            reserver = new Reserver(firstName, lastName, email, phone);
            
            // Get Reservable from Location with matching datetimes
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            
            LocalDateTime sDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime eDateTime = LocalDateTime.of(endDate, endTime);
            
            reservable = loc.deriveReservable(
                    r -> r.startsOnDatetime(sDateTime) &&
                         r.endsOnDatetime(eDateTime));
            
            // Build Reservation
            eventType = rs.getString("EventType");
            numAttending = rs.getInt("NumberAttending");
            approved = rs.getBoolean("Approved");
            
            reservation = new Reservation(reserver, reservable, numAttending,
                    eventType, approved);
            
            reservations.add(reservation);
        }
        
        return reservations;
    }
    
    /**
        Parses data on a location's timeframes
    
        @throws SQLException Error parsing the result set
        @return List of timeframes
    */
    
    public List<Timeframe> parseTimeframes() throws SQLException
    {
        List<Timeframe> timeframes = new ArrayList<>();
        
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        BigDecimal cost;
        LocalDateTime start, end;
        
        while (rs.next())
        {
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            cost = rs.getBigDecimal("Cost");
            
            start = LocalDateTime.of(startDate, startTime);
            end = LocalDateTime.of(endDate, endTime);
            
            // Check if the location's name appears as reserved
            rs.getString("ReservedLocationName");
            
            if (rs.wasNull())
                timeframes.add(new Timeframe(start, end, cost, false));
            else
                timeframes.add(new Timeframe(start, end, cost, true));
        }
        
        return timeframes;
    }
    
    /**
        Sets the ResultSet for the parser to parse
    
        @param resultSet The ResultSet for the parser to parse
    */
    
    public void setResultSet(ResultSet resultSet)
    {
        rs = resultSet;
    }
}