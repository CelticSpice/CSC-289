/**
    A timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timeframe
{
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
    }
    
    /**
        Constructs a new Timeframe initialized with the given starting & ending
        datetimes, & the ID
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param id The timeframe's ID
        @throws IllegalArgumentException Starting datetime after or equal to
                                         ending datetime
    */
    
    public Timeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            int id) throws IllegalArgumentException
    {
        // We ignore nanoseconds & seconds
        startDateTime = sDateTime.withNano(0).withSecond(0);
        endDateTime = eDateTime.withNano(0).withSecond(0);
        
        // Check that starting datetime before ending date time
        if (!(startDateTime.compareTo(endDateTime) < 0))
            throw new IllegalArgumentException
                ("End datetime before or equal to start datetime");
        
        this.id = id;
    }
    
    /**
        Returns whether the timeframe consists of another timeframe
    
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
        Returns whether the timeframe consists of the given datetime
    
        @param datetime Datetime to determine if the timeframe consists of
        @return Whether the timeframe consists of the given datetime
    */
    
    public boolean consistsOf(LocalDateTime datetime)
    {
        // We ignore nanoseconds & seconds
        datetime = datetime.withNano(0).withSecond(0);
        
        return (datetime.compareTo(startDateTime) >= 0 &&
                datetime.compareTo(endDateTime) <= 0);
    }
    
    /**
        Returns whether the timeframe ends on the given datetime
    
        @param datetime Datetime to check if timeframe ends on
        @return If timeframe ends on given datetime
    */
    
    public boolean endsOnDatetime(LocalDateTime datetime)
    {
        // We ignore nanoseconds & seconds
        datetime = datetime.withNano(0).withSecond(0);
        
        return endDateTime.equals(datetime);
    }
    
    /**
        Returns the ending date of the timeframe
    
        @return The ending date of the timeframe
    */
    
    public LocalDate getEndDate()
    {
        return endDateTime.toLocalDate();
    }
    
    /**
        Returns the ending time of the timeframe
    
        @return The ending time of the timeframe
    */
    
    public LocalTime getEndTime()
    {
        return endDateTime.toLocalTime();
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
        Returns the starting date of the timeframe
    
        @return The starting date of the timeframe
    */
    
    public LocalDate getStartDate()
    {
        return startDateTime.toLocalDate();
    }
    
    /**
        Returns the starting time of the timeframe
    
        @return The starting time of the timeframe
    */
    
    public LocalTime getStartTime()
    {
        return startDateTime.toLocalTime();
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
        Returns whether the timeframe starts on the given datetime
    
        @param datetime Datetime to check if timeframe starts on
        @return If timeframe starts on given datetime
    */
    
    public boolean startsOnDatetime(LocalDateTime datetime)
    {
        // We ignore nanoseconds & seconds
        datetime = datetime.withNano(0).withSecond(0);
        
        return startDateTime.equals(datetime);
    }
    
    /**
        Returns a string representation of the object
    
        @return A string representation of the object
    */
    
    @Override
    public String toString()
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
        
        return startDateTime.format(fmt) + " - " + endDateTime.format(fmt);
    }
}