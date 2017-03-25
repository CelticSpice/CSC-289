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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class ManageReservableButtonController implements ActionListener
{
    // Fields
    private HashMap<Location, List<Timeframe>> timeframeMap;
    private List<Location> locations;
    private ManageReservablePanel view;
    private Filter filter;
    
    /**
        Constructs a new ManageReservableButtonController to manage the
        given view's buttons & a list of locations

        @param v The view
        @param f The filter
        @param locs The locations
    */
    
    public ManageReservableButtonController(ManageReservablePanel v,
            Filter<Reservable> f, List<Location> locs)
    {
        view = v;
        filter = f;
        locations = locs;
        timeframeMap = new HashMap<>();
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
                if (!view.getSearchCriteria().equals(""))
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

    private void doClear()
    {
        filter.setPredicate(null);
        
        view.setTimeframes(new ArrayList());
        view.setLocations(locations);
        view.clearSearch();
    }

    /**
        Perform the deletion of timeframes & potentially a location
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
        if (validateSearch(criteria))
        {
            System.out.println(numSearchLocations(criteria));
            switch (numSearchLocations(criteria))
            {
                case 0:
                    searchOnSelectedLocation(criteria);
                    break;
                case 1:
                    searchOnOneLocation(criteria);
                    break;
                default:
                    searchOnMultipleLocations(criteria);
                    break;
            }
        }
//        List<Reservable> reservables = new ArrayList();
//        for (Location loc : locations)
//            reservables.addAll(loc.deriveReservables(filter.getPredicate()));
//
//        List<Location> filteredLocations = new ArrayList();
//        List<Timeframe> timeframes = new ArrayList();
//
//        for (Reservable r : reservables)
//        {
//             
//             timeframes.add(r.getTimeframe());
//        }
//
//        view.setLocations(filteredLocations);

        // location=cabin 01; cap=15; startdate=2017-03-23; starttime=00:00; enddate=2017-03-23; endtime=01:00; cost=325.00
    }
    
    private int numSearchLocations(String criteria)
    {
        int num = 0;
        
        // Split search criteria
        String[] filterArray = criteria.split(";");

        for (String f : filterArray)
        {
            // Split keys and values
            String[] constraint = f.split("=");

            if (constraint.length == 2)
            {                
                String key = constraint[0].trim(), 
                       val = constraint[1].trim();

                switch(key.toLowerCase())
                {
                    case "locationname":
                    case "location":
                        num++;
                        break;
                }
            }
            else
                JOptionPane.showMessageDialog(view, "Invalid search criteria");
        }
        
        return num;
    }
    private void searchOnMultipleLocations(String criteria)
    {
        SearchActualizer search = new SearchActualizer();
        
        filter.setPredicate(search.searchLocations(criteria));
        
        List<Location> filteredLocations = (ArrayList<Location>)
                locations.stream().filter(filter.getPredicate())
                        .collect(Collectors.toList());
        
        view.setLocations(filteredLocations);
    }
    
    private void searchOnOneLocation(String criteria)
    {
        SearchActualizer search = new SearchActualizer();

        filter.setPredicate(search.searchLocations(criteria));
        
        List<Location> filteredLocation = (ArrayList<Location>)
                locations.stream().filter(filter.getPredicate())
                        .collect(Collectors.toList());
        
        filter.setPredicate(search.searchTimeframes(criteria));
        
        view.setLocations(filteredLocation);
        for (Location l : filteredLocation)
            view.setTimeframes(l.getTimeframes(filter.getPredicate()));
    }
    
    private void searchOnSelectedLocation(String criteria)
    {
        if (view.getSelectedLocation() != null)
        {
            SearchActualizer search = new SearchActualizer();

            filter.setPredicate(search.searchTimeframes(criteria));

            List<Timeframe> timeframes = view.getSelectedLocation()
                    .getTimeframes(filter.getPredicate());

            view.setTimeframes(timeframes);
        }
        else
            JOptionPane.showMessageDialog(view, "No location selected");
    }
    
    /**
        Show the dialog enabling the addition of reservables
    */

    private void showAddDialog()
    {
        ReservableAddDialog d = new ReservableAddDialog(locations);

        d.registerButtonController(new ReservableAddButtonController(d,
                locations));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));

        d.setVisible(true);
        
        view.setLocations(locations);
    }

    /**
     * Validate the capacity input
     *
     * @return If the capacity input is valid
     */
    private boolean validateCapacity(String capacity)
    {
        boolean valid = capacity.matches("\\d+");

        if (valid)
        {
            valid = Integer.parseInt(capacity) > 0;

            if (!valid)
                JOptionPane.showMessageDialog(view,
                    "Capacity must be greater than 0");
        }
        else
            JOptionPane.showMessageDialog(view, "Invalid input for capacity");

        return valid;
    }
    
    private boolean validateCost(String cost)
    {
        boolean valid = cost.matches("\\d+.\\d{2}");
        
        if (valid)
        {
            valid = Double.parseDouble(cost) >= 0.00;
            
            if (!valid)
                JOptionPane.showMessageDialog(view,
                        "Cost must be greater than or equal to 0.00");
        }
        else
            JOptionPane.showMessageDialog(view, "Invalid input for cost");
        
        return valid;
    }
    
    private boolean validateEndDate(String date)
    {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    private boolean validateEndTime(String time)
    {
        
        return time.matches("\\d{2}:\\d{2}");
    }
    
    /**
     * Validates the location name input
     * 
     * @param name The location name
     * @return Whether the location is valid or not
     */
    private boolean validateLocationName(String name)
    {
        for (char c : name.toCharArray())
        {
            if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c))
            {
                JOptionPane.showMessageDialog(view,
                        "A location name consists only of letters, digits," +
                                " and/or spaces");
                
                return false;
            }
        }
        
        return true;
    }
    
    private boolean validateSearch(String criteria)
    {
        // Split search criteria
        String[] filterArray = criteria.split(";");

        for (String f : filterArray)
        {
            // Split keys and values
            String[] constraint = f.split("=");

            if (constraint.length == 2)
            {                
                String key = constraint[0].trim(), 
                       val = constraint[1].trim();

                switch(key.toLowerCase())
                {
                    case "locationname":
                    case "location":
                        if (!validateLocationName(val))
                            return false;
                        break;
                    case "capacity":
                    case "cap":
                        if (!validateCapacity(val))
                            return false;
                        break;
                    case "startdate":
                        if (!validateStartDate(val))
                            return false;
                        break;
                    case "starttime":
                        if (!validateStartTime(val))
                            return false;
                        break;
                    case "enddate":
                        if (!validateEndDate(val))
                            return false;
                        break;
                    case "endtime":
                        if (!validateEndTime(val))
                            return false;
                        break;
                    case "cost":
                    case "price":
                        if (!validateCost(val))
                            return false;
                        break;
                }
            }
            else
                JOptionPane.showMessageDialog(view, "Invalid search criteria");
        }
        
        return true;
    }
    
    private boolean validateStartDate(String date)
    {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    private boolean validateStartTime(String time)
    {
        return time.matches("\\d{2}:\\d{2}");
    }
    
//    private boolean validateTimeframe()
//    {
//        Do we need to validate the entire timeframe?
//        (i.e. ensuring dates and times don't take place before one another)
//    }
}
