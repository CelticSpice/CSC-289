/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Admin;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.TimeframeList;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

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
                doDelete();
                break;
            case "Logout":
                System.exit(0);
                break;
        }
    }
    
    /**
        Return if any timeframe within a list of timeframes is reserved
    
        @param timeframes Timeframe list to check for any reserved timeframes
        @return If any timeframe in the list is reserved
    */
    
    private boolean areTimeframesReserved(List<Timeframe> timeframes)
    {
        boolean reserved = false;
        for (Timeframe timeframe : timeframes)
            if (timeframe.isReserved())
            {
                reserved = true;
                break;
            }
        return reserved;
    }
    
    /**
        Delete timeframes given in a list
    
        @param timeframes Timeframes to delete
    */
    
    private void deleteTimeframes(List<Timeframe> timeframes)
    {
        Location loc = view.getSelectedLocation();
        
        try
        {
            for (Timeframe timeframe : timeframes)
            {
                Admin.removeReservable(new Reservable(loc, timeframe));
                loc.removeTimeframe(timeframe);
            }
            
            view.setTimeframes(loc.getTimeframes());
            
            if (loc.getNumTimeframes() == 0)
                view.removeLocation(loc);
        }
        catch (SQLException ex)
        {
            
        }
    }
    
    /**
        Respond to the "Delete" button being clicked
    */
    
    private void doDelete()
    {
        List<Timeframe> timeframes = view.getSelectedTimeframes();
        
        if (!timeframes.isEmpty())
        {
            if (areTimeframesReserved(timeframes))
            {
                JOptionPane.showMessageDialog(view,
                    "Cannot remove reserved timeframes : Cancel reservations " +
                    "first");
            }
            else
            {
                // Confirm deletion
                int choice = JOptionPane.showConfirmDialog(view,
                    "Are you sure you want to delete the selected timeframes?");

                if (choice == JOptionPane.YES_OPTION)
                    deleteTimeframes(timeframes);
            }   
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