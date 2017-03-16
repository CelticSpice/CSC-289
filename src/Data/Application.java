/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import Controller.AdminPanelChangeController;
import Exception.RecordExistsException;
import GUI.AdminPanel;
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
            throws BackingStoreException, SQLException, RecordExistsException
    {
        JFrame frame = new JFrame();
        AdminPanel panel = new AdminPanel();
        panel.registerChangeController(new AdminPanelChangeController(panel));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}