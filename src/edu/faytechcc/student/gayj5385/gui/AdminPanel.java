/**
    The root administrator panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.gayj5385.controller.ManageReservableBtnController;
import edu.faytechcc.student.gayj5385.controller.SettingsPanelController;
import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.gayj5385.controller.ManageReservableComboBoxController;
import edu.faytechcc.student.gayj5385.controller.ManageReservableListController;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

public class AdminPanel extends JPanel
{
    // Fields
    private JTabbedPane tabbedPane;
    private ManageReservablePanel mngReservablePanel;
    private SettingsPanel settingsPanel;
    
    /**
        Constructor
    */
    
    public AdminPanel()
    {
        super(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        
        tabbedPane.add("Manage Reservables", buildManageReservablePanel());
        tabbedPane.add("Settings", buildSettingsPanel());
        
        add(tabbedPane);
    }
    
    /**
        Build & return the panel to manage reservables on
    
        @return The built panel
    */
    
    private ManageReservablePanel buildManageReservablePanel()
    {
        mngReservablePanel = new ManageReservablePanel();
        
        mngReservablePanel.registerButtonController
            (new ManageReservableBtnController(mngReservablePanel));
        
        mngReservablePanel.registerComboBoxController
            (new ManageReservableComboBoxController(mngReservablePanel));
        
        mngReservablePanel.registerTimeframeListController
            (new ManageReservableListController(mngReservablePanel));
        
        try
        {
            Query q = new Query();
            mngReservablePanel.setLocations(q.queryLocations());
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Error loading location data",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return mngReservablePanel;
    }
    
    /**
        Build & return the panel allowing updates to settings to be made
    
        @return The built panel
    */
    
    private SettingsPanel buildSettingsPanel()
    {
        settingsPanel = new SettingsPanel();
        settingsPanel.registerController(
                new SettingsPanelController(settingsPanel));
        return settingsPanel;
    }
    
    /**
        Return the name of the active tab
    
        @return The name of the active tab
    */
    
    public String getActiveTab()
    {
        return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
    }
    
    /**
        Set the database fields for the settings panel
    
        @param dbName Database username
        @param dbPass Database password
    */
    
    public void setSettingsDBFields(String dbName, String dbPass)
    {
        settingsPanel.setDatabaseFields(dbName, dbPass);
    }
    
    /**
        Populate the email fields in the settings tab
    
        @param adminSMTP Admin SMTP properties
        @param guestSMTP Guest SMTP properties
        @param adminGet Address for admin to receive email at
    */
    
    public void setSettingsEmailFields(SMTPProperties adminSMTP,
                                       SMTPProperties guestSMTP,String adminGet)
    {
        settingsPanel.setEmailFields(adminSMTP, guestSMTP, adminGet);
    }
    
    /**
        Set the security options available to choose from on the settings panel
    
        @param options The options available to choose from
    */
    
    public void setSettingsSecurityOptions(SecurityOption[] options)
    {
        settingsPanel.setSecurityOptions(options);
    }
    
    /**
        Register change controller to the tabbed pane
    
        @param controller The controller to register
    */
    
    public void registerChangeController(ChangeListener controller)
    {
        tabbedPane.addChangeListener(controller);
    }
}