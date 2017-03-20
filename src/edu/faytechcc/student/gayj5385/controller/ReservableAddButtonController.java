/**
    Button controller for the dialog to add reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Admin;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.exception.RecordExistsException;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class ReservableAddButtonController implements ActionListener
{
    // Fields
    private static final int START = 0;
    private static final int END = 1;
    
    private ReservableAddDialog view;
    
    /**
        Constructor - Accepts the view
    
        @param v The view
    */
    
    public ReservableAddButtonController(ReservableAddDialog v)
    {
        view = v;
    }
    
    /**
        Respond to action event
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Add":
                addReservable();
                break;
            case "Exit":
                view.setVisible(false);
                view.dispose();
                break;
        }
    }
    
    /**
        Add a reservable
    */
    
    private void addReservable()
    {
        if (validateFields())
        {
            Reservable r = parseReservable();
            
            try
            {
                Admin.addReservable(r);

                if (creatingNewLocation())
                {
                    Location[] locs = view.getExistingLocations();
                    locs = Arrays.copyOf(locs, locs.length + 1);

                    locs[locs.length - 1] = r.getLocation();

                    Comparator<Location> c = 
                            (a, b) -> a.getName().compareTo(b.getName());

                    Arrays.sort(locs, c);

                    view.setExistingLocations(locs);
                }
                else
                {
                    Location loc = view.getSelectedLocation();
                    loc.getTimeframes().add(r.getTimeframe());
                }

                view.setIfRecordsAdded(true);
                JOptionPane.showMessageDialog(view, "Reservable created");
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(view,
                        "Error adding record to database");
            }
            catch (RecordExistsException ex)
            {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }
    
    /**
        Check if input location name matches that of an existing one
    
        @return Whether the input location name matches that of an existing
                location
    */
    
    private boolean isNameMatching()
    {
        String inputName = view.getInputLocation();
        Location[] locs = view.getExistingLocations();
        
        for (Location loc : locs)
        {
            if (loc.getName().equalsIgnoreCase(inputName))
                return true;
        }
        return false;
    }
    
    /**
        Parse a location with data input on the dialog
    
        @return Parsed location
    */
    
    private Location parseLocation()
    {
        if (creatingNewLocation())
        {
            String locName = view.getInputLocation();
            int capacity = Integer.parseInt(view.getCapacity());
            
            return new Location(locName, capacity);
        }
        else
            return view.getSelectedLocation();
    }
    
    /**
        Parse a reservable with data input on the dialog
    
        @return Parsed reservable
    */
    
    private Reservable parseReservable()
    {
        Location loc = parseLocation();
        Timeframe timeframe = parseTimeframe();
        
        return new Reservable(loc, timeframe);
    }
    
    /**
        Parse a datetime, start or end
    
        @param startOrEnd Parse either start datetime or end datetime
        @return A datetime
    */
    
    private LocalDateTime parseDateTime(int startOrEnd)
    {
        int year, month, day, hour, minute;
        
        if (startOrEnd == START)
        {
            year = view.getStartYear();
            month = view.getStartMonth();
            day = view.getStartDay();
            hour = view.getStartHour();
            minute = view.getStartMinute();
        }
        else
        {
            year = view.getEndYear();
            month = view.getEndMonth();
            day = view.getEndDay();
            hour = view.getEndHour();
            minute = view.getEndMinute();
        }
        
        return LocalDateTime.of(year, month, day, hour, minute);
    }
    
    /**
        Parse a timeframe with data input on the dialog
    
        @return Parsed timeframe
    */
    
    private Timeframe parseTimeframe()
    {
        BigDecimal cost = new BigDecimal(view.getCost());
        
        LocalDateTime startDateTime = parseDateTime(START);
        LocalDateTime endDateTime = parseDateTime(END);
        
        return new Timeframe(startDateTime, endDateTime, cost);
    }
    
    /**
        Return if a new location is being created
    
        @return If a new location is being created
    */
    
    private boolean creatingNewLocation()
    {
        Location loc = view.getSelectedLocation();
        
        if (loc != null)
            return !loc.getName().equalsIgnoreCase(view.getInputLocation());
        else
            return true;
    }
    
    /**
        Validate the input fields
    
        @return If the input fields are valid
    */
    
    private boolean validateFields()
    {
        return validateLocationAndCost() && validateTimeframe();
    }
    
    /**
        Validate the location & cost data fields
    
        @return If the location & cost data fields are valid
    */
    
    private boolean validateLocationAndCost()
    {
        // Validate location name
        boolean nameValid = !view.getInputLocation().isEmpty() &&
                            !(creatingNewLocation() && isNameMatching());
        
        if (!nameValid)
        {
            JOptionPane.showMessageDialog(view, "Bad location name entered");
            return false;
        }
        
        // Validate capacity input
        boolean capValid = view.getCapacity().trim().matches("\\d+");
        
        // Validate capacity greater than 0
        if (capValid)
            capValid = Integer.parseInt(view.getCapacity()) > 0;
        
        if (!capValid)
        {
            JOptionPane.showMessageDialog(view,
                    "Capacity must be a value greater than 0");
            return false;
        }
            
        // Validate cost - Is proper number entered?
        boolean costValid = view.getCost().trim()
                .matches("\\$?(\\d+(\\.\\d{1,2})?)|\\$?(\\.\\d{1,2})");
        
        // Validate cost at least 0
        if (costValid)
        {
            String inputCost = view.getCost();
            inputCost = inputCost.replace("$", "");
            costValid = Double.parseDouble(inputCost) >= 0;
        }

        if (!costValid)
        {
            JOptionPane.showMessageDialog(view, "Cost must be at least 0");
            return false;
        }
        
        return true;
    }
    
    /**
        Validate the timeframe fields
    
        @return if the timeframe fields are valid
    */
    
    private boolean validateTimeframe()
    {
        LocalDateTime startDateTime = parseDateTime(START);
        LocalDateTime endDateTime = parseDateTime(END);
        
        // Is start before end?
        if (startDateTime.isBefore(endDateTime))
            return true;
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Start datetime must be before end datetime");
            return false;
        }
    }
}