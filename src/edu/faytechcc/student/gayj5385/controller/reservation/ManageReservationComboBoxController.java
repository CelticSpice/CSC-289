/**
    Controller for the locations combo box on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservation;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageReservationComboBoxController implements ActionListener
{
    private DataRepository repo;
    private Filter<Reservation> filter;
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationComboBoxController
    
        @param v The view
        @param repo Data repository
        @param f The filter object
    */
    
    public ManageReservationComboBoxController(ManageReservationPanel v,
                                DataRepository repo, Filter<Reservation> f)
    {
        view = v;
        this.repo = repo;
        filter = f;
    }
    
    /**
        Respond to action event
        
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        ReservableLocation loc = view.getSelectedLocation();
        
        if (loc != null)
        {
            view.setCapacity(String.valueOf(loc.getCapacity()));
            
            if (filter.getPredicate() != null)
                view.setReservations(
                        filter.filter(repo.getLocationReservations(loc)));
            else
                view.setReservations(repo.getLocationReservations(loc));
        }
        else
        {
            view.setCapacity(null);
            view.setReservations(null);
        }
    }
}