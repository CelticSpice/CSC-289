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
        Compare - Compare one object to another object
    
        @param o1 Object to compare with object 2
        @param o2 Object to compare with object 1
        @return 1 if object 1 is greater than object 2; 0 if object 1 & 2 are
                equal; else, -1 if object 1 is less than object 2
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