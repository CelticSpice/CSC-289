/**
 * A reservation search
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Reservation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ReservationSearch
{
    // Fields
    Predicate<Reservation> predicate;
    
    /**
     * Constructs a new ReservationSearch
     */
    public ReservationSearch()
    {
        predicate = null;
    }
    
    /**
     * Builds a Reservation predicate based on search parameters
     * 
     * @param searchParams The search parameters
     * @return A Reservation predicate containing search parameters to match
     *         with existing reservations
     */
    public Predicate<Reservation> search(
            HashMap<String, List<String>> searchParams)
    {
        // The following represent the number of times their respective key
        // has been specified in the search
        int numStartDates = 0;
        int numStartTimes = 0;
        int numEndDates = 0;
        int numEndTimes = 0;
        int numCosts = 0;
        int numFirsts = 0;
        int numLasts = 0;
        int numEmails = 0;
        int numPhones = 0;
        
        if (searchParams.containsKey("StartDate"))
        {
            for (String v : searchParams.get("StartDate"))
            {
                numStartDates++;
                if (predicate == null)
                    predicate = filterByStartDate(v);
                else if (numStartDates == 1)
                    predicate = predicate.and(filterByStartDate(v));
                else
                    predicate = predicate.or(filterByStartDate(v));
            }
        }
        if (searchParams.containsKey("StartTime"))
        {
            for (String v : searchParams.get("StartTime"))
            {
                numStartTimes++;
                if (predicate == null)
                    predicate = filterByStartTime(v);
                else if (numStartTimes == 1)
                    predicate = predicate.and(filterByStartTime(v));
                else
                    predicate = predicate.or(filterByStartTime(v));
            }
        }
        if (searchParams.containsKey("EndDate"))
        {
            for (String v : searchParams.get("EndDate"))
            {
                numEndDates++;
                if (predicate == null)
                    predicate = filterByEndDate(v);
                else if (numEndDates == 1)
                    predicate = predicate.and(filterByEndDate(v));
                else
                    predicate = predicate.or(filterByEndDate(v));
            }
        }
        if (searchParams.containsKey("EndTime"))
        {
            for (String v : searchParams.get("EndTime"))
            {
                numEndTimes++;
                if (predicate == null)
                    predicate = filterByEndTime(v);
                else if (numEndTimes == 1)
                    predicate = predicate.and(filterByEndTime(v));
                else
                    predicate = predicate.or(filterByEndTime(v));
            }
        }
        if (searchParams.containsKey("Cost"))
        {
            for (String v : searchParams.get("Cost"))
            {
                numCosts++;
                if (predicate == null)
                    predicate = filterByCost(v);
                else if (numCosts == 1)
                    predicate = predicate.and(filterByCost(v));
                else
                    predicate = predicate.or(filterByCost(v));
            }
        }
        if (searchParams.containsKey("FirstName"))
        {
            for (String v : searchParams.get("FirstName"))
            {
                numFirsts++;
                if (predicate == null)
                    predicate = filterByFirstName(v);
                else if (numFirsts == 1)
                    predicate = predicate.and(filterByFirstName(v));
                else
                    predicate = predicate.or(filterByFirstName(v));
            }
        }
        if (searchParams.containsKey("LastName"))
        {
            for (String v : searchParams.get("LastName"))
            {
                numLasts++;
                if (predicate == null)
                    predicate = filterByLastName(v);
                else if (numLasts == 1)
                    predicate = predicate.and(filterByLastName(v));
                else
                    predicate = predicate.or(filterByLastName(v));
            }
        }
        if (searchParams.containsKey("EmailAddress"))
        {
            for (String v : searchParams.get("EmailAddress"))
            {
                numEmails++;
                if (predicate == null)
                    predicate = filterByEmail(v);
                else if (numEmails == 1)
                    predicate = predicate.and(filterByEmail(v));
                else
                    predicate = predicate.or(filterByEmail(v));
            }
        }
        if (searchParams.containsKey("PhoneNumber"))
        {
            for (String v : searchParams.get("PhoneNumber"))
            {
                numPhones++;
                if (predicate == null)
                    predicate = filterByPhone(v);
                else if (numPhones == 1)
                    predicate = predicate.and(filterByPhone(v));
                else
                    predicate = predicate.or(filterByPhone(v));
            }
        }
        return predicate;
    }
    
    /**
     * Filter reservations by cost
     * 
     * @param cost The cost
     * @return A predicate that checks for matching reservations with the cost
     */
    private Predicate<Reservation> filterByCost(String cost)
    {
        return r -> r.getCost().equals(new BigDecimal(cost));
    }
    
    /**
     * Filter reservations by end date
     * 
     * @param date The end date
     * @return A predicate that checks for matching reservations with the end
     *         date
     */
    private Predicate<Reservation> filterByEndDate(String date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(date, format);
        
        return r -> r.getEndDate().equals(end);
    }
    
    /**
     * Filter reservations by a reserver's email address
     * 
     * @param email The reserver's email address
     * @return A predicate that checks for matching reservations with the
     *         reserver's email address
     */
    private Predicate<Reservation> filterByEmail(String email)
    {
        return r -> r.getReserverEmail().equalsIgnoreCase(email);
    }
    
    /**
     * Filter reservations by end time
     * 
     * @param time The end time
     * @return A predicate that checks for matching reservations with the end
     *         time
     */
    private Predicate<Reservation> filterByEndTime(String time)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime end = LocalTime.parse(time, format);
        
        return r -> r.getEndTime().equals(end);
    }
    
    /**
     * Filter reservations by a reserver's first name
     * 
     * @param name The reserver's first name
     * @return A predicate that checks for matching reservations with the 
     *         reserver's first name
     */
    private Predicate<Reservation> filterByFirstName(String name)
    {
        return r -> r.getReserverFirstName().equalsIgnoreCase(name);
    }
    
    /**
     * Filter reservations by a reserver's last name
     * 
     * @param name The reserver's last name
     * @return A predicate that checks for matching reservations with the
     *         reserver's last name
     */
    private Predicate<Reservation> filterByLastName(String name)
    {
        return r -> r.getReserverLastName().equalsIgnoreCase(name);
    }
    
    /**
     * Filter reservations by a reserver's phone number
     * 
     * @param phone The reserver's phone number
     * @return A predicate that checks for matching reservations with the
     *         reserver's phone number
     */
    private Predicate<Reservation> filterByPhone(String phone)
    {
        return r -> r.getReserverPhone().equalsIgnoreCase(phone);
    }
    
    /**
     * Filter reservations by start date
     * 
     * @param date The start date
     * @return A predicate that checks for matching reservations with the start
     *         date
     */
    private Predicate<Reservation> filterByStartDate(String date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(date, format);
        
        return r -> r.getStartDate().equals(start);
    }
    
    /**
     * Filter reservations by start time
     * 
     * @param time The start time
     * @return A predicate that checks for matching reservations with the start
     *         time
     */
    private Predicate<Reservation> filterByStartTime(String time)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(time, format);
        
        return r -> r.getStartTime().equals(start);
    }
}
