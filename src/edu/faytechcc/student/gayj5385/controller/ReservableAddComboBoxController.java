/**
    Controller for combo boxes on the dialog to add reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ReservableAddComboBoxController implements ActionListener
{
    // Fields
    private static final int START = 0;
    private static final int END = 1;
    
    private ReservableAddDialog view;
    
    /**
        Constructor - Accepts the view
    
        @param v The view
    */
    
    public ReservableAddComboBoxController(ReservableAddDialog v)
    {
        view = v;
    }
    
    /**
        Respond to action event
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Existing Location":
                setExistingLocationFields();
                break;
            case "Start Year":
                setMonths(START);
            case "Start Month":
                setDays(START);
            case "Start Day":
                setHours(START);
            case "Start Hour":
                setMinutes(START);
                break;
            case "End Year":
                setMonths(END);
            case "End Month":
                setDays(END);
            case "End Day":
                setHours(END);
            case "End Hour":
                setMinutes(END);
                break;
        }
    }
    
    /**
        Set the available starting or ending days on the dialog
    
        @param startOrEnd Set either the starting days or the ending days
    */
    
    private void setDays(int startOrEnd)
    {   
        int year, month;
        if (startOrEnd == START)
        {
            year = view.getStartYear();
            month = view.getStartMonth();
        }
        else
        {
            year = view.getEndYear();
            month = view.getEndMonth();
        }
        
        LocalDateTime datetime = LocalDateTime
                .now()
                .withYear(year)
                .withMonth(month)
                .withSecond(0)
                .withNano(0);

        int[] days;
        int day;
        
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
        {
            day = datetime.getDayOfMonth();
            days = new int[datetime.toLocalDate().lengthOfMonth() + 1 - day];
        }
        else
        {
            day = 1;
            days = new int[datetime.toLocalDate().lengthOfMonth()];
        }
        
        for (int i = 0; i < days.length; i++)
            days[i] = day++;

        if (startOrEnd == START)
            view.setStartDays(days);
        else
            view.setEndDays(days);
    }
    
    /**
        Set the name & capacity of the currently selected existing location
    */
    
    private void setExistingLocationFields()
    {
        Location loc = view.getSelectedLocation();
        view.setLocation(loc.getName());
        view.setCapacity(String.valueOf(loc.getCapacity()));
    }
    
    /**
        Set the available starting or ending hours on the dialog
    
        @param startOrEnd Set either the starting hours or the ending hours
    */
    
    private void setHours(int startOrEnd)
    {   
        final int HOURS = 24;
        
        int year, month, day;
        if (startOrEnd == START)
        {
            year = view.getStartYear();
            month = view.getStartMonth();
            day = view.getStartDay();
        }
        else
        {
            year = view.getEndYear();
            month = view.getEndMonth();
            day = view.getEndDay();
        }
        
        LocalDateTime datetime = LocalDateTime
                .now()
                .withYear(year)
                .withMonth(month)
                .withDayOfMonth(day)
                .withSecond(0)
                .withNano(0);

        int[] hours;
        int hour;
        
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
        {
            hour = datetime.getHour();
            hours = new int[HOURS + 1 - hour];
        }
        else
        {
            hour = 0;
            hours = new int[HOURS];
        }
        
        for (int i = 0; i < hours.length; i++)
            hours[i] = hour++;
        
        if (startOrEnd == START)
            view.setStartHours(hours);
        else
            view.setEndHours(hours);
    }
    
    /**
        Set the available starting or ending minutes on the dialog
    
        @param startOrEnd Set either the starting minutes or the minutes days
    */
    
    private void setMinutes(int startOrEnd)
    {
        final int MINUTES = 60;
        
        int year, month, day, hour;
        if (startOrEnd == START)
        {
            year = view.getStartYear();
            month = view.getStartMonth();
            day = view.getStartDay();
            hour = view.getStartHour();
        }
        else
        {
            year = view.getEndYear();
            month = view.getEndMonth();
            day = view.getEndDay();
            hour = view.getEndHour();
        }
        
       LocalDateTime datetime = LocalDateTime
                .now()
                .withYear(year)
                .withMonth(month)
                .withDayOfMonth(day)
                .withHour(hour)
                .withSecond(0)
                .withNano(0);

        int[] minutes;
        int minute;
        
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
        {
            minute = datetime.getMinute();
            minutes = new int[MINUTES + 1 - minute];
        }
        else
        {
            minute = 0;
            minutes = new int[MINUTES];
        }
        
        for (int i = 0; i < minutes.length; i++)
            minutes[i] = minute++;
        
        if (startOrEnd == START)
            view.setStartMinutes(minutes);
        else
            view.setEndMinutes(minutes);
    }
    
    /**
        Set the available starting or ending months on the dialog
    
        @param startOrEnd Set either the starting months or the ending months
    */
    
    private void setMonths(int startOrEnd)
    {
        final int MONTHS = 12;
        
        int year;
        if (startOrEnd == START)
            year = view.getStartYear();
        else
            year = view.getEndYear();
        
        LocalDateTime datetime = LocalDateTime
                .now()
                .withYear(year)
                .withSecond(0)
                .withNano(0);

        int[] months;
        int month;
        
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
        {
            month = datetime.getMonthValue();
            months = new int[MONTHS + 1 - month];
        }
        else
        {
            month = 1;
            months = new int[MONTHS];
        }
        
        for (int i = 0; i < months.length; i++)
            months[i] = month++;
        
        if (startOrEnd == START)
            view.setStartMonths(months);
        else
            view.setEndMonths(months);
    }
}