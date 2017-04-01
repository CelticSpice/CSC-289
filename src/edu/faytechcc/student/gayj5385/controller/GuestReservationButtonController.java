/**
    Controller for buttons on GuestReservationPanel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.gayj5385.gui.GuestReservationPanel;
import edu.faytechcc.student.gayj5385.gui.MainPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuestReservationButtonController implements ActionListener
{
    private GuestReservationPanel view;
    private List<Location> locations;
    
    /**
        Constructs a new GuestReservationButtonController initialized with
        the given GuestReservationPanel to control & a list of locations
    
        @param v The view, GuestReservationPanel
        @param locs The locations
    */
    
    public GuestReservationButtonController(GuestReservationPanel v,
            List<Location> locs)
    {
        view = v;
        locations = locs;
    }
    
    /**
        Performs an action on a button being clicked
    
        @param e The ActionEvent
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Reserve":
                showMakeReservationDialog();
                break;
            case "Search":
                // Do this
                break;
            case "Clear":
                // Do this
                break;
            case "Exit":
                exit();
                break;
        }
    }
    
    /**
        Displays the dialog for the guest to provide details & confirm
        making a reservation
    */
    
    private void showMakeReservationDialog()
    {
        List<Timeframe> timeframes = view.getSelectedTimeframes();
        
        if (timeframes.size() == 1)
        {
            Location loc = view.getSelectedLocation();
            Reservable reservable = new Reservable(loc, timeframes.get(0));
            
            new MakeReservationDialog(reservable).
        }
    }
    
    /**
        Exits the GuestReservationPanel
    */
    
    private void exit()
    {
        MainPanel parent = ((MainPanel) view.getParent());
        parent.showOpenPanel();
    }
}