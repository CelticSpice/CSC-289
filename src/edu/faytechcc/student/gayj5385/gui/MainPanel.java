/**
    The main panel of the application interface
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.gayj5385.controller.GuestReservationButtonController;
import edu.faytechcc.student.gayj5385.controller.GuestReservationComboBoxController;
import edu.faytechcc.student.gayj5385.controller.GuestReservationListController;
import edu.faytechcc.student.gayj5385.controller.OpeningController;
import java.awt.CardLayout;
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
        
        DataRepository repo = new DataRepository();
        
        buildOpenPanel(repo);
        buildAdminPanel(repo);        
        buildReservationPanel(repo);
    }
    
    /**
        Builds the administrator's panel
    
        @param repo The data repository
    */
    
    private void buildAdminPanel(DataRepository repo)
    {
        adminPanel = new AdminPanel(repo);
        
        add(adminPanel, CARDS[1]);
    }
    
    /**
        Builds the opening panel
    
        @param repo The data repository
    */
    
    private void buildOpenPanel(DataRepository repo)
    {
        openPanel = new OpenPanel();
        
        openPanel.registerButtonListener(new OpeningController(this, openPanel,
            repo));
        
        add(openPanel, CARDS[0]);
    }
    
    /**
        Builds the guest reservation panel
    
        @param repo The data repository
    */
    
    private void buildReservationPanel(DataRepository repo)
    {
        final int CARD_INDEX = 2;
        
        guestReservePanel = new GuestReservationPanel();
        
        Filter<ReservableLocation> locationFilter = new Filter();
        Filter<ReservableTimeframe> timeframeFilter = new Filter();
        
        guestReservePanel.registerButtonController(
                new GuestReservationButtonController(guestReservePanel, repo,
                locationFilter, timeframeFilter));
        
        guestReservePanel.registerComboBoxController(
                new GuestReservationComboBoxController(guestReservePanel,
                        timeframeFilter));
        
        guestReservePanel.registerTimeframeListController(
                new GuestReservationListController(guestReservePanel));
        
        add(guestReservePanel, CARDS[CARD_INDEX]);
    }
    
    /**
        Shows the administrator's view
    
        @param locs Locations to display
    */
    
    public void showAdminPanel(List<ReservableLocation> locs)
    {
        final int CARD_INDEX = 1;
                
        adminPanel.showInitTab(locs);
        
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
        Shows the guest reservation panel
    
        @param availLocs Locations that can be reserved
    */
    
    public void showGuestReservationPanel(List<ReservableLocation> availLocs)
    {
        final int CARD_INDEX = 2;
        
        guestReservePanel.setLocations(availLocs);
        
        layout.show(this, CARDS[CARD_INDEX]);
        
        parent.setSize(800, 425);
        parent.setLocationRelativeTo(null);
    }
}