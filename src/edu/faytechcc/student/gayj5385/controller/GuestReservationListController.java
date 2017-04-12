/**
    Controller for the list of timeframes on the panel enabling the guest
    to make a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.gayj5385.gui.GuestReservationPanel;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GuestReservationListController implements ListSelectionListener
{
    // Fields
    private GuestReservationPanel view;

    /**
        Constructor - Accepts the view to manage the list of

        @param v The view
    */

    public GuestReservationListController(GuestReservationPanel v)
    {
        view = v;
    }

    /**
        Handle when the selected value in the list changes

        @param e The value changed event
    */

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        List<ReservableTimeframe> timeframes = view.getSelectedTimeframes();

        if (timeframes.size() == 1)
        {
            DateTimeFormatter dFmt = DateTimeFormatter.ofPattern(
                "y-MMM-d");
            DateTimeFormatter tFmt = DateTimeFormatter.ofPattern(
                "H:mm");
            NumberFormat cFmt = NumberFormat.getCurrencyInstance();

            view.setStartDate(timeframes.get(0).getStartDate().format(dFmt));
            view.setStartTime(timeframes.get(0).getStartTime().format(tFmt));
            view.setEndDate(timeframes.get(0).getEndDate().format(dFmt));
            view.setEndTime(timeframes.get(0).getEndTime().format(tFmt));
            view.setCost(cFmt.format(timeframes.get(0).getCost()));
        }
        else
        {
            view.setStartDate("");
            view.setStartTime("");
            view.setEndDate("");
            view.setEndTime("");
            view.setCost("");
        }
    }
}