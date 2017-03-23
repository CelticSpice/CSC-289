/**
 * Provides search functionality
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Reservable;
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
            throws SQLException
    {
        ReservableSearch search = new ReservableSearch();
        
        return search.search(criteria);
    }
}
