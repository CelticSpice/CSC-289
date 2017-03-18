/**
    Controller for buttons on the administrator's panel enabling the management
    of reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import edu.faytechcc.student.gayj5385.gui.ReservableAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageReservableBtnController implements ActionListener
{
    // Fields
    private ManageReservablePanel view;
    
    /**
        Constructor - Accepts the view to control buttons for
    
        @param v The view
    */
    
    public ManageReservableBtnController(ManageReservablePanel v)
    {
        view = v;
    }
    
    /**
        Handle the clicking of a button
    
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
            case "Modify":
                //doModify();
                break;
            case "Delete":
                //doDelete();
                break;
            case "Logout":
                System.exit(0);
                break;
        }
    }
    
    /**
        Perform an add operation
    */
    
    private void doAdd()
    {
        ReservableAddDialog d = new ReservableAddDialog();
        
        d.setExistingLocations(view.getLocations());
        
        d.registerButtonController(new ReservableAddButtonController(d));
        d.registerRadioButtonController(new ReservableAddRadioController(d));
        d.registerComboBoxController(new ReservableAddComboBoxController(d));
        
        d.setVisible(true);
    }
}