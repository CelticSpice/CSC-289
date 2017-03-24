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
    private boolean reviewed;
    private int numberAttending;
    private Reservable reservable;
    private Reserver reserver;
    private String eventType;
    
    /**
        Constructs a new Reservation with the specified reserver, the reservable
        that is reserved, the estimated number of attendees, the type of event
        the reservation entails, & whether the reservation is reviewed
    
        @param rsver The reserver
        @param rble The reservable
        @param attendence Estimated number of attendees
        @param type Type of event the reservation entails
        @param viewed If the reservation is reviewed
    */
    
    public Reservation(Reserver rsver, Reservable rble, int attendence,
            String type, boolean viewed)
    {
        reviewed = viewed;
        reserver = rsver;
        numberAttending = attendence;
        reservable = rble;
        eventType = type;
    }
    
    /**
        Reviewed - Approve the reservation
    */
    
    public void approve()
    {
        reviewed = true;
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
        Returns the cost of the reservations as a string
        
        @return The cost of the reservation as a string
    */
    
    public String getCostString()
    {
        return reservable.getTimeframe().getCostString();
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
        Returns the who made the reservation
    
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
        Returns the first name of the reserver
    
        @return The reserver's first name
    */
    
    public String getReserverFirstName()
    {
        return reserver.getFirstName();
    }
    
    /**
        Returns the last name of the reserver
    
        @return The reserver's last name
    */
    
    public String getReserverLastName()
    {
        return reserver.getLastName();
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
    
        @return The phone number of the reserver
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
        IsReviewed - Return if the reservation is reviewed
    
        @return If the reservation is reviewed
    */
    
    public boolean isReviewed()
    {
        return reviewed;
    }
}