/**
    A timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Timeframe
{
    // Fields
    private BigDecimal cost;
    private boolean reserved;
    private ZonedDateTime startDateTime, endDateTime;
    
    /**
        Constructor - Accepts the starting & ending dates & times of the
        timeframe
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
    */
    
    public Timeframe(ZonedDateTime sDateTime, ZonedDateTime eDateTime)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        cost = new BigDecimal(0);
        reserved = false;
    }
    
    /**
        Constructor - Accepts the starting & ending dates & times of the
        timeframe, and the cost to reserve it
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
    */
    
    public Timeframe(ZonedDateTime sDateTime, ZonedDateTime eDateTime,
            BigDecimal c)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        cost = c;
        reserved = false;
    }
    
    /**
        Constructor - Accepts the starting & ending dates & times of the
        timeframe, the cost to reserve it, and whether it is reserved
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @param isRsrvd If the timeframe is reserved
    */
    
    public Timeframe(ZonedDateTime sDateTime, ZonedDateTime eDateTime,
            BigDecimal c, boolean isRsrvd)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        cost = c;
        reserved = isRsrvd;
    }
    
    /**
        CancelReserve - Cancel the reservation of the timeframe
    */
    
    public void cancelReserve()
    {
        reserved = false;
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
        GetCost - Return the cost to reserve the timeframe
    
        @return The cost to reserve the timeframe
    */
    
    public BigDecimal getCost()
    {
        return cost;
    }
    
    /**
        GetCostString - Return the cost to reserve the timeframe as a string
    
        @return The cost to reserve the timeframe as a string
    */
    
    public String getCostString()
    {
        BigDecimal displayVal = cost.setScale(2, RoundingMode.HALF_EVEN);
        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
        return fmt.format(displayVal.doubleValue());
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
        IsReserved - Return if the timeframe is reserved
    
        @return Whether the timeframe is reserved
    */
    
    public boolean isReserved()
    {
        return reserved;
    }
    
    /**
        Reserve - Reserve the timeframe
    */
    
    public void reserve()
    {
        reserved = true;
    }
    
    /**
        SetCost - Set the cost to reserve the timeframe
    
        @param c The cost to reserve the timeframe
    */
    
    public void setCost(BigDecimal c)
    {
        cost = c;
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
                ofPattern("yyyy-MM-dd:HH-mm");
        
        return startDateTime.format(fmt) + ", " +
                endDateTime.format(fmt) + " : " + cost +
                " : " + ((reserved) ? "Reserved" : "Not Reserved");
    }
}