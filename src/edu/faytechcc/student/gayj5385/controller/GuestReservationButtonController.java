/**
    Controller for buttons on GuestReservationPanel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
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

public class GuestReservationButtonController implements ActionListener
{
    private DataRepository repo;
    private GuestReservationPanel view;
    private Filter<ReservableLocation> locationFilter;
    private Filter<ReservableTimeframe> timeframeFilter;
    
    /**
        Constructs a new GuestReservationButtonController
    
        @param v The view
        @param repo Data repository
        @param lf Location filter
        @param tf Timeframe filter
    */
    
    public GuestReservationButtonController(GuestReservationPanel v,
            DataRepository repo, Filter<ReservableLocation> lf, 
            Filter<ReservableTimeframe> tf)
    {
        view = v;
        locationFilter = lf;
        timeframeFilter = tf;
        this.repo = repo;
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
                new SearchHelpDialog(false).setVisible(true);
                break;
            case "Exit":
                exit();
                break;
        }
    }
    
    /**
     * Clears search results
     */
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
        List<ReservableTimeframe> timeframes = view.getSelectedTimeframes();
        
        if (timeframes.size() == 1)
        {
            ReservableLocation loc = view.getSelectedLocation();
            Reservable reservable = new Reservable(loc, timeframes.get(0));
            
            new MakeReservationDialog(reservable, repo).setVisible(true);
            
            updateLocations();
        }
    }
    
    /**
        Performs a search for reservables based on search criteria
     
        @param criteria The search criteria
    */
    
    private void search(String criteria)
    {
        SearchActualizer search = new SearchActualizer(criteria);
        
        // Get relevant locations
        locationFilter.setPredicate(search.searchLocations());
        List<ReservableLocation> locs = repo.getAvailableLocations();
        
        if (locationFilter.getPredicate() != null)
        {
            locs = locationFilter.filter(repo.getAvailableLocations());
        }
        
        // Get relevant timeframes
        timeframeFilter.setPredicate(search.searchTimeframes());
        List<ReservableTimeframe> times = new ArrayList();
            
        if (timeframeFilter.getPredicate() != null)
        {
            // Used to specify locations to remove from locs
            List<ReservableLocation> locsToRemove = new ArrayList();
            
            for (ReservableLocation l : locs)
            {
                times = timeframeFilter.filter(l.getTimeframes());
            
                if (times.isEmpty())
                {
                    // Add to locsToRemove since there is no matching timeframes
                    locsToRemove.add(l);
                }
            }
            // Remove each location in locsToRemove from locs
            for (ReservableLocation l : locsToRemove)
            {
                locs.remove(l);
            }
            view.setTimeframes(times);
        }
        view.setLocations(locs);
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
        view.setLocations(repo.getAvailableLocations());
    }
}