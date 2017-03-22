/**
 * Provides search functionality
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SearchActualizer
{
    // Fields
    private JPanel view;
    
    /**
     * Constructor
     */
    public SearchActualizer(JPanel v)
    {
        view = v;
    }
    
    /**
     * SearchReservables - Search for reservables based on criteria
     * 
     * @param v ManageReservablePanel
     * @param criteria The criteria
     * @throws SQLException Error querying locations
     */
    public void searchReservables(ManageReservablePanel v, String criteria)
            throws SQLException
    {
        Query query = new Query();
        
        Location[] allLocations = query.queryLocations();
        ArrayList<Location> filteredLocations = new ArrayList();
        
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        Timeframe timeframe;
        
        // Split search criteria
        String[] filters = criteria.split(";");
        
        for (String filter : filters)
        {
            // Split keys and values
            String[] constraint = filter.split("=");
            
            if (constraint.length == 2)
            {                
                String key = constraint[0].trim();
                String val = constraint[1].trim();
                
                switch(key.toLowerCase())
                {
                    case "locationname":
                    case "location":
                        filterReservablesByLocationName(allLocations,
                                filteredLocations, val, v);
                        break;
                    case "capacity":
                    case "cap":
                        filterReservablesByCapacity(allLocations,
                                filteredLocations, val, v);
                        break;
                    case "startdatetime":
                    case "start":
                        if (val.matches("\\d{4}-\\d{2}-\\d{2},\\d{2}:\\d{2}"))
                        {
                            DateTimeFormatter formatter =
                                    DateTimeFormatter.ofPattern(
                                            "yyyy-MM-dd,HH:mm");
                            startDateTime = LocalDateTime.parse(val, formatter);
                            if (endDateTime != null)
                            {
                                timeframe = new Timeframe(startDateTime,
                                        endDateTime);
                                filterReservablesByTimeframe(allLocations,
                                        filteredLocations, timeframe, v);
                            }
                        }
                        break;
                    case "enddatetime":
                    case "end":
                        if (val.matches("\\d{4}-\\d{2}-\\d{2},\\d{2}:\\d{2}"))
                        {
                            DateTimeFormatter formatter =
                                    DateTimeFormatter.ofPattern(
                                            "yyyy-MM-dd,HH:mm");
                            endDateTime = LocalDateTime.parse(val, formatter);
                            if (startDateTime != null)
                            {
                                timeframe = new Timeframe(startDateTime,
                                        endDateTime);
                                filterReservablesByTimeframe(allLocations,
                                        filteredLocations, timeframe, v);
                            }
                        }
                        break;
                        //start=2017-03-20,13:00; end=2017-03-20,14:00
                    case "cost":
                    case "price":

                        break;
                    default:
                        v.setLocations(allLocations);
                }
            }
        }
    }
    
    /**
     * FilterReservablesByCapacity - Filter search results by a specified
     * capacity
     * 
     * @param allLocations All locations in the database
     * @param filteredLocations List of filtered locations
     * @param value The capacity
     * @param v ManageReservablePanel
     */
    public void filterReservablesByCapacity(Location[] allLocations,
            ArrayList<Location> filteredLocations, String value,
            ManageReservablePanel v)
    {
        if (validateCapacity(value))
        {
            if (filteredLocations.isEmpty())
            {
                for (Location location : allLocations)
                {
                    if (location.getCapacity() ==
                            Integer.parseInt(value))
                        filteredLocations.add(location);
                }
            }
            else
            {
                for (Location location : filteredLocations)
                {
                    if (location.getCapacity() !=
                            Integer.parseInt(value))
                        filteredLocations.remove(location);
                }
            }

            if (!filteredLocations.isEmpty())
            {
                Location[] locationsToAdd =
                        new Location[filteredLocations.size()];
                v.setLocations(filteredLocations.toArray(
                        locationsToAdd));
            }
            else
            {
                JOptionPane.showMessageDialog(v, "No " +
                        "locations with that capacity were " +
                        "found");
            }
        }
    }
    
    /**
     * FilterReservablesByLocationName - Filter search results by a specified
     * location name
     * 
     * @param allLocations All locations in the database
     * @param filteredLocations List of filtered locations
     * @param value The location name
     * @param v ManageReservablePanel
     */
    public void filterReservablesByLocationName(Location[] allLocations,
            ArrayList<Location> filteredLocations, String value,
            ManageReservablePanel v)
    {
        for (Location location : allLocations)
        {
            if (!filteredLocations.contains(location))
                filteredLocations.add(location);
            filteredLocations.removeIf(l -> !l.getName().equalsIgnoreCase(
                    value));
        }

        if (!filteredLocations.isEmpty())
        {
        
            Location[] locationsToAdd = new Location[filteredLocations.size()];
            v.setLocations(filteredLocations.toArray(locationsToAdd));
        }
        else
        {
            JOptionPane.showMessageDialog(v, "No locations" +
                    " with that name were found");
        }
    }
    
    /**
     * FilterReservablesByTimeframe - Filter search results by a specified
     * timeframe
     * 
     * @param allLocations All locations in the database
     * @param filteredLocations List of filtered locations
     * @param timeframe The timeframe being searched for
     * @param v ManageReservablePanel
     */
    public void filterReservablesByTimeframe(Location[] allLocations,
            ArrayList<Location> filteredLocations, Timeframe timeframe,
            ManageReservablePanel v)
    {
        LocalDateTime start = LocalDateTime.of(timeframe.getStartDate(),
                timeframe.getStartTime());
        LocalDateTime end = LocalDateTime.of(timeframe.getEndDate(),
                timeframe.getEndTime());
        for (Location location : allLocations)
        {
            if (location.hasTimeframe(t -> t.startsOnDatetime(start) &&
                    t.endsOnDatetime(end)))
            {
                if (!filteredLocations.contains(location))
                    filteredLocations.add(location);
            }
        }
        if (!filteredLocations.isEmpty())
        {
            for (Location location : filteredLocations)
            {
                if (!location.hasTimeframe(t -> t.startsOnDatetime(start) &&
                        t.endsOnDatetime(end)))
                {
                    filteredLocations.remove(location);
                }
            }
        }
        
        if (!filteredLocations.isEmpty())
        {
            Location[] locationsToAdd = new Location[filteredLocations.size()];
            v.setLocations(filteredLocations.toArray(locationsToAdd));
        }
        else
        {
            JOptionPane.showMessageDialog(v, "No locations" +
                    " with that timeframe were found");
        }
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
