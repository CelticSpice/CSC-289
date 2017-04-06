/**
    Change controller for root admin panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.EmailSettings;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.gayj5385.gui.AdminPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdminPanelController implements ChangeListener
{
    // Fields
    private AdminPanel view;
    
    /**
        Constructs a new AdminPanelController with the given view
    
        @param v The view
    */
    
    public AdminPanelController(AdminPanel v)
    {
        view = v;
    }
    
    /**
        Handles the changing of a tab
    
        @param e The change event
    */
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        if (view.getActiveTab().equals("Settings"))
        {
            SystemPreferences prefs = SystemPreferences.getInstance();
        
            EmailSettings adminEmail = prefs.getAdminEmailSettings();
            EmailSettings guestEmail = prefs.getGuestEmailSettings();
            DatabaseSettings db = prefs.getDBSettings();
            SecurityOption[] options = SecurityOption.values();
            
            view.setSettingsSettings(adminEmail, guestEmail, db, options);
        }
        else
            view.updateModel();
    }
}