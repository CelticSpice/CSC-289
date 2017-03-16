/**
    Controller for buttons on the settings panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Controller;

import Data.SystemUtil;
import GUI.SettingsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class SettingsPanelController implements ActionListener
{
    // Fields
    private SettingsPanel view;
    
    /**
        Constructor - Accepts the view to act for
    
        @param v The view to act for
    */
    
    public SettingsPanelController(SettingsPanel v)
    {
        view = v;
    }
    
    /**
        ActionPerformed - Handles action events for view
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            
        }
    }
}