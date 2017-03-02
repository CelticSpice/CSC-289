/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**

 @author burnst
 */
public class TimeframeTest {
    /**
     * Test of conflicts method, of class Timeframe.
     */
    @Test
    public void testConflicts() {
        GregorianCalendar startDateTime = new GregorianCalendar(2017, 3, 30);
        GregorianCalendar endDateTime = new GregorianCalendar(2017, 3, 30);
        
        GregorianCalendar otherStartDateTime = new GregorianCalendar(2017, 3, 31);
        GregorianCalendar otherEndDateTime = new GregorianCalendar(2017, 3, 31);
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        Timeframe otherTimeframe = new Timeframe(otherStartDateTime, otherEndDateTime);
        
        boolean expectedResult = false;
        boolean actualResult = timeframe.conflicts(otherTimeframe);
        
        assertEquals(expectedResult, actualResult);
    }
//
//    /**
//     * Test of endsOn method, of class Timeframe.
//     */
//    @Test
//    public void testEndsOn() {
//        System.out.println("endsOn");
//        GregorianCalendar date = null;
//        boolean dateOnly = false;
//        Timeframe instance = null;
//        boolean expResult = false;
//        boolean result = instance.endsOn(date, dateOnly);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of startsOn method, of class Timeframe.
//     */
//    @Test
//    public void testStartsOn() {
//        System.out.println("startsOn");
//        GregorianCalendar date = null;
//        boolean dateOnly = false;
//        Timeframe instance = null;
//        boolean expResult = false;
//        boolean result = instance.startsOn(date, dateOnly);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEndDateString method, of class Timeframe.
//     */
//    @Test
//    public void testGetEndDateString() {
//        System.out.println("getEndDateString");
//        Timeframe instance = null;
//        String expResult = "";
//        String result = instance.getEndDateString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEndTimeString method, of class Timeframe.
//     */
//    @Test
//    public void testGetEndTimeString() {
//        System.out.println("getEndTimeString");
//        Timeframe instance = null;
//        String expResult = "";
//        String result = instance.getEndTimeString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStartDateString method, of class Timeframe.
//     */
//    @Test
//    public void testGetStartDateString() {
//        System.out.println("getStartDateString");
//        Timeframe instance = null;
//        String expResult = "";
//        String result = instance.getStartDateString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStartTimeString method, of class Timeframe.
//     */
//    @Test
//    public void testGetStartTimeString() {
//        System.out.println("getStartTimeString");
//        Timeframe instance = null;
//        String expResult = "";
//        String result = instance.getStartTimeString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Timeframe.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Timeframe instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
