/**
    A reservable location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.ArrayList;

public class ReservableLocation extends Location
{
    // Fields
    private ArrayList<ReservableTimeframe> reserveTimeframes;
    
    /**
        Constructor - Accepts the reservable location's name & capacity
    
        @param name The reservable location's name
        @param capacity The reservable location's capacity
    */
    
    public ReservableLocation(String name, int capacity)
    {
        super(name, capacity);
        reserveTimeframes = new ArrayList<>();
    }
    
    /**
        AddReservableTimeframe - Add a timeframe the location
        can be reserved for
    
        @param reserveTimeframe Timeframe the location can be
                                reserved for
    */
    
    public void addReservableTimeframe(ReservableTimeframe reserveTimeframe)
    {
        reserveTimeframes.add(reserveTimeframe);
    }
    
    /**
        GetNumReservableTimeframes - Return the number of reservable timeframes
        allocated to the location
    
        @return The number of reservable timeframes allocated to the location
    */
    
    public int getNumReservableTimeframes()
    {
        return reserveTimeframes.size();
    }
    
    /**
        RemoveReservableTimeframe - Remove the timeframe the location
        can be reserved for specified by the index given
    
        @param index Index specifying timeframe the location can be
                     reserved for to remove
    */
    
    public void removeReservableTimeframe(int index)
    {
        reserveTimeframes.remove(index);
    }
}