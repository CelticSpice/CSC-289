/**
    A reservable timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservableTimeframe extends Timeframe
{
    private BigDecimal cost;
    private boolean reserved;
    
    /**
        Constructs a new ReservableTimeframe initialized with the given starting
        & ending datetimes
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
    */
    
    public ReservableTimeframe(LocalDateTime sDateTime, LocalDateTime eDateTime)
    {
        super(sDateTime, eDateTime);
        cost = new BigDecimal(0);
        reserved = false;
    }
    
    /**
        Constructs a new ReservableTimeframe initialized with the given starting
        & ending datetimes, & the cost to reserve it
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @throws IllegalArgumentException Cost is less than $0.00
    */
    
    public ReservableTimeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            BigDecimal c) throws IllegalArgumentException
    {
        super(sDateTime, eDateTime);
        
        // Check that cost is greater than or equal to $0.00
        if (!(c.compareTo(new BigDecimal(0.00)) >= 0))
            throw new IllegalArgumentException("Cost less than $0.00");
        
        cost = c;
        reserved = false;
    }
    
    /**
        Constructs a new ReservableTimeframe initialized with the given starting
        & ending datetimes, the cost to reserve it, & the ID
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @param id The timeframe ID
        @throws IllegalArgumentException Cost is less than $0.00
    */
    
    public ReservableTimeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            BigDecimal c, int id)
    {
        super(sDateTime, eDateTime, id);
        
        // Check that cost is greater than or equal to $0.00
        if (!(c.compareTo(new BigDecimal(0.00)) >= 0))
            throw new IllegalArgumentException("Cost less than $0.00");
        
        cost = c;
        reserved = false;
    }
    
    /**
        Constructs a new ReservableTimeframe initialized with the given starting
        & ending datetimes, the cost to reserve it, whether the timeframe is
        reserved, & ID
    
        @param sDateTime The starting date & time
        @param eDateTime The ending date & time
        @param c The cost to reserve the timeframe
        @param isRsrvd If the timeframe is reserved
        @param id The timeframe ID
        @throws IllegalArgumentException Cost is less than $0.00
    */
    
    public ReservableTimeframe(LocalDateTime sDateTime, LocalDateTime eDateTime,
            BigDecimal c, boolean isRsrvd, int id)
    {
        super(sDateTime, eDateTime, id);
        
        // Check that cost is greater than or equal to $0.00
        if (!(c.compareTo(new BigDecimal(0.00)) >= 0))
            throw new IllegalArgumentException("Cost less than $0.00");
        
        cost = c;
        reserved = isRsrvd;
    }
    
    /**
        Cancels the reservation of the timeframe
    */
    
    public void cancelReserve()
    {
        reserved = false;
    }
    
    /**
        Returns the cost to reserve the timeframe
    
        @return The cost to reserve the timeframe
    */
    
    public BigDecimal getCost()
    {
        return cost;
    }
    
    /**
        Returns if the timeframe is reserved
    
        @return If the timeframe is reserved
    */
    
    public boolean isReserved()
    {
        return reserved;
    }
    
    /**
        Reserves the timeframe
    */
    
    public void reserve()
    {
        reserved = true;
    }
    
    /**
        Sets the cost to reserve the timeframe
    
        @param c The cost to reserve the timeframe
    */
    
    public void setCost(BigDecimal c)
    {
        cost = c;
    }
}