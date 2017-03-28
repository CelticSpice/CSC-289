/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.gayj5385.gui.MainFrame;
import edu.faytechcc.student.mccanns0131.database.LocationQuery;
import edu.faytechcc.student.mccanns0131.database.ReservationQuery;
import edu.faytechcc.student.mccanns0131.database.ResultSetParser;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.BackingStoreException;
import javax.swing.JOptionPane;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args) throws BackingStoreException,
            NoSuchAlgorithmException
    {
        try
        {
            SystemUtil.initPreferences();
        }
        catch (BackingStoreException | NoSuchAlgorithmException ex)
        {
            JOptionPane.showMessageDialog(null,
                "Failed initializing preferences", "Fatal Error",
                    JOptionPane.ERROR_MESSAGE);
            
            System.exit(1);
        }
        
        List<Location> locations = queryLocations();
        HashMap<Location, List<Reservation>> reservations = queryReservations(
            locations);
        
        SystemUtil.initPreferences();
        new MainFrame(locations, reservations);
    }
    
    /**
        Queries the database for locations
    
        @return List of locations
    */
    
    private static List<Location> queryLocations()
    {
        List<Location> locations = new ArrayList<>();
        
        try
        {
            ResultSetParser parser = new ResultSetParser();
            parser.setResultSet(new LocationQuery().queryLocations());
            return parser.parseLocations();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null,
                "Failed acquiring location information", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return locations;
    }
    
    /**
        Queries the database for reservations
    
        @param locations List of locations to query reservations of
        @return Mapping of reservations
    */
    
    private static HashMap<Location, List<Reservation>> queryReservations(
        List<Location> locations)
    {
        HashMap<Location, List<Reservation>> reservations = new HashMap<>();
        
        try
        {
            ResultSetParser parser = new ResultSetParser();
            ReservationQuery query = new ReservationQuery();
            
            for (Location loc : locations)
            {
                parser.setResultSet(query.queryReservations(loc));
                if (!parser.isEmpty())
                    reservations.put(loc, parser.parseReservations(loc));
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null,
                "Failed acquiring reservation information", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return reservations;
    }
}