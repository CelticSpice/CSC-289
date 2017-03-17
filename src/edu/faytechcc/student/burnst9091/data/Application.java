/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.gayj5385.controller.AdminPanelChangeController;
import edu.faytechcc.student.burnst9091.exception.RecordExistsException;
import edu.faytechcc.student.gayj5385.gui.AdminPanel;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.prefs.BackingStoreException;
import javax.swing.JFrame;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args)
            throws BackingStoreException, SQLException, RecordExistsException,
                   NoSuchAlgorithmException
    {
        SystemUtil.initPreferences();
        JFrame frame = new JFrame();
        AdminPanel panel = new AdminPanel();
        panel.registerChangeController(new AdminPanelChangeController(panel));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}