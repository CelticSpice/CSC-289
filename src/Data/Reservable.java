/**
    A reservable, encapsulating a location & reservable timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reservable
{
    // Fields
    private Location location;
    private Timeframe timeframe;
    
    /**
        Constructor - Accepts the location & timeframe of the reservable
    
        @param loc The location
        @param time The timeframe
    */
    
    public Reservable(Location loc, Timeframe time)
    {
        location = loc;
        timeframe = time;
    }
    
    /**
        GetCapacity - Return the capacity of the reservable
    
        @return The capacity of the reservable
    */
    
    public int getCapacity()
    {
        return location.getCapacity();
    }
    
    /**
        GetCost - Return the cost of the reservable
    
        @return The cost of the reservable
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
        GetTimeframe - Return the reservable timeframe of the reservable
    
        @return The reservable timeframe of the reservable
    */
    
    public Timeframe getTimeframe()
    {
        return timeframe;
    }
}