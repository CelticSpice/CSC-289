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
    Predicate<Reservation> startDate;
    Predicate<Reservation> startTime;
    Predicate<Reservation> endDate;
    Predicate<Reservation> endTime;
    Predicate<Reservation> cost;
    Predicate<Reservation> reserver;
    Predicate<Reservation> finalPredicate;
    
    public ReservationSearch()
    {
        startDate = null;
        startTime = null;
        endDate = null;
        endTime = null;
        cost = null;
        reserver = null;
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
                    startDate = filterByStartDate(val);
                    if (finalPredicate == null)
                        finalPredicate = startDate;
                    else
                        finalPredicate = finalPredicate.and(startDate);
                    break;
                case "starttime":                        
                    startTime = filterByStartTime(val);
                    if (finalPredicate == null)
                        finalPredicate = startTime;
                    else
                        finalPredicate = finalPredicate.and(startTime);
                    break;
                case "enddate":
                    endDate = filterByEndDate(val);
                    if (finalPredicate == null)
                        finalPredicate = endDate;
                    else
                        finalPredicate = finalPredicate.and(endDate);
                    break;
                case "endtime":
                    endTime = filterByEndTime(val);
                    if (finalPredicate == null)
                        finalPredicate = endTime;
                    else
                        finalPredicate = finalPredicate.and(endTime);
                    break;
                    //start=2017-03-20,13:00; end=2017-03-20,14:00
                case "cost":
                case "price":
                    cost = filterByCost(val);
                    if (finalPredicate == null)
                        finalPredicate = cost;
                    else
                        finalPredicate = finalPredicate.and(cost);
                    break;
                case "first name":
                case "firstname":
                case "first":
                    break;
                case "last name":
                case "lastname":
                case "last":
                    break;
                case "email":
                case "e-mail":
                    break;
                case "phone":
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
    private Predicate<Reservation> filterByStartDate(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(value, format);
        
        return r -> r.getStartDate().equals(start);
    }
    
    /**
     * FilterByStartTime - Filter reservables by start time
     * @param value The start time
     * @return A predicate that checks for a match with the start time
     */
    private Predicate<Reservation> filterByStartTime(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(value, format);
        
        return r -> r.getStartTime().equals(start);
    }
    
    /**
     * FilterByEndDate - Filter reservables by end date
     * @param value The end date
     * @return A predicate that checks for a match with the end date
     */
    private Predicate<Reservation> filterByEndDate(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(value, format);
        
        return r -> r.getEndDate().equals(end);
    }
    
    /**
     * FilterByEndTime - Filter reservables by end time
     * @param value The end time
     * @return A predicate that checks for a match with the end time
     */
    private Predicate<Reservation> filterByEndTime(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime end = LocalTime.parse(value, format);
        
        return r -> r.getEndTime().equals(end);
    }
    
    /**
     * FilterByCost - Filter reservables by cost
     * @param value The cost
     * @return A predicate that checks for a match with the cost
     */
    private Predicate<Reservation> filterByCost(String value)
    {
        return r -> r.getCost().equals(new BigDecimal(value));
    }
}
