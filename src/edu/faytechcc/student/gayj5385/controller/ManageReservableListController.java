/**
    Controller for the list of timeframes on the panel enabling the admin
    to manage reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageReservableListController implements ListSelectionListener
{
    // Fields
    private ManageReservablePanel view;

    /**
        Constructor - Accepts the view to manage the list of

        @param v The view
    */

    public ManageReservableListController(ManageReservablePanel v)
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
        List<Timeframe> timeframes = view.getSelectedTimeframes();

        if (timeframes.size() == 1)
        {
            DateTimeFormatter dFmt = DateTimeFormatter.ofPattern(
                "y-MMM-d");
            DateTimeFormatter tFmt = DateTimeFormatter.ofPattern(
                "H:mm");

            view.setStartDate(timeframes.get(0).getStartDateTimeString(dFmt));
            view.setStartTime(timeframes.get(0).getStartDateTimeString(tFmt));
            view.setEndDate(timeframes.get(0).getEndDateTimeString(dFmt));
            view.setEndTime(timeframes.get(0).getEndDateTimeString(tFmt));
            view.setCost(timeframes.get(0).getCostString());
            view.setReserved((timeframes.get(0).isReserved()) ? "Reserved" :
                                                                "Available");
        }
        else
        {
            view.setStartDate("");
            view.setStartTime("");
            view.setEndDate("");
            view.setEndTime("");
            view.setCost("");
            view.setReserved("");
        }
    }
}
