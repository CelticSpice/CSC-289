/**
    Tests for Timeframe class
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TimeframeTest {
    /**
        Test for a true consistency with another timeframe
    */
    
    @Test
    public void testConsistsOfDoesConsist() {
        // March 30, 2017 - 6:30
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 30), LocalTime.of(6, 30),
                ZoneId.systemDefault());
        
        // April 2, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 2), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        // March 25, 2017 - 6:00
        ZonedDateTime otherStartDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime otherEndDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        Timeframe otherTimeframe = new Timeframe(
                otherStartDateTime, otherEndDateTime);
        
        boolean expectedResult = true;
        boolean actualResult = timeframe.consistsOf(otherTimeframe);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test for a false consistency with another timeframe
    */
    
    @Test
    public void testConsistsOfDoesNotConsist() {
        // March 30, 2017 - 6:30
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 30), LocalTime.of(6, 30),
                ZoneId.systemDefault());
        
        // March 30, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 30), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        // March 31, 2017 - 6:00
        ZonedDateTime otherStartDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 31), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // March 31, 2017 - 14:00
        ZonedDateTime otherEndDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 31), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        Timeframe otherTimeframe = new Timeframe
            (otherStartDateTime, otherEndDateTime);
        
        boolean expectedResult = false;
        boolean actualResult = timeframe.consistsOf(otherTimeframe);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test checking for end date timeframe does end on
    */
    
    @Test
    public void testEndsOnDateDoesEndOn() {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 20, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        // April 20, 2017
        LocalDate date = LocalDate.of(2017, 4, 20);
        
        boolean expectedResult = true;
        boolean actualResult = timeframe.endsOnDate(date);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test checking for end date timeframe does not end on
    */
    
    @Test
    public void testEndsOnDateDoesNotEndOn() {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        // April 21, 2017
        LocalDate date = LocalDate.of(2017, 4, 21);
        
        boolean expectedResult = false;
        boolean actualResult = timeframe.endsOnDate(date);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test checking for start date timeframe does start on
    */
    
    @Test
    public void testStartsOnDateDoesStartOn() {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        // March 25, 2017
        LocalDate date = LocalDate.of(2017, 3, 25);
        
        boolean expectedResult = true;
        boolean actualResult = timeframe.startsOnDate(date);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test checking for start date timeframe does not start on
    */
    
    @Test
    public void testStartsOnDateDoesNotStartOn() {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        // April 25, 2017
        LocalDate date = LocalDate.of(2017, 4, 25);
        
        boolean expectedResult = false;
        boolean actualResult = timeframe.startsOnDate(date);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test checking for start time timeframe does start on
    */
    
    @Test
    public void testStartsOnTimeDoesStartOn() {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        // 6:00
        LocalTime time = LocalTime.of(6, 0);
        
        boolean expectedResult = true;
        boolean actualResult = timeframe.startsOnTime(time);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test checking for start time timeframe does not start on
    */
    
    @Test
    public void testStartsOnTimeDoesNotStartOn() {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        // 14:00
        LocalTime time = LocalTime.of(14, 0);
        
        boolean expectedResult = false;
        boolean actualResult = timeframe.startsOnTime(time);
        
        assertEquals(expectedResult, actualResult);
    }
    
    /**
        Test toString method
    */
    
    @Test
    public void testToString()
    {
        // March 25, 2017 - 6:00
        ZonedDateTime startDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 3, 25), LocalTime.of(6, 0),
                ZoneId.systemDefault());
        
        // April 4, 2017 - 14:00
        ZonedDateTime endDateTime = ZonedDateTime.of(
                LocalDate.of(2017, 4, 20), LocalTime.of(14, 0),
                ZoneId.systemDefault());
        
        Timeframe timeframe = new Timeframe(startDateTime, endDateTime);
        
        String expectedResult = "2017-03-25, 06:00 : 2017-04-20, 14:00";
        String actualResult = timeframe.toString();
        
        assertEquals(expectedResult, actualResult);
    }
}
