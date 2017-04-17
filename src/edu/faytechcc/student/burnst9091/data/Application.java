/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.gayj5385.gui.MainFrame;
import edu.faytechcc.student.gayj5385.gui.dialog.CreateReportDialog;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args)
    {
        SystemInitialization.run();
        new MainFrame();
        new CreateReportDialog(true);
    }
}