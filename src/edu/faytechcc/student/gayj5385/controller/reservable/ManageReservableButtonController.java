/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservable;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.controller.ReservableAddButtonController;
import edu.faytechcc.student.gayj5385.controller.ReservableAddComboBoxController;
import edu.faytechcc.student.gayj5385.controller.ReservableAddRadioController;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.dialog.AddReservableDialog;
import edu.faytechcc.student.mccanns0131.database.DatabaseConnection;
import edu.faytechcc.student.mccanns0131.database.RecordDelete;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservableButtonController implements ActionListener
{
    // Fields
    private List<Location> locations;
    private ManageReservablePanel view;
    private Filter<Timeframe> timeframeFilter;
    private Filter<Location> locationFilter;
    
    /**
        Constructs a new ManageReservableButtonController to manage the
        given view's buttons and accepts a list of locations, filters for
        filtering timeframes & locations

        @param v The view
        @param locs The locations
        @param timeFilter Timeframe filter
        @param locFilter Location filter
    */
    
    public ManageReservableButtonController(ManageReservablePanel v,
            List<Location> locs, Filter<Timeframe> timeFilter,
            Filter<Location> locFilter)
    {
        view = v;
        locations = locs;
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
            case "Modify":
                //doModify();
                break;
            case "Delete":
                doDelete();
                break;
            case "Search":
                if (!view.getSearchCriteria().isEmpty())
                    doSearch(view.getSearchCriteria());
                break;
            case "Clear":
                doClear();
                break;
        }
    }

    /**
        Returns if any timeframe within a list of timeframes is reserved

        @param timeframes Timeframe list to check for any reserved timeframes
        @return If any timeframe in the list is reserved
    */

    private boolean isTimeframeReserved(List<Timeframe> timeframes)
    {
        boolean reserved = false;
        for (Timeframe timeframe : timeframes)
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

    private void deleteTimeframes(List<Timeframe> timeframes)
    {
        Location loc = view.getSelectedLocation();

        try
        {
            SystemPreferences prefs = SystemPreferences.getInstance();
            String user = prefs.getDBUser();
            String pass = prefs.getDBPass();
            DatabaseConnection conn = DatabaseConnection.getConnection(
                    user, pass);
            
            RecordDelete delete = new RecordDelete(conn);
            
            for (Timeframe timeframe : timeframes)
            {
                delete.deleteReservable(new Reservable(loc, timeframe));
                loc.removeTimeframe(timeframe);
            }
            
            conn.close();
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
        view.clearSearch();
        
        setLocations();
    }

    /**
        Respond to the "Delete" command
    */

    private void doDelete()
    {
        List<Timeframe> timeframes = view.getSelectedTimeframes();

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
                    
                    // Check if location should be deleted as well
                    Location loc = view.getSelectedLocation();
                    if (loc.getNumTimeframes() == 0)
                    {
                        locations.remove(loc);
                        setLocations();
                    }
                    else
                        setTimeframes(loc.getTimeframes());
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
        
        if (search.validateSearch())
        {            
            switch (search.getNumSearchLocations())
            {
                case 0:
                    if (criteria.toLowerCase().contains("cap:") ||
                        criteria.toLowerCase().contains("capacity:"))
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
     * Returns the number of locations that are searched for
     * 
     * @param criteria The search criteria
     * @return number of locations searched for
     */
    private int numSearchLocations(String criteria)
    {
        int num = 0;
        
        // Split search criteria
        String[] filterArray = criteria.split(";");

        for (String f : filterArray)
        {
            // Split keys and values
            String[] constraint = f.split(":");
            
            String key = constraint[0].trim();

            switch(key.toLowerCase())
            {
                case "locationname":
                case "location":
                case "loc":
                    num++;
                    break;
                case "capacity":
                case "cap":
            }
        }
        
        return num;
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
            
            List<Timeframe> timeframes = view.getSelectedLocation()
                    .getTimeframes(timeframeFilter.getPredicate());

            view.setTimeframes(timeframes);
        }
        else
            JOptionPane.showMessageDialog(view, "No location selected");
    }
    
    /**
        Shows the specified location on the view
    
        @param loc The location to display
    */
    
    private void showLocation(Location loc)
    {
        view.setSelectedLocation(loc);
    }
    
    /**
        Sets the locations on the view to the current location list
    */
    
    private void setLocations()
    {
        if (locationFilter.getPredicate() != null)
            view.setLocations(locationFilter.filter(locations));
        else
            view.setLocations(locations);
    }
    
    /**
        Sets the timeframes on the view
    */
    
    private void setTimeframes(List<Timeframe> timeframes)
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
        AddReservableDialog d = new AddReservableDialog(locations);

        d.registerButtonController(new ReservableAddButtonController(d,
            locations));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));

        d.setVisible(true);
        
        view.setLocations(locations);
    }
}