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
        Cancels the reservation
    */
    
    public void cancel()
    {
        reservable.cancelReservation();
    }
    
    /**
        Marks the reservation as reviewed
    */
    
    public void reviewed()
    {
        reviewed = true;
    }
    
    /**
        Returns the cost of the reservation
    
        @return The cost of the reservation
    */
    
    public BigDecimal getCost()
    {
        return reservable.getCost();
    }
    
    /**
        Returns the ending date of the reservation
    
        @return The ending date of the reservation
    */
    
    public LocalDate getEndDate()
    {
        return reservable.getEndDate();
    }
    
    /**
        Returns the ending time of the reservation
    
        @return The ending time of the reservation
    */
    
    public LocalTime getEndTime()
    {
        return reservable.getEndTime();
    }
    
    /**
        Returns the type of event the reservation entails
    
        @return The type of event the reservation was made for
    */
    
    public String getEventType()
    {
        return eventType;
    }
    
    /**
        Returns the location of this reservation
    
        @return The location of this reservation
    */
    
    public ReservableLocation getLocation()
    {
        return reservable.getLocation();
    }
    
    /**
        Returns the capacity of the reservation's location
    
        @return Capacity of reservation's location
    */
    
    public int getLocationCapacity()
    {
        return reservable.getCapacity();
    }
    
    /**
        Returns the ID of the reservation's location
    
        @return ID of reservation's location
    */
    
    public int getLocationID()
    {
        return reservable.getLocationID();
    }
    
    /**
        Returns the name of the location reserved
    
        @return The name of the location reserved
    */
    
    public String getLocationName()
    {
        return reservable.getName();
    }
    
    /**
        Returns the expected number of attendees
    
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
        Returns the email address of the reserver
    
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
        Returns the ID of the reservation's reserver
    
        @return ID of reservation's reserver
    */
    
    public int getReserverID()
    {
        return reserver.getID();
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
        Returns the name of the reserver
    
        @return The name of the reserver
    */
    
    public String getReserverName()
    {
        return reserver.getName();
    }
    
    /**
        Returns the phone number of the reserver
    
        @return The phone number of the reserver
    */
    
    public String getReserverPhone()
    {
        return reserver.getPhoneNumber();
    }
    
    /**
        Returns the starting date of the reservation
    
        @return The starting date of the reservation
    */
    
    public LocalDate getStartDate()
    {
        return reservable.getStartDate();
    }
    
    /**
        Returns the starting time of the reservation
    
        @return The starting time of the reservation
    */
    
    public LocalTime getStartTime()
    {
        return reservable.getStartTime();
    }
    
    /**
        Returns the timeframe of the reservation
    
        @return The reservation's timeframe
    */
    
    public ReservableTimeframe getTimeframe()
    {
        return reservable.getTimeframe();
    }
    
    /**
        Returns the ID of the reservation's timeframe
    
        @return ID of reservation's timeframe
    */
    
    public int getTimeframeID()
    {
        return reservable.getTimeframeID();
    }
    
    /**
        Returns if the reservation is reviewed
    
        @return If the reservation is reviewed
    */
    
    public boolean isReviewed()
    {
        return reviewed;
    }
    
    /**
        Marks the reservation as not reviewed
    */
    
    public void notReviewed()
    {
        reviewed = false;
    }
    
    /**
        Sets the event type of the reservation
    
        @param type Reservation event type
    */
    
    public void setEventType(String type)
    {
        eventType = type;
    }
    
    /**
        Sets the reservation's expected number attending
    
        @param numAttending Expected number attending
    */
    
    public void setNumAttending(int numAttending)
    {
        numberAttending = numAttending;
    }
    
    /**
        Sets the reserver's email
    
        @param e Reserver's email
    */
    
    public void setReserverEmail(String e)
    {
        reserver.setEmail(e);
    }
    
    /**
        Sets the reserver's first name
    
        @param name Reserver's first name
    */
    
    public void setReserverFirstName(String name)
    {
        reserver.setFirstName(name);
    }
    
    /**
        Sets the reserver's last name
    
        @param name Reserver's last name
    */
    
    public void setReserverLastName(String name)
    {
        reserver.setLastName(name);
    }
    
    /**
        Sets the reserver's phone number
    
        @param p Reserver's phone number
    */
    
    public void setReserverPhone(String p)
    {
        reserver.setPhoneNumber(p);
    }
}