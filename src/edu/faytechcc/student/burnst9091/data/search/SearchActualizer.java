/**
 * A SearchActualizer provides search functionality
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

public class SearchActualizer
{
    // Fields
    String criteria;
    int numLocations;
    List<String> acceptedKeys;
    
    /**
     * Constructor
     * 
     * @param c Search criteria
     */
    public SearchActualizer(String c)
    {
        criteria = c;
        acceptedKeys = new ArrayList();
        
        acceptedKeys.add("locationname");
        acceptedKeys.add("location name");
        acceptedKeys.add("location");
        acceptedKeys.add("loc");
        acceptedKeys.add("capacity");
        acceptedKeys.add("cap");
        acceptedKeys.add("startdate");
        acceptedKeys.add("start date");
        acceptedKeys.add("starttime");
        acceptedKeys.add("start time");
        acceptedKeys.add("enddate");
        acceptedKeys.add("end date");
        acceptedKeys.add("endtime");
        acceptedKeys.add("end time");
        acceptedKeys.add("cost");
        acceptedKeys.add("price");
    }
    
    public int getNumSearchLocations()
    {
        return numLocations;
    }
    
    /**
     * Return a predicate that checks for a match with a given location
     * 
     * @return A timeframe predicate
     */
    public Predicate<ReservableLocation> searchLocations()
    {
        LocationSearch search = new LocationSearch();
        return search.search(criteria);
    }
    
    public Predicate<Reservation> searchReservations()
    {
        ReservationSearch search = new ReservationSearch();
        return search.search(criteria);
    }
    
    /**
     * Return a predicate that checks for a match with a given timeframe
     * 
     * @return A timeframe predicate
     */
    public Predicate<ReservableTimeframe> searchTimeframes()
    {
        TimeframeSearch search = new TimeframeSearch();
        return search.search(criteria);
    }

    /**
     * Validate the capacity input
     * 
     * @param capacity The capacity
     * @return If the capacity input is valid
     */
    public boolean validateCapacity(String capacity)
    {
        boolean valid = capacity.matches("([<>]=|[<>=])\\d+");

        if (valid)
        {
            valid = Integer.parseInt(capacity.replaceFirst("([<>]=|[<>=])", "")) > 0;

            if (!valid)
                JOptionPane.showMessageDialog(null,
                    "Capacity must be greater than 0");
        }
        else
            JOptionPane.showMessageDialog(null, "Invalid input for capacity");
        
        return valid;
    }
    
    /**
     * Validate the cost input
     * 
     * @param cost The cost
     * @return If the cost is valid
     */
    public boolean validateCost(String cost)
    {
        boolean valid = cost.matches("\\d+.\\d{2}");
        
        if (valid)
        {
            valid = Double.parseDouble(cost) >= 0.00;
            
            if (!valid)
                JOptionPane.showMessageDialog(null,
                        "Cost must be greater than or equal to 0.00");
        }
        else
            JOptionPane.showMessageDialog(null, "Invalid input for cost");
        
        return valid;
    }
    
    /**
     * Validate the end date input
     * 
     * @param date The end date
     * @return If the end date is valid
     */
    public boolean validateEndDate(String date)
    {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    /**
     * Validate the end time input
     * 
     * @param time The end time
     * @return If the end time is valid
     */
    public boolean validateEndTime(String time)
    {
        
        return time.matches("\\d{2}:\\d{2}");
    }
    
    /**
     * Validates the location name input
     * 
     * @param name The location name
     * @return Whether the location is valid or not
     */
    public boolean validateLocationName(String name)
    {
        for (char c : name.toCharArray())
        {
            if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c))
            {
                JOptionPane.showMessageDialog(null,
                        "A location name consists only of letters, digits," +
                                " and/or spaces");
                
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Validate the entire search
     * 
     * @return If the search is valid
     */
    public boolean validateSearch()
    {        
        // Split search criteria
        String[] filterArray = criteria.split(";");
        
        try
        {
            for (String f : filterArray)
            {
                // Split keys and values
                String[] constraint = f.split(":");
                
                String key = constraint[0].trim(), 
                       val = constraint[1].trim();

                if (!key.isEmpty() && acceptedKeys.contains(key))
                {
                    switch(key.toLowerCase())
                    {
                        case "locationname":
                        case "location name":
                        case "location":
                        case "loc":
                            if (!validateLocationName(val))
                                return false;
                            numLocations++;
                            break;
                        case "capacity":
                        case "cap":
                            if (!validateCapacity(val))
                                return false;
                            break;
                        case "startdate":
                        case "start date":
                            if (!validateStartDate(val))
                                return false;
                            break;
                        case "starttime":
                        case "start time":
                            if (!validateStartTime(val))
                                return false;
                            break;
                        case "enddate":
                        case "end date":
                            if (!validateEndDate(val))
                                return false;
                            break;
                        case "endtime":
                        case "end time":
                            if (!validateEndTime(val))
                                return false;
                            break;
                        case "cost":
                        case "price":
                            if (!validateCost(val))
                                return false;
                            break;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "Missing or misspelled key (i.e. \"start time\")");
                    return false;
                }
            }
        }
        catch (NullPointerException | ArrayIndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(null, "Invalid search" +
                    "\nEnsure your search keys (i.e. \"start time\") and " + 
                    " parameters (i.e. \"12:00\") are correct.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Validate the start date input
     * 
     * @param date The start date
     * @return If the start date is valid
     */
    public boolean validateStartDate(String date)
    {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    /**
     * Validate the start time input
     * 
     * @param time The start time
     * @return If the start time is valid
     */
    public boolean validateStartTime(String time)
    {
        return time.matches("\\d{2}:\\d{2}");
    }
}
