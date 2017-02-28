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
        Constructor - Accepts the reservable location's name and capacity
    
        @param name The reservable location's name
        @param capacity The reservable location's capacity
    */
    
    public ReservableLocation(String name, int capacity)
    {
        super(name, capacity);
        reserveCosts = new ArrayList<>();
        reserveTimeframes = new ArrayList<>();
    }
    
    /**
        AddReserveSlot - Add a timeframe and cost that the reservable location
        can be reserved for
    */
}