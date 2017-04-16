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
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class TimeframeSearch
{
    // Fields
    Predicate<ReservableTimeframe> predicate;
    
    /**
     * Constructs a new TimeframeSearch
     */
    public TimeframeSearch()
    {
        predicate = null;
    }
    
    /**
     * Builds a ReservableTimeframe predicate based on search parameters
     * 
     * @param searchParams The search parameters
     * @return A ReservableTimeframe predicate containing search parameters to
     *         match with existing timeframes
     */
    public Predicate<ReservableTimeframe> search(
            HashMap<String, List<String>> searchParams)
    {
        // The following represent the number of times their respective key
        // has been specified in the search
        int numStartDates = 0;
        int numStartTimes = 0;
        int numEndDates = 0;
        int numEndTimes = 0;
        int numCosts = 0;
        
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
        return predicate;
    }
    
    /**
     * Filter reservables by cost
     * 
     * @param cost The cost
     * @return A predicate that checks for matching reservables with the cost
     */
    private Predicate<ReservableTimeframe> filterByCost(String cost)
    {
        return t -> t.getCost().equals(new BigDecimal(cost));
    }
    
    /**
     * Filter reservables by end date
     * 
     * @param date The end date
     * @return A predicate that checks for matching reservables with the end
     *         date
     */
    private Predicate<ReservableTimeframe> filterByEndDate(String date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(date, format);
        
        return t -> t.getEndDate().equals(end);
    }
    
    /**
     * Filter reservables by end time
     * 
     * @param time The end time
     * @return A predicate that checks for matching reservables with the end
     *         time
     */
    private Predicate<ReservableTimeframe> filterByEndTime(String time)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime end = LocalTime.parse(time, format);
        
        return t -> t.getEndTime().equals(end);
    }
    
    /**
     * Filter reservables by start date
     * 
     * @param date The start date
     * @return A predicate that checks for matching reservables with the start
     *         date
     */
    private Predicate<ReservableTimeframe> filterByStartDate(String date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(date, format);
        
        return t -> t.getStartDate().equals(start);
    }
    
    /**
     * Filter reservables by start time
     * 
     * @param time The start time
     * @return A predicate that checks for matching reservables with the start
     *         time
     */
    private Predicate<ReservableTimeframe> filterByStartTime(String time)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(time, format);
        
        return t -> t.getStartTime().equals(start);
    }
}
