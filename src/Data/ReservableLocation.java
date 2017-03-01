/**
    A reservable location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReservableLocation extends Location
{
    // Fields
    private ArrayList<ReservableTimeframe> timeframes;
    
    /**
        Constructor - Accepts the reservable location's name & capacity
    
        @param name The reservable location's name
        @param capacity The reservable location's capacity
    */
    
    public ReservableLocation(String name, int capacity)
    {
        super(name, capacity);
        timeframes = new ArrayList<>();
    }
    
    /**
        AddTimeframe - Add a timeframe the location
        can be reserved for
    
        @param timeframe Timeframe the location can be
                                reserved for
    */
    
    public void addTimeframe(ReservableTimeframe timeframe)
    {
        timeframes.add(timeframe);
    }
    
    /**
        IsAvailable - Return whether the location is available to be reserved
        on the specified date
    
        @param availableDate Date to return whether the location is available
                             to be reserved for
        @return Whether the location can be reserved on the specified date
    */
    
    public boolean isAvailable(GregorianCalendar availableDate)
    {
        return true;
    }
    
    /**
        NumTimeframes - Return the number of timeframes
        allocated to the location
    
        @return The number of timeframes allocated to the location
    */
    
    public int numTimeFrames()
    {
        return timeframes.size();
    }
    
    /**
        RemoveTimeframe - Remove the timeframe the location
        can be reserved for specified by the index given
    
        @param index Index specifying timeframe the location can be
                     reserved for to remove
    */
    
    public void removeTimeframe(int index)
    {
        timeframes.remove(index);
    }
}