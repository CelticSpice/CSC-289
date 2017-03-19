/**
    Button controller for the dialog to add reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservableAddButtonController implements ActionListener
{
    // Fields
    private ReservableAddDialog view;
    
    /**
        Constructor - Accepts the view
    
        @param v The view
    */
    
    public ReservableAddButtonController(ReservableAddDialog v)
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
        switch (e.getActionCommand())
        {
            case "Add":
                doAdd();
                break;
            case "Exit":
                view.setVisible(false);
                view.dispose();
                break;
        }
    }
    
    /**
        Perform an add operation
    */
    
    private void doAdd()
    {
    }
}