/**
    Test Timeframe class
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeframeTest {
    /**
        Test for a true conflict
    */
    
    @Test
    public void testConflictsWithDoesConflict() {
        GregorianCalendar startDateTime = new GregorianCalendar(2017, 3, 30);
        GregorianCalendar endDateTime = new GregorianCalendar(2017, 4, 2);
        
        GregorianCalendar otherStartDateTime = new GregorianCalendar
            (2017, 3, 25);
        
        GregorianCalendar otherEndDateTime = new GregorianCalendar(2017, 4, 20);
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        Timeframe otherTimeframe = new Timeframe
            (otherStartDateTime, otherEndDateTime);
        
        boolean expectedResult = true;
        boolean actualResult = timeframe.conflictsWith(otherTimeframe);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test for a false conflict
    */
    
    @Test
    public void testConflictsWithDoesNotConflict() {
        GregorianCalendar startDateTime = new GregorianCalendar(2017, 3, 30);
        GregorianCalendar endDateTime = new GregorianCalendar(2017, 3, 30);
        
        GregorianCalendar otherStartDateTime = new GregorianCalendar
            (2017, 3, 31);
        
        GregorianCalendar otherEndDateTime = new GregorianCalendar(2017, 3, 31);
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        Timeframe otherTimeframe = new Timeframe
            (otherStartDateTime, otherEndDateTime);
        
        boolean expectedResult = false;
        boolean actualResult = timeframe.conflictsWith(otherTimeframe);
        
        assertEquals(expectedResult, actualResult);
    }
}
