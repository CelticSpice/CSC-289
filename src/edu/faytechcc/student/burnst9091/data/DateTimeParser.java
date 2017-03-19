/**
    Parses appropriate int values for dates & times based on a given datetime
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.time.LocalDateTime;

public class DateTimeParser
{
    // Fields
    private LocalDateTime datetime;
    
    /**
        Constructor - Accepts the datetime
    
        @param datetime Datetime to parse appropriate values from
    */
    
    public DateTimeParser(LocalDateTime datetime)
    {
        this.datetime = datetime;
    }
    
    /**
        Return an array of valid day values from the held datetime
    
        @return An array of valid day values
    */
    
    public int[] getDays()
    {
        int day = datetime.getDayOfMonth();
        int daysInMonth = datetime.toLocalDate().lengthOfMonth();
        
        int[] days = new int[daysInMonth + 1 - day];

        for (int i = 0; i < days.length; i++)
            days[i] = day++;
        
        return days;
    }
    
    /**
        Return an array of valid hour values from the held datetime
    
        @return An array of valid hour values
    */
    
    public int[] getHours()
    {
        final int MAX_HOUR = 23;
        
        int hour = datetime.getHour();
        
        int[] hours = new int[MAX_HOUR + 1 - hour];
        
        for (int i = 0; i < hours.length; i++)
            hours[i] = hour++;
        
        return hours;
    }
    
    /**
        Return an array of valid minute values from the held datetime
    
        @return An array of valid minute values
    */
    
    public int[] getMinutes()
    {
        final int MAX_MINUTE = 59;
        
        int minute = datetime.getMinute();
        
        int[] minutes = new int[MAX_MINUTE + 1 - minute];
        
        for (int i = 0; i < minutes.length; i++)
            minutes[i] = minute++;
        
        return minutes;
    }
    
    /**
        Return an array of valid month values from the held datetime
    
        @return An array of valid month values
    */
    
    public int[] getMonths()
    {
        final int NUM_MONTHS = 12;
        
        int month = datetime.getMonthValue();
        
        int[] months = new int[NUM_MONTHS + 1 - month];
        
        for (int i = 0; i < months.length; i++)
            months[i] = month++;
        
        return months;
    }
    
    /**
        Return an array of valid year values up to the year 2099 from the held
        datetime
    
        @return An array of valid year values up to the year 2099
    */
    
    public int[] getYears()
    {
        final int MAX_YEAR = 2099;
        
        int year = datetime.getYear();
        
        int[] years = new int[MAX_YEAR + 1 - year];
        
        for (int i = 0; i < years.length; i++)
            years[i] = year++;
        
        return years;
    }
    
    /**
        Set the datetime to parse values from
    
        @param datetime Datetime to parse appropriate values from
    */
    
    public void setDatetime(LocalDateTime datetime)
    {
        this.datetime = datetime;
    }
}