/**
    Controller for buttons on the settings panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Controller;

import GUI.SettingsPanel;
import GUI.UpdatePasswordDialog;
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
        Handles the clicking of a button
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Update Password":
                UpdatePasswordDialog dialog = new UpdatePasswordDialog();
                dialog.registerController(new UpdatePasswordController(dialog));
                dialog.setVisible(true);
                break;
        }
    }
}