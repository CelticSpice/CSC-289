/**
    A list of timeframes
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class TimeframeList extends ArrayList<Timeframe>
{    
    /*
        Static class objects
    */
    
    /**
        COST_COMPARATOR - Comparator to compare timeframes on their
        cost
    */
    
    public static final Comparator<Timeframe> COST_COMPARATOR =
            (a, b) -> a.getCost().compareTo(b.getCost());
    
    /**
        END_DATE_COMPARATOR - Comparator to compare timeframes on
        their end dates
    */
    
    public static final Comparator<Timeframe> END_DATE_COMPARATOR =
            (a, b) -> {
                LocalDate dateA = a.getEndDate();
                LocalDate dateB = b.getEndDate();
                return dateA.compareTo(dateB);
            };
    
    /**
        END_TIME_COMPARATOR - Comparator to compare timeframes on
        their end times
    */
    
    public static final Comparator<Timeframe> END_TIME_COMPARATOR =
            (a, b) -> {
                LocalTime timeA = a.getEndTime();
                LocalTime timeB = b.getEndTime();
                return timeA.compareTo(timeB);
            };
    
    /**
        START_DATE_COMPARATOR - Comparator to compare timeframes on
        their start dates
    */
    
    public static final Comparator<Timeframe> START_DATE_COMPARATOR =
            (a, b) -> {
                LocalDate dateA = a.getStartDate();
                LocalDate dateB = b.getStartDate();
                return dateA.compareTo(dateB);
            };
    
    /**
        START_TIME_COMPARATOR - Comparator to compare timeframes on
        their start times
    */
    
    public static final Comparator<Timeframe> START_TIME_COMPARATOR =
            (a, b) -> {
                LocalTime timeA = a.getStartTime();
                LocalTime timeB = b.getStartTime();
                return timeA.compareTo(timeB);
            };
    
    /**
        Constructor
    */
    
    public TimeframeList()
    {
        super();
    }
    
    /**
        Constructor - Accepts a list of timeframes to include
        in the list
    
        @param timeframes List of timeframes to include in the list
    */
    
    public TimeframeList(List<Timeframe> timeframes)
    {
        super(timeframes);
    }
    
    /**
        FilterCost - Derive a sublist of timeframes with the given cost
    
        @param cost Amount timeframes should cost
        @return A sublist of timeframes with the given cost
    */
    
    public TimeframeList filerCost(BigDecimal cost)
    {
        return new TimeframeList(stream()
                .filter(t -> t.getCost().equals(cost))
                .collect(toList()));
    }
    
    /**
        FilterCost - Derive a sublist of timeframes between the given costs,
        inclusively
    
        @param costA Amount timeframes should cost at minimum
        @param costB Amount timeframes should cost at maximum
        @return A sublist of timeframes with costs between the given costs,
                inclusively
    */
    
    public TimeframeList filterCost(BigDecimal costA,
                                                BigDecimal costB)
    {
        return new TimeframeList(stream()
                .filter(t -> t.getCost().compareTo(costA) >= 0 &&
                             t.getCost().compareTo(costB) <= 0)
                .collect(toList()));
    }
    
    /**
        FilterEndDate - Derive a sublist of timeframes ending on the given
        date
    
        @param date Date timeframes should end on
        @return A sublist of timeframes that end on the given date
    */
    
    public TimeframeList filterEndDate(LocalDate date)
    {
        return new TimeframeList(stream()
                .filter(t -> t.endsOnDate(date))
                .collect(toList()));
    }
    
    /**
        FilterEndTime - Derive a sublist of timeframes ending on the given
        time
    
        @param time Time timeframes should end on
        @return A sublist of timeframes that end on the given time
    */
    
    public TimeframeList filterEndTime(LocalTime time)
    {
        return new TimeframeList(stream()
                .filter(t -> t.endsOnTime(time))
                .collect(toList()));
    }
    
    /**
        FilterNotReserved - Derive a sublist of timeframes that have not been
        reserved
    
        @return A sublist of timeframes that have not been reserved
    */
    
    public TimeframeList filterNotReserved()
    {
        return new TimeframeList(stream()
                .filter(t -> !t.isReserved())
                .collect(toList()));
    }
    
    /**
        FilterReserved - Derive a sublist of timeframes that have been reserved
    
        @return A sublist of timeframes that have been reserved
    */
    
    public TimeframeList filterReserved()
    {
        return new TimeframeList(stream()
                .filter(t -> t.isReserved())
                .collect(toList()));
    }
    
    /**
        FilterStartDate - Derive a sublist of timeframes starting on the given
        date
    
        @param date Date timeframes should start on
        @return A sublist of timeframes that start on the given date
    */
    
    public TimeframeList filterStartDate(LocalDate date)
    {
        return new TimeframeList(stream()
                .filter(t -> t.startsOnDate(date))
                .collect(toList()));
    }
    
    /**
        FilterStartTime - Derive a sublist of timeframes starting on the given
        time
    
        @param time Time timeframes should start on
        @return A sublist of timeframes that start on the given time
    */
    
    public TimeframeList filterStartTime(LocalTime time)
    {
        return new TimeframeList(stream()
                .filter(t -> t.startsOnTime(time))
                .collect(toList()));
    }
    
    /**
        Sort - Sort the list of timeframes according to the provided comparators
    
        @param cmprtrs Comparators to sort the list of timeframes by
    */
    
    public void sort(Comparator<Timeframe> ... cmprtrs)
    {
        MultiComparator<Timeframe> comparator = new MultiComparator(cmprtrs);
        sort(comparator);
    }
    
    /**
        MultiComparator inner class
    */
    
    private class MultiComparator<Timeframe> implements Comparator<Timeframe>
    {
        // Fields
        private final Comparator<Timeframe>[] comparators;

        /**
            Constructor - Accepts an array of timeframe comparators
            @param cmprtrs Array of timeframe comparators
        */
        
        public MultiComparator(Comparator<Timeframe>... cmprtrs)
        {
            comparators = cmprtrs;
        }
        
        /**
            Compare - Compare timeframes using the multiple comparators within
            this comparator
        
            @param t1 Timeframe 1
            @param t2 Timeframe 2
            @return The result of the comparison
        */

        @Override
        public int compare(Timeframe t1, Timeframe t2)
        {
            for (Comparator<Timeframe> c : comparators)
            {
                int result = c.compare(t1, t2);
                if (result != 0)
                    return result;
            }
            return 0;
        }
    }
}