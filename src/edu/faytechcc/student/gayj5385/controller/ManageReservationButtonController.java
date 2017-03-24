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
        }
    }
}