/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

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
        AddReservation - Adds a reservation to the list of the reserver's
        reservations
    
        @param reservation Reservation to add
    */
    
    public void addReservation
    
    /**
        GetEmail - Return the email address of the reserver
    
        @return Email address of the reserver
    */
    
    public String getEmail()
    {
        return contactInfo.getEmail();
    }
    
    /**
        GetFirstName - Return the first name of the reserver
    
        @return First name of the reserver
    */
    
    public String getFirstName()
    {
        return contactInfo.getFirstName();
    }
    
    /**
        GetFullName - Return full name of the reserver
    
        @param lastFirst Whether to return in format last, first
        @return Full name of reserver
    */
    
    public String getFullName(boolean lastFirst)
    {
        return contactInfo.getFullName(lastFirst);
    }
    
    /**
        GetLastName - Return the last name of the reserver
    
        @return Last name of reserver
    */
    
    public String getLastName()
    {
        return contactInfo.getLastName();
    }
    
    /**
        GetPhoneNumber - Return the phone number of the reserver
    
        @return Phone number of reserver
    */
    
    public String getPhoneNumber()
    {
        return contactInfo.getPhoneNumber();
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
        reservations.add(new Reservation(location, timeframe, numberAttending,
                                         eventType));
    }
}