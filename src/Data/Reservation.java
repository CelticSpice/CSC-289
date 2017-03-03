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
    private Reserver reserver;
    private ReservableLocation locationReserved;
    private ReservableTimeframe timeframeReserved;
    private String eventType;
    
    /**
        Constructor - Accepts who made the reservation, the location & the
        timeframe reserved, and the expected number of attendees
    
        @param res Who made the reservation
        @param location Reservable location reserved
        @param timeframe Reservable timeframe reserved
        @param numAttending Expected number of attendees
    */
    
    public Reservation(Reserver res, ReservableLocation location,
                       ReservableTimeframe timeframe,
                       int numAttending)
    {
        numberAttending = numAttending;
        reserver = res;
        locationReserved = location;
        timeframeReserved = timeframe;
        eventType = "General";
    }
    
    /**
        Constructor - Accepts who made the reservation, the location & the
        timeframe reserved, expected number of attendees, and the event type
        
        @param res Who made the reservation
        @param location Reservable location reserved
        @param timeframe Reservable timeframe reserved
        @param numAttending Expected number of attendees
        @param type Event type
    */
    
    public Reservation(Reserver res, ReservableLocation location,
                       ReservableTimeframe timeframe,
                       int numAttending, String type)
    {
        numberAttending = numAttending;
        reserver = res;
        locationReserved = location;
        timeframeReserved = timeframe;
        eventType = type;
    }
    
    /**
        Cancel - Cancel the reservation
    */
    
    public void cancel()
    {
        locationReserved.cancelReserve
            (locationReserved.indexOfTimeframe(timeframeReserved));
        
        reserver = null;
    }
    
    /**
        GetEventType - Return the type of event the reservation entails
    
        @return The type of event the reservation was made for
    */
    
    public String getEventType()
    {
        return eventType;
    }
    
    /**
        GetLocation - Return the location reserved
    
        @return The location reserved
    */
    
    public ReservableLocation getLocation()
    {
        return locationReserved;
    }
    
    /**
        GetNumberAttending - Return the expected number of attendees
    
        @return The expected number of attendees
    */
    
    public int getNumberAttending()
    {
        return numberAttending;
    }
    
    /**
        GetReservedTimeframe - Return the timeframe reserved
    
        @return The timeframe reserved
    */
    
    public ReservableTimeframe getReservedTimeframe()
    {
        return timeframeReserved;
    }
    
    /**
        GetReserver - Return who made the reservation
    
        @return Who made the reservation
    */
    
    public Reserver getReserver()
    {
        return reserver;
    }
}