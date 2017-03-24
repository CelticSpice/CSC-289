/**
    Controller for the list of reservations on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageReservationListController implements ListSelectionListener
{
    // Fields
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationListController to control the given
        view
    
        @param v The view
    */
    
    public ManageReservationListController(ManageReservationPanel v)
    {
        view = v;
    }
    
    /**
        Handle the selection of a list item
    
        @param e The list selection event
    */
    
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        List<Reservation> reservations = view.getSelectedReservations();
        
        if (reservations.size() == 1)
        {
            DateTimeFormatter dFmt = DateTimeFormatter.ofPattern(
                "y-MMM-d");
            DateTimeFormatter tFmt = DateTimeFormatter.ofPattern(
                "H:mm");
            
            Reservation reservation = reservations.get(0);
            
            view.setStartDate(reservation.getStartDate().format(dFmt));
            view.setStartTime(reservation.getStartTime().format(tFmt));
            view.setEndDate(reservation.getEndDate().format(dFmt));
            view.setEndTime(reservation.getEndTime().format(tFmt));
            view.setCost(reservation.getCostString());
            view.setAttendees(String.valueOf(reservation.getNumberAttending()));
            view.setEvent(reservation.getEventType());
            view.setContactName(reservation.getReserverName());
            view.setEmail(reservation.getReserverEmail());
            view.setPhone(reservation.getReserverPhone());
        }
        else
        {
            view.setStartDate("");
            view.setStartTime("");
            view.setEndDate("");
            view.setEndTime("");
            view.setCost("");
            view.setAttendees("");
            view.setEvent("");
            view.setContactName("");
            view.setEmail("");
            view.setPhone("");
        }
    }
}