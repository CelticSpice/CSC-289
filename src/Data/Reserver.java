/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.ArrayList;
import static java.util.Arrays.asList;

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
        reservations = new ArrayList<>(asList(reservationListing));
    }
    
    /**
        GetEmailAddress - Return the email address of the reserver
    
        @return The email address of the reserver
    */
    
    public String getEmailAddress()
    {
        return contactInfo.getEmail();
    }
    
    /**
        GetFirstName - Return the first name of the reserver
    
        @return The first name of the reserver
    */
    
    public String getFirstName()
    {
        return contactInfo.getFirstName();
    }
    
    /**
        GetName - Return the full name of the reserver
    
        @return The full name of the reserver
    */
    
    public String getName()
    {
        return contactInfo.getFullName(false);
    }
    
    /**
        GetLastName - Return the last name of the reserver
    
        @return The last name of the reserver
    */
    
    public String getLastName()
    {
        return contactInfo.getLastName();
    }
    
    /**
        GetPhoneNumber - Return the phone number of the reserver
    
        @return The phone number of the reserver
    */
    
    public String getPhoneNumer()
    {
        return contactInfo.getPhoneNumber();
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
        GetContactInfo - Get the contact info of this reserver
         
        @return the contact info
     */
    public ContactInfo getContactInfo()
    {
        return contactInfo;
    }
}