/**
    A result set parser
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.TimeframeList;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        Parse a result set containing a location capacity
    
        @param rs The result set to parse
        @throws SQLException Error parsing the result set
        @return The location capacity parsed from the result set
    */
    
    public static int parseLocationCapacity(ResultSet rs) throws SQLException
    {
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
            names.add(rs.getString(0));
        return names.toArray(new String[names.size()]);
    }
    
    /**
        ParseTimeframes - Parse a result set containing timeframes
    
        @param rs The result set to parse
        @param reserved Whether the parser should mark timeframes as reserved
        @throws SQLException Error parsing the result set
        @return timeframes A list of timeframes
    */
    
    public static TimeframeList parseTimeframes(ResultSet rs, boolean reserved)
            throws SQLException
    {
        TimeframeList timeframes = new TimeframeList();
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
    public static TimeframeList parseTimeframes(ResultSet rs)
            throws SQLException
    {
        TimeframeList timeframes = new TimeframeList();
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