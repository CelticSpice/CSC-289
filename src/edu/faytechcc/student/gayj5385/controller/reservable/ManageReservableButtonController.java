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
    private List<String> searchKeys;
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
        SearchActualizer search = new SearchActualizer(criteria, searchKeys);
        
        if (search.validateSearch())
        {
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
     * Performs a search on two or more locations
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnMultipleLocations(SearchActualizer s)
    {
        locationFilter.setPredicate(s.searchLocations());
        setLocations();
        
        if (timeframeFilter.getPredicate() != null)
        {
            timeframeFilter.setPredicate(s.searchTimeframes());
            setTimeframes(timeframeFilter.filter(view.getSelectedLocation()
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
        setLocations();
            
        if (view.getSelectedLocation() != null)
        {
            timeframeFilter.setPredicate(s.searchTimeframes());
            setTimeframes(timeframeFilter.filter(view.getSelectedLocation()
                    .getTimeframes()));
        }
        else
        {
            JOptionPane.showMessageDialog(view, "No such location exists");
            doClear();
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
            
            List<ReservableTimeframe> timeframes = view.getSelectedLocation()
                    .getTimeframes(timeframeFilter.getPredicate());

            view.setTimeframes(timeframes);
        }
        else
            JOptionPane.showMessageDialog(view, "No location selected");
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
