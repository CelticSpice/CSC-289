/**
    The root administrator panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.gayj5385.controller.SettingsPanelController;
import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.controller.ManageReservableButtonController;
import edu.faytechcc.student.gayj5385.controller.ManageReservableComboBoxController;
import edu.faytechcc.student.gayj5385.controller.ManageReservableListController;
import edu.faytechcc.student.gayj5385.controller.ManageReservationComboBoxController;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import static java.util.stream.Collectors.toList;

public class AdminPanel extends JPanel
{
    // Fields
    private JTabbedPane tabbedPane;
    private ManageReservablePanel mngReservablePanel;
    private ManageReservationPanel mngReservationPanel;
    private SettingsPanel settingsPanel;
    
    /**
        Constructs a new AdminPanel with the given location & reservation data
    
        @param locs The locations
        @param reserves The reservations
    */
    
    public AdminPanel(List<Location> locs,
            HashMap<Location, List<Reservation>> reserves)
    {
        super(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        
        buildManageReservablePanel(locs);
        buildManageReservationPanel(locs, reserves);
        buildSettingsPanel();
        
        tabbedPane.add("Manage Reservables", mngReservablePanel);
        tabbedPane.add("Manage Reservations", mngReservationPanel);
        tabbedPane.add("Settings", settingsPanel);
        
        add(tabbedPane);
    }
    
    /**
        Builds the panel to manage reservables on, initialized with
        the given list of locations
    
        @param locs The locations
        @return The built panel
    */
    
    private void buildManageReservablePanel(List<Location> locs)
    {
        mngReservablePanel = new ManageReservablePanel(locs);
        
        Filter<Timeframe> timeframeFilter = new Filter<>();
        Filter<Location> locationFilter = new Filter<>();
        
        mngReservablePanel.registerButtonController(
                new ManageReservableButtonController(mngReservablePanel,
                        locs, timeframeFilter, locationFilter));
        
        mngReservablePanel.registerComboBoxController(
                new ManageReservableComboBoxController(mngReservablePanel,
                        timeframeFilter));
        
        mngReservablePanel.registerTimeframeListController(
                new ManageReservableListController(mngReservablePanel));        
    }
    
    /**
        Builds & returns the panel to manage reservations on, initialized with
        the given list of locations & mapping of reservations
    
        @return The built panel
    */
    
    private ManageReservationPanel buildManageReservationPanel(
            List<Location> locs, HashMap<String, List<Reservation>> reserves)
    {
        List<Location> reservedLocs = locs.stream()
                .filter(l -> l.isReserved())
                .collect(toList());
        
        Filter<Reservation> filter = new Filter<>();
        
        mngReservationPanel = new ManageReservationPanel(reservedLocs);
        
        mngReservationPanel.registerComboBoxController(
                new ManageReservationComboBoxController(mngReservationPanel,
                    reserves, filter));
        
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