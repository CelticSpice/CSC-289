/**
    The root administrator panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.EmailSettings;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import edu.faytechcc.student.gayj5385.controller.SettingsPanelController;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.controller.AdminPanelController;
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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminPanel extends JPanel
{
    // Fields
    private JButton logout;
    private JTabbedPane tabbedPane;
    private ManageReservablePanel mngReservablePanel;
    private ManageReservationPanel mngReservationPanel;
    private SettingsPanel settingsPanel;
    
    /**
        Constructs a new AdminPanel
    
        @param repo Data repository
    */
    
    public AdminPanel(DataRepository repo)
    {
        super(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        
        tabbedPane.addChangeListener(new AdminPanelController(this, repo));
        
        buildManageReservablePanel(repo);
        buildManageReservationPanel(repo);
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
        Builds the panel to manage reservables on
    
        @param repo Data repository
    */
    
    private void buildManageReservablePanel(DataRepository repo)
    {
        mngReservablePanel = new ManageReservablePanel();
        
        Filter<ReservableTimeframe> timeframeFilter = new Filter<>();
        Filter<ReservableLocation> locationFilter = new Filter<>();
        
        mngReservablePanel.registerButtonController(
            new ManageReservableButtonController(mngReservablePanel,
                repo, timeframeFilter, locationFilter));
        
        mngReservablePanel.registerComboBoxController(
            new ManageReservableComboBoxController(mngReservablePanel,
                timeframeFilter));
        
        mngReservablePanel.registerTimeframeListController(
            new ManageReservableListController(mngReservablePanel));        
    }
    
    /**
        Builds the panel to manage reservations on
    
        @param repo Data repository
    */
    
    private void buildManageReservationPanel(DataRepository repo)
    {   
        mngReservationPanel = new ManageReservationPanel();
        
        Filter<ReservableLocation> locationFilter = new Filter<>();
        Filter<Reservation> reservationFilter = new Filter<>();
        
        mngReservationPanel.registerButtonController(
            new ManageReservationButtonController(mngReservationPanel,
                repo, locationFilter, reservationFilter));
        
        mngReservationPanel.registerComboBoxController(
            new ManageReservationComboBoxController(mngReservationPanel,
                repo, reservationFilter));
        
        mngReservationPanel.registerReservationListController(
            new ManageReservationListController(mngReservationPanel));
    }
    
    /**
        Builds the panel allowing updates to settings to be made
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
        Updates the settings panel's current settings input
        
        @param adminEmail Settings for the administrator's email
        @param guestEmail Settings for the guest's email
        @param db Settings for the database
        @param securityOptions Available security options to choose from
    */
    
    public void setSettingsSettings(EmailSettings adminEmail,
            EmailSettings guestEmail, DatabaseSettings db,
            SecurityOption[] securityOptions)
    {
        settingsPanel.setSecurityOptions(securityOptions);
        settingsPanel.setAdminEmailSettings(adminEmail);
        settingsPanel.setGuestEmailSettings(guestEmail);
        settingsPanel.setDBSettings(db);
    }
    
    /**
        Updates the view for the given tab with the given list of locations
    
        @param tabName Name of tab to update view for
        @param locs List of locations to display
    */
    
    public void updateView(String tabName, List<ReservableLocation> locs)
    {
        if (tabName.equals("Manage Reservables"))
            mngReservablePanel.setLocations(locs);
        
        if (tabName.equals("Manage Reservations"))
            mngReservationPanel.setLocations(locs);
    }
}