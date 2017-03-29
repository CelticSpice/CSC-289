/**
    Controller for the dialog to update the password
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Admin;
import edu.faytechcc.student.burnst9091.data.SHA256SaltHasher;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.gayj5385.gui.UpdatePasswordDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class UpdatePasswordController implements ActionListener
{
    // Fields
    private UpdatePasswordDialog view;
    
    /**
        Constructor - Accepts the view to control
    
        @param v The view
    */
    
    public UpdatePasswordController(UpdatePasswordDialog v)
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
            case "Update":
                try
                {
                    performUpdate();
                }
                catch (NoSuchAlgorithmException ex)
                {
                    view.setMessage("Error: " + ex);
                }
                break;
            case "Exit":
                view.setVisible(false);
                view.dispose();
                break;
        }
    }
    
    /**
        Performs an update operation
    */
    
    private void performUpdate() throws NoSuchAlgorithmException
    {
        // Check that old password matches
        String oldPass = view.getOldPass();
        
        if (new SystemPreferences().validateAdminPassword(oldPass))
        {
            // Check that new passwords entered & matching
            String newPass = view.getNewPass();
            String verifiedNewPass = view.getVerifiedNewPass();

            if (newPass.equals(verifiedNewPass))
            {
                Admin.updatePassword(newPass);
                view.setMessage("Password updated");
            }
            else
                view.setMessage("New password entered not matching");
        }
        else
            view.setMessage("Incorrect current password entered");
    }
}