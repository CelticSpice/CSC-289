/**
    A location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

public class Location
{
    // Fields
    private int capacity;
    private TimeframeList timeframes;
    private String name;
    
    /**
        Constructor - Accepts the location's name & capacity
    
        @param n The location name
        @param cap The location capacity
    */
    
    public Location(String n, int cap)
    {
        name = n;
        capacity = cap;
        timeframes = new TimeframeList();
    }
    
    /**
        Constructor - Accepts the location's name, capacity, and a list of 
        timeframes allocated to the location
    
        @param n The reservable location's name
        @param cap The reservable location's capacity
        @param times The location's allocated timeframes
    */
    
    public Location(String n, int cap, TimeframeList times)
    {
        name = n;
        capacity = cap;
        timeframes = times;
    }
    
    /**
        GetCapacity - Return the location's capacity
    
        @return The location's capacity
    */
    
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
        GetName - Return the location's name
    
        @return The location's name
    */
    
    public String getName()
    {
        return name;
    }
    
    /**
        GetTimeframes - Return the list of timeframes allocated to the location
    
        @return The list of timeframes allocated to the location
    */
    
    public TimeframeList getTimeframes()
    {
        return timeframes;
    }
    
    /**
        SetCapacity - Set the location's capacity
    
        @param cap The location's capacity
    */
    
    public void setCapacity(int cap)
    {
        capacity = cap;
    }
    
    /**
        SetName - Set the name of the location
    
        @param n The name of the location
    */
    
    public void setName(String n)
    {
        name = n;
    }
}