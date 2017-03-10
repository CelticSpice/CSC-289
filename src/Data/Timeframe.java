/**
    A timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Timeframe
{
    // Fields
    private int id;
    private ZonedDateTime startDateTime, endDateTime;
    
    /**
        Constructor - Accepts the starting & ending dates & times
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
    */
    
    public Timeframe(ZonedDateTime sDateTime, ZonedDateTime eDateTime)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
    }
    
    /**
        Constructor - Accepts the starting & ending dates & times and the
        timeframe's ID
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param i The timeframe's ID
    */
    
    public Timeframe(ZonedDateTime sDateTime, ZonedDateTime eDateTime, int i)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        id = i;
    }
    
    /**
        ConsistsOf - Return whether the timeframe consists of another
        timeframe
    
        @param t Timeframe to check for consistency with
        @return Whether the timeframe consists of the given timeframe
    */
    
    public boolean consistsOf(Timeframe t)
    {
        long thisSDateTimeMilli = startDateTime.toInstant().toEpochMilli();
        long thisEDateTimeMilli = endDateTime.toInstant().toEpochMilli();
        long otherSDateTimeMilli = t.startDateTime.toInstant().toEpochMilli();
        long otherEDateTimeMilli = t.endDateTime.toInstant().toEpochMilli();
        
        return (otherSDateTimeMilli >= thisSDateTimeMilli   &&
                otherSDateTimeMilli <= thisEDateTimeMilli)  ||
               (otherEDateTimeMilli >= thisSDateTimeMilli   &&
                otherEDateTimeMilli <= thisEDateTimeMilli)
                
                                                            ||
                
               (thisSDateTimeMilli >= otherSDateTimeMilli   &&
                thisSDateTimeMilli <= otherEDateTimeMilli)  ||
               (thisEDateTimeMilli >= otherSDateTimeMilli   &&
                thisEDateTimeMilli <= otherEDateTimeMilli);
    }
    
    /**
        ConsistsOf - Return whether the timeframe consists of the given
        date & time
    
        @param dateTime Date & time to determine if the timeframe consists of
        @return Whether the timeframe consists of the given date & time
    */
    
    public boolean consistsOf(LocalDateTime dateTime)
    {
        Timeframe t = new Timeframe(dateTime.atZone(startDateTime.getZone()),
                                    dateTime.atZone(startDateTime.getZone()));
        return consistsOf(t);
    }
    
    /**
        EndsOnDate - Return whether the timeframe ends on the given date
    
        @param date Date to check if the timeframe ends on
        @return Whether the timeframe ends on the given date
    */
    
    public boolean endsOnDate(LocalDate date)
    {
        return endDateTime.toLocalDate().equals(date);
    }
    
    /**
        EndsOnTime - Return whether the timeframe ends on the given time
    
        @param time Time to check if the timeframe ends on
        @return Whether the timeframe ends on the given time
    */
    
    public boolean endsOnTime(LocalTime time)
    {
        // We ignore nanoseconds & seconds
        time = time.withNano(0).withSecond(0);
        
        return endDateTime.toLocalTime().equals(time);
    }
    
    /**
        GetEndDate - Return the ending date of the timeframe
    
        @return The ending date of the timeframe
    */
    
    public LocalDate getEndDate()
    {
        return endDateTime.toLocalDate();
    }
    
    /**
        GetEndTime - Return the ending time of the timeframe
    
        @return The ending time of the timeframe
    */
    
    public LocalTime getEndTime()
    {
        return endDateTime.toLocalTime();
    }
    
    /**
        GetEndDateTimeString - Return the ending date & time as a string in the
        format specified by the given formatter
    
        @param format Format to return the starting date & time as a string in
        @return String representing the starting date & time in the specified
                format
    */
    
    public String getEndDateTimeString(DateTimeFormatter format)
    {
        return endDateTime.format(format);
    }
    
    /**
        GetID - Return the timeframe's ID
    
        @return The timeframe's ID
    */
    
    public int getID()
    {
        return id;
    }
    
    /**
        GetStartDate - Return the starting date of the timeframe
    
        @return The starting date of the timeframe
    */
    
    public LocalDate getStartDate()
    {
        return startDateTime.toLocalDate();
    }
    
    /**
        GetStartTime - Return the starting time of the timeframe
    
        @return The starting time of the timeframe
    */
    
    public LocalTime getStartTime()
    {
        return startDateTime.toLocalTime();
    }
    
    /**
        GetStartDateTimeString - Return the starting date & time as a string
        in the format specified by the given formatter
    
        @param format Format to return the starting date & time as a string in
        @return String representing the starting date & time in the specified
                format
    */
    
    public String getStartDateTimeString(DateTimeFormatter format)
    {
        return startDateTime.format(format);
    }
    
    /**
        StartsOnDate - Return whether the timeframe starts on the given date
    
        @param date Date to check if the timeframe starts on
        @return Whether the timeframe starts on the given date
    */
    
    public boolean startsOnDate(LocalDate date)
    {
        return startDateTime.toLocalDate().equals(date);
    }
    
    /**
        StartsOnTime - Return whether the timeframe starts on the given time
    
        @param time Time to check if the timeframe starts on
        @return Whether the timeframe starts on the given time
    */
    
    public boolean startsOnTime(LocalTime time)
    {
        // We ignore nanoseconds & seconds
        time = time.withNano(0).withSecond(0);
        
        return startDateTime.toLocalTime().equals(time);
    }
    
    /**
        ToString - Return a string representation of the object
    
        @return String representation of the object
    */
    
    @Override
    public String toString()
    {
        DateTimeFormatter fmt = DateTimeFormatter.
                ofPattern("yyyy-MM-dd, HH:mm");
        
        return startDateTime.format(fmt) + " : " + endDateTime.format(fmt);
    }
}