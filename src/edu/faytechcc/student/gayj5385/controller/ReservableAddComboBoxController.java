/**
    Controller for existing location combo box on the dialog to add
    reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservableAddComboBoxController implements ActionListener
{
    // Fields
    private ReservableAddDialog view;
    
    /**
        Constructor - Accepts the view
    
        @param v The view
    */
    
    public ReservableAddComboBoxController(ReservableAddDialog v)
    {
        view = v;
    }
    
    /**
        Respond to action event
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (view.isExistingLocationRadioSelected())
        {
            Location loc = view.getSelectedLocation();
            view.setLocation(loc.getName());
            view.setCapacity(String.valueOf(loc.getCapacity()));
        }
    }
}