/**
    A location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.time.LocalDateTime;

public class Location
{
    // Fields
    private int capacity;
    private TimeframeList timeframes;
    private String name;
    
    /**
        Constructor - Accepts the location's name & capacity
    
        @param n The location name
        @param cap The location capacity
    */
    
    public Location(String n, int cap)
    {
        name = n;
        capacity = cap;
        timeframes = new TimeframeList();
    }
    
    /**
        Constructor - Accepts the location's name, capacity, and a list of 
        timeframes allocated to the location
    
        @param n The reservable location's name
        @param cap The reservable location's capacity
        @param times The location's allocated timeframes
    */
    
    public Location(String n, int cap, TimeframeList times)
    {
        name = n;
        capacity = cap;
        timeframes = times;
    }
    
    /**
        AddTimeframe - Add a timeframe the location can be reserved for
    
        @param timeframe Timeframe the location can be reserved for
    */
    
    public void addTimeframe(Timeframe timeframe)
    {
        timeframes.add(timeframe);
    }
    
    /**
        GetCapacity - Return the location's capacity
    
        @return The location's capacity
    */
    
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
        GetName - Return the location's name
    
        @return The location's name
    */
    
    public String getName()
    {
        return name;
    }
    
    /**
        GetNumTimeframes - Return the number of timeframes allocated to the
        location
    
        @return The number of timeframes allocated to the location
    */
    
    public int getNumTimeframes()
    {
        return timeframes.size();
    }
    
    /**
        GetTimeframes - Return a list of timeframes allocated to the location
    
        @return A list of timeframes allocated to the location
    */
    
    public TimeframeList getTimeframes()
    {
        return timeframes;
    }
    
    /**
        GetTimeframesEndingOn - Return a list of timeframes allocated to the
        location ending on the given datetime
    
        @param datetime Datetime to get a list of timeframes ending on
        @return A list of timeframes ending on the specified date
    */
    
    public TimeframeList getTimeframesEndingOn(LocalDateTime datetime)
    {
        return timeframes
                .filterEndDate(datetime.toLocalDate())
                .filterEndTime(datetime.toLocalTime());
    }
    
    /**
        GetTimeframesStartingOn - Return a list of timeframes allocated to the
        location starting on the given datetime
    
        @param datetime Datetime to get a list of timeframes starting on
        @return A list of timeframes starting on the specified date
    */
    
    public TimeframeList getTimeframesStartingOn(LocalDateTime datetime)
    {
        return timeframes
                .filterStartDate(datetime.toLocalDate())
                .filterStartTime(datetime.toLocalTime());
    }
    
    /**
        IsAvailable - Return whether the location is available to be reserved
        on the specified datetime
    
        @param datetime Datetime to return whether the location is available to
                        be reserved on
        @return Whether the location is available to be reserved on the
                specified datetime
    */
    
    public boolean isAvailable(LocalDateTime datetime)
    {
        return !timeframes
                .filterStartDate(datetime.toLocalDate())
                .filterStartTime(datetime.toLocalTime())
                .filterNotReserved()
                .isEmpty();
    }
    
    /**
        SetCapacity - Set the location's capacity
    
        @param cap The location's capacity
    */
    
    public void setCapacity(int cap)
    {
        capacity = cap;
    }
}