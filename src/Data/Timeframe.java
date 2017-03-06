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
    public static final int START = 0;
    public static final int END = 1;
    
    public static final int TIME = 0;
    public static final int DATE = 1;
    
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
        ConflictsWith - Return whether the timeframe conflicts with another
        timeframe
    
        @param timeframe Timeframe to check for conflicts with
        @return Whether there is a conflict
    */
    
    public boolean conflictsWith(Timeframe timeframe)
    {
        long thisStartDateTimeMili = startDateTime.getTimeInMillis();
        long thisEndDateTimeMili = endDateTime.getTimeInMillis();
        long otherStartDateTimeMili = timeframe.startDateTime.getTimeInMillis();
        long otherEndDateTimeMili = timeframe.endDateTime.getTimeInMillis();
        
        return (otherStartDateTimeMili >= thisStartDateTimeMili &&
                otherStartDateTimeMili <= thisEndDateTimeMili)  ||
               (otherEndDateTimeMili >= thisStartDateTimeMili   &&
                otherEndDateTimeMili <= thisEndDateTimeMili)
                
                                                                ||
                
               (thisStartDateTimeMili >= otherStartDateTimeMili &&
                thisStartDateTimeMili <= otherEndDateTimeMili)  ||
               (thisEndDateTimeMili >= otherStartDateTimeMili   &&
                thisEndDateTimeMili <= otherEndDateTimeMili);
    }
    
    /**
        GetString - Return the starting or ending date or time of the timeframe
        as a string in the formats of mm/dd/yyyy or hh:mm
    
        @param timeDate Whether to get the time or date as a string
        @param startEnd Whether to get the starting or ending time or date as a
                        string
        @return The starting or ending time or date as a string
    */
    
    public String getString(int timeDate, int startEnd)
    {
        if (timeDate == TIME)
        {
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
            fmt.setCalendar((startEnd == START) ? startDateTime : endDateTime);
            return fmt.format((startEnd == START) ? startDateTime.getTime() :
                                                    endDateTime.getTime());
        }
        else
        {
            SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
            fmt.setCalendar((startEnd == START) ? startDateTime : endDateTime);
            return fmt.format((startEnd == START) ? startDateTime.getTime() :
                                                    endDateTime.getTime());
        }
    }
    
    /**
        StartsEndsOn - Return whether the timeframe starts or ends on the
        given date
    
        @param date Date to check if the timeframe starts or ends on
        @param startEnd Whether to check if the timeframe starts or ends on
        @param dateOnly Whether to ignore hours & minutes in the computation
        @return isOn Whether the timeframe starts or ends on the given date
    */
    
    public boolean startsEndsOn(GregorianCalendar date, int startEnd,
                                boolean dateOnly)
    {
        boolean isOn;
        int dateMilliseconds = date.get(GregorianCalendar.MILLISECOND);
        int dateSeconds = date.get(GregorianCalendar.SECOND);
        
        date.clear(GregorianCalendar.MILLISECOND);
        date.clear(GregorianCalendar.SECOND);
        
        if (dateOnly)
        {
            int dateHour = date.get(GregorianCalendar.HOUR_OF_DAY);
            int dateMinute = date.get(GregorianCalendar.MINUTE);
            
            if (startEnd == START)
            {
                date.set(GregorianCalendar.HOUR_OF_DAY,
                     startDateTime.get(GregorianCalendar.HOUR_OF_DAY));
                date.set(GregorianCalendar.MINUTE,
                     startDateTime.get(GregorianCalendar.MINUTE));
                
                isOn = startDateTime.compareTo(date) == 0;
            }
            else
            {
                date.set(GregorianCalendar.HOUR_OF_DAY,
                     endDateTime.get(GregorianCalendar.HOUR_OF_DAY));
                date.set(GregorianCalendar.MINUTE,
                     endDateTime.get(GregorianCalendar.MINUTE));
            
                isOn = endDateTime.compareTo(date) == 0;
            }
            
            date.set(GregorianCalendar.HOUR_OF_DAY, dateHour);
            date.set(GregorianCalendar.MINUTE, dateMinute);
        }
        else
            isOn = (startEnd == START) ? startDateTime.compareTo(date) == 0 :
                                         endDateTime.compareTo(date) == 0;
        
        date.set(GregorianCalendar.MILLISECOND, dateMilliseconds);
        date.set(GregorianCalendar.SECOND, dateSeconds);
        
        return isOn;
    }
    
    /**
        ToString - Return a string representation of the object
    
        @return String representation of the object
    */
    
    @Override
    public String toString()
    {
        return getString(TIME, START) + "-" + getString(DATE, START)    +
               ", " + getString(TIME, END) + "-" + getString(DATE, END);
    }
}
