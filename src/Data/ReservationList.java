/**
    A list of reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class ReservationList extends ArrayList<Reservation>
{
    /**
        Constructor
    */
    
    public ReservationList()
    {
        super();
    }
    
    /**
        Constructor - Accepts a list of reservations
    
        @param reservations A list of reservations
    */
    
    public ReservationList(List<Reservation> reservations)
    {
        super(reservations);
    }
    
    /**
        FilterApproved - Derive a sublist of reservations that have been
        approved
    
        @return A sublist of reservations that have been approved
    */
    
    public ReservationList filterApproved()
    {
        return new ReservationList(stream()
                .filter(r -> r.isApproved())
                .collect(toList()));
    }
    
    /**
        FilterCost - Derive a sublist of reservations with the given cost
    
        @param cost Amount reservations should cost
        @return A sublist of reservations with the given cost
    */
    
    public ReservationList filerCost(BigDecimal cost)
    {
        return new ReservationList(stream()
                .filter(r -> r.getCost().equals(cost))
                .collect(toList()));
    }
    
    /**
        FilterCost - Derive a sublist of reservations between the given costs,
        inclusively
    
        @param costA Amount reservations should cost at minimum
        @param costB Amount reservations should cost at maximum
        @return A sublist of reservations with costs between the given costs,
                inclusively
    */
    
    public ReservationList filterCost(BigDecimal costA,
                                      BigDecimal costB)
    {
        return new ReservationList(stream()
                .filter(r -> r.getCost().compareTo(costA) >= 0 &&
                             r.getCost().compareTo(costB) <= 0)
                .collect(toList()));
    }
    
    /**
        FilterNotApproved - Derive a sublist of reservations that have not
        been approved
    
        @return A sublist of reservations that have not been approved
    */
    
    public ReservationList filterNotApproved()
    {
        return new ReservationList(stream()
                .filter(r -> !r.isApproved())
                .collect(toList()));
    }
    
    /**
        FilterEndDate - Derive a sublist of reservations ending on the given
        date
    
        @param date Date reservations should end on
        @return A sublist of reservations that end on the given date
    */
    
    public ReservationList filterEndDate(LocalDate date)
    {
        return new ReservationList(stream()
                .filter(r -> date.equals(r.getEndDate()))
                .collect(toList()));
    }
    
    /**
        FilterEndTime - Derive a sublist of reservations ending on the given
        time
    
        @param time Time reservations should end on
        @return A sublist of reservations that end on the given time
    */
    
    public ReservationList filterEndTime(LocalTime time)
    {
        return new ReservationList(stream()
                .filter(r -> time.equals(r.getEndTime()))
                .collect(toList()));
    }
    
    /**
        FilterEventType - Derive a sublist of reservations with the given
        event type
    
        @param type The type of event reservations should have
        @return A sublist of reservations with the given event type
    */
    
    public ReservationList filterEventType(String type)
    {
        return new ReservationList(stream()
                .filter(r -> r.getEventType().equalsIgnoreCase(type))
                .collect(toList()));
    }
    
    /**
        FilterNumAttending - Derive a sublist of reservations with the given
        expected number of attendees
    
        @param numAttending Expected number of attendees reservations should
                            have
        @return A sublist of reservations with the given expected number of
                attendees
    */
    
    public ReservationList filterNumAttending(int numAttending)
    {
        return new ReservationList(stream()
                .filter(r -> r.getNumberAttending() == numAttending)
                .collect(toList()));
    }
    
    /**
        FilterNumAttending - Derive a sublist of reservations with the given
        expected number of attendees between the given numbers, inclusively
    
        @param minNum Minimum expected number of attendees reservations should
                      have
        @param maxNum Maximum expected number of attendees reservations should
                      have
        @return A sublist of reservations with the given expected number of
                attendees between the given numbers, inclusively
    */
    
    public ReservationList filterNumAttending(int minNum, int maxNum)
    {
        return new ReservationList(stream()
                .filter(r -> r.getNumberAttending() >= minNum &&
                             r.getNumberAttending() <= maxNum)
                .collect(toList()));
    }
    
    /**
        FilterStartDate - Derive a sublist of reservations starting on the given
        date
    
        @param date Date reservations should start on
        @return A sublist of reservations that start on the given date
    */
    
    public ReservationList filterStartDate(LocalDate date)
    {
        return new ReservationList(stream()
                .filter(r -> r.getStartDate().equals(date))
                .collect(toList()));
    }
    
    /**
        FilterStartTime - Derive a sublist of reservations starting on the given
        time
    
        @param time Time reservations should start on
        @return A sublist of reservations that start on the given time
    */
    
    public ReservationList filterStartTime(LocalTime time)
    {
        return new ReservationList(stream()
                .filter(r -> r.getStartTime().equals(time))
                .collect(toList()));
    }
}