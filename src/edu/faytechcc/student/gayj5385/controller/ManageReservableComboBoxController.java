/**
    Controller for the locations combo box on the panel enabling the
    administrator to manage reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageReservableComboBoxController implements ActionListener
{
    // Fields
    private ManageReservablePanel view;
    private Filter<Timeframe> filter;
    
   /**
        Constructs a new ManageReservableComboBoxController initialized
        with the given view & filter for timeframes
    
        @param v The view
        @param f The filter
    */
    
    public ManageReservableComboBoxController(ManageReservablePanel v,
            Filter<Timeframe> f)
    {
        view = v;
        filter = f;
    }
    
    /**
        Handle combo box action events
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Location loc = view.getSelectedLocation();
        
        if (filter.getPredicate() != null)
            view.setTimeframes(filter.filter(loc.getTimeframes()));
        else
            view.setTimeframes(loc.getTimeframes());
    }
}