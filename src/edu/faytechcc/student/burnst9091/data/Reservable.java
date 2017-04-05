/**
    A reservable, encapsulating a location & timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reservable
{
    // Fields
    private Location location;
    private Timeframe timeframe;
    
    /**
        Constructs a new Reservable with the given location & timeframe
    
        @param loc The location
        @param time The timeframe
    */
    
    public Reservable(Location loc, Timeframe time)
    {
        location = loc;
        timeframe = time;
    }
    
    /**
        Cancels the reservation of the reservable
    */
    
    public void cancelReservation()
    {
        timeframe.cancelReserve();
    }
    
    /**
        Returns the capacity of the Reservable
    
        @return The capacity of the Reservable
    */
    
    public int getCapacity()
    {
        return location.getCapacity();
    }
    
    /**
        Returns the cost of the Reservable
    
        @return The cost of the Reservable
    */
    
    public BigDecimal getCost()
    {
        return timeframe.getCost();
    }
    
    /**
        GetEndDate - Return the ending date of the reservable
    
        @return The ending date of the reservable
    */
    
    public LocalDate getEndDate()
    {
        return timeframe.getEndDate();
    }
    
    /**
        GetEndTime - Return the ending time of the reservable
    
        @return The ending time of the reservable
    */
    
    public LocalTime getEndTime()
    {
        return timeframe.getEndTime();
    }
    
    /**
        Return the location of the reservable
    
        @return The location of the reservable
    */
    
    public Location getLocation()
    {
        return location;
    }
    
    /**
        Returns the ID of the reservable's location
    
        @return ID of reservable's location
    */
    
    public int getLocationID()
    {
        return location.getID();
    }
    
    /**
        GetName - Return the location name of the reservable
    
        @return The name of the reservable
    */
    
    public String getName()
    {
        return location.getName();
    }
    
    /**
        GetStartDate - Return the starting date of the reservable
    
        @return The starting date of the reservable
    */
    
    public LocalDate getStartDate()
    {
        return timeframe.getStartDate();
    }
    
    /**
        GetStartTime - Return the starting time of the reservable
    
        @return The starting time of the reservable
    */
    
    public LocalTime getStartTime()
    {
        return timeframe.getStartTime();
    }
    
    /**
        GetTimeframe - Return the timeframe of the reservable
    
        @return The timeframe of the reservable
    */
    
    public Timeframe getTimeframe()
    {
        return timeframe;
    }
    
    /**
        Returns the ID of the reservable's timeframe
    
        @return ID of reservable's timeframe
    */
    
    public int getTimeframeID()
    {
        return timeframe.getID();
    }
    
    /**
        Returns if the reservable is reserved
    
        @return If the reservable is reserved
    */
    
    public boolean isReserved()
    {
        return timeframe.isReserved();
    }
    
    /**
        Returns if the Reservable ends on the given datetime
    
        @param datetime Datetime to check if Reservable ends on
        @return If the Reservable ends on the given datetime
    */
    
    public boolean endsOnDatetime(LocalDateTime datetime)
    {
        return timeframe.endsOnDatetime(datetime);
    }
    
    /**
        Returns if the Reservable starts on the given datetime
    
        @param datetime Datetime to check if Reservable starts on
        @return If the Reservable starts on the given datetime
    */
    
    public boolean startsOnDatetime(LocalDateTime datetime)
    {
        return timeframe.startsOnDatetime(datetime);
    }
}