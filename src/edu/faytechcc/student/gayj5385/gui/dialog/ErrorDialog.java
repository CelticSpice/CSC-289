/**
    An error dialog
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.Component;
import javax.swing.JOptionPane;

public class ErrorDialog
{
    /**
        Displays the specified error message
        
        @param parent Parent component
        @param message Message to display
    */
    
    public static void show(Component parent, String message)
    {
        JOptionPane.showMessageDialog(parent, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}