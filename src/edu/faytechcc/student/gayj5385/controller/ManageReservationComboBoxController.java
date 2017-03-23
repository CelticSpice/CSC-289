/**
    Controller for the locations combo box on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservationComboBoxController implements ActionListener
{
    // Fields
    private Filter<Reservation> filter;
    private HashMap<String, List<Reservation>> reservations;
    private ManageReservationPanel view;
    
    /**
        Constructor - Accepts the view & a filter object
    
        @param v The view
        @param f The filter object
    */
    
    public ManageReservationComboBoxController(ManageReservationPanel v,
                                               Filter<Reservation> f)
    {
        view = v;
        filter = f;
        reservations = new HashMap<>();
    }
    
    /**
        Respond to action event
        
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String locName = view.getSelectedLocation().getName();
        
        if (reservations.containsKey(locName))
            view.setReservations(reservations.get(locName));
        else
        {
            try
            {
                Query query = new Query();
                List<Reservation> reserves = query.queryReservations(locName);
                reservations.put(locName, reserves);
                view.setReservations(reserves);
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(view,
                        "Failed loading reservation information");
            }
        }
    }
}