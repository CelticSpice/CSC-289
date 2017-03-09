/**
    A reservable, encapsulating a location & reservable timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reservable
{
    // Fields
    private Location location;
    private ReservableTimeframe timeframe;
    
    /**
        Constructor - Accepts the location & timeframe of the reservable
    
        @param loc The location
        @param time The timeframe
    */
    
    public Reservable(Location loc, ReservableTimeframe time)
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
        GetEndDateTime - Return the ending date & time of the reservable
    
        @return The ending date & time of the reservable
    */
    
    public LocalDateTime getEndDateTime()
    {
        return timeframe.getEndDate().atTime(timeframe.getEndTime());
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
        GetStartDateTime - Return the starting date & time of the reservable
    
        @return The starting date & time of the reservable
    */
    
    public LocalDateTime getStartDateTime()
    {
        return timeframe.getStartDate().atTime(timeframe.getStartTime());
    }
}