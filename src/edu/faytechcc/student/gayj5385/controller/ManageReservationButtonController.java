/**
    Controller for buttons on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservationButtonController implements ActionListener
{
    // Fields
    private HashMap<String, List<Reservation>> reservations;
    private List<Location> locations;
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationButtonController to control buttons
        on the given view, initialized with a list of locations and a mapping
        of location reservations
    
        @param v The view
        @param locs The locations
        @param reserves The reservation mapping
    */
    
    public ManageReservationButtonController(ManageReservationPanel v,
            List<Location> locs, HashMap<String, List<Reservation>> reserves)
    {
        view = v;
        locations = locs;
        reservations = reserves;
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
        Respond to the "Cancel" button being clicked
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
                cancelReservation(selectedReservations.get(1));
        }
        
        if (selectedReservations.size() > 1)
            JOptionPane.showMessageDialog(view, 
                "Cannot cancel multiple reservations");
    }
}