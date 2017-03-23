/**
    A location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

public class Location
{
    // Fields
    private int capacity;
    private List<Timeframe> timeframes;
    private String name;
    
    /**
        Constructor - Accepts the location's name & capacity
    
        @param n The location name
        @param cap The location capacity
        @throws IllegalArgumentException Name is empty or capacity 0 or less
    */
    
    public Location(String n, int cap)
    {
        // Check that name is valid
        if (n == null || n.isEmpty())
            throw new IllegalArgumentException("Name is blank");
        
        // Check that capacity is valid
        if (cap <= 0)
            throw new IllegalArgumentException("Capacity 0 or less");
        
        name = n;
        capacity = cap;
        timeframes = new ArrayList<>();
    }
    
    /**
        Constructor - Accepts the location's name, capacity, and a list of 
        timeframes allocated to the location
    
        @param n The reservable location's name
        @param cap The reservable location's capacity
        @param times The location's allocated timeframes
        @throws IllegalArgumentException Name is empty or capacity 0 or less
    */
    
    public Location(String n, int cap, List<Timeframe> times)
    {
        // Check that name is valid
        if (n == null || n.isEmpty())
            throw new IllegalArgumentException("Name is blank");
        
        // Check that capacity is valid
        if (cap <= 0)
            throw new IllegalArgumentException("Capacity 0 or less");
        
        name = n;
        capacity = cap;
        timeframes = times;
    }
    
    /**
        Adds a timeframe that this location can be reserved for
    
        @param timeframe The timeframe to add
    */
    
    public void addTimeframe(Timeframe timeframe)
    {
        timeframes.add(timeframe);
    }
    
    /**
        Derives a list of reservables at this location
    
        @return A list of reservables
    */
    
    public List<Reservable> deriveReservables()
    {
        List<Reservable> reservables = new ArrayList<>();
        for (Timeframe timeframe : timeframes)
            reservables.add(new Reservable(this, timeframe));
        return reservables;
    }
    
    /**
        Derives a list of reservables at this location matching the given
        predicate
    
        @param predicate Predicate that reservables must match
        @return A list of reservables
    */
    
    public List<Reservable> deriveReservables(Predicate<Reservable> predicate)
    {
        List<Reservable> reservables = deriveReservables();
        reservables = reservables.stream().filter(predicate).collect(toList());
        return reservables;
    }
    
    /**
        Returns the location's capacity
    
        @return The location's capacity
    */
    
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
        Returns the location's name
    
        @return The location's name
    */
    
    public String getName()
    {
        return name;
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
        Returns a list of timeframes allocated to the location. The order
        in which timeframes will be sorted is unreliable
    
        @return A list of timeframes allocated to the location
    */
    
    public List<Timeframe> getTimeframes()
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
    
    public List<Timeframe> getTimeframes(Predicate<Timeframe> predicate)
    {
        return new ArrayList<>(timeframes.stream()
                .filter(predicate)
                .collect(toList()));
    }
    
    /**
        Returns a list of timeframes allocated to this location sorted according
        to the specified comparator
    
        @param comparator Comparator to sort the location's timeframes with
        @return A sorted list of timeframes
    */
    
    public List<Timeframe> getTimeframes(Comparator<Timeframe> comparator)
    {
        ArrayList<Timeframe> list = new ArrayList<>(timeframes);
        list.sort(comparator);
        return list;
    }
    
    /**
        Returns a list of timeframes allocated to the location matching the
        specified predicate & sorted according to the specified comparator
    
        @param predicate The predicate which timeframes must match to be listed
        @param comparator Comparator to sort the location's timeframes with
        @return A sorted list of timeframes matching the specified predicate
    */
    
    public List<Timeframe> getTimeframes(Predicate<Timeframe> predicate,
                                         Comparator<Timeframe> comparator)
    {
        List<Timeframe> list = getTimeframes(predicate);
        list.sort(comparator);
        return list;
    }
    
    /**
        Returns if the location has a timeframe or timeframes matching the
        specified predicate
    
        @param predicate Predicate that timeframes must match
        @return If location has a timeframe or timeframes matching the predicate
    */
    
    public boolean hasTimeframe(Predicate<Timeframe> predicate)
    {
        return !getTimeframes(predicate).isEmpty();
    }
    
    /**
        Removes a timeframe that this location can be reserved for
    
        @param timeframe The timeframe to remove
    */
    
    public void removeTimeframe(Timeframe timeframe)
    {
        timeframes.remove(timeframe);
    }
    
    /**
        Sets the location's capacity
    
        @param cap The location's capacity
    */
    
    public void setCapacity(int cap)
    {
        capacity = cap;
    }
    
    /**
        Sets the name of the location
    
        @param n The name of the location
    */
    
    public void setName(String n)
    {
        name = n;
    }
    
    /**
        Returns a string representation of the object
    
        @return A string representation of the object
    */
    
    @Override
    public String toString()
    {
        return name;
    }
}