/**
    The root administrator panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package GUI;

import Controller.ManageReservableBtnController;
import java.awt.BorderLayout;
import java.util.Properties;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

public class AdminPanel extends JPanel
{
    // Fields
    private JTabbedPane tabbedPane;
    private SettingsPanel settingsPanel;
    
    /**
        Constructor
    */
    
    public AdminPanel()
    {
        super(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        
        tabbedPane.add("Manage Reservables", buildManageReservablePanel());
        
        settingsPanel = buildSettingsPanel();
        tabbedPane.add("Settings", settingsPanel);
        
        add(tabbedPane);
    }
    
    /**
        BuildManageReservablePanel - Build & return the panel to manage
        reservables on
    
        @return panel The built panel
    */
    
    private ManageReservablePanel buildManageReservablePanel()
    {
        ManageReservablePanel panel = new ManageReservablePanel();
        panel.registerButtonController
            (new ManageReservableBtnController(panel));
        return panel;
    }
    
    /**
        BuildSettingsPanel - Build & return the panel allowing settings
        changes
    
        @return panel The built panel
    */
    
    private SettingsPanel buildSettingsPanel()
    {
        SettingsPanel panel = new SettingsPanel();
        return panel;
    }
    
    /**
        GetActiveTab - Return the name of the active tab
    
        @return The name of the active tab
    */
    
    public String getActiveTab()
    {
        return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
    }
    
    /**
        PopulateSettingsFields - Populate the fields in the settings tab
    
        @param adminEmailProps Properties for the admin's email
        @param adminGet Admin's get address
        @param guestEmailProps Properties for the guest's email
        @param dbProps Properties for the database
        @param adminPassword The current administrator password
    */
    
    public void populateSettingsFields(Properties adminEmailProps,
                                       String adminGet, 
                                       Properties guestEmailProps,
                                       Properties dbProps,
                                       String adminPassword)
    {
        settingsPanel.populateFields(adminEmailProps, adminGet, guestEmailProps,
                                     dbProps, adminPassword);
    }
    
    /**
        RegisterChangeController - Register change controller to the tabbed pane
    
        @param controller The controller to register
    */
    
    public void registerChangeController(ChangeListener controller)
    {
        tabbedPane.addChangeListener(controller);
    }
}