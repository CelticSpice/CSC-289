/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Admin;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class ManageReservableButtonController implements ActionListener
{
    // Fields
    private List<Location> locations;
    private ManageReservablePanel view;
    
    /**
        Constructor - Accepts the view to control buttons for and a list of
        locations
    
        @param v The view
        @param locs The locations
    */
    
    public ManageReservableButtonController(ManageReservablePanel v,
            List<Location> locs)
    {
        view = v;
        locations = locs;
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
            case "Logout":
                System.exit(0);
                break;
            case "Search":
                doSearch(view.getSearchCriteria());
                break;
            case "Clear":
                try
                {
                    doClear();
                    view.clearSearch();
                }
                catch (SQLException ex){}
                break;
        }
    }
    
    /**
        Return if any timeframe within a list of timeframes is reserved
    
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
        Deletes the specified location
    
        @param loc The location to delete
    */
    
    private void deleteLocation(Location loc)
    {
        locations.remove(loc);
        view.setLocations(locations);
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
            for (Timeframe timeframe : timeframes)
            {
                Admin.removeReservable(new Reservable(loc, timeframe));
                loc.removeTimeframe(timeframe);
            }
            
            view.setTimeframes(loc.getTimeframes());
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(view, "Failed to update database");
        }
    }
    
    private void doClear() throws SQLException
    {
        Query query = new Query();
        
        Location[] locationsArray = query.queryLocations();
        List<Location> locationsList = new ArrayList();
        
        for (Location loc : locationsArray)
        {
            locationsList.add(loc);
        }
        
        view.setLocations(locationsList);
    }
    
    /**
        Respond to the "Delete" button being clicked
    */
    
    private void doDelete()
    {
        List<Timeframe> timeframes = view.getSelectedTimeframes();
        
        if (!timeframes.isEmpty())
        {
            if (isTimeframeReserved(timeframes))
            {
                JOptionPane.showMessageDialog(view,
                    "Cannot remove reserved timeframes : Cancel reservations " +
                    "first");
            }
            else
            {
                // Confirm deletion
                int choice = JOptionPane.showConfirmDialog(view,
                    "Are you sure you want to delete the selected timeframes?");

                if (choice == JOptionPane.YES_OPTION)
                    deleteTimeframes(timeframes);
                
                // Check if location should be deleted as well
                Location loc = view.getSelectedLocation();
                if (loc.getNumTimeframes() == 0)
                    deleteLocation(loc);
            }   
        }
    }
    
    /**
     * DoSearch - Perform a search for reservables based on search criteria
     * 
     * @param criteria The search criteria
     */
    private void doSearch(String criteria)
    {
        try
        {
            SearchActualizer search = new SearchActualizer();
            
            Filter<Reservable> f = new Filter();
            f.setPredicate(search.searchReservables(criteria));
            
            List<Reservable> reservables = ;
            
            JOptionPane.showMessageDialog(view, reservables.size());
                        
            List<Reservable> filteredReservables = reservables.stream().filter
                (f.getPredicate()).collect(Collectors.<Reservable>toList());
            
            JOptionPane.showMessageDialog(view, f.getPredicate());
            
            List<Location> locations = new ArrayList();
            
            for (Reservable r : filteredReservables)
            {
                locations.add(r.getLocation());
            }
            
//            Location[] locsToAdd = new Location[locations.size()];
            
            view.setLocations(locations);
            
            // location=cabin 01; cap=15; startdate=2017-03-23; starttime=00:00; enddate=2017-03-23; endtime=01:00; cost=325
        }
        catch (SQLException ex)
        {
        }
    }
    
    /**
        Show the dialog enabling the addition of a reservable
    */
    
    private void showAddDialog()
    {        
        ReservableAddDialog d = new ReservableAddDialog(locations);
        
        d.registerButtonController(new ReservableAddButtonController(d,
                locations));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));
        
        d.setVisible(true);
        
        if (d.getIfRecordsAdded())
            view.setLocations(locations);
    }
    
    /**
     * Validate the capacity input
     *
     * @return If the capacity input is valid
     */
    private boolean validateCapacity(String cap)
    {
        boolean valid = cap.matches("\\d+");
        
        if (valid)
        {
            valid = Integer.parseInt(cap) > 0;
            
            if (!valid)
                JOptionPane.showMessageDialog(view,
                    "Capacity must be greater than 0");
        }
        else
            JOptionPane.showMessageDialog(view, "Invalid input for capacity");
        
        return valid;
    }
}