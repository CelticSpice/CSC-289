/**
 * Provides search functionality
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;
import java.util.function.Predicate;

public class SearchActualizer
{   
    /**
     * Constructor
     */
    public SearchActualizer()
    {
    }
    
    /**
     * SearchReservables - Search for reservables based on criteria
     * 
     * @param criteria The criteria
     * @return 
     * @throws SQLException Error querying locations
     */
    public Predicate<Reservable> searchReservables(String criteria)
    {
        ReservableSearch search = new ReservableSearch();
        
        return search.search(criteria);
    }
    
    public Predicate<Timeframe> searchTimeframes(String criteria)
    {
        TimeframeSearch search = new TimeframeSearch();
        
        return search.search(criteria);
    }
    
    public Predicate<Location> searchLocations(String criteria)
    {
        LocationSearch search = new LocationSearch();
        
        return search.search(criteria);
    }
}
