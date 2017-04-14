/**
    Change controller for root admin panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.EmailSettings;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.gayj5385.gui.AdminPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdminPanelController implements ChangeListener
{
    private AdminPanel view;
    private DataRepository repo;
    
    /**
        Constructs a new AdminPanelController with the given view
    
        @param v The view
        @param repo Data repository
    */
    
    public AdminPanelController(AdminPanel v, DataRepository repo)
    {
        view = v;
        this.repo = repo;
    }
    
    /**
        Handles the changing of a tab
    
        @param e The change event
    */
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        String tab = view.getActiveTab();
        
        if (tab.equals("Settings"))
        {
            EmailSettings adminEmail = SystemPreferences
                    .getAdminEmailSettings();
            EmailSettings guestEmail = SystemPreferences
                    .getGuestEmailSettings();
            
            DatabaseSettings db = SystemPreferences.getDBSettings();
            SecurityOption[] options = SecurityOption.values();
            
            view.setSettingsSettings(adminEmail, guestEmail, db, options);
        }
        else if (tab.equals("Manage Reservables"))
        {
            view.updateView(tab, repo.getLocations());
        }
        else
        {
            view.updateView(tab, repo.getReservedLocations());
        }
    }
}