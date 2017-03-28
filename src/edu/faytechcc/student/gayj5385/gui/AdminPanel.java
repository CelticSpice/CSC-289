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
import edu.faytechcc.student.gayj5385.controller.reservable.ManageReservableButtonController;
import edu.faytechcc.student.gayj5385.controller.reservable.ManageReservableComboBoxController;
import edu.faytechcc.student.gayj5385.controller.reservable.ManageReservableListController;
import edu.faytechcc.student.gayj5385.controller.reservation.ManageReservationButtonController;
import edu.faytechcc.student.gayj5385.controller.reservation.ManageReservationComboBoxController;
import edu.faytechcc.student.gayj5385.controller.reservation.ManageReservationListController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

public class AdminPanel extends JPanel
{
    // Fields
    private JButton logout;
    private JTabbedPane tabbedPane;
    private ManageReservablePanel mngReservablePanel;
    private ManageReservationPanel mngReservationPanel;
    private SettingsPanel settingsPanel;
    
    /**
        Constructs a new AdminPanel initialized with the given listing of
        locations & mapping of reservations
    
        @param locations The locations
        @param reservations The reservations
    */
    
    public AdminPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {
        super(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        
        buildManageReservablePanel(locations);
        buildManageReservationPanel(reservations);
        buildSettingsPanel();
        buildBottomPanel();
        
        tabbedPane.add("Manage Reservables", mngReservablePanel);
        tabbedPane.add("Manage Reservations", mngReservationPanel);
        tabbedPane.add("Settings", settingsPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    /**
        Builds the bottom panel of this panel
    */
    
    private void buildBottomPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        panel.add(logout = new JButton("Logout"));
        
        logout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainPanel parent = ((MainPanel)getParent());
                parent.showOpenPanel();
            }
        });
        
        add(panel, BorderLayout.SOUTH);
    }
    
    /**
        Builds the panel to manage reservables on initialized with the given
        list of locations
    
        @param locations The locations
    */
    
    private void buildManageReservablePanel(List<Location> locations)
    {
        mngReservablePanel = new ManageReservablePanel(locations);
        
        Filter<Timeframe> timeframeFilter = new Filter<>();
        Filter<Location> locationFilter = new Filter<>();
        
        mngReservablePanel.registerButtonController(
            new ManageReservableButtonController(mngReservablePanel,
                locations, timeframeFilter, locationFilter));
        
        mngReservablePanel.registerComboBoxController(
            new ManageReservableComboBoxController(mngReservablePanel,
                timeframeFilter));
        
        mngReservablePanel.registerTimeframeListController(
            new ManageReservableListController(mngReservablePanel));        
    }
    
    /**
        Builds the panel to manage reservations on, initialized with
        the given mapping of reservations
    
        @param reservations Location reservation mapping
        @return The built panel
    */
    
    private void buildManageReservationPanel(
            HashMap<Location, List<Reservation>> reservations)
    {
        List<Location> locs = new ArrayList<>(reservations.keySet());
        List<Reservation> res;
        
        if (locs.size() > 0)
            res = reservations.get(locs.get(0));
        else
            res = new ArrayList<>();
        
        mngReservationPanel = new ManageReservationPanel(locs, res);
        
        Filter<Location> locationFilter = new Filter<>();
        Filter<Reservation> reservationFilter = new Filter<>();
        
        mngReservationPanel.registerButtonController(
            new ManageReservationButtonController(mngReservationPanel,
                reservations, locationFilter, reservationFilter));
        
        mngReservationPanel.registerComboBoxController(
            new ManageReservationComboBoxController(mngReservationPanel,
                reservations, reservationFilter));
        
        mngReservationPanel.registerReservationListController(
            new ManageReservationListController(mngReservationPanel));
    }
    
    /**
        Builds the panel allowing updates to settings to be made
    
        @return The built panel
    */
    
    private void buildSettingsPanel()
    {
        settingsPanel = new SettingsPanel();
        settingsPanel.registerController(
            new SettingsPanelController(settingsPanel));
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