/**
    Controller for the locations combo box on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class ManageReservationComboBoxController implements ActionListener
{
    // Fields
    private Filter<Reservation> filter;
    private HashMap<String, List<Reservation>> reservations;
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
                                    HashMap<String, List<Reservation>> reserves,
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
        String locName = view.getSelectedLocation().getName();
        List<Reservation> reserves = reservations.get(locName);
        if (filter.getPredicate() != null)
        {
            reserves = reserves.stream()
                    .filter(filter.getPredicate())
                    .collect(toList());
        }
        view.setReservations(reserves);
    }
}