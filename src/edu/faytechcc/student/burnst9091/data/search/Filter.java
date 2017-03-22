/**
    A filter to be applied to a list of objects
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data.search;

import java.util.function.Predicate;

public class Filter<T>
{
    // Fields
    private Predicate<T> filterPredicate;
    
    /**
        Constructor
    */
    
    public Filter()
    {
        filterPredicate = null;
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