/**
    The main panel of the application interface
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.gayj5385.controller.GuestReservationButtonController;
import edu.faytechcc.student.gayj5385.controller.GuestReservationComboBoxController;
import edu.faytechcc.student.gayj5385.controller.GuestReservationListController;
import edu.faytechcc.student.gayj5385.controller.OpeningController;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    // Fields
    private AdminPanel adminPanel;
    private CardLayout layout;
    private GuestReservationPanel guestReservePanel;
    private MainFrame parent;
    private OpenPanel openPanel;
    
    private final String[] CARDS = { "Open", "Admin", "Guest" };
    
    /**
        Constructs a new MainPanel with the given reference to its parent
    
        @param p The parent
    */
    
    public MainPanel(MainFrame p)
    {        
        parent = p;
        
        setLayout(layout = new CardLayout());
        List<Location> locations = new ArrayList<>();
        HashMap<Location, List<Reservation>> reservations = new HashMap<>();
        
        buildOpenPanel(locations, reservations);
        buildAdminPanel(locations, reservations);        
        buildReservationPanel(locations);
    }
    
    /**
        Builds the administrator's panel
    */
    
    private void buildAdminPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {
        adminPanel = new AdminPanel(locations, reservations);
        
        add(adminPanel, CARDS[1]);
    }
    
    /**
        Builds the opening panel
    */
    
    private void buildOpenPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {
        openPanel = new OpenPanel();
        
        openPanel.registerButtonListener(new OpeningController(this, openPanel,
            locations, reservations));
        
        add(openPanel, CARDS[0]);
    }
    
    /**
        Builds the guest reservation panel, initialized with the given list
        of locations
    
        @param locs The locations
    */
    
    private void buildReservationPanel(List<Location> locs)
    {
        final int CARD_INDEX = 2;
        
        guestReservePanel = new GuestReservationPanel();
        
        guestReservePanel.registerButtonController(
                new GuestReservationButtonController(guestReservePanel, locs));
        
        guestReservePanel.registerComboBoxController(
                new GuestReservationComboBoxController(guestReservePanel));
        
        guestReservePanel.registerTimeframeListController(
                new GuestReservationListController(guestReservePanel));
        
        add(guestReservePanel, CARDS[CARD_INDEX]);
    }
    
    /**
        Shows the administrator's view
    */
    
    public void showAdminPanel()
    {
        final int CARD_INDEX = 1;
        
        adminPanel.updateModel();
        
        layout.show(this, CARDS[CARD_INDEX]);
        
        parent.pack();
        parent.setLocationRelativeTo(null);
    }
    
    /**
        Shows the opening panel
    */
    
    public void showOpenPanel()
    {
        final int CARD_INDEX = 0;
        
        layout.show(this, CARDS[CARD_INDEX]);
        
        parent.setSize(350, 200);
        parent.setLocationRelativeTo(null);
    }
    
    /**
        Shows the guest reservation panel with the updated list of locations
        that can be reserved
    
        @param availLocs Locations that can be reserved
    */
    
    public void showGuestReservationPanel(List<Location> availLocs)
    {
        final int CARD_INDEX = 2;
        
        guestReservePanel.setLocations(availLocs);
        
        layout.show(this, CARDS[CARD_INDEX]);
        
        parent.setSize(800, 425);
        parent.setLocationRelativeTo(null);
    }
}