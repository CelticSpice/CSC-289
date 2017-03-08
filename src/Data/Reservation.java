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
        Constructor - Accepts the location & the
        timeframe reserved, and the expected number of attendees
    
        @param location Reservable location reserved
        @param timeframe Reservable timeframe reserved
        @param numAttending Expected number of attendees
    */
    
    public Reservation(ReservableLocation location,
                       ReservableTimeframe timeframe,
                       int numAttending)
    {
        numberAttending = numAttending;
        locationReserved = location;
        timeframeReserved = timeframe;
        eventType = "General";
    }
    
    /**
        Constructor - Accepts the location & the
        timeframe reserved, expected number of attendees, and the event type
        
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
}