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
                SMTPProperties adminSMTP = SystemUtil.getAdminSMTPProperties();
                SMTPProperties guestSMTP = SystemUtil.getGuestSMTPProperties();
                String adminGet = SystemUtil.getAdminGetAddress();
                view.setSettingsSecurityOptions(SecurityOption.values());
                view.setSettingsEmailFields(adminSMTP, guestSMTP, adminGet);
                break;
        }
    }
}