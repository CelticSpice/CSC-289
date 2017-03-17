/**
    A reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation
{
    // Fields
    private boolean approved;
    private int numberAttending;
    private Reservable reservable;
    private Reserver reserver;
    private String eventType;
    
    /**
        Constructor - Accepts the reserver, reservable & the expected number of
        attendees
    
        @param rver The reserver
        @param rble The reservable
        @param numAttending Expected number of attendees
    */
    
    public Reservation(Reserver rver, Reservable rble, int numAttending)
    {
        approved = false;
        numberAttending = numAttending;
        reservable = rble;
        reserver = rver;
        eventType = "General";
    }
    
   /**
        Constructor - Accepts the reserver, reservable, expected number of
        attendees, & the event type
    
        @param rver The reserver
        @param rble The reservable
        @param numAttending Expected number of attendees
        @param type The event type
    */
    
    public Reservation(Reserver rver, Reservable rble, int numAttending,
                       String type)
    {
        approved = false;
        numberAttending = numAttending;
        reservable = rble;
        reserver = rver;
        eventType = type;
    }
    
    /**
        Approve - Approve the reservation
    */
    
    public void approve()
    {
        approved = true;
    }
    
    /**
        GetCost - Return the cost of the reservation
    
        @return The cost of the reservation
    */
    
    public BigDecimal getCost()
    {
        return reservable.getCost();
    }
    
    /**
        GetEndDate - Return the ending date of the reservation
    
        @return The ending date of the reservation
    */
    
    public LocalDate getEndDate()
    {
        return reservable.getEndDate();
    }
    
    /**
        GetEndTime - Return the ending time of the reservation
    
        @return The ending time of the reservation
    */
    
    public LocalTime getEndTime()
    {
        return reservable.getEndTime();
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
        GetLocationName - Return the name of the location reserved
    
        @return The name of the location reserved
    */
    
    public String getLocationName()
    {
        return reservable.getName();
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
        GetReserver - Return the reserver of the reservation
    
        @return The reserver
    */
    
    public Reserver getReserver()
    {
        return reserver;
    }
    
    /**
        GetReserverEmail - Return the email address of the reserver
    
        @return The email address of the reserver
    */
    
    public String getReserverEmail()
    {
        return reserver.getEmailAddress();
    }
    
    /**
        GetReserverName - Return the name of the reserver
    
        @return The name of the reserver
    */
    
    public String getReserverName()
    {
        return reserver.getName();
    }
    
    /**
        GetReserverPhone - Return the phone number of the reserver
    */
    
    public String getReserverPhone()
    {
        return reserver.getPhoneNumber();
    }
    
    /**
        GetStartDate - Return the starting date of the reservation
    
        @return The starting date of the reservation
    */
    
    public LocalDate getStartDate()
    {
        return reservable.getStartDate();
    }
    
    /**
        GetStartTime - Return the starting time of the reservation
    
        @return The starting time of the reservation
    */
    
    public LocalTime getStartTime()
    {
        return reservable.getStartTime();
    }
    
    /**
        IsApproved - Return if the reservation is approved
    
        @return If the reservation is approved
    */
    
    public boolean isApproved()
    {
        return approved;
    }
}