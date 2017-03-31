/**
 * A reservation search
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Reservation;
import java.util.function.Predicate;

public class ReservationSearch
{
    // Fields
    Predicate<Reservation> locationName;
    Predicate<Reservation> startDate;
    Predicate<Reservation> startTime;
    Predicate<Reservation> endDate;
    Predicate<Reservation> endTime;
    Predicate<Reservation> cost;
    Predicate<Reservation> reserver;
    Predicate<Reservation> finalPredicate;
    
    public ReservationSearch()
    {
        
    }
}
