/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

public class Reserver
{
    // Fields
    private ContactInfo contactInfo;
    private ReservationList reservations;
    
    /**
        Constructor - Accepts the contact information of the reserver
    
        @param contact Contact info
    */
    
    public Reserver(ContactInfo contact)
    {
        contactInfo = contact;
        reservations = new ReservationList();
    }
    
    /**
        Constructor - Accepts the contact information of the reserver & a
        list of reservations that the reserver has made
    
        @param contact Contact info
        @param reservationList Reservations that the reserver has made
    */
    
    public Reserver(ContactInfo contact, ReservationList reservationList)
    {
        contactInfo = contact;
        reservations = reservationList;
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
    
    public String getPhoneNumber()
    {
        return contactInfo.getPhoneNumber();
    }
    
    /**
        GetReservations - Return the reservations that the reserver has made
    
        @return The reservations the reserver made
    */
    
    public ReservationList getReservations()
    {
        return reservations;
    }
}