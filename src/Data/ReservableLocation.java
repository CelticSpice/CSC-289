/**
    A reservable location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ReservableLocation extends Location
{
    // Fields
    private ArrayList<BigDecimal> reserveCosts;
    private ArrayList<Timeframe> reserveTimeframes;
    
    /**
        Constructor - Accepts the reservable location's name, capacity,
        starting and ending dates and times, and cost
    
        @param n The reservable location's name
        @param cap The reservable location's capacity
        @param sDateTime The reservable location's start date and time
        @param eDateTime The reservable location's end date and time
        @param c The cost to reserve the location
    */
    
    public ReservableLocation(String n, int cap, GregorianCalendar sDateTime,
                              GregorianCalendar eDateTime, BigDecimal c)
    {
        super(n, cap);
        startDateTime = sDateTime;
        endDateTime = eDateTime;
        cost = c;
    }
    
    
}