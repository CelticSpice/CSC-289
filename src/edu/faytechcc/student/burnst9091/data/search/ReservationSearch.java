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
import java.util.function.Predicate;

public class ReservationSearch
{
    // Fields
    Predicate<Reservation> finalPredicate;
    
    public ReservationSearch()
    {
        finalPredicate = null;
    }
    
    public Predicate<Reservation> search(String criteria)
    {
        // Split search criteria
        String[] filters = criteria.split(";");

        for (String filter : filters)
        {
            // Split keys and values
            String[] constraint = filter.split(":");
            
            String key = constraint[0].trim(), 
                   val = constraint[1].trim();

            switch(key.toLowerCase())
            {
                case "startdate":
                    if (finalPredicate == null)
                        finalPredicate = filterByStartDate(val);
                    else
                        finalPredicate = finalPredicate.and(filterByStartDate(val));
                    break;
                case "starttime":
                    if (finalPredicate == null)
                        finalPredicate = filterByStartTime(val);
                    else
                        finalPredicate = finalPredicate.and(filterByStartTime(val));
                    break;
                case "enddate":
                    if (finalPredicate == null)
                        finalPredicate = filterByEndDate(val);
                    else
                        finalPredicate = finalPredicate.and(filterByEndDate(val));
                    break;
                case "endtime":
                    if (finalPredicate == null)
                        finalPredicate = filterByEndTime(val);
                    else
                        finalPredicate = finalPredicate.and(filterByEndTime(val));
                    break;
                case "cost":
                case "price":
                    if (finalPredicate == null)
                        finalPredicate = filterByCost(val);
                    else
                        finalPredicate = finalPredicate.and(filterByCost(val));
                    break;
                case "first name":
                case "firstname":
                case "first":
                    if (finalPredicate == null)
                        finalPredicate = filterByFirstName(val);
                    else
                        finalPredicate = finalPredicate.and(filterByFirstName(val));
                    break;
                case "last name":
                case "lastname":
                case "last":
                    if (finalPredicate == null)
                        finalPredicate = filterByLastName(val);
                    else
                        finalPredicate = finalPredicate.and(filterByLastName(val));
                    break;
                case "email":
                case "e-mail":
                    if (finalPredicate == null)
                        finalPredicate = filterByEmail(val);
                    else
                        finalPredicate = finalPredicate.and(filterByEmail(val));
                    break;
                case "phone":
                    if (finalPredicate == null)
                        finalPredicate = filterByPhone(val);
                    else
                        finalPredicate = finalPredicate.and(filterByPhone(val));
                    break;
            }
        }
        
        return finalPredicate;
    }
    
    /**
     * FilterByStartDate - Filter reservables by start date
     * @param value The start date
     * @return A predicate that checks for a match with the start date
     */
    private Predicate<Reservation> filterByStartDate(String date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(date, format);
        
        return r -> r.getStartDate().equals(start);
    }
    
    /**
     * FilterByStartTime - Filter reservables by start time
     * @param value The start time
     * @return A predicate that checks for a match with the start time
     */
    private Predicate<Reservation> filterByStartTime(String time)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(time, format);
        
        return r -> r.getStartTime().equals(start);
    }
    
    /**
     * FilterByEndDate - Filter reservables by end date
     * @param value The end date
     * @return A predicate that checks for a match with the end date
     */
    private Predicate<Reservation> filterByEndDate(String date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(date, format);
        
        return r -> r.getEndDate().equals(end);
    }
    
    /**
     * FilterByEndTime - Filter reservables by end time
     * @param value The end time
     * @return A predicate that checks for a match with the end time
     */
    private Predicate<Reservation> filterByEndTime(String time)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime end = LocalTime.parse(time, format);
        
        return r -> r.getEndTime().equals(end);
    }
    
    /**
     * FilterByCost - Filter reservables by cost
     * @param value The cost
     * @return A predicate that checks for a match with the cost
     */
    private Predicate<Reservation> filterByCost(String cost)
    {
        return r -> r.getCost().equals(new BigDecimal(cost));
    }
    
    private Predicate<Reservation> filterByFirstName(String name)
    {
        return r -> r.getReserverFirstName().equalsIgnoreCase(name);
    }
    
    private Predicate<Reservation> filterByLastName(String name)
    {
        return r -> r.getReserverLastName().equalsIgnoreCase(name);
    }
    
    private Predicate<Reservation> filterByEmail(String email)
    {
        return r -> r.getReserverEmail().equalsIgnoreCase(email);
    }
    
    private Predicate<Reservation> filterByPhone(String phone)
    {
        return r -> r.getReserverPhone().equalsIgnoreCase(phone);
    }
}
