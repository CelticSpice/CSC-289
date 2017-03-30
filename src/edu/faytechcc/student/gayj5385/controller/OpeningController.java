/**
    Controller for buttons on the opening view
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.SHA256SaltHasher;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.gayj5385.gui.MainPanel;
import edu.faytechcc.student.gayj5385.gui.OpenPanel;
import edu.faytechcc.student.gayj5385.gui.SendEmailDialog;
import edu.faytechcc.student.mccanns0131.database.DatabaseConnection;
import edu.faytechcc.student.mccanns0131.database.LocationQuery;
import edu.faytechcc.student.mccanns0131.database.ReservationQuery;
import edu.faytechcc.student.mccanns0131.database.ResultSetParser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class OpeningController implements ActionListener
{
    // Fields
    private HashMap<Location, List<Reservation>> reservations;
    private List<Location> locations;
    private MainPanel mainPanel;
    private OpenPanel view;
    private SystemPreferences preferences;
    private SHA256SaltHasher saltHasher;
    
    /**
        Constructs a new OpeningController initialized with the given panels,
        location & reservation listings, system preferences, and salt-hashes
    
        @param main Main panel
        @param v The view
        @param locs The locations
        @param reserves The reservations
        @param prefs System preferences
        @param saltHash Salt-hasher
    */
    
    public OpeningController(MainPanel main, OpenPanel v, List<Location> locs,
        HashMap<Location, List<Reservation>> reserves, SystemPreferences prefs,
        SHA256SaltHasher saltHash)
    {
        mainPanel = main;
        view = v;
        locations = locs;
        reservations = reserves;
        preferences = prefs;
        saltHasher = saltHash;
    }
    
    /**
        Responds to a button being clicked
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Make Reservation":
                showGuestReservationPanel();
                break;
            case "Contact Administrator":
                contactAdmin();
                break;
            case "Login":
                login();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
    
    /**
        Show the dialog enabling a guest to contact the administrator
    */
    
    private void contactAdmin()
    {
        SendEmailDialog dialog = new SendEmailDialog(SendEmailDialog.GUEST,
            null);
        
        dialog.setVisible(true);
    }
    
    /**
        Show the dialog enabling the administrator to login
    */
    
    private void login()
    {
        JPasswordField passwordField = new JPasswordField(15);
        int action = JOptionPane.showConfirmDialog(view, passwordField,
            "Administrator Password", JOptionPane.OK_CANCEL_OPTION);
        
        if (action == JOptionPane.OK_OPTION)
        {
            // Validate password
            String password = new String(passwordField.getPassword());
            
            try
            {
                password = saltHasher.saltHash(password);
                String currentPassword = preferences.getAdminPassword();
                
                if (password.equals(currentPassword))
                    showAdminView();
                else
                    JOptionPane.showMessageDialog(view, "Invalid Password");
            }
            catch (NoSuchAlgorithmException ex)
            {
                JOptionPane.showMessageDialog(view,
                    "Salt & hash operation failed", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
        Shows the administrator's view
    */
    
    private void showAdminView()
    {
        try
        {
            // Update locations & reservations
            String user = preferences.getDBUser();
            String pass = preferences.getDBPass();
            DatabaseConnection conn = DatabaseConnection.getConnection(
                    user, pass);
            
            ResultSetParser parser = new ResultSetParser();
            LocationQuery locQuery = new LocationQuery();
            locQuery.queryLocations();
            parser.setResultSet(conn.runQuery(locQuery));

            locations.clear();
            locations.addAll(parser.parseLocations());
            
            reservations.clear();
            ReservationQuery reserveQuery = new ReservationQuery();
            for (Location loc : locations)
            {
                reserveQuery.queryReservations(loc.getName());
                parser.setResultSet(conn.runQuery(reserveQuery));
                
                if (!parser.isEmpty())
                    reservations.put(loc, parser.parseReservations(loc));
            }
            
            conn.close();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(view,
                "Failed updating location & reservation data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        mainPanel.showAdminPanel();
    }
    
    /**
        Shows the panel for the guest to make a reservation on
    */
    
    private void showGuestReservationPanel()
    {
        mainPanel.showGuestReservationPanel();
    }
}