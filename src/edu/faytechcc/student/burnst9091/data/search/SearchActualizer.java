/**
 * A SearchActualizer provides search functionality
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.util.function.Predicate;

public class SearchActualizer
{   
    /**
     * Constructor
     */
    public SearchActualizer(){}
    
    /**
     * Return a predicate that checks for a match with a given location
     * 
     * @param criteria The search criteria
     * @return A timeframe predicate
     */
    public Predicate<Location> searchLocations(String criteria)
    {
        LocationSearch search = new LocationSearch();
        return search.search(criteria);
    }
    
    /**
     * Return a predicate that checks for a match with a given reserver
     * 
     * @param criteria The search criteria
     * @return A reserver predicate
     */
    public Predicate<Reserver> searchReservers(String criteria)
    {
        ReserverSearch search = new ReserverSearch();
        return search.search(criteria);
    }
    
    /**
     * Return a predicate that checks for a match with a given timeframe
     * 
     * @param criteria The search criteria
     * @return A timeframe predicate
     */
    public Predicate<Timeframe> searchTimeframes(String criteria)
    {
        TimeframeSearch search = new TimeframeSearch();
        return search.search(criteria);
    }
}
