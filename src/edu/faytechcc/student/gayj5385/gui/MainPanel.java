/**
    The main panel of the application interface
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.gayj5385.controller.OpeningController;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    // Fields
    private CardLayout layout;
    private MainFrame parent;
    
    private final String[] CARDS = { "Open", "Admin", "Guest" };
    
    /**
        Constructs a new MainPanel with the given reference to its parent, and
        a list of locations & mapping of reservations to initialize with
    
        @param p The parent
        @param locations The locations
        @param reservations The reservations
    */
    
    public MainPanel(MainFrame p, List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {        
        parent = p;
        
        setLayout(layout = new CardLayout());
        
        buildOpenPanel(locations, reservations);
        buildAdminPanel(locations, reservations);
        
        List<Location> availableLocs = deriveAvailableLocs(locations);
        buildReservationPanel(availableLocs);
    }
    
    /**
        Builds the administrator's panel
    */
    
    private void buildAdminPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {
        AdminPanel panel = new AdminPanel(locations, reservations);
        
        add(panel, CARDS[1]);
    }
    
    /**
        Builds the opening panel
    */
    
    private void buildOpenPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {
        OpenPanel panel = new OpenPanel();
        
        panel.registerButtonListener(new OpeningController(this, panel,
            locations, reservations));
        
        add(panel, CARDS[0]);
    }
    
    /**
        Builds the guest reservation panel, initialized with the given list
        of locations
    
        @param locs The locations
    */
    
    private void buildReservationPanel(List<Location> locs)
    {
        final int CARD_INDEX = 2;
        
        GuestReservationPanel panel = new GuestReservationPanel(locs);
        
        add(panel, CARDS[CARD_INDEX]);
    }
    
    /**
        Derives locations available to be reserved from a list of locations
    
        @param locs The list of locations
        @return List of locations available to reserve
    */
    
    private List<Location> deriveAvailableLocs(List<Location> locs)
    {
        List<Location> locations = new ArrayList<>();
        
        for (Location loc : locs)
        {
            if (!loc.deriveReservableTimeframes().isEmpty())
                locations.add(loc);
        }
        
        return locations;
    }
    
    /**
        Shows the administrator's view
    */
    
    public void showAdminPanel()
    {
        final int CARD_INDEX = 1;
        
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
        
        parent.pack();
        parent.setLocationRelativeTo(null);
    }
    
    /**
        Shows the guest reservation panel
    */
    
    public void showGuestReservationPanel()
    {
        final int CARD_INDEX = 2;
        
        layout.show(this, CARDS[CARD_INDEX]);
        
        parent.pack();
        parent.setLocationRelativeTo(null);
    }
}