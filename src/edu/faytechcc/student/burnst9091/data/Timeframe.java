/**
    A timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Timeframe
{
    // Fields
    private BigDecimal cost;
    private boolean reserved;
    private int id;
    private LocalDateTime startDateTime, endDateTime;
    
    /**
        Constructs a new Timeframe initialized with the given starting & ending
        datetimes
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @throws IllegalArgumentException Starting datetime after or equal to
                                         ending datetime
    */
    
    public Timeframe(LocalDateTime sDateTime, LocalDateTime eDateTime)
            throws IllegalArgumentException
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        
        // Check that starting datetime before ending date time
        if (!(startDateTime.compareTo(endDateTime) < 0))
            throw new IllegalArgumentException
                ("End datetime before or equal to start datetime");
        
        id = -1;
        cost = new BigDecimal(0);
        reserved = false;
    }
    
    /**
        Constructs a new Timeframe initialized with the given starting & ending
        datetimes, & the cost to reserve it
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @throws IllegalArgumentException Starting datetime after or equal to
                                         ending datetime
    */
    
    public Timeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            BigDecimal c) throws IllegalArgumentException
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        
        // Check that starting datetime before ending date time
        if (!(startDateTime.compareTo(endDateTime) < 0))
            throw new IllegalArgumentException
                ("End datetime before or equal to start datetime");
        
        id = -1;
        cost = c;
        reserved = false;
    }
    
    /**
        Constructs a new Timeframe initialized with the given starting & ending
        datetimes, the cost to reserve it, & the ID
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @param id The timeframe ID
        @throws IllegalArgumentException Starting datetime after or equal to
                                         ending datetime or the cost is less
                                         than $0.00
    */
    
    public Timeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            BigDecimal c, int id)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        
        // Check that starting datetime before ending date time
        if (!(startDateTime.compareTo(endDateTime) < 0))
            throw new IllegalArgumentException
                ("End datetime before or equal to start datetime");
        
        // Check that cost is greater than or equal to $0.00
        if (!(c.compareTo(new BigDecimal(0.00)) >= 0))
            throw new IllegalArgumentException("Cost less than $0.00");
        
        this.id = id;
        cost = c;
        reserved = false;
    }
    
    /**
        Constructs a new Timeframe initialized with the given starting & ending
        datetimes, the cost to reserve it, whether the timeframe is reserved, &
        ID
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @param isRsrvd If the timeframe is reserved
        @param id The timeframe ID
        @throws IllegalArgumentException Starting datetime after or equal to
                                         ending datetime or the cost is less
                                         than $0.00
    */
    
    public Timeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            BigDecimal c, boolean isRsrvd, int id)
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        
        // Check that starting datetime before ending date time
        if (!(startDateTime.compareTo(endDateTime) < 0))
            throw new IllegalArgumentException
                ("End datetime before or equal to start datetime");
        
        // Check that cost is greater than or equal to $0.00
        if (!(c.compareTo(new BigDecimal(0.00)) >= 0))
            throw new IllegalArgumentException("Cost less than $0.00");
        
        this.id = id;
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
    
        @param timeframe Timeframe to check for consistency with
        @return Whether the timeframe consists of the given timeframe
    */
    
    public boolean consistsOf(Timeframe timeframe)
    {
        return (timeframe.consistsOf(startDateTime) ||
                timeframe.consistsOf(endDateTime))  ||
               (consistsOf(timeframe.startDateTime) ||
                consistsOf(timeframe.endDateTime));
    }
    
    /**
        ConsistsOf - Return whether the timeframe consists of the given
        datetime
    
        @param dateTime Datetime to determine if the timeframe consists of
        @return Whether the timeframe consists of the given datetime
    */
    
    public boolean consistsOf(LocalDateTime dateTime)
    {
        // We ignore nanoseconds & seconds
        dateTime = dateTime.withNano(0).withSecond(0);
        
        return (dateTime.compareTo(startDateTime) >= 0 &&
                dateTime.compareTo(endDateTime) <= 0);
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
        Returns whether the timeframe ends on the given datetime
    
        @param datetime Datetime to check if timeframe ends on
        @return If timeframe ends on given datetime
    */
    
    public boolean endsOnDatetime(LocalDateTime datetime)
    {
        return endDateTime.equals(datetime);
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
        Returns the timeframe's ID
    
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
        Sets the timeframe's ID
    
        @param id The timeframe's ID
    */
    
    public void setID(int id)
    {
        this.id = id;
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
        Returns whether the timeframe starts on the given datetime
    
        @param datetime Datetime to check if timeframe starts on
        @return If timeframe starts on given datetime
    */
    
    public boolean startsOnDatetime(LocalDateTime datetime)
    {
        return startDateTime.equals(datetime);
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
    
        @return str String representation of the object
    */
    
    @Override
    public String toString()
    {
        DateTimeFormatter fmt = DateTimeFormatter.
                ofPattern("yyyy-MM-dd,HH:mm");
        
        return startDateTime.format(fmt) + " - " +
               endDateTime.format(fmt);
    }
}