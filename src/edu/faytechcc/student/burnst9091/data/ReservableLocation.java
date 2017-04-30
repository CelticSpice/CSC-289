/**
    A reservable location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

public class ReservableLocation extends Location
{
    private List<ReservableTimeframe> timeframes;
    
    /**
        Constructs a new ReservableLocation initialized with the given name &
        capacity
    
        @param n The location name
        @param cap The location capacity
    */
    
    public ReservableLocation(String n, int cap)
    {
        super(n, cap);
        timeframes = new ArrayList<>();
    }
    
    /**
        Constructs a new ReservableLocation initialized with the given name,
        capacity, & ID
    
        @param n The location name
        @param cap The location capacity
        @param id The location ID
    */
    
    public ReservableLocation(String n, int cap, int id)
    {
        super(n, cap, id);
        timeframes = new ArrayList<>();
    }
    
    /**
        Constructs a new ReservableLocation initialized with the given name,
        capacity, reservable timeframes, & ID
    
        @param n The location name
        @param cap The location capacity
        @param times The location timeframes
        @param id The location ID
    */
    
    public ReservableLocation(String n, int cap,
            List<ReservableTimeframe> times, int id)
    {
        super(n, cap, id);
        timeframes = times;
    }
    
    /**
        Adds a timeframe that this location can be reserved for
    
        @param timeframe The timeframe to add
    */
    
    public void addTimeframe(ReservableTimeframe timeframe)
    {
        timeframes.add(timeframe);
    }
    
    /**
        Return the number of timeframes allocated to the location
    
        @return The number of timeframes allocated to the location
    */
    
    public int getNumTimeframes()
    {
        return timeframes.size();
    }
    
    /**
        Returns a list of timeframes that can be reserved at this location
    
        @return List of timeframes that can be reserved
    */
    
    public List<ReservableTimeframe> getReservableTimeframes()
    {
        List<ReservableTimeframe> avails = getTimeframes(t -> !t.isReserved());
        List<ReservableTimeframe> unavails = getTimeframes(t -> t.isReserved());
        
        for (ReservableTimeframe unavailable : unavails)
        {
            for (int i = 0; i < avails.size(); i++)
            {
                if (unavailable.consistsOf(avails.get(i)))
                {
                    avails.remove(i);
                    i--;
                }
            }
        }
        return avails;
    }
    
    /**
        Returns a list of timeframes allocated to the location. The order
        in which timeframes will be sorted is unreliable
    
        @return A list of timeframes allocated to the location
    */
    
    public List<ReservableTimeframe> getTimeframes()
    {
        return new ArrayList<>(timeframes);
    }
    
    /**
        Returns a list of timeframes allocated to the location matching the
        specified predicate. The order in which timeframes will be sorted
        is unreliable
    
        @param predicate The predicate which timeframes must match to be listed
        @return A list of timeframes matching the given predicate
    */
    
    public List<ReservableTimeframe> getTimeframes(
            Predicate<ReservableTimeframe> predicate)
    {
        return new ArrayList<>(timeframes.stream()
                .filter(predicate)
                .collect(toList()));
    }
    
    /**
        Returns if the location has a timeframe or timeframes matching the
        specified predicate
    
        @param predicate Predicate that timeframes must match
        @return If location has a timeframe or timeframes matching the predicate
    */
    
    public boolean hasTimeframe(Predicate<ReservableTimeframe> predicate)
    {
        return !getTimeframes(predicate).isEmpty();
    }
    
    /**
        Returns if the location is reserved
    
        @return If the location is reserved
    */
    
    public boolean isReserved()
    {
        for (ReservableTimeframe timeframe : timeframes)
        {
            if (timeframe.isReserved())
                return true;
        }
        return false;
    }
    
    /**
        Removes a timeframe that this location can be reserved for
    
        @param timeframe The timeframe to remove
    */
    
    public void removeTimeframe(ReservableTimeframe timeframe)
    {
        timeframes.remove(timeframe);
    }
}