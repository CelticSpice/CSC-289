/**
    A reservable, encapsulating a location & timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reservable
{
    private ReservableLocation location;
    private ReservableTimeframe timeframe;
    
    /**
        Constructs a new Reservable with the given location & timeframe
    
        @param loc The location
        @param time The timeframe
    */
    
    public Reservable(ReservableLocation loc, ReservableTimeframe time)
    {
        location = loc;
        timeframe = time;
    }
    
    /**
        Cancels the reservation of the reservable
    */
    
    public void cancelReservation()
    {
        timeframe.cancelReserve();
    }
    
    /**
        Returns the capacity of the reservable's location
    
        @return The capacity of the reservable's location
    */
    
    public int getCapacity()
    {
        return location.getCapacity();
    }
    
    /**
        Returns the cost to reserve the reservable
    
        @return The cost to reserve the reservable
    */
    
    public BigDecimal getCost()
    {
        return timeframe.getCost();
    }
    
    /**
        Returns the ending date of the reservable
    
        @return The ending date of the reservable
    */
    
    public LocalDate getEndDate()
    {
        return timeframe.getEndDate();
    }
    
    /**
        Returns the ending time of the reservable
    
        @return The ending time of the reservable
    */
    
    public LocalTime getEndTime()
    {
        return timeframe.getEndTime();
    }
    
    /**
        Returns the location of the reservable
    
        @return The location of the reservable
    */
    
    public ReservableLocation getLocation()
    {
        return location;
    }
    
    /**
        Returns the ID of the reservable's location
    
        @return ID of reservable's location
    */
    
    public int getLocationID()
    {
        return location.getID();
    }
    
    /**
        Returns the location name of the reservable
    
        @return The name of the reservable's location
    */
    
    public String getName()
    {
        return location.getName();
    }
    
    /**
        Returns the starting date of the reservable
    
        @return The starting date of the reservable
    */
    
    public LocalDate getStartDate()
    {
        return timeframe.getStartDate();
    }
    
    /**
        Returns the starting time of the reservable
    
        @return The starting time of the reservable
    */
    
    public LocalTime getStartTime()
    {
        return timeframe.getStartTime();
    }
    
    /**
        Returns the timeframe of the reservable
    
        @return The timeframe of the reservable
    */
    
    public ReservableTimeframe getTimeframe()
    {
        return timeframe;
    }
    
    /**
        Returns the ID of the reservable's timeframe
    
        @return ID of reservable's timeframe
    */
    
    public int getTimeframeID()
    {
        return timeframe.getID();
    }
    
    /**
        Returns if the reservable is reserved
    
        @return If the reservable is reserved
    */
    
    public boolean isReserved()
    {
        return timeframe.isReserved();
    }
    
    /**
        Reserves the reservable
    */
    
    public void reserve()
    {
        timeframe.reserve();
    }
    
    /**
        Sets the capacity of the reservable's location's capacity
    
        @param cap The reservable's location's capacity
    */
    
    public void setLocationCapacity(int cap)
    {
        location.setCapacity(cap);
    }
    
    /**
        Sets the cost of the reservable
    
        @param cost Cost of the reservable
    */
    
    public void setCost(BigDecimal cost)
    {
        timeframe.setCost(cost);
    }
    
    /**
        Sets the name of the reservable's location
    
        @param name Name of reservable's location
    */
    
    public void setLocationName(String name)
    {
        location.setName(name);
    }
}