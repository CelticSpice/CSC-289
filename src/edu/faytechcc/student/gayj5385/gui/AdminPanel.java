/**
    The root administrator panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.gayj5385.controller.ManageReservableButtonController;
import edu.faytechcc.student.gayj5385.controller.SettingsPanelController;
import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.controller.ManageReservableComboBoxController;
import edu.faytechcc.student.gayj5385.controller.ManageReservableListController;
import edu.faytechcc.student.gayj5385.controller.ManageReservationComboBoxController;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

public class AdminPanel extends JPanel
{
    // Fields
    private JTabbedPane tabbedPane;
    private ManageReservablePanel mngReservablePanel;
    private ManageReservationPanel mngReservationPanel;
    private SettingsPanel settingsPanel;
    
    /**
        Constructor
    */
    
    public AdminPanel()
    {
        super(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        
        tabbedPane.add("Manage Reservables", buildManageReservablePanel());
        tabbedPane.add("Manage Reservations", buildManageReservationPanel());
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
        
        try
        {
            Query q = new Query();
            Location[] locationsArray = q.queryLocations();
            List<Location> locationsList = new ArrayList();
            
            for (Location loc : locationsArray)
            {
                locationsList.add(loc);
            }
            mngReservablePanel.setLocations(locationsList);
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Error loading location data",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        Filter<Reservable> filter = new Filter();
        
        mngReservablePanel.registerButtonController
            (new ManageReservableButtonController(mngReservablePanel, filter));
        
        mngReservablePanel.registerComboBoxController
            (new ManageReservableComboBoxController(mngReservablePanel, filter));
        
        mngReservablePanel.registerTimeframeListController
            (new ManageReservableListController(mngReservablePanel));
        
        return mngReservablePanel;
    }
    
    /**
        Builds & returns the panel to manage reservations on
    
        @return The built panel
    */
    
    private ManageReservationPanel buildManageReservationPanel()
    {
        mngReservationPanel = new ManageReservationPanel();
        mngReservationPanel.registerComboBoxController
            (new ManageReservationComboBoxController(mngReservationPanel, new Filter()));
        
        try
        {
            Query q = new Query();
            mngReservationPanel.setLocations(q.queryLocations());
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Error loading location data",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return mngReservationPanel;
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