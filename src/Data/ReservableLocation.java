/**
    A reservable location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        GetTimeframesEndingOn - Return a list of timeframes allocated to the
        location ending on the given datetime
    
        @param datetime Datetime to get a list of timeframes ending on
        @return A list of timeframes ending on the specified date
    */
    
    public ReservableTimeframeList getTimeframesEndingOn
        (LocalDateTime datetime)
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
    
    public ReservableTimeframeList getTimeframesStartingOn
        (LocalDateTime datetime)
    {
        return timeframes
                .filterStartDate(datetime.toLocalDate())
                .filterStartTime(datetime.toLocalTime());
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
        ReservableTimeframeList list = timeframes
                .filterStartDate(date)
                .filterNotReserved();
        return !list.isEmpty();
    }
    
    /**
        NumTimeframes - Return the number of reservable timeframes
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
        ReserveAt - Reserve the location at the given timeframe
    
        @param timeframe Timeframe to reserve the location at
    */
    
    public void reserveAt(ReservableTimeframe timeframe)
    {
        
    }
}