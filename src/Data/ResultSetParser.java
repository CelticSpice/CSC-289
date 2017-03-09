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

public class ResultSetParser
{  
    /**
        IsEmpty - Return whether a result set is empty
    
        @param rs Result set to determine if empty
        @throws SQLException Error in working with the result set
        @return Whether the result set is empty
    */
    
    public static boolean isEmpty(ResultSet rs) throws SQLException
    {
        return !rs.isBeforeFirst();
    }
    
    /**
        ParseLocationNames - Parse a result set containing only location names
    
        @param rs The result set to parse
        @throws SQLException There was an error working with the result set
        @return names Every location name included in the result set
    */
    
    public static String[] parseLocationNames(ResultSet rs) throws SQLException
    {
        ArrayList<String> names = new ArrayList<>();
        if (!isEmpty(rs))
            while (rs.next())
                names.add(rs.getString(1));
        return names.toArray(new String[names.size()]);
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
            
            ReservableTimeframe timeframe = new ReservableTimeframe
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
    
    public static ReservableTimeframeList parseReservableTimeframes
        (ResultSet rs) throws SQLException
    {
        ReservableTimeframeList timeframes = new ReservableTimeframeList();
        
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
                
                timeframes.add(new ReservableTimeframe(startDateTime,
                                                       endDateTime, cost));     
            }
        }
        
        return timeframes;
    }
}