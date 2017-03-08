/**
    A reservable location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.time.LocalDate;

public class ReservableLocation extends Location
{
    // Fields
    private ReservableTimeframeList timeframes;
    
    /**
        Constructor - Accepts the reservable location's name & capacity
    
        @param name The reservable location's name
        @param capacity The reservable location's capacity
    */
    
    public ReservableLocation(String name, int capacity)
    {
        super(name, capacity);
        timeframes = new ReservableTimeframeList();
    }
    
    /**
        Constructor - Accepts the reservable location's name, capacity, and a
        list of reservable timeframes
    
        @param name The reservable location's name
        @param capacity The reservable location's capacity
        @param reservableTimeframes The location's reservable timeframes
    */
    
    public ReservableLocation(String name, int capacity,
                              ReservableTimeframeList reservableTimeframes)
    {
        super(name, capacity);
        timeframes = reservableTimeframes;
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
        CancelReserve - Cancel the reservation of the location at the timeframe
        specified by the given index
    
        @param index Index of timeframe to cancel the reservation of the
                     location on
    */
    
    public void cancelReserve(int index)
    {
        timeframes.get(index).cancelReserve();
    }
    
    /**
        GetTimeframes - Return the list of timeframes allocated to the location
    
        @return The list of timeframes allocated to the location
    */
    
    public ReservableTimeframeList getTimeframes()
    {
        return timeframes;
    }
    
    /**
        IsAvailable - Return whether the location is available to be reserved
        on the specified date
    
        @param date Date to return whether the location is available to be
                    reserved for
        @return avail Whether the location can be reserved on the specified date
    */
    
    public boolean isAvailable(LocalDate date)
    {
        boolean avail = false;
        int i = 0;
                
        while (!avail && i < timeframes.size())
        {
            if (!timeframes.get(i).isReserved() &&
                 timeframes.get(i).startsOnDate(date))
            {
                avail = true;
            }
            else
                i++;
        }
        
        return avail;
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
    
    /**
        Reserve - Reserve the location at the timeframe specified by the given
        index
    
        @param index Index specifying timeframe to reserve the location for
    */
    
    public void reserve(int index)
    {
        timeframes.get(index).reserve();
    }
}