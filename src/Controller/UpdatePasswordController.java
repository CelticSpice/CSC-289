/**
    Controller for the dialog to update the password
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Controller;

import Data.Admin;
import Data.SystemUtil;
import GUI.UpdatePasswordDialog;
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
        Check that two character arrays are equal in their content
    
        @return If two character arrays are equal in their content
    */
    
    private boolean isEqual(char[] c1, char[] c2)
    {
        for (int i = 0; i < c1.length && i < c2.length; i++)
            if (c1[i] != c2[i])
                return false;
        return true;
    }
    
    /**
        Perform an update operation
    */
    
    private void performUpdate() throws NoSuchAlgorithmException
    {
        // Check that old password matches
        char[] oldPass = view.getOldPass();
        
        if (SystemUtil.validateAdminPassword(String.valueOf(oldPass)))
        {
            // Check that new passwords entered & matching
            char[] newPass = view.getNewPass();
            char[] verifiedNewPass = view.getVerifiedNewPass();

            if (isEqual(newPass, verifiedNewPass))
            {
                Admin.updatePassword(String.valueOf(newPass));
                view.setMessage("Password updated");
            }
            else
                view.setMessage("New password entered not matching");
        }
        else
            view.setMessage("Incorrect current password entered");
    }
}