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
        CancelReserve - Cancels the reservation of the location at the timeframe
        specified by the given index
    
        @param index Index of timeframe to cancel the reservation of the
                     location on
    */
    
    public void cancelReserve(int index)
    {
        timeframes.get(index).cancelReserve();
    }
    
    /**
        GetTimeframes - Return the reservable timeframes allocated to the
        location
    
        @return The reservable timeframes allocated to the location
    */
    
    public ReservableTimeframe[] getTimeframes()
    {
        return timeframes.toArray(new ReservableTimeframe[timeframes.size()]);
    }
    
    /**
        IndexOfTimeframe - Return the index of the timeframe given
    
        @param timeframe Timeframe to get index of
        @return Index of timeframe; else, -1
    */
    
    public int indexOfTimeframe(ReservableTimeframe timeframe)
    {
        return timeframes.indexOf(timeframe);
    }
    
    /**
        IsAvailable - Return whether the location is available to be reserved
        on the specified date
    
        @param date Date to return whether the location is available to be
                    reserved for
        @return avail Whether the location can be reserved on the specified date
    */
    
    public boolean isAvailable(GregorianCalendar date)
    {
        boolean dateOnly = true;
        boolean avail = false;
        int i = 0;
        
        while (!avail && i < timeframes.size())
        {
            if (!timeframes.get(i).isReserved() &&
                timeframes.get(i).startsOn(date, dateOnly))
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