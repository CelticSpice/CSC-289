/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ManageReservableButtonController implements ActionListener
{
    // Fields
    private ManageReservablePanel view;
    
    /**
        Constructor - Accepts the view to control buttons for
    
        @param v The view
    */
    
    public ManageReservableButtonController(ManageReservablePanel v)
    {
        view = v;
    }
    
    /**
        Handle the clicking of a button
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Add":
                showAddDialog();
                break;
            case "Modify":
                //doModify();
                break;
            case "Delete":
                //doDelete();
                break;
            case "Logout":
                System.exit(0);
                break;
        }
    }
    
    /**
        Show the dialog enabling the addition of a reservable
    */
    
    private void showAddDialog()
    {        
        ReservableAddDialog d = new ReservableAddDialog();
        
        d.registerButtonController(new ReservableAddButtonController(d));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));
        
        d.setExistingLocations(view.getLocations());
        
        setInitialDatetimeFields(d);
        
        d.setVisible(true);
        
        if (d.getIfRecordsAdded())
            view.setLocations(d.getExistingLocations());
    }
    
    /**
        Set the initial datetime fields on a dialog
    
        @param dialog The dialog
    */
    
    private void setInitialDatetimeFields(ReservableAddDialog dialog)
    {
        final int HOURS = 24;
        final int MAX_YEAR = 2099;
        final int MINUTES = 60;
        final int MONTHS = 12;
        
        LocalDateTime datetime = LocalDateTime.now();
        
        int[] years = new int[MAX_YEAR + 1 - datetime.getYear()];
        for (int i = 0; i < years.length; i++)
            years[i] = datetime.getYear() + i;
        
        int[] months = new int[MONTHS + 1 - datetime.getMonthValue()];
        for (int i = 0; i < months.length; i++)
            months[i] = datetime.getMonthValue() + i;
        
        int monthDays = datetime.toLocalDate().lengthOfMonth();
        int[] days = new int[monthDays + 1 - datetime.getDayOfMonth()];
        for (int i = 0; i < days.length; i++)
            days[i] = datetime.getDayOfMonth() + i;
        
        int[] hours = new int[HOURS - datetime.getHour()];
        for (int i = 0; i < hours.length; i++)
            hours[i] = datetime.getHour() + i;
        
        int[] minutes = new int[MINUTES - datetime.getMinute()];
        for (int i = 0; i < minutes.length; i++)
            minutes[i] = datetime.getMinute() + i;
        
        dialog.setStartYears(years);
        dialog.setStartMonths(months);
        dialog.setStartDays(days);
        dialog.setStartHours(hours);
        dialog.setStartMinutes(minutes);
        
        dialog.setEndYears(years);
        dialog.setEndMonths(months);
        dialog.setEndDays(days);
        dialog.setEndHours(hours);
        dialog.setEndMinutes(minutes);
    }
}