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
        List<String> locVals = null,
                     capVals = null,
                     sdVals = null,
                     stVals = null,
                     edVals = null,
                     etVals = null,
                     costVals = null,
                     firstVals = null,
                     lastVals = null,
                     emailVals = null,
                     phoneVals = null,
                     eventVals = null,
                     attendingVals = null;
        
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
                            {
                                if (locVals == null)
                                    locVals = new ArrayList();
                                locVals.add(val);
                            }
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
                            {
                                if (capVals == null)
                                    capVals = new ArrayList();
                                capVals.add(val);
                            }
                            break;
                        case "startdate":
                            if (validStartDate(val))
                            {
                                if (sdVals == null)
                                    sdVals = new ArrayList();
                                sdVals.add(val);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid start date", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "starttime":
                            if (validStartTime(val))
                            {
                                if (stVals == null)
                                    stVals = new ArrayList();
                                stVals.add(val);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid start time", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "enddate":
                            if (validEndDate(val))
                            {
                                if (edVals == null)
                                    edVals = new ArrayList();
                                edVals.add(val);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid end date", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "endtime":
                            if (validEndTime(val))
                            {
                                if (etVals == null)
                                    etVals = new ArrayList();
                                etVals.add(val);
                            }
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
                            {
                                if (costVals == null)
                                    costVals = new ArrayList();
                                costVals.add(val);
                            }
                            break;
                        case "firstname":
                        case "first":
                            if (validReserverName(val))
                            {
                                if (firstVals == null)
                                    firstVals = new ArrayList();
                                firstVals.add(val);
                            }
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
                            {
                                if (lastVals == null)
                                    lastVals = new ArrayList();
                                lastVals.add(val);
                            }
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
                            {
                                if (emailVals == null)
                                    emailVals = new ArrayList();
                                emailVals.add(val);
                            }
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
                            {
                                if (phoneVals == null)
                                    phoneVals = new ArrayList();
                                phoneVals.add(val);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid phone number", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "eventtype":
                        case "event":
                        case "type":
                            if (validEventType(val))
                            {
                                if (eventVals == null)
                                    eventVals = new ArrayList();
                                eventVals.add(val);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid event type", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "numberattending":
                        case "attending":
                        case "attendees":
                            if (validCapacity(val))
                            {
                                if (attendingVals == null)
                                    attendingVals = new ArrayList();
                                attendingVals.add(val);
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
        
        if (locVals != null)
            splitCriteria.put("Location", locVals);
        if (capVals != null)
            splitCriteria.put("Capacity", capVals);
        if (sdVals != null)
            splitCriteria.put("StartDate", sdVals);
        if (stVals != null)
            splitCriteria.put("StartTime", stVals);
        if (edVals != null)
            splitCriteria.put("EndDate", edVals);
        if (etVals != null)
            splitCriteria.put("EndTime", etVals);
        if (costVals != null)
            splitCriteria.put("Cost", costVals);
        if (firstVals != null)
            splitCriteria.put("FirstName", firstVals);
        if (lastVals != null)
            splitCriteria.put("LastName", lastVals);
        if (emailVals != null)
            splitCriteria.put("EmailAddress", emailVals);
        if (phoneVals != null)
            splitCriteria.put("PhoneNumber", phoneVals);
        if (eventVals != null)
            splitCriteria.put("EventType", eventVals);
        if (attendingVals != null)
            splitCriteria.put("Attending", attendingVals);
        
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
     * Validate the event type input
     * 
     * @param type The event type
     * @return If the event type is valid
     */
    private boolean validEventType(String type)
    {
        for (char c : type.toCharArray())
        {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c))
                return false;
        }
        return true;
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
