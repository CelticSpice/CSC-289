/**
 * A timeframe search
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class TimeframeSearch
{
    // Fields
    Predicate<ReservableTimeframe> startDate,
                         startTime,
                         endDate,
                         endTime,
                         cost,
                         finalPredicate;
    
    /**
     * Constructor
     */
    public TimeframeSearch()
    {
        startDate = null;
        startTime = null;
        endDate = null;
        endTime = null;
        cost = null;
        finalPredicate = null;
    }
    
    public Predicate<ReservableTimeframe> search(String criteria)
    {
        // Split search criteria
        String[] filters = criteria.split(";");

        for (String filter : filters)
        {
            // Split keys and values
            String[] constraint = filter.split("::");
            
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
                    //start=2017-03-20,13:00; end=2017-03-20,14:00
                case "cost":
                case "price":
                    if (finalPredicate == null)
                        finalPredicate = filterByCost(val);
                    else
                        finalPredicate = finalPredicate.and(filterByCost(val));
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
    private Predicate<ReservableTimeframe> filterByStartDate(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(value, format);
        
        return t -> t.getStartDate().equals(start);
    }
    
    /**
     * FilterByStartTime - Filter reservables by start time
     * @param value The start time
     * @return A predicate that checks for a match with the start time
     */
    private Predicate<ReservableTimeframe> filterByStartTime(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(value, format);
        
        return t -> t.getStartTime().equals(start);
    }
    
    /**
     * FilterByEndDate - Filter reservables by end date
     * @param value The end date
     * @return A predicate that checks for a match with the end date
     */
    private Predicate<ReservableTimeframe> filterByEndDate(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(value, format);
        
        return t -> t.getEndDate().equals(end);
    }
    
    /**
     * FilterByEndTime - Filter reservables by end time
     * @param value The end time
     * @return A predicate that checks for a match with the end time
     */
    private Predicate<ReservableTimeframe> filterByEndTime(String value)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime end = LocalTime.parse(value, format);
        
        return t -> t.getEndTime().equals(end);
    }
    
    /**
     * FilterByCost - Filter reservables by cost
     * @param value The cost
     * @return A predicate that checks for a match with the cost
     */
    private Predicate<ReservableTimeframe> filterByCost(String value)
    {
        return t -> t.getCost().equals(new BigDecimal(value));
    }
}
