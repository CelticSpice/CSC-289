/**
    Controller for the locations combo box on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservation;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class ManageReservationComboBoxController implements ActionListener
{
    // Fields
    private Filter<Reservation> filter;
    private HashMap<Location, List<Reservation>> reservations;
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationComboBoxController with the given
        view to manage, a mapping of reservations, & a filter object to apply
        filtering
    
        @param v The view
        @param reserves The reservations
        @param f The filter object
    */
    
    public ManageReservationComboBoxController(ManageReservationPanel v,
                                HashMap<Location, List<Reservation>> reserves,
                                Filter<Reservation> f)
    {
        view = v;
        reservations = reserves;
        filter = f;
    }
    
    /**
        Respond to action event
        
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Location loc = view.getSelectedLocation();
        if (loc != null)
        {
            view.setCapacity(String.valueOf(loc.getCapacity()));
            if (filter.getPredicate() != null)
                view.setReservations(filter.filter(reservations.get(loc)));
            else
                view.setReservations(reservations.get(loc));
        }
    }
}