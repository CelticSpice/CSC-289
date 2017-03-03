/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.Arrays;
import java.util.ArrayList;

public class Reserver
{
    // Fields
    private ContactInfo contactInfo;
    private ArrayList<Reservation> reservations;
    
    /**
        Constructor - Accepts the contact information of the reserver
    
        @param contact Contact info
    */
    
    public Reserver(ContactInfo contact)
    {
        contactInfo = contact;
        reservations = new ArrayList<>();
    }
    
    /**
        Constructor - Accepts the contact information of the reserver & a
        list of reservations that the reserver has made
    
        @param contact Contact info
        @param reservationListing Reservations that the reserver has made
    */
    
    public Reserver(ContactInfo contact, Reservation[] reservationListing)
    {
        contactInfo = contact;
        reservations = new ArrayList<>(Arrays.asList(reservationListing));
    }
    
    /**
        GetContactInfo - Return the contact information of the reserver
    
        @return The contact information of the reserver
    */
    
    public ContactInfo getContactInfo()
    {
        return contactInfo;
    }
    
    /**
        GetReservations - Return the reservations that the reserver has made
    
        @return The reservations the reserver made
    */
    
    public Reservation[] getReservations()
    {
        return reservations.toArray(new Reservation[reservations.size()]);
    }
    
    /**
        MakeReservation - Reserve a location for a timeframe. Accepts the
        location & timeframe to reserve, the number of people expected to
        attend, and the type of event the location is reserved for
    
        @param location Location to reserve
        @param timeframe Timeframe to reserve the location for
        @param numberAttending Expected number of attendees
        @param eventType Type of event reserving for
    */
    
    public void makeReservation(ReservableLocation location,
                                ReservableTimeframe timeframe,
                                int numberAttending, String eventType)
    {
        reservations.add(new Reservation(this, location, timeframe,
                                         numberAttending, eventType));
    }
    
    /**
        RemoveReservation - Remove the reservation specified by the given index
    
        @param index Index of reservation the reserver has made to remove
    */
    
    public void removeReservation(int index)
    {
        reservations.remove(index);
    }
}