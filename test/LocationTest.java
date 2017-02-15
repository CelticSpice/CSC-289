/**
    Testing for Location class
    CSC-289 - Group 4
    @author Timothy Burns
*/

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationTest {
    /**
     * Test of getCapacity method, of class Location.
     */
    
    @Test
    public void testGetCapacity() {
        String name = "Magic";
        int capacity = 50;
        Location loc = new Location(name, capacity);
        
        // We expect getCapacity() to return the same value as capacity
        assertEquals(capacity, loc.getCapacity());
    }

    /**
     * Test of getName method, of class Location.
     */
    
    @Test
    public void testGetName() {
        String name = "Magic";
        int capacity = 50;
        Location loc = new Location(name, capacity);
        
        // We expect getName() to return the same String as name
        assertEquals(name, loc.getName());
    }

    /**
     * Test of setCapacity method, of class Location.
     */
    
    @Test
    public void testSetCapacity() {
        String name = "Magic";
        int initialCapacity = 50;
        Location loc = new Location(name, initialCapacity);
        
        // Set new capacity
        int newCapacity = 100;
        loc.setCapacity(newCapacity);
        
        // We expect that the location's capacity is equal to newCapacity
        assertEquals(newCapacity, loc.getCapacity());
    }

    /**
     * Test of setName method, of class Location.
     */
    
    @Test
    public void testSetName() {
        String initialName = "Magic";
        int capacity = 50;
        Location loc = new Location(initialName, capacity);
        
        // Set new name
        String newName = "Technology";
        loc.setName(newName);
        
        // We expect that the location's name is equal to newName
        assertEquals(newName, loc.getName());
    }
}
