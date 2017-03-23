/**
    A result set parser
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.ContactInfo;
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
    /**
        IsEmpty - Return whether a result set is empty
    
        @param rs The result set to parse
        @throws SQLException Error parsing the result set
        @return Whether the result set is empty
    */
    
    public static boolean isEmpty(ResultSet rs) throws SQLException
    {
        return !rs.isBeforeFirst();
    }
    
    /**
        Parse a result set containing a location capacity
    
        @param rs The result set to parse
        @throws SQLException Error parsing the result set
        @return The location capacity parsed from the result set
    */
    
    public static int parseLocationCapacity(ResultSet rs) throws SQLException
    {
        rs.next();
        return rs.getInt(1);
    }
    
    /**
        ParseLocationNames - Parse a result set containing the names of
        locations
    
        @param rs The result set to parse
        @throws SQLException Error parsing the result set
        @return The location names parsed from the result set
    */
    
    public static String[] parseLocationNames(ResultSet rs) throws SQLException
    {
        List<String> names = new ArrayList<>();
        while (rs.next())
            names.add(rs.getString(1));
        return names.toArray(new String[names.size()]);
    }
    
    /**
        Parse reservations from a result set
    
        @param rs The result set
        @throws SQLException Error parsing the result set
        @return List of reservations
    */
    
    public static List<Reservation> parseReservations(ResultSet rs)
            throws SQLException
    {
        List<Reservation> reservations = new ArrayList<>();
        HashMap<String, Location> locations = new HashMap<>();
        HashMap<ContactInfo, Reserver> reservers = new HashMap<>();
        
        Location loc;
        Timeframe timeframe;
        Reservable reservable;
        Reserver reserver;
        Reservation reservation;
        
        boolean approved;
        String locName, firstName, lastName, email, phone, eventType;
        int capacity, numAttending;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        BigDecimal cost;
        LocalDateTime sDateTime, eDateTime;
        ContactInfo contact;
        
        while (rs.next())
        {
            locName = rs.getString("LocationName");
            capacity = rs.getInt("Capacity");
            
            if (!locations.containsKey(locName))
            {
                loc = new Location(locName, capacity);
                locations.put(locName, loc);
            }
            else
                loc = locations.get(locName);
            
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            cost = rs.getBigDecimal("Cost");
            
            sDateTime = LocalDateTime.of(startDate, startTime);
            eDateTime = LocalDateTime.of(endDate, endTime);
            
            timeframe = new Timeframe(sDateTime, eDateTime, cost, true);
            loc.addTimeframe(timeframe);
            
            reservable = new Reservable(loc, timeframe);
            
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            email = rs.getString("Email");
            phone = rs.getString("Phone");
            contact = new ContactInfo(firstName, lastName, email, phone);
            
            if (!reservers.containsKey(contact))
            {
                reserver = new Reserver(contact);
                reservers.put(contact, reserver);
            }
            else
                reserver = reservers.get(contact);
            
            eventType = rs.getString("EventType");
            numAttending = rs.getInt("NumberAttending");
            approved = rs.getBoolean("Approved");
            
            reservation = new Reservation(reserver, reservable, numAttending,
                eventType, approved);
            
            reserver.addReservation(reservation);
            
            reservations.add(reservation);
        }
        
        return reservations;
    }
    
    /**
        ParseTimeframes - Parse a result set containing timeframes
    
        @param rs The result set to parse
        @param reserved Whether the parser should mark timeframes as reserved
        @throws SQLException Error parsing the result set
        @return timeframes A list of timeframes
    */
    
    public static List<Timeframe> parseTimeframes(ResultSet rs,
            boolean reserved) throws SQLException
    {
        List<Timeframe> timeframes = new ArrayList<>();
        Timeframe timeframe;
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
            
            if (reserved)
                timeframe = new Timeframe(start, end, cost, true);
            else
                timeframe = new Timeframe(start, end, cost);
            
            timeframes.add(timeframe);
        }
        
        return timeframes;
    }
    
    /**
     * ParseTimeframes - Parse a result set containing timeframes
     * 
     * @param rs The result set to parse
     * @return A list of timeframes
     * @throws SQLException Error parsing the result set
     */
    public static List<Timeframe> parseTimeframes(ResultSet rs)
            throws SQLException
    {
        List<Timeframe> timeframes = new ArrayList<>();
        Timeframe timeframe;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        LocalDateTime start, end;
        
        while (rs.next())
        {
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            
            start = LocalDateTime.of(startDate, startTime);
            end = LocalDateTime.of(endDate, endTime);
            
            timeframe = new Timeframe(start, end);
            
            timeframes.add(timeframe);
        }
        
        return timeframes;
    }
}