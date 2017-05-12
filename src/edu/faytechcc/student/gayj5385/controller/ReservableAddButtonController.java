/**
    Button controller for the dialog to add reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.gayj5385.gui.dialog.AddReservableDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

public class ReservableAddButtonController implements ActionListener
{
    // Fields
    private static final int START = 0;
    private static final int END = 1;

    private AddReservableDialog view;
    private DataRepository repo;

    /**
        Constructs a new ReservableAddButtonController

        @param v The view
        @param repo Data repository
    */

    public ReservableAddButtonController(AddReservableDialog v,
            DataRepository repo)
    {
        view = v;
        this.repo = repo;
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
        if (validateInput())
        {
            try
            {                        
                ReservableLocation loc = parseLocation();
                ReservableTimeframe timeframe = parseTimeframe();
                
                Reservable reservable = new Reservable(loc, timeframe);

                repo.addReservable(reservable);
                
                List<ReservableLocation> locs = repo.getLocations();
                view.setExistingLocations(locs);
                                
                JOptionPane.showMessageDialog(view, "Reservable created");
                
                if (creatingNewLocation() && locs.size() == 1)
                    view.setExistingLocationsRadioEnabled(true);
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(view,
                        "Error adding record to database");
            }
        }
    }

    /**
        Return if input location name matches that of an existing location

        @return If input location name matches that of an existing location
    */

    private boolean isNameMatching()
    {
        String inputName = view.getInputLocation().trim();

        for (ReservableLocation loc : repo.getLocations())
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

    private ReservableLocation parseLocation()
    {
        if (creatingNewLocation())
        {
            String locName = view.getInputLocation().trim();
            int capacity = Integer.parseInt(view.getCapacity());

            return new ReservableLocation(locName, capacity);
        }
        else
            return view.getSelectedLocation();
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

    private ReservableTimeframe parseTimeframe()
    {
        String costInput = view.getCost();
        costInput = costInput.replace("$", "");
        BigDecimal cost = new BigDecimal(costInput);
        LocalDateTime startDateTime = parseDateTime(START);
        LocalDateTime endDateTime = parseDateTime(END);
        
        ReservableTimeframe timeframe = new ReservableTimeframe(startDateTime,
                endDateTime, cost);
        
        timeframe.setID(repo.acquireTimeframeID(timeframe));

        return timeframe;
    }

    /**
        Return if a new location is being created

        @return If a new location is being created
    */

    private boolean creatingNewLocation()
    {
        return !view.isExistingLocationsRadioSelected();
    }

    /**
        Validate the capacity input field

        @return If the capacity input field is valid
    */

    private boolean validateCapacity()
    {
        boolean valid = view.getCapacity().trim().matches("\\d+");

        if (valid)
        {
            valid = Integer.parseInt(view.getCapacity()) > 0;

            if (!valid)
                JOptionPane.showMessageDialog(view,
                    "Capacity must be greater than 0");
        }
        else
            JOptionPane.showMessageDialog(view, "Invalid input for capacity");

        return valid;
    }

    /**
        Validate the cost input field

        @return If the cost input field is valid
    */

    private boolean validateCost()
    {
        // Is a currency amount entered?
        boolean valid = view.getCost().trim()
                .matches("\\$?(\\d+(\\.\\d{1,2})?)|\\$?(\\.\\d{1,2})");

        if (valid)
        {
            String input = view.getCost().trim();
            input = input.replace("$", "");
            valid = Double.parseDouble(input) >= 0.0;

            if (!valid)
                JOptionPane.showMessageDialog(view, "Cost must be at least $0");
        }
        else
            JOptionPane.showMessageDialog(view, "Invalid input for cost");

        return valid;
    }

    /**
        Validate the input fields

        @return If the input fields are valid
    */

    private boolean validateInput()
    {
        return validateLocation() && validateCapacity() && validateCost() &&
                validateTimeframe();
    }

    /**
        Validate the location input field

        @return If the location input field is valid
    */

    private boolean validateLocation()
    {
        if (!view.getInputLocation().isEmpty() &&
                !(creatingNewLocation() && isNameMatching()))
        {
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(view, "Bad location name entered");
            return false;
        }
    }

    /**
        Validate the timeframe fields

        @return if the timeframe fields are valid
    */

    private boolean validateTimeframe()
    {
        LocalDateTime startDateTime = parseDateTime(START);
        LocalDateTime endDateTime = parseDateTime(END);

        boolean valid = startDateTime.isBefore(endDateTime);

        if (valid)
        {
            if (!creatingNewLocation())
            {
                ReservableLocation loc = view.getSelectedLocation();

                valid = !loc.hasTimeframe(
                        t -> t.startsOnDatetime(startDateTime) &&
                             t.endsOnDatetime(endDateTime));

                if (!valid)
                    JOptionPane.showMessageDialog(view,
                            "Location has an identical timeframe");
            }
        }
        else
            JOptionPane.showMessageDialog(view,
                    "Start datetime must be before end datetime");

        return valid;
    }
}
