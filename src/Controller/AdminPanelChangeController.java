/**
    Change controller for root admin panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Controller;

import Data.SystemUtil;
import GUI.AdminPanel;
import java.util.Properties;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdminPanelChangeController implements ChangeListener
{
    // Fields
    private AdminPanel view;
    
    /**
        Constructor - Accepts the view
    
        @param v The view
    */
    
    public AdminPanelChangeController(AdminPanel v)
    {
        view = v;
    }
    
    /**
        StateChanged - Handle the changing of a tab
    
        @param e The change event
    */
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        switch (view.getActiveTab())
        {
            case "Settings":
                case "Save":
                Properties adminEmailProps = SystemUtil.getAdminSMTPProperties();
                Properties guestEmailProps = SystemUtil.getGuestSMTPProperties();
                String adminPass = SystemUtil.getAdminPassword();
                String adminGetAddress = SystemUtil.getAdminGetAddress();
                Properties dbProps = new Properties();
                dbProps.put("User", SystemUtil.getDBUser());
                dbProps.put("Pass", SystemUtil.getDBPass());
                
                view.populateSettingsFields(adminEmailProps, adminPass, guestEmailProps, dbProps, adminPass);
        }
    }
}