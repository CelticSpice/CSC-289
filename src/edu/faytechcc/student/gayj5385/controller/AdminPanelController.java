/**
    Change controller for root admin panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.burnst9091.data.SystemUtil;
import edu.faytechcc.student.gayj5385.gui.AdminPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdminPanelController implements ChangeListener
{
    // Fields
    private AdminPanel view;
    
    /**
        Constructor - Accepts the view
    
        @param v The view
    */
    
    public AdminPanelController(AdminPanel v)
    {
        view = v;
    }
    
    /**
        Handle the changing of a tab
    
        @param e The change event
    */
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        switch (view.getActiveTab())
        {
            case "Settings":
                setSettingsFields();
                break;
        }
    }
    
    /**
        Set the fields for the settings panel
    */
    
    private void setSettingsFields()
    {
        SMTPProperties adminSMTP = SystemUtil.getAdminSMTPProperties();
        SMTPProperties guestSMTP = SystemUtil.getGuestSMTPProperties();
        String adminGet = SystemUtil.getAdminGetAddress();
        String dbUser = SystemUtil.getDBUser();
        String dbPass = SystemUtil.getDBPass();
        
        view.setSettingsSecurityOptions(SecurityOption.values());
        view.setSettingsEmailFields(adminSMTP, guestSMTP, adminGet);
        view.setSettingsDBFields(dbUser, dbPass);
    }
}