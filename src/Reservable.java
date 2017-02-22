/**
    Interface for a reservable object
    CSC-289 - Group 4
    @author Timothy Burns
*/

import java.math.BigDecimal;

public interface Reservable
{
    /**
        GetTimeframe - Return the timeframe for which the object
        may be reserved
    
        @return The object's timeframe
    */
    
    public Timeframe getTimeframe();
    
    /**
        GetCost - Return the cost for reserving the object
    
        @return The cost for reserving the object
    */
    
    public BigDecimal getCost();
    
    /**
        GetIfReserved - Return if the object is reserved
    
        @return If the object is reserved
    */
    
    public boolean getIfReserved();
    
    /**
        SetTimeframe - Set the timeframe for which the object may be reserved
    
        @param timeframe The timeframe for which the object may be reserved
    */
    
    public void setTimeframe(Timeframe timeframe);
    
    /**
        SetCost - Set the cost for reserving the object
    
        @param cost The cost for reserving the object
    */
    
    public void setCost(BigDecimal cost);
    
    /**
        SetIfReserved - Set if the object is reserved
    
        @param isReserved If the object is reserved
    */
    
    public void setIfReserved(boolean isReserved);
}