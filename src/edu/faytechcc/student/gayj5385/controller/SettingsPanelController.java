/**
    Controller for buttons on the settings panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.SHA256SaltHasher;
import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SMTPValidator;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.gayj5385.gui.SettingsPanel;
import edu.faytechcc.student.gayj5385.gui.UpdatePasswordDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanelController implements ActionListener
{
    // Fields
    private SettingsPanel view;
    private SystemPreferences preferences;
    private SHA256SaltHasher saltHasher;
    
    /**
        Constructs a new SettingsPanelController to control the given view,
        initialized with the given system preferences & salt-hasher
    
        @param v The view
        @param prefs System preferences
        @param saltHash Salt-hasher
    */
    
    public SettingsPanelController(SettingsPanel v, SystemPreferences prefs,
            SHA256SaltHasher saltHash)
    {
        view = v;
        preferences = prefs;
        saltHasher = saltHash;
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
        Resets the fields to current values
    */
    
    private void cancelChanges()
    {
        SMTPProperties adminProps = preferences.getAdminSMTPProperties();
        SMTPProperties guestProps = preferences.getGuestSMTPProperties();
        String adminGetAddress = preferences.getAdminGetAddress();
        String dbUser = preferences.getDBUser();
        String dbPass = preferences.getDBPass();
        
        view.setEmailFields(adminProps, guestProps, adminGetAddress);
        view.setDatabaseFields(dbUser, dbPass);
    }
    
    /**
        Shows the dialog for the user to update the administrator password
    */
    
    private void showUpdatePasswordDialog()
    {
        new UpdatePasswordDialog(preferences, saltHasher).setVisible(true);
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
            preferences.setAdminSMTPPrefs(view.getAdminSMTPProperties());
            preferences.setGuestSMTPPrefs(view.getGuestSMTPProperties());
            preferences.setAdminGetAddress(view.getAdminGetAddress());
            preferences.setDBUser(view.getDBUser());
            preferences.setDBPass(String.valueOf(view.getDBPass()));
            
            // Inform of success in saving settings
            view.showMessage("Settings saved");
        }
    }
    
    /**
        Validates the admin's email settings
    
        @param v The SMTP validator to validate SMTP settings
    */
    
    private boolean validateAdminEmailSettings(SMTPValidator v)
    {
        boolean goodSoFar = v.validateAddress();
        
        // Validate address
        if (!goodSoFar)
        {
            view.showMessage("Bad sending address for administrator");
            return goodSoFar;
        }
        
        // Validate host
        goodSoFar = v.validateHost();
        if (!goodSoFar)
        {
            view.showMessage("Bad host for administrator");
            return goodSoFar;
        }
        
        // Validate port
        goodSoFar = v.validatePort();
        if (!goodSoFar)
        {
            view.showMessage("Bad port for administrator");
            return goodSoFar;
        }
        
        // Validate get address
        String pattern = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
        
        goodSoFar = view.getAdminGetAddress().matches(pattern);
        if (!goodSoFar)
        {
            view.showMessage("Bad get address for administrator");
            return goodSoFar;
        }
        
        return goodSoFar;
    }
    
    /**
        Validate the guest's email settings
    
        @param v The SMTP validator to validate SMTP settings
    */
    
    private boolean validateGuestEmailSettings(SMTPValidator v)
    {
        boolean goodSoFar = v.validateAddress();
        
        // Validate address
        if (!goodSoFar)
        {
            view.showMessage("Bad sending address for administrator");
            return goodSoFar;
        }
        
        // Validate host
        goodSoFar = v.validateHost();
        if (!goodSoFar)
        {
            view.showMessage("Bad host for administrator");
            return goodSoFar;
        }
        
        // Validate port
        goodSoFar = v.validatePort();
        if (!goodSoFar)
        {
            view.showMessage("Bad port for administrator");
            return goodSoFar;
        }
        
        return goodSoFar;
    }
    
    /**
        Validates that fields have been input appropriate data
    
        @return If all fields have been input appropriate data
    */
    
    private boolean validateInput()
    {        
        // Validate admin email settings
        SMTPValidator valid = new SMTPValidator(view.getAdminSMTPProperties());
        boolean good = validateAdminEmailSettings(valid);
        
        // Validate guest email settings
        if (good)
        {
            valid.setSMTPProperties(view.getGuestSMTPProperties());
            good = validateGuestEmailSettings(valid);
        }
        
        // Validate database settings
        if (good)
        {
            good = !(view.getDBUser().isEmpty() &&
                    (String.valueOf(view.getDBPass()).isEmpty()));
            
            if (!good)
                view.showMessage("Bad database username or password");
        }
        
        return good;
    }
}