/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DateTimeParser;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
        
        registerDialogControllers(d);
        setExistingLocations(d);
        setInitialFields(d);
        
        d.setVisible(true);
        
        if (d.getIfRecordsAdded())
            view.setLocations(d.getExistingLocations());
    }
    
    /**
        Register controllers to a dialog
    
        @param d The dialog
    */
    
    private void registerDialogControllers(ReservableAddDialog d)
    {
        d.registerButtonController(new ReservableAddButtonController(d));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));
    }
    
    /**
        Set the existing locations on a dialog to add a reservable
    
        @param dialog The dialog
    */
    
    private void setExistingLocations(ReservableAddDialog dialog)
    {
        try
        {
            Query query = new Query();
            dialog.setExistingLocations(query.queryLocations());
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(view, "Failed loading existing " +
                    "locations", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
        Set the initial fields on a dialog
    
        @param dialog The dialog
    */
    
    private void setInitialFields(ReservableAddDialog dialog)
    {
        DateTimeParser parser = new DateTimeParser(LocalDateTime.now());
        
        int[] years = parser.getYears();
        int[] months = parser.getMonths();
        int[] days = parser.getDays();
        int[] hours = parser.getHours();
        int[] minutes = parser.getMinutes();
        
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