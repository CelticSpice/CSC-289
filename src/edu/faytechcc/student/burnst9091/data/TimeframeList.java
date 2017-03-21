/**
    A list of timeframes
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

public class TimeframeList extends ArrayList<Timeframe>
{    
    /**
        Constructor
    */
    
    public TimeframeList()
    {
        super();
    }
    
    /**
        Constructor - Accepts a list of timeframes to include in the list
    
        @param timeframes List of timeframes to include in the list
    */
    
    public TimeframeList(List<Timeframe> timeframes)
    {
        super(timeframes);
    }
    
    /**
        Returns if the list has a timeframe or timeframes matching the specified
        predicate
    
        @param predicate Predicate that timeframes must match
        @return If list has a timeframe or timeframes matching the predicate
    */
    
    public boolean contains(Predicate<Timeframe> predicate)
    {
        return !filter(predicate).isEmpty();
    }
    
    /**
        Derives a sublist of timeframes matching the specified predicate
    
        @param predicate The predicate which timeframes must match
        @return A sublist of timeframes matching the given predicate
    */
    
    public TimeframeList filter(Predicate<Timeframe> predicate)
    {
        return new TimeframeList(stream()
                .filter(predicate)
                .collect(toList()));
    }
}