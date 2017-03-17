/**
    Controller for buttons on the settings panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SMTPValidator;
import edu.faytechcc.student.burnst9091.data.SystemUtil;
import edu.faytechcc.student.gayj5385.gui.SettingsPanel;
import edu.faytechcc.student.gayj5385.gui.UpdatePasswordDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanelController implements ActionListener
{
    // Fields
    private SettingsPanel view;
    
    /**
        Constructor - Accepts the view to control buttons for
    
        @param v The view
    */
    
    public SettingsPanelController(SettingsPanel v)
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
            case "Save":
                saveSettings();
                break;
            case "Update Password":
                showUpdatePassDialog();
                break;
            case "Cancel":
                cancelChanges();
                break;
        }
    }
    
    /**
        Reset the fields to presently saved values
    */
    
    private void cancelChanges()
    {
        SMTPProperties adminProps = SystemUtil.getAdminSMTPProperties();
        SMTPProperties guestProps = SystemUtil.getGuestSMTPProperties();
        String adminGetAddress = SystemUtil.getAdminGetAddress();
        String dbUser = SystemUtil.getDBUser();
        String dbPass = SystemUtil.getDBPass();
        
        view.setEmailFields(adminProps, guestProps, adminGetAddress);
        view.setDatabaseFields(dbUser, dbPass);
    }
    
    /**
        Show the dialog for the user to update the administrator password
    */
    
    private void showUpdatePassDialog()
    {
        UpdatePasswordDialog dialog = new UpdatePasswordDialog();
        dialog.registerController(new UpdatePasswordController(dialog));
        dialog.setVisible(true);
    }
    
    /**
        Save settings
    */
    
    private void saveSettings()
    {
        // Validate that fields are entered appropriately
        if (validateInput())
        {
            // Update settings
            SystemUtil.setAdminSMTPPrefs(view.getAdminSMTPProperties());
            SystemUtil.setGuestSMTPPrefs(view.getGuestSMTPProperties());
            SystemUtil.setAdminGetAddress(view.getAdminGetAddress());
            SystemUtil.setDBUser(view.getDBUser());
            SystemUtil.setDBPass(String.valueOf(view.getDBPass()));
            
            // Inform of success in saving settings
            view.showMessage("Settings saved");
        }
    }
    
    /**
        Validate the admin's email settings
    
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
        SMTPProperties props = new SMTPProperties();
        props.setAddress(view.getAdminGetAddress());
        v.setSMTPProperties(props);
        
        goodSoFar = v.validateAddress();
        if (!goodSoFar)
        {
            view.showMessage("Bad getting address for administrator");
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
        Validate that fields have been input appropriate data
    
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