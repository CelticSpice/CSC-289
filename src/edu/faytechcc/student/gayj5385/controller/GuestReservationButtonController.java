/**
    Controller for buttons on GuestReservationPanel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.gayj5385.gui.GuestReservationPanel;
import edu.faytechcc.student.gayj5385.gui.MainPanel;
import edu.faytechcc.student.gayj5385.gui.dialog.MakeReservationDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GuestReservationButtonController implements ActionListener
{
    private GuestReservationPanel view;
    private List<Location> locations;
    private Filter<Location> locationFilter;
    private Filter<Timeframe> timeframeFilter;
    
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
        locationFilter = new Filter();
        timeframeFilter = new Filter();
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
                if (!view.getSearchCriteria().isEmpty())
                    search(view.getSearchCriteria());
                break;
            case "Clear":
                clear();
                break;
            case "Exit":
                exit();
                break;
        }
    }
    
    private void clear()
    {
        locationFilter.setPredicate(null);
        timeframeFilter.setPredicate(null);
        view.clearSearch();
        
        updateLocations();
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
            
            new MakeReservationDialog(reservable).setVisible(true);
            
            updateLocations();
        }
    }
    
    /**
        Performs a search for reservables based on search criteria
     
        @param criteria The search criteria
    */
    
    private void search(String criteria)
    {
        List<String> acceptedKeys = new ArrayList();
        
        acceptedKeys.add("locationname");
        acceptedKeys.add("location");
        acceptedKeys.add("loc");
        acceptedKeys.add("capacity");
        acceptedKeys.add("cap");
        acceptedKeys.add("startdate");
        acceptedKeys.add("starttime");
        acceptedKeys.add("enddate");
        acceptedKeys.add("endtime");
        acceptedKeys.add("cost");
        acceptedKeys.add("price");
        
        SearchActualizer search = new SearchActualizer(criteria, acceptedKeys);
        
        if (search.validateSearch())
        {            
            locationFilter.setPredicate(search.searchLocations());
            view.setLocations(locationFilter.filter(locations));
            
            if (view.getSelectedLocation() != null)
            {            
                timeframeFilter.setPredicate(search.searchTimeframes());
                view.setTimeframes(timeframeFilter.filter(
                        view.getSelectedLocation().getTimeframes()));
            }
            else
            {
                JOptionPane.showMessageDialog(view, "No locations found");
                clear();
            }
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
    
    /**
        Updates the list of locations available to be reserved
    */
    
    private void updateLocations()
    {
        List<Location> availLocs = new ArrayList<>();
        for (Location loc : locations)
        {
            List<Timeframe> timeframes = loc.deriveReservableTimeframes();
            if (timeframes.size() > 0)
                availLocs.add(loc);
        }
        view.setLocations(availLocs);
    }
}