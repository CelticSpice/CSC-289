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
        startDateTime = new GregorianCalendar
            (sDateTime.get(GregorianCalendar.YEAR),
             sDateTime.get(GregorianCalendar.MONTH),
             sDateTime.get(GregorianCalendar.DAY_OF_MONTH),
             sDateTime.get(GregorianCalendar.HOUR_OF_DAY),
             sDateTime.get(GregorianCalendar.MINUTE));
        
        endDateTime = new GregorianCalendar
            (eDateTime.get(GregorianCalendar.YEAR),
             eDateTime.get(GregorianCalendar.MONTH),
             eDateTime.get(GregorianCalendar.DAY_OF_MONTH),
             eDateTime.get(GregorianCalendar.HOUR_OF_DAY),
             eDateTime.get(GregorianCalendar.MINUTE));
    }
    
    /**
        CompareStartTo - Compare the timeframe's start with 
        another timeframe's start
    
        @param timeframe Timeframe to compare to
        @param dateOnly Whether to ignore hours & minutes in the comparison
        @return 0 if the starts are equal, -1 if this timeframe's start
                 is less than, or 1 otherwise
    */
    
    public int compareStartTo(Timeframe timeframe, boolean dateOnly)
    {
        if (!dateOnly)
            return startDateTime.compareTo(timeframe.startDateTime);
        else
        {
            GregorianCalendar startCopy1 = new GregorianCalendar
                (startDateTime.get(GregorianCalendar.YEAR),
                 startDateTime.get(GregorianCalendar.MONTH),
                 startDateTime.get(GregorianCalendar.DAY_OF_MONTH));
            
            GregorianCalendar startCopy2 = new GregorianCalendar
                (timeframe.startDateTime.get(GregorianCalendar.YEAR),
                 timeframe.startDateTime.get(GregorianCalendar.MONTH),
                 timeframe.startDateTime.get(GregorianCalendar.DAY_OF_MONTH));
            
            return startCopy1.compareTo(startCopy2);
        }
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