/**
    A location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

public class Location
{
    // Fields
    private int capacity, id;
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
        id = -1;
    }
    
    /**
        Constructor - Accepts the location's name, capacity, & ID
    
        @param n The location's name
        @param cap The location's capacity
        @param i The location's ID
    */
    
    public Location(String n, int cap, int i)
    {
        name = n;
        capacity = cap;
        id = i;
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
        GetID - Return the location's ID
    
        @return The location's ID
    */
    
    public int getID()
    {
        return id;
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
        SetCapacity - Set the location's capacity
    
        @param cap The location's capacity
    */
    
    public void setCapacity(int cap)
    {
        capacity = cap;
    }
}