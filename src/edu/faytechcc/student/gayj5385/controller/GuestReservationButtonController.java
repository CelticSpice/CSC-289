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
import edu.faytechcc.student.gayj5385.gui.dialog.SearchHelpDialog;
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
    private List<String> searchKeys;
    
    /**
        Constructs a new GuestReservationButtonController initialized with
        the given GuestReservationPanel to control & a list of locations
    
        @param v The view, GuestReservationPanel
        @param locs The locations
    */
    
    public GuestReservationButtonController(GuestReservationPanel v,
            List<Location> locs, Filter<Location> lf, Filter<Timeframe> tf)
    {
        view = v;
        locations = locs;
        locationFilter = lf;
        timeframeFilter = tf;
        searchKeys = new ArrayList();
        
        searchKeys.add("locationname");
        searchKeys.add("location");
        searchKeys.add("loc");
        searchKeys.add("capacity");
        searchKeys.add("cap");
        searchKeys.add("startdate");
        searchKeys.add("starttime");
        searchKeys.add("enddate");
        searchKeys.add("endtime");
        searchKeys.add("cost");
        searchKeys.add("price");
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
                clear();
                if (!view.getSearchCriteria().isEmpty())
                    search(view.getSearchCriteria());
                break;
            case "Clear":
                clear();
                view.clearSearch();
                break;
            case "Help":
                new SearchHelpDialog(searchKeys).setVisible(true);
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
        SearchActualizer search = new SearchActualizer(criteria, searchKeys);
        
        if (search.validateSearch())
        {            
//            locationFilter.setPredicate(search.searchLocations());
//            view.setLocations(locationFilter.filter(locations));
//            
//            if (view.getSelectedLocation() != null)
//            {            
//                timeframeFilter.setPredicate(search.searchTimeframes());
//                view.setTimeframes(timeframeFilter.filter(
//                        view.getSelectedLocation().getTimeframes()));
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(view, "No locations found");
//                clear();
//            }
            switch (search.getNumSearchLocations())
            {
                case 0:
                    if (criteria.toLowerCase().contains("cap::") ||
                        criteria.toLowerCase().contains("capacity::"))
                        searchOnMultipleLocations(search);
                    else
                        searchOnSelectedLocation(search);
                    break;
                case 1:
                    searchOnOneLocation(search);
                    break;
                default:
                    searchOnMultipleLocations(search);
                    break;
            }
        }
    }
    
    /**
     * Performs a search on two or more locations
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnMultipleLocations(SearchActualizer s)
    {
        locationFilter.setPredicate(s.searchLocations());
        view.setLocations(locationFilter.filter(locations));
        
        timeframeFilter.setPredicate(s.searchTimeframes());
            
        if (timeframeFilter.getPredicate() != null)
        {
            view.setTimeframes(timeframeFilter.filter(view.getSelectedLocation()
                    .getTimeframes()));
        }
    }
    
    /**
     * Performs a search on one specified location
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnOneLocation(SearchActualizer s)
    {
        locationFilter.setPredicate(s.searchLocations());
        view.setLocations(locationFilter.filter(locations));
        
        timeframeFilter.setPredicate(s.searchTimeframes());
            
        if (view.getSelectedLocation() != null)
        {
            timeframeFilter.setPredicate(s.searchTimeframes());
            view.setTimeframes(timeframeFilter.filter(view.getSelectedLocation()
                    .getTimeframes()));
        }
        else
        {
            JOptionPane.showMessageDialog(view, "No such location exists");
            clear();
        }
    }
    
    /**
     * Performs a search on the currently selected location
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnSelectedLocation(SearchActualizer s)
    {
        if (view.getSelectedLocation() != null)
        {
            timeframeFilter.setPredicate(s.searchTimeframes());
            
            List<Timeframe> timeframes = view.getSelectedLocation()
                    .getTimeframes(timeframeFilter.getPredicate());

            view.setTimeframes(timeframes);
        }
        else
            JOptionPane.showMessageDialog(view, "No location selected");
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