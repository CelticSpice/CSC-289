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
        Constructs a new Reservation with the specified reserver, the reservable
        that is reserved, & the estimated number of attendees. The event type of
        the reservation will default to "General"
    
        @param rsver The reserver
        @param rble The reservable
        @param attendence Estimated number of attendees
    */
    
    public Reservation(Reserver rsver, Reservable rble, int attendence)
    {
        approved = false;
        reserver = rsver;
        numberAttending = attendence;
        reservable = rble;
        eventType = "General";
    }
    
   /**
        Constructs a new Reservation with the specified reserver, the reservable
        that is reserved, the estimated number of attendees, & the type of event
        the reservation entails
    
        @param rsver The reserver
        @param rble The reservable
        @param attendence Estimated number of attendees
        @param type Type of event the reservation entails
    */
    
    public Reservation(Reserver rsver, Reservable rble, int attendence,
            String type)
    {
        approved = false;
        reserver = rsver;
        numberAttending = attendence;
        reservable = rble;
        eventType = type;
    }
    
    /**
        Constructs a new Reservation with the specified reserver, the reservable
        that is reserved, the estimated number of attendees, the type of event
        the reservation entails, & whether the reservation is approved
    
        @param rsver The reserver
        @param rble The reservable
        @param attendence Estimated number of attendees
        @param type Type of event the reservation entails
        @param proved If the reservation is approved
    */
    
    public Reservation(Reserver rsver, Reservable rble, int attendence,
            String type, boolean proved)
    {
        approved = proved;
        reserver = rsver;
        numberAttending = attendence;
        reservable = rble;
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
        IsApproved - Return if the reservation is approved
    
        @return If the reservation is approved
    */
    
    public boolean isApproved()
    {
        return approved;
    }
}