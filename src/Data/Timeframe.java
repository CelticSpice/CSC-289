/**
    A timeframe for which a location can be reserved for
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Timeframe
{
    // Fields
    private GregorianCalendar startDateTime, endDateTime;
    
    /**
        Constructor - Accepts the starting & ending dates & times
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
    */
    
    public Timeframe(GregorianCalendar sDateTime, GregorianCalendar eDateTime)
    {
        startDateTime = sDateTime;
        endDateTime = eDateTime;
    }
    
    /**
        GetEndDate - Return the ending date of the timeframe
    
        @return The ending date
    */
    
    public String getEndDate()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("MM-dd-yyyy");
        fmt.setCalendar(endDateTime);
        return fmt.format(endDateTime.getTime());
    }
    
    /**
        GetStartDate - Return the starting date of the timeframe
    
        @return The starting date
    */
    
    public String getStartDate()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("MM-dd-yyyy");
        fmt.setCalendar(startDateTime);
        return fmt.format(startDateTime.getTime());
    }
    
    /**
        GetEndTime - Return the ending time of the timeframe
    
        @return The ending time
    */
    
    public String getEndTime()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("HH-mm");
        fmt.setCalendar(endDateTime);
        return fmt.format(endDateTime.getTime());
    }
    
    /**
        GetStartTime - Return the starting time of the timeframe
    
        @return The starting time
    */
    
    public String getStartTime()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("HH-mm");
        fmt.setCalendar(startDateTime);
        return fmt.format(startDateTime.getTime());
    }
}