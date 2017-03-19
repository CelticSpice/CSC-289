/**
    Controller for combo boxes on the dialog to add reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DateTimeParser;
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
                .withMonth(month);
        datetime = datetime.withNano(0).withSecond(0);

        DateTimeParser parser;
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
            parser = new DateTimeParser(datetime);
        else
            parser = new DateTimeParser(datetime.withDayOfMonth(1));
        
        int[] days = parser.getDays();

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
        if (view.isExistingLocationRadioSelected())
        {
            Location loc = view.getSelectedLocation();
            view.setLocation(loc.getName());
            view.setCapacity(String.valueOf(loc.getCapacity()));
        }
    }
    
    /**
        Set the available starting or ending hours on the dialog
    
        @param startOrEnd Set either the starting hours or the ending hours
    */
    
    private void setHours(int startOrEnd)
    {        
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
        
        LocalDateTime datetime = LocalDateTime.now().withNano(0).withSecond(0);
        datetime = datetime.withYear(year).withMonth(month).withDayOfMonth(day);
        
        DateTimeParser parser;
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
            parser = new DateTimeParser(datetime);
        else
            parser = new DateTimeParser(datetime.withHour(0));
        
        int[] hours = parser.getHours();
        
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
        
        LocalDateTime datetime = LocalDateTime.now().withNano(0).withSecond(0);
        datetime = datetime.withYear(year).withMonth(month).withDayOfMonth(day);
        datetime = datetime.withHour(hour);
        
        DateTimeParser parser;
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
            parser = new DateTimeParser(datetime);
        else
            parser = new DateTimeParser(datetime.withMinute(0));
        
        int[] minutes = parser.getMinutes();
        
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
        int year;
        if (startOrEnd == START)
            year = view.getStartYear();
        else
            year = view.getEndYear();
        
        LocalDateTime datetime = LocalDateTime.now().withYear(year);
        datetime = datetime.withNano(0).withSecond(0);
        
        DateTimeParser parser;
        if (datetime.equals(LocalDateTime.now().withNano(0).withSecond(0)))
            parser = new DateTimeParser(datetime);
        else
            parser = new DateTimeParser(datetime.withMonth(1));
        
        int[] months = parser.getMonths();
        
        if (startOrEnd == START)
            view.setStartMonths(months);
        else
            view.setEndMonths(months);
    }
}