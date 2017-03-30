/**
    The main panel of the application interface
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.SHA256SaltHasher;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
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
        SystemPreferences prefs = new SystemPreferences();
        SHA256SaltHasher saltHasher = new SHA256SaltHasher();
        
        buildOpenPanel(locations, reservations, prefs, saltHasher);
        buildAdminPanel(locations, reservations, prefs, saltHasher);        
        buildReservationPanel(locations, prefs);
    }
    
    /**
        Builds the administrator's panel
    */
    
    private void buildAdminPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations,
        SystemPreferences prefs, SHA256SaltHasher saltHasher)
    {
        adminPanel = new AdminPanel(locations, reservations, prefs,
                saltHasher);
        
        add(adminPanel, CARDS[1]);
    }
    
    /**
        Builds the opening panel
    */
    
    private void buildOpenPanel(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations,
        SystemPreferences prefs,
        SHA256SaltHasher saltHash)
    {
        openPanel = new OpenPanel();
        
        openPanel.registerButtonListener(new OpeningController(this, openPanel,
            locations, reservations, prefs, saltHash));
        
        add(openPanel, CARDS[0]);
    }
    
    /**
        Builds the guest reservation panel, initialized with the given list
        of locations
    
        @param locs The locations
    */
    
    private void buildReservationPanel(List<Location> locs,
            SystemPreferences prefs)
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