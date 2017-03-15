/**
    A result set parser
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
            names.add(rs.getString(0));
        return names.toArray(new String[names.size()]);
    }
    
    /**
        ParseTimeframes - Parse a result set containing timeframes
    
        @param rs The result set to parse
        @param reserved Whether the parser should mark timeframes as reserved
        @throws SQLException Error parsing the result set
        @return timeframes A list of timeframes at the given location
    */
    
    public static TimeframeList parseTimeframes(ResultSet rs, boolean reserved)
            throws SQLException
    {
        TimeframeList timeframes = new TimeframeList();
        Timeframe timeframe;
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        BigDecimal cost;
        ZonedDateTime start, end;
        
        while (rs.next())
        {
            startDate = rs.getDate("StartDate").toLocalDate();
            startTime = rs.getTime("StartTime").toLocalTime();
            endDate = rs.getDate("EndDate").toLocalDate();
            endTime = rs.getTime("EndTime").toLocalTime();
            cost = rs.getBigDecimal("Cost");
            
            start = ZonedDateTime
                    .of(startDate, startTime, ZoneId.systemDefault());
            
            end = ZonedDateTime
                    .of(endDate, endTime, ZoneId.systemDefault());
            
            if (reserved)
                timeframe = new Timeframe(start, end, cost, true);
            else
                timeframe = new Timeframe(start, end, cost);
            
            timeframes.add(timeframe);
        }
        
        return timeframes;
    }
    
    /**
        ParseReservable - Parse a result set containing a reservable
    
        @param rs The result set to parse
        @throws SQLException There was an error working with the result set
        @return The reservable defined by the result set; else, if result set
                is empty, null
    */
    
    public static Reservable parseReservable(ResultSet rs) throws SQLException
    {
        if (!isEmpty(rs))
        {
            Location location = new Location(rs.getString("LocationName"),
                                             rs.getInt("Capacity"));
            
            ZonedDateTime startDateTime = ZonedDateTime
                    .of(rs.getDate("StartDate").toLocalDate(),
                        rs.getTime("StartTime").toLocalTime(),
                        ZoneId.systemDefault());
            
            ZonedDateTime endDateTime = ZonedDateTime
                    .of(rs.getDate("EndDate").toLocalDate(),
                        rs.getTime("EndTime").toLocalTime(),
                        ZoneId.systemDefault());
            
            BigDecimal cost = rs.getBigDecimal("Cost");
            
            Timeframe timeframe = new Timeframe
                    (startDateTime, endDateTime, cost);
            
            return new Reservable(location, timeframe);
        }
        else
            return null;
    }
    
    /**
        ParseReservableTimeframes - Parse a result set containing reservable
        timeframes
    
        @param rs The result set to parse
        @throws SQLException There was an error working with the result set
        @return timeframes The reservable timeframes defined by the result set
    */
    
    public static TimeframeList parseReservableTimeframes
        (ResultSet rs) throws SQLException
    {
        TimeframeList timeframes = new TimeframeList();
        
        if (!isEmpty(rs))
        {
            ZonedDateTime startDateTime, endDateTime;
            BigDecimal cost;
            
            while (rs.next())
            {
                startDateTime = ZonedDateTime
                        .of(rs.getDate("StartDate").toLocalDate(),
                            rs.getTime("StartTime").toLocalTime(),
                            ZoneId.systemDefault());
                
                endDateTime = ZonedDateTime
                        .of(rs.getDate("EndDate").toLocalDate(),
                            rs.getTime("EndTime").toLocalTime(),
                            ZoneId.systemDefault());
                
                cost = rs.getBigDecimal("Cost");
                
                timeframes.add(new Timeframe(startDateTime,
                                                       endDateTime, cost));     
            }
        }
        
        return timeframes;
    }
}