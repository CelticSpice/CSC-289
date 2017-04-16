/**
 * A location search
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class LocationSearch
{
    // Fields
    private Predicate<ReservableLocation> predicate;
    
    /**
     * Constructs a new LocationSearch
     */
    public LocationSearch()
    {
        predicate = null;
    }
    
    /**
     * Builds a ReservableLocation predicate based on search parameters
     * 
     * @param searchParams The search parameters
     * @return A ReservableLocation predicate containing search parameters to
     *         match with existing locations
     */
    public Predicate<ReservableLocation> search(
            HashMap<String, List<String>> searchParams)
    {
        int numLocations = 0;   // The number of locations searched
        
        if (searchParams.containsKey("Location"))
        {
            for (String v : searchParams.get("Location"))
            {
                numLocations++;
                if (predicate == null)
                    predicate = filterByLocationName(v);
                else if (numLocations == 1)
                    predicate = predicate.and(filterByLocationName(v));
                else
                    predicate = predicate.or(filterByLocationName(v));
            }
        }
        if (searchParams.containsKey("Capacity"))
        {
            for (String v : searchParams.get("Capacity"))
            {
                // For the purpose of searching, a capacity search acts as a
                // separate location search, so we increment numLocations
                numLocations++;
                if (predicate == null)
                    predicate = filterByCapacity(v);
                else if (numLocations == 1)
                    predicate = predicate.and(filterByCapacity(v));
                else
                    predicate = predicate.or(filterByCapacity(v));
            }
        }
        return predicate;
    }
    
    /**
     * Filter locations by a location name
     * 
     * @param name The location name
     * @return A Predicate that checks for matching locations with the location
     *         name
     */
    private Predicate<ReservableLocation> filterByLocationName(String name)
    {
        return l -> l.getName().equalsIgnoreCase(name);
    }
    
    /**
     * Filter locations by capacity
     * 
     * @param cost The location capacity
     * @return A predicate that checks for matching locations with the capacity
     */
    private Predicate<ReservableLocation> filterByCapacity(String cost)
    {
        // Check for specific relational operators and remove it from cost to
        // parse it into an integer
        if (cost.startsWith(">="))
            return l -> l.getCapacity() >= Integer.parseInt(
                    cost.replace(">=", ""));
        else if (cost.startsWith("<="))
            return l -> l.getCapacity() <= Integer.parseInt(
                    cost.replace("<=", ""));
        else if (cost.startsWith(">"))
            return l -> l.getCapacity() > Integer.parseInt(
                    cost.replace(">", ""));
        else if (cost.startsWith("<"))
            return l -> l.getCapacity() < Integer.parseInt(
                    cost.replace("<", ""));
        else
            return l -> l.getCapacity() == Integer.parseInt(
                    cost.replace("=", ""));
    }
}
