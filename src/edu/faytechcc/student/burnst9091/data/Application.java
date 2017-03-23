/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.burnst9091.exception.RecordExistsException;
import edu.faytechcc.student.gayj5385.controller.AdminPanelController;
import edu.faytechcc.student.gayj5385.gui.AdminPanel;
import edu.faytechcc.student.mccanns0131.database.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.prefs.BackingStoreException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args)
            throws BackingStoreException, SQLException, RecordExistsException,
                   NoSuchAlgorithmException, AddressException, MessagingException,
                   UnsupportedEncodingException, ClassNotFoundException,
                   InstantiationException, IllegalAccessException,
                   UnsupportedLookAndFeelException
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        
        SystemUtil.initPreferences();
        JFrame frame = new JFrame();
        
        Query q = new Query();
        List<Location> locations = q.queryLocations();
        List<Reservation> reservations = q.queryReservations();
        
        AdminPanel panel = new AdminPanel(locations, reservations);
        panel.registerChangeController(new AdminPanelController(panel));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}