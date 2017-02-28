/**
    A timeframe & associated cost that a reservable location can be reserved for
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ReservableTimeframe extends Timeframe
{
    // Fields
    private BigDecimal cost;
    
    /**
        Constructor - Accepts the starting & ending dates & times of the
        reservable timeframe, and the cost to reserve it
    
        @param startDateTime The starting date & time of the timeframe
        @param endDateTime The starting date & time of the timeframe
        @param c The cost to reserve the timeframe
    */
    
    public ReservableTimeframe(GregorianCalendar startDateTime,
                               GregorianCalendar endDateTime, BigDecimal c)
    {
        super(startDateTime, endDateTime);
        cost = c;
    }
    
    /**
        GetCost - Return the cost to reserve the timeframe
    
        @return The cost to reserve the timeframe
    */
    
    public BigDecimal getCost()
    {
        return cost;
    }
    
    /**
        GetCostString - Return the cost to reserve the timeframe as a string
    
        @return The cost to reserve the timeframe as a string
    */
    
    public String getCostString()
    {
        BigDecimal displayVal = cost.setScale(2, RoundingMode.HALF_EVEN);
        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
        return fmt.format(displayVal.doubleValue());
    }
    
    /**
        SetCost - Set the cost to reserve the timeframe
    
        @param c The cost to reserve the timeframe
    */
    
    public void setCost(BigDecimal c)
    {
        cost = c;
    }
}