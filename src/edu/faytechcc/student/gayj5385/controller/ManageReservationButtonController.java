/**
    Controller for buttons on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Admin;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservationButtonController implements ActionListener
{
    // Fields
    private Filter<Location> locationFilter;
    private Filter<Reservation> reservationFilter;
    private HashMap<Location, List<Reservation>> reservations;
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationButtonController to control buttons
        on the given view, initialized with a mapping of location reservations
        & filters for locations & reservations
    
        @param v The view
        @param reserves The reservation mapping
        @param locFilter Location filter
        @param resFilter Reservation filter
    */
    
    public ManageReservationButtonController(ManageReservationPanel v,
            HashMap<Location, List<Reservation>> reserves,
            Filter<Location> locFilter, Filter<Reservation> resFilter)
    {
        view = v;
        reservations = reserves;
        locationFilter = locFilter;
        reservationFilter = resFilter;
    }
    
    /**
        Handles the clicking of a button
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Update":
                // Do this
                break;
            case "Contact":
                // Do this
                break;
            case "Reviewed":
                // Do this
                break;
            case "Cancel":
                doCancel();
                break;
            case "Logout":
                System.exit(0);
                break;
        }
    }
    
    /**
        Cancels a reservation
    
        @param reservation Reservation to cancel
    */
    
    private void cancelReservation(Reservation reservation)
    {
        try
        {
            Admin.cancelReservation(reservation);
            Location loc = reservation.getLocation();
            reservations.get(loc).remove(reservation);
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(view, "Error updating database",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
        Responds to the "Cancel" command
    */
    
    private void doCancel()
    {
        List<Reservation> selectedReservations = view.getSelectedReservations();
        
        if (selectedReservations.size() == 1)
        {
            // Confirm cancellation
            int choice = JOptionPane.showConfirmDialog(view,
                    "Cancel this reservation?");
            
            if (choice == JOptionPane.YES_OPTION)
            {
                Reservation reservation = selectedReservations.get(0);
                cancelReservation(reservation);
                    
                Location loc = reservation.getLocation();
                if (reservations.get(loc).isEmpty())
                {
                    reservations.remove(loc);
                    setLocations();
                }
                else
                    setReservations(reservations.get(loc));
            }
        }
        
        if (selectedReservations.size() > 1)
            JOptionPane.showMessageDialog(view, 
                "Cannot cancel multiple reservations", "Warning",
                    JOptionPane.WARNING_MESSAGE);
    }
    
    /**
        Sets locations on the view to the current location listing
    */
    
    private void setLocations()
    {
        List<Location> locs = new ArrayList<>(reservations.keySet());
        if (locationFilter.getPredicate() != null)
            view.setLocations(locationFilter.filter(locs));
        else
            view.setLocations(locs);
    }
    
    /**
        Sets reservations on the view
    */
    
    private void setReservations(List<Reservation> reservations)
    {
        if (reservationFilter.getPredicate() != null)
            view.setReservations(reservationFilter.filter(reservations));
        else
            view.setReservations(reservations);
    }
}