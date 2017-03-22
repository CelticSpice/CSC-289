/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.ArrayList;
import java.util.List;

public class Reserver
{
    // Fields
    private ContactInfo contact;
    private List<Reservation> reservations;
    
    /**
        Constructor - Accepts the contact information of the reserver
    
        @param c Contact info
    */
    
    public Reserver(ContactInfo c)
    {
        contact = c;
        reservations = new ArrayList<>();
    }
    
    /**
        Constructor - Accepts the contact information of the reserver & a
        list of reservations that the reserver has made
    
        @param c Contact info
        @param reservationList Reservations that the reserver has made
    */
    
    public Reserver(ContactInfo c, List<Reservation> reservationList)
    {
        contact = c;
        reservations = reservationList;
    }
    
    /**
        GetEmailAddress - Return the email address of the reserver
    
        @return The email address of the reserver
    */
    
    public String getEmailAddress()
    {
        return contact.getEmail();
    }
    
    /**
        GetFirstName - Return the first name of the reserver
    
        @return The first name of the reserver
    */
    
    public String getFirstName()
    {
        return contact.getFirstName();
    }
    
    /**
        GetName - Return the full name of the reserver
    
        @return The full name of the reserver
    */
    
    public String getName()
    {
        return contact.getFullName(false);
    }
    
    /**
        GetLastName - Return the last name of the reserver
    
        @return The last name of the reserver
    */
    
    public String getLastName()
    {
        return contact.getLastName();
    }
    
    /**
        GetPhoneNumber - Return the phone number of the reserver
    
        @return The phone number of the reserver
    */
    
    public String getPhoneNumber()
    {
        return contact.getPhoneNumber();
    }
    
    /**
        GetReservations - Return the reservations that the reserver has made
    
        @return The reservations the reserver made
    */
    
    public List<Reservation> getReservations()
    {
        return new ArrayList<>(reservations);
    }
}