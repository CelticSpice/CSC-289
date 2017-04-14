/**
    A parser for ResultSet objects
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
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
    
    public boolean isResultSetEmpty() throws SQLException
    {
        return !rs.isBeforeFirst();
    }
    
    /**
        Parses an ID
        
        @throws SQLException Error parsing the result set
        @return ID
    */
    
    public int parseID() throws SQLException
    {
        rs.next();
        return rs.getInt(1);
    }
    
    /**
        Parses locations
    
        @throws SQLException Error parsing the result set
        @return List of locations
    */
    
    public List<ReservableLocation> parseLocations() throws SQLException
    {
        List<ReservableLocation> locations = new ArrayList<>();
        HashMap<Integer, ReservableLocation> locationMap = new HashMap<>();
        
        int capacity, locationID, timeframeID;
        String name;
        ReservableLocation loc;
        ReservableTimeframe timeframe;
        BigDecimal cost;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        LocalDateTime sDateTime, eDateTime;
        
        while (rs.next())
        {
            // Build location
            locationID = rs.getInt("LocationID");
            name = rs.getString("LocationName");
            capacity = rs.getInt("Capacity");
            
            if (locationMap.containsKey(locationID))
                loc = locationMap.get(locationID);
            else
            {
                loc = new ReservableLocation(name, capacity, locationID);
                locationMap.put(locationID, loc);
                locations.add(loc);
            }
            
            // Build timeframe
            timeframeID = rs.getInt("TimeframeID");
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            cost = rs.getBigDecimal("Cost");
            
            sDateTime = LocalDateTime.of(startDate, startTime);
            eDateTime = LocalDateTime.of(endDate, endTime);
            
            // Check if timeframe is reserved
            rs.getInt("ReservedTimeframeID");
            
            if (rs.wasNull())
                timeframe = new ReservableTimeframe(sDateTime, eDateTime, cost,
                        timeframeID);
            else
                timeframe = new ReservableTimeframe(sDateTime, eDateTime, cost,
                        true, timeframeID);
            
            loc.addTimeframe(timeframe);            
        }
        
        return locations;
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
        Reservation reservation;
        Reserver reserver;
        
        int numAttending, reserverID, timeframeID;
        BigDecimal cost;
        boolean reviewed;
        String firstName, lastName, email, phone, eventType;
        ReservableTimeframe timeframe;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        
        while (rs.next())
        {  
            // Build Reserver
            reserverID = rs.getInt("ReserverID");
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            email = rs.getString("Email");
            phone = rs.getString("Phone");
            reserver = new Reserver(firstName, lastName, email, phone,
                    reserverID);
            
            // Build ReservableTimeframe
            timeframeID = rs.getInt("TimeframeID");
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            cost = rs.getBigDecimal("Cost");
            
            LocalDateTime sDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime eDateTime = LocalDateTime.of(endDate, endTime);
            
            timeframe = new ReservableTimeframe(sDateTime, eDateTime, cost,
                    true, timeframeID);
            
            // Build Reservable
            reservable = new Reservable(((ReservableLocation)loc), timeframe);            
            
            // Build Reservation
            eventType = rs.getString("EventType");
            numAttending = rs.getInt("NumberAttending");
            reviewed = rs.getBoolean("Reviewed");
            
            reservation = new Reservation(reserver, reservable, numAttending,
                    eventType, reviewed);
            
            reservations.add(reservation);
        }
        
        return reservations;
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