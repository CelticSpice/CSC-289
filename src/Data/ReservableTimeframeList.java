/**
    A list of reservable timeframes
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

public class ReservableTimeframeList extends ArrayList<ReservableTimeframe>
{    
    /*
        Static class objects
    */
    
    /**
        COST_COMPARATOR - Comparator to compare reservable timeframes on their
        cost
    */
    
    public static final Comparator<ReservableTimeframe> COST_COMPARATOR =
            (a, b) -> a.getCost().compareTo(b.getCost());
    
    /**
        END_DATE_COMPARATOR - Comparator to compare reservable timeframes on
        their end dates
    */
    
    public static final Comparator<ReservableTimeframe> END_DATE_COMPARATOR =
            (a, b) -> {
                LocalDate dateA = a.getEndDate();
                LocalDate dateB = b.getEndDate();
                return dateA.compareTo(dateB);
            };
    
    /**
        END_TIME_COMPARATOR - Comparator to compare reservable timeframes on
        their end times
    */
    
    public static final Comparator<ReservableTimeframe> END_TIME_COMPARATOR =
            (a, b) -> {
                LocalTime timeA = a.getEndTime();
                LocalTime timeB = b.getEndTime();
                return timeA.compareTo(timeB);
            };
    
    /**
        START_DATE_COMPARATOR - Comparator to compare reservable timeframes on
        their start dates
    */
    
    public static final Comparator<ReservableTimeframe> START_DATE_COMPARATOR =
            (a, b) -> {
                LocalDate dateA = a.getStartDate();
                LocalDate dateB = b.getStartDate();
                return dateA.compareTo(dateB);
            };
    
    /**
        START_TIME_COMPARATOR - Comparator to compare reservable timeframes on
        their start times
    */
    
    public static final Comparator<ReservableTimeframe> START_TIME_COMPARATOR =
            (a, b) -> {
                LocalTime timeA = a.getStartTime();
                LocalTime timeB = b.getStartTime();
                return timeA.compareTo(timeB);
            };
    
    /**
        Constructor
    */
    
    public ReservableTimeframeList()
    {
        super();
    }
    
    /**
        Constructor - Accepts a list of reservable timeframes to include
        in the list
    
        @param timeframes List of reservable timeframes to include in the list
    */
    
    public ReservableTimeframeList(List<ReservableTimeframe> timeframes)
    {
        super(timeframes);
    }
    
    /**
        FilterCost - Derive a sublist of timeframes with the given cost
    
        @param cost Amount timeframes should cost
        @return A sublist of timeframes with the given cost
    */
    
    public ReservableTimeframeList filerCost(BigDecimal cost)
    {
        return new ReservableTimeframeList(stream()
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
    
    public ReservableTimeframeList filterCost(BigDecimal costA,
                                                BigDecimal costB)
    {
        return new ReservableTimeframeList(stream()
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
    
    public ReservableTimeframeList filterEndDate(LocalDate date)
    {
        return new ReservableTimeframeList(stream()
                .filter(t -> t.endsOnDate(date))
                .collect(toList()));
    }
    
    /**
        FilterEndTime - Derive a sublist of timeframes ending on the given
        time
    
        @param time Time timeframes should end on
        @return A sublist of timeframes that end on the given time
    */
    
    public ReservableTimeframeList filterEndTime(LocalTime time)
    {
        return new ReservableTimeframeList(stream()
                .filter(t -> t.endsOnTime(time))
                .collect(toList()));
    }
    
    /**
        FilterNotReserved - Derive a sublist of timeframes that have not been
        reserved
    
        @return A sublist of timeframes that have not been reserved
    */
    
    public ReservableTimeframeList filterNotReserved()
    {
        return new ReservableTimeframeList(stream()
                .filter(t -> !t.isReserved())
                .collect(toList()));
    }
    
    /**
        FilterReserved - Derive a sublist of timeframes that have been reserved
    
        @return A sublist of timeframes that have been reserved
    */
    
    public ReservableTimeframeList filterReserved()
    {
        return new ReservableTimeframeList(stream()
                .filter(t -> t.isReserved())
                .collect(toList()));
    }
    
    /**
        FilterStartDate - Derive a sublist of timeframes starting on the given
        date
    
        @param date Date timeframes should start on
        @return A sublist of timeframes that start on the given date
    */
    
    public ReservableTimeframeList filterStartDate(LocalDate date)
    {
        return new ReservableTimeframeList(stream()
                .filter(t -> t.startsOnDate(date))
                .collect(toList()));
    }
    
    /**
        FilterStartTime - Derive a sublist of timeframes starting on the given
        time
    
        @param time Time timeframes should start on
        @return A sublist of timeframes that start on the given time
    */
    
    public ReservableTimeframeList filterStartTime(LocalTime time)
    {
        return new ReservableTimeframeList(stream()
                .filter(t -> t.startsOnTime(time))
                .collect(toList()));
    }
}