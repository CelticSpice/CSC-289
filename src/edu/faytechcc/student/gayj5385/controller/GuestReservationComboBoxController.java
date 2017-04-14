/**
    Controller for the locations combo box on the panel for the guest to make
    a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import edu.faytechcc.student.gayj5385.gui.GuestReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuestReservationComboBoxController implements ActionListener
{
    private GuestReservationPanel view;
    private Filter<ReservableTimeframe> filter;
    
    /**
        Constructs a new GuestReservationComboBoxController initialized with
        the given GuestReservationPanel to control the combo box of
    
        @param v The view
    */
    
    public GuestReservationComboBoxController(GuestReservationPanel v,
            Filter<ReservableTimeframe> f)
    {
        view = v;
        filter = f;
    }
    
    /**
        Performs an action on a location being selected
    
        @param e The ActionEvent
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        ReservableLocation loc = view.getSelectedLocation();
        
        if (loc != null)
        {
            view.setCapacity(String.valueOf(loc.getCapacity()));
            if (filter.getPredicate() != null)
                view.setTimeframes(filter.filter(loc.getReservableTimeframes()));
            else
            {
                List<ReservableTimeframe> timeframes = loc.getReservableTimeframes();
                view.setTimeframes(timeframes);
            }
        }
    }
}