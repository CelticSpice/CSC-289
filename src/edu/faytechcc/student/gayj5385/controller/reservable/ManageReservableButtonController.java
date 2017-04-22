/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservable;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.controller.ReservableAddButtonController;
import edu.faytechcc.student.gayj5385.controller.ReservableAddComboBoxController;
import edu.faytechcc.student.gayj5385.controller.ReservableAddRadioController;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.dialog.AddReservableDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.CreateReservableReportDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.SearchHelpDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.UpdateReservableDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservableButtonController implements ActionListener
{
    private DataRepository repo;
    private ManageReservablePanel view;
    private Filter<ReservableTimeframe> timeframeFilter;
    private Filter<ReservableLocation> locationFilter;
    
    /**
        Constructs a new ManageReservableButtonController

        @param v The view
        @param repo Data repository
        @param timeFilter ReservableTimeframe filter
        @param locFilter ReservableLocation filter
    */
    
    public ManageReservableButtonController(ManageReservablePanel v,
            DataRepository repo, Filter<ReservableTimeframe> timeFilter,
            Filter<ReservableLocation> locFilter)
    {
        view = v;
        this.repo = repo;
        timeframeFilter = timeFilter;
        locationFilter = locFilter;
    }

    /**
        Handle the clicking of a button

        @param e The action event
    */

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Add":
                showAddDialog();
                break;
            case "Update":
                doUpdate();
                break;
            case "Delete":
                doDelete();
                break;
            case "Search":
                doClear();
                if (!view.getSearchCriteria().isEmpty())
                    doSearch(view.getSearchCriteria());
                break;
            case "Clear":
                doClear();
                view.clearSearch();
                break;
            case "Help":
                new SearchHelpDialog(false).setVisible(true);
                break;
            case "Create Report":
                new CreateReservableReportDialog(getAllMatchingReservables());
                break;
        }
    }

    /**
        Returns if any timeframe within a list of timeframes is reserved

        @param timeframes Timeframe list to check for any reserved timeframes
        @return If any timeframe in the list is reserved
    */

    private boolean isTimeframeReserved(List<ReservableTimeframe> timeframes)
    {
        boolean reserved = false;
        for (ReservableTimeframe timeframe : timeframes)
            if (timeframe.isReserved())
            {
                reserved = true;
                break;
            }
        return reserved;
    }

    /**
        Delete timeframes given in a list

        @param timeframes Timeframes to delete
    */

    private void deleteTimeframes(List<ReservableTimeframe> timeframes)
    {
        ReservableLocation loc = view.getSelectedLocation();

        try
        {            
            for (ReservableTimeframe timeframe : timeframes)
                repo.removeReservable(new Reservable(loc, timeframe));
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(view, "Failed to update database",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
        Clears the search parameters
    */
    
    private void doClear()
    {
        timeframeFilter.setPredicate(null);
        locationFilter.setPredicate(null);
        
        setLocations();
    }

    /**
        Respond to the "Delete" command
    */

    private void doDelete()
    {
        List<ReservableTimeframe> timeframes = view.getSelectedTimeframes();

        if (!timeframes.isEmpty())
        {
            if (!isTimeframeReserved(timeframes))
            {
                // Confirm deletion
                int choice = JOptionPane.showConfirmDialog(view,
                    "Are you sure you want to delete the selected timeframes?");

                if (choice == JOptionPane.YES_OPTION)
                {
                    deleteTimeframes(timeframes);
                    setLocations();
                }
            }
            else
                JOptionPane.showMessageDialog(view,
                    "Cannot remove reserved timeframes. Cancel reservations " +
                        "first", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
        Performs a search for reservables based on search criteria
     
        @param criteria The search criteria
    */
    
    private void doSearch(String criteria)
    {        
        SearchActualizer search = new SearchActualizer(criteria);
        
        // Get relevant locations
        locationFilter.setPredicate(search.searchLocations());
        List<ReservableLocation> locs;
        
        if (locationFilter.getPredicate() != null)
            locs = locationFilter.filter(repo.getAvailableLocations());
        else
            locs = repo.getAvailableLocations();
        
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
        }
        view.setTimeframes(times);
        view.setLocations(locs);
    }
    
    /**
        Shows the dialog enabling an update to be made to a reservable
    */
    
    private void doUpdate()
    {
        List<ReservableTimeframe> selectedTimes = view.getSelectedTimeframes();
        if (selectedTimes.size() == 1 && !selectedTimes.get(0).isReserved())
        {
            ReservableLocation loc = view.getSelectedLocation();
            ReservableTimeframe timeframe = selectedTimes.get(0);
            new UpdateReservableDialog(new Reservable(loc, timeframe), repo);
            setLocations();
        }
    }
    
    /**
     * Gets all reservables that match the location and timeframe filters
     */
    private List<Reservable> getAllMatchingReservables()
    {
        List<Reservable> reservables = new ArrayList();
        
        if (locationFilter.getPredicate() != null)
        {
            if (timeframeFilter.getPredicate() != null)
            {
                for (ReservableLocation l : locationFilter.filter(
                        repo.getAvailableLocations()))
                {            
                    for (ReservableTimeframe t : timeframeFilter.filter(
                            l.getTimeframes()))
                    {
                        reservables.add(new Reservable(l, t));
                    }
                }
            }
            else
            {
                for (ReservableLocation l : locationFilter.filter(
                        repo.getAvailableLocations()))
                {            
                    for (ReservableTimeframe t : l.getTimeframes())
                    {
                        reservables.add(new Reservable(l, t));
                    }
                }
            }
        }
        else
        {
            if (timeframeFilter.getPredicate() != null)
            {
                for (ReservableLocation l : repo.getAvailableLocations())
                {            
                    for (ReservableTimeframe t : timeframeFilter.filter(
                            l.getTimeframes()))
                    {
                        reservables.add(new Reservable(l, t));
                    }
                }
            }
            else
            {
                for (ReservableLocation l : repo.getAvailableLocations())
                {            
                    for (ReservableTimeframe t : l.getTimeframes())
                    {
                        reservables.add(new Reservable(l, t));
                    }
                }
            }
        }
        return reservables;
    }
    
    /**
        Sets the locations on the view to the current locating listing
    */
    
    private void setLocations()
    {
        if (locationFilter.getPredicate() != null)
            view.setLocations(locationFilter.filter(repo.getLocations()));
        else
            view.setLocations(repo.getLocations());
    }
    
    /**
        Sets the timeframes on the view
    */
    
    private void setTimeframes(List<ReservableTimeframe> timeframes)
    {
        if (timeframeFilter.getPredicate() != null)
            view.setTimeframes(timeframeFilter.filter(timeframes));
        else
            view.setTimeframes(timeframes);
    }
    
    /**
        Show the dialog enabling the addition of reservables
    */

    private void showAddDialog()
    {
        AddReservableDialog d = new AddReservableDialog(repo.getLocations());

        d.registerButtonController(new ReservableAddButtonController(d,
            repo));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));

        d.setVisible(true);
        
        setLocations();
    }
}
