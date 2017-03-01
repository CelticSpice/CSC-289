/**
    A reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

public class Reservation
{
    // Fields
    private int numberAttending;
    private ReservableLocation locationReserved;
    private ReservableTimeframe timeframeReserved;
    private String eventType;
    
    /**
        Constructor - Accepts the location & the timeframe reserved, the
        expected number of attendees, and the event type
    
        @param location Reservable location reserved
        @param timeframe Reservable timeframe reserved
        @param numAttending Expected number of attendees
        @param type Event type
    */
    
    public Reservation(ReservableLocation location,
                       ReservableTimeframe timeframe,
                       int numAttending, String type)
    {
        numberAttending = numAttending;
        locationReserved = location;
        timeframeReserved = timeframe;
        eventType = type;
    }
}