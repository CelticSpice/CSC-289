/**
    A timeframe
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
        // We ignore milliseconds & seconds
        sDateTime.clear(GregorianCalendar.MILLISECOND);
        sDateTime.clear(GregorianCalendar.SECOND);
        eDateTime.clear(GregorianCalendar.MILLISECOND);
        eDateTime.clear(GregorianCalendar.SECOND);
        
        startDateTime = sDateTime;
        endDateTime = eDateTime;
    }
    
    /**
        StartsOn - Return whether the timeframe starts on the given date
    
        @param date Date to check if the timeframe starts on
        @param dateOnly Whether to ignore hours & minutes in the computation
        @return Whether the timeframe starts on the given date
    */
    
    public boolean startsOn(GregorianCalendar date, boolean dateOnly)
    {
        date.clear(GregorianCalendar.MILLISECOND);
        date.clear(GregorianCalendar.SECOND);
        
        if (dateOnly)
        {
            date.set(GregorianCalendar.HOUR_OF_DAY,
                     startDateTime.get(GregorianCalendar.HOUR_OF_DAY));
            date.set(GregorianCalendar.MINUTE,
                     startDateTime.get(GregorianCalendar.MINUTE));
        }
        
        if (startDateTime.compareTo(date) == 0)
            return true;
        else
            return false;
    }
    
    /**
        GetEndDateString - Return the ending date of the timeframe as a string
        in the format mm/dd/yyyy
    
        @return The ending date as a string
    */
    
    public String getEndDateString()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        fmt.setCalendar(endDateTime);
        return fmt.format(endDateTime.getTime());
    }
    
    /**
        GetEndTimeString - Return the ending time of the timeframe as a string
        in the format hh:mm
    
        @return The ending time as a string
    */
    
    public String getEndTimeString()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        fmt.setCalendar(endDateTime);
        return fmt.format(endDateTime.getTime());
    }
    
    /**
        GetStartDateString - Return the starting date of the timeframe as a
        string in the format mm/dd/yyyy
    
        @return The starting date as a string
    */
    
    public String getStartDateString()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        fmt.setCalendar(startDateTime);
        return fmt.format(startDateTime.getTime());
    }
    
    /**
        GetStartTimeString - Return the starting time of the timeframe as a
        string in the format hh:mm
    
        @return The starting time as a string
    */
    
    public String getStartTimeString()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        fmt.setCalendar(startDateTime);
        return fmt.format(startDateTime.getTime());
    }
    
    /**
        ToString - Return a string representation of the object
    
        @return String representation of the object
    */
    
    @Override
    public String toString()
    {
        return getStartTimeString() + "-" + getStartDateString() +
               ", " + getEndTimeString() + "-" + getEndDateString();
    }
}