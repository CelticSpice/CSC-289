/**
    A location
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

public class Location
{
    private int capacity, id;
    private String name;
    
    /**
        Constructs a new Location initialized with the given name & capacity
    
        @param n The location name
        @param cap The location capacity
        @throws IllegalArgumentException Name is empty or capacity 0 or less
    */
    
    public Location(String n, int cap)
    {
        // Check that name is valid
        if (n == null || n.isEmpty())
            throw new IllegalArgumentException("Name is blank");
        
        // Check that capacity is valid
        if (cap <= 0)
            throw new IllegalArgumentException("Capacity 0 or less");
        
        id = -1;
        name = n;
        capacity = cap;
    }
    
    /**
        Constructs a new Location initialized with the given name, capacity, &
        ID
    
        @param n The location name
        @param cap The location capacity
        @param id The location ID
        @throws IllegalArgumentException Name is empty or capacity 0 or less
    */
    
    public Location(String n, int cap, int id)
    {
        // Check that name is valid
        if (n == null || n.isEmpty())
            throw new IllegalArgumentException("Name is blank");
        
        // Check that capacity is valid
        if (cap <= 0)
            throw new IllegalArgumentException("Capacity 0 or less");
        
        this.id = id;
        name = n;
        capacity = cap;
    }
    
    /**
        Returns the location's capacity
    
        @return The location's capacity
    */
    
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
        Returns the location's ID
    
        @return The location's ID
    */
    
    public int getID()
    {
        return id;
    }
    
    /**
        Returns the location's name
    
        @return The location's name
    */
    
    public String getName()
    {
        return name;
    }
    
    /**
        Sets the location's capacity
    
        @param cap The location's capacity
    */
    
    public void setCapacity(int cap)
    {
        capacity = cap;
    }
    
    /**
        Sets the location's ID
    
        @param id The location's ID
    */
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
        Sets the name of the location
    
        @param n The name of the location
    */
    
    public void setName(String n)
    {
        name = n;
    }
    
    /**
        Returns a string representation of the object
    
        @return A string representation of the object
    */
    
    @Override
    public String toString()
    {
        return name;
    }
}