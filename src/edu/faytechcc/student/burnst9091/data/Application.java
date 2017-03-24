/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.burnst9091.exception.RecordExistsException;
import edu.faytechcc.student.gayj5385.controller.AdminPanelController;
import edu.faytechcc.student.gayj5385.gui.AdminPanel;
import edu.faytechcc.student.mccanns0131.database.LocationQuery;
import edu.faytechcc.student.mccanns0131.database.ReservationQuery;
import edu.faytechcc.student.mccanns0131.database.ResultSetParser;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
        
        ResultSetParser parser = new ResultSetParser();
        LocationQuery q = new LocationQuery();
        ReservationQuery reserveQ = new ReservationQuery();
        
        parser.setResultSet(q.queryLocationNames());
        
        List<String> locNames = parser.parseLocationNames();
        
        List<Location> locations = new ArrayList<>();
        HashMap<String, List<Reservation>> reserveMap = new HashMap<>();
        
        for (String locName : locNames)
        {
            parser.setResultSet(q.queryLocationCapacity(locName));
            int capacity = parser.parseLocationCapacity();
            
            parser.setResultSet(q.queryLocationTimeframes(locName));
            List<Timeframe> timeframes = parser.parseTimeframes();
            
            Location loc = new Location(locName, capacity, timeframes);
            
            locations.add(loc);
            
            parser.setResultSet(reserveQ.queryReservations(loc));
            
            reserveMap.put(locName, parser.parseReservations(loc));
        }
        
        AdminPanel panel = new AdminPanel(locations, reserveMap);
        panel.registerChangeController(new AdminPanelController(panel));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
        Consolidates a list of reservations with a list of locations
    */
    
    private static void consolidate(List<Location> locs,
            List<Reservation> reserves)
    {
        for (Location loc : locs)
        {
            for (Reservation reserve : reserves)
            {
                if (loc.getName().equals(reserve.getLocationName()));
                    
            }
        }
    }
}