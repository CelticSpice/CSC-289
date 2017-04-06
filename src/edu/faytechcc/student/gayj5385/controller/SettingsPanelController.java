/**
    Controller for buttons on the settings panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.EmailSettings;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.gayj5385.gui.SettingsPanel;
import edu.faytechcc.student.gayj5385.gui.dialog.UpdatePasswordDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SettingsPanelController implements ActionListener
{
    // Fields
    private SettingsPanel view;
    
    /**
        Constructs a new SettingsPanelController to control the given view
    
        @param v The view
    */
    
    public SettingsPanelController(SettingsPanel v)
    {
        view = v;
    }
    
    /**
        Responds to an action event
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Save":
                saveSettings();
                break;
            case "Update Password":
                showUpdatePasswordDialog();
                break;
            case "Cancel":
                cancelChanges();
                break;
        }
    }
    
    /**
        Displays a warning message
    
        @param mesg Message to display
    */
    
    private void displayWarning(String mesg)
    {
        JOptionPane.showMessageDialog(view, mesg, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    
    /**
        Resets the fields to current settings
    */
    
    private void cancelChanges()
    {
        SystemPreferences prefs = SystemPreferences.getInstance();
        
        view.setAdminEmailSettings(prefs.getAdminEmailSettings());
        view.setGuestEmailSettings(prefs.getGuestEmailSettings());
        view.setDBSettings(prefs.getDBSettings());
    }
    
    /**
        Shows the dialog for the user to update the administrator password
    */
    
    private void showUpdatePasswordDialog()
    {
        new UpdatePasswordDialog().setVisible(true);
    }
    
    /**
        Saves settings
    */
    
    private void saveSettings()
    {
        // Validate that fields are entered appropriately
        if (validateInput())
        {
            // Update settings
            SystemPreferences prefs = SystemPreferences.getInstance();
            
            prefs.setAdminEmailSettings(view.getAdminEmailSettings());
            prefs.setGuestEmailSettings(view.getGuestEmailSettings());
            prefs.setDBSettings(view.getDBSettings());
            
            // Inform of success in saving settings
            JOptionPane.showMessageDialog(view, "Settings saved");
        }
    }
    
    /**
        Validates the database settings
    
        @return If the database settings are valid
    */
    
    private boolean validateDatabaseSettings()
    {
        DatabaseSettings settings = view.getDBSettings();
        
        // Validate database host
        if (settings.getDBHost().isEmpty())
        {
            displayWarning("Database host must be entered");
            return false;
        }
        
        return true;
    }
    
    /**
        Validates the email settings
   
        @param role One of "Admin" or "Guest", specifying whose settings to
                    validate
        @return If the email settings for the specified role are valid
    */
    
    private boolean validateEmailSettings(String role)
    {
        final String ADMIN = "Admin";
        
        EmailSettings settings;
        if (role.equals(ADMIN))
            settings = view.getAdminEmailSettings();
        else
            settings = view.getGuestEmailSettings();
        
        // Validate sending address
        String pattern = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
        if (!settings.getSendAddress().matches(pattern))
        {
            displayWarning("Invalid sending address for " + role);
            return false;
        }
        
        // Validate SMTP host address
        pattern = "([A-Za-z0-9-]+\\.[A-Za-z]+)(\\.\\w+)?((\\.[A-Za-z]+)?)?";
        if (!settings.getSMTPHost().matches(pattern))
        {
            displayWarning("Invalid SMTP host for " + role);
            return false;
        }
        
        if (role.equals(ADMIN))
        {
            // Validate getting address
            pattern = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
            if (!settings.getGetAddress().matches(pattern))
            {
                displayWarning("Invalid getting address for administrator");
                return false;
            }
        }
        
        return true;
    }
    
    /**
        Validates that fields have been input appropriate data
    
        @return If all fields have been input appropriate data
    */
    
    private boolean validateInput()
    {        
        // Validate admin email settings
        if (!validateEmailSettings("Admin"))
            return false;
        
        // Validate guest email settings
        if (!validateEmailSettings("Guest"))
            return false;
        
        // Validate database settings
        return validateDatabaseSettings();
    }
}