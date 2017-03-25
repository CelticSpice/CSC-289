/**
    A filter to be applied to a list of objects
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data.search;

import java.util.List;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

public class Filter<T>
{
    // Fields
    private Predicate<T> filterPredicate;
    
    /**
        Constructs a new Filter
    */
    
    public Filter()
    {
        filterPredicate = null;
    }
    
    /**
        Constructs a new Filter initialized with the given predicate
    
        @param predicate The predicate
    */
    
    public Filter(Predicate<T> predicate)
    {
        filterPredicate = predicate;
    }
    
    /**
        Filters a list of objects
    
        @param list List to filter
        @return Filtered list of objects
    */
    
    public List<T> filter(List<T> list)
    {
        return list.stream().filter(filterPredicate).collect(toList());
    }
    
    /**
        Returns the filter predicate
    
        @return The filter predicate
    */
    
    public Predicate<T> getPredicate()
    {
        return filterPredicate;
    }
    
    /**
        Sets the filter predicate
    
        @param predicate The predicate to set
    */
    
    public void setPredicate(Predicate<T> predicate)
    {
        filterPredicate = predicate;
    }
}