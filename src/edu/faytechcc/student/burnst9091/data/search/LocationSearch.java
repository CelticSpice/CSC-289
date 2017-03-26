/**
 * A location search
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Location;
import java.util.function.Predicate;

public class LocationSearch
{
    // Fields
    private Predicate<Location> locationName,
                                capacity,
                                finalPredicate;
    private int numLocations;
    
    /**
     * Constructor
     */
    public LocationSearch()
    {
        locationName = null;
        capacity = null;
        finalPredicate = null;
        numLocations = 0;
    }
    
    /**
     * Search - Gather search constraints and create predicates to filter by
     * with those constraints
     * 
     * @param criteria The search criteria
     * @return A Predicate containing all relevant filters based on search
     *         constraints
     */
    public Predicate<Location> search(String criteria)
    {
        // Split search criteria
        String[] filters = criteria.split(";");

        for (String filter : filters)
        {
            // Split keys and values
            String[] constraint = filter.split("=");
            
            String key = constraint[0].trim(), 
                   val = constraint[1].trim();

            switch(key.toLowerCase())
            {
                case "locationname":
                case "location":
                case "loc":
                    numLocations++;
                    
                    locationName = filterByLocationName(val);
                    
                    if (finalPredicate == null)
                        finalPredicate = locationName;
                    else if (numLocations == 1)
                        finalPredicate = finalPredicate.and(locationName);
                    else
                        finalPredicate = finalPredicate.or(locationName);
                    break;
                case "capacity":
                case "cap":
                    capacity = filterByCapacity(val);
                    
                    if (finalPredicate == null)
                        finalPredicate = capacity;
                    else
                        finalPredicate = finalPredicate.and(capacity);
                    break;
            }
        }
        
        return finalPredicate;
    }
    
    /**
     * FilterByLocationName - Filter reservables by location name.
     * 
     * @param value The location name
     * @return A Predicate that checks for a match with the location name
     */
    private Predicate<Location> filterByLocationName(String value)
    {
        return l -> l.getName().equalsIgnoreCase(value);
    }
    
    /**
     * FilterByCapacity - Filter reservables by capacity
     * 
     * @param value The location capacity
     * @return A predicate that checks for a match with the location capacity
     */
    private Predicate<Location> filterByCapacity(String value)
    {
        return l -> l.getCapacity() == Integer.parseInt(value);
    }
}
