/**
 * Provides search functionality
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

public class SearchActualizer
{
    // Fields
    private String criteria;
    private HashMap<String, List<String>> splitCriteria;
        
    /**
     * Constructs a new SearchActualizer
     * 
     * @param c Search criteria
     */
    public SearchActualizer(String c)
    {
        criteria = c;
        splitCriteria = splitSearchCriteria();
    }
    
    /**
     * Return a predicate that checks for a match with a given location
     * 
     * @return A timeframe predicate
     */
    public Predicate<ReservableLocation> searchLocations()
    {
        return new LocationSearch().search(splitCriteria);
    }
    
    /**
     * Return a predicate that checks for a match with a given reservation
     * 
     * @return A reservation predicate 
     */
    public Predicate<Reservation> searchReservations()
    {
        return new ReservationSearch().search(splitCriteria);
    }
    
    /**
     * Return a predicate that checks for a match with a given timeframe
     * 
     * @return A timeframe predicate
     */
    public Predicate<ReservableTimeframe> searchTimeframes()
    {
        return new TimeframeSearch().search(splitCriteria);
    }
    
    /**
     * Splits the search parameters and store them into a HashMap to be passed
     * into the xSearch classes to check if relevant keys are contained within
     * it
     * 
     * @return A HashMap containing search parameters
     */
    public HashMap<String, List<String>> splitSearchCriteria()
    {
        // The following represent lists of values pertaining to their
        // specified keys
        List<String> locVals = new ArrayList(),
                     capVals = new ArrayList(),
                     sdVals = new ArrayList(),
                     stVals = new ArrayList(),
                     edVals = new ArrayList(),
                     etVals = new ArrayList(),
                     costVals = new ArrayList(),
                     firstVals = new ArrayList(),
                     lastVals = new ArrayList(),
                     emailVals = new ArrayList(),
                     phoneVals = new ArrayList();
        
        // Split the search constraints
        String[] constraints = criteria.split(";");
        
        for (String c : constraints)
        {
            String[] params = c.split("::");
            
            if (params.length >= 2)
            {
                String key = params[0].toLowerCase().trim();
                
                for (int i = 1; i < params.length; i++)
                {
                    String val = params[i].trim();

                    switch (key)
                    {
                        case "locationname":
                        case "location":
                        case "loc":
                            if (validLocationName(val))
                                locVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid location name", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "capacity":
                        case "cap":
                            if (validCapacity(val))
                                capVals.add(val);
                            break;
                        case "startdate":
                            if (validStartDate(val))
                                sdVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid start date", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "starttime":
                            if (validStartTime(val))
                                stVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid start time", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "enddate":
                            if (validEndDate(val))
                                edVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid end date", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "endtime":
                            if (validEndTime(val))
                                etVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid end time", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "cost":
                        case "price":
                            if (validCost(val))
                                costVals.add(val);
                            break;
                        case "firstname":
                        case "first":
                            if (validReserverName(val))
                                firstVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid first name", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "lastname":
                        case "last":
                            if (validReserverName(val))
                                lastVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid last name", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "emailaddress":
                        case "email":
                            if (validEmailAddress(val))
                                emailVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid email address", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "phonenumber":
                        case "phone":
                            if (validPhoneNumber(val))
                                phoneVals.add(val);
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid phone number", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid search key(s)\n\n" +
                                    "To view valid keys, click Help to view " +
                                    "the Search Help Dialog.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                }
            }
            else if (params.length == 1)
            {
                JOptionPane.showMessageDialog(null,
                                "Invalid search\n\n" +
                                "Missing a \"::\" delimiter. To view\n" +
                                "more search info, click the Help button.",
                                "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        splitCriteria = new HashMap();
        
        splitCriteria.put("Location", locVals);
        splitCriteria.put("Capacity", capVals);
        splitCriteria.put("StartDate", sdVals);
        splitCriteria.put("StartTime", stVals);
        splitCriteria.put("EndDate", edVals);
        splitCriteria.put("EndTime", etVals);
        splitCriteria.put("Cost", costVals);
        splitCriteria.put("FirstName", firstVals);
        splitCriteria.put("LastName", lastVals);
        splitCriteria.put("EmailAddress", emailVals);
        splitCriteria.put("PhoneNumber", phoneVals);
        
        return splitCriteria;
    }

    /**
     * Validate the capacity input
     * 
     * @param capacity The capacity
     * @return If the capacity input is valid
     */
    private boolean validCapacity(String capacity)
    {
        boolean valid = capacity.matches("([<>]=|[<>=])\\d+");

        if (valid)
        {
            valid = Integer.parseInt(capacity.replaceFirst(
                    "([<>]=|[<>=])", "")) > 0;

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
    private boolean validCost(String cost)
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
     * Validate the email address input
     * 
     * @param email The email address
     * @return If the email address is valid
     */
    private boolean validEmailAddress(String email)
    {
        return email.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
    }
    
    /**
     * Validate the end date input
     * 
     * @param date The end date
     * @return If the end date is valid
     */
    private boolean validEndDate(String date)
    {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    /**
     * Validate the end time input
     * 
     * @param time The end time
     * @return If the end time is valid
     */
    private boolean validEndTime(String time)
    {
        return time.matches("\\d{2}:\\d{2}");
    }
    
    /**
     * Validates the location name input
     * 
     * @param name The location name
     * @return Whether the location is valid or not
     */
    private boolean validLocationName(String name)
    {
        for (char c : name.toCharArray())
        {
            if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c))
                return false;
        }
        return true;
    }
    
    /**
     * Validate the phone number input
     * 
     * @param phone The phone number
     * @return If the phone number is valid or not
     */
    private boolean validPhoneNumber(String phone)
    {
        return phone.matches("[0-9]{10,16}");
    }
    
    /**
     * Validate the first or last name of a reserver
     * 
     * @param name The reserver's first or last name
     * @return If the name is valid or not
     */
    private boolean validReserverName(String name)
    {
        for (char c : name.toCharArray())
        {
            if (!Character.isLetter(c))
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
    private boolean validStartDate(String date)
    {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    /**
     * Validate the start time input
     * 
     * @param time The start time
     * @return If the start time is valid
     */
    private boolean validStartTime(String time)
    {
        return time.matches("\\d{2}:\\d{2}");
    }
}
