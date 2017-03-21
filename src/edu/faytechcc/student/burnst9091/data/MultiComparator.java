/**
    Comparator to compare objects using multiple comparators
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.Comparator;
import java.util.List;
import static java.util.Arrays.asList;

public class MultiComparator<T> implements Comparator<T>
{
    // Fields
    private final List<Comparator<T>> comparators;

    /**
        Constructor - Accepts an array of comparators
    
        @param cmprtrs Array of comparators
    */
    
    public MultiComparator(Comparator<T>... cmprtrs) {
        comparators = asList(cmprtrs);
    }
    
    /**
        Constructor - Accepts a list of comparators
    
        @param cmprtrs List of comparators
    */
    
    public MultiComparator(List<Comparator<T>> cmprtrs)
    {
        comparators = cmprtrs;
    }
    
    /**
        Compare - Compare one object to another object
    
        @param o1 First object to compare
        @param o2 Second object to compare
        @return 1 if first object is greater than second object; 0 if first
                object & second object are equal; else, -1 if first object is
                less than second object
    */

    @Override
    public int compare(T o1, T o2) {
        for (Comparator<T> c : comparators) {
            int result = c.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}