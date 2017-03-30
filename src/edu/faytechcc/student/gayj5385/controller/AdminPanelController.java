/**
    Change controller for root admin panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.burnst9091.data.SystemUtil;
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
        switch (view.getActiveTab())
        {
            case "Settings":
                initSettingsFields();
                break;
        }
    }
    
    /**
        Initializes the fields for the settings panel
    */
    
    private void initSettingsFields()
    {
        SystemPreferences systemPrefs = new SystemPreferences();
        
        SMTPProperties adminSMTP = systemPrefs.getAdminSMTPProperties();
        SMTPProperties guestSMTP = systemPrefs.getGuestSMTPProperties();
        String adminGet = systemPrefs.getAdminGetAddress();
        String dbUser = systemPrefs.getDBUser();
        String dbPass = systemPrefs.getDBPass();
        
        view.setSettingsSecurityOptions(SecurityOption.values());
        view.setSettingsEmailFields(adminSMTP, guestSMTP, adminGet);
        view.setSettingsDBFields(dbUser, dbPass);
    }
}