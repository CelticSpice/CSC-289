/**
    Controller for buttons on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservation;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.exception.RecordNotExistsException;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import edu.faytechcc.student.gayj5385.gui.dialog.SendEmailDialog;
import edu.faytechcc.student.mccanns0131.database.DatabaseConnection;
import edu.faytechcc.student.mccanns0131.database.RecordDelete;
import edu.faytechcc.student.mccanns0131.database.RecordModify;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservationButtonController implements ActionListener
{
    // Fields
    private Filter<Location> locationFilter;
    private Filter<Reservation> reservationFilter;
    private HashMap<Location, List<Reservation>> reservations;
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationButtonController to control buttons
        on the given view, initialized with a mapping of location reservations
        & filters for locations & reservations
    
        @param v The view
        @param reserves The reservation mapping
        @param locFilter Location filter
        @param resFilter Reservation filter
    */
    
    public ManageReservationButtonController(ManageReservationPanel v,
            HashMap<Location, List<Reservation>> reserves,
            Filter<Location> locFilter, Filter<Reservation> resFilter)
    {
        view = v;
        reservations = reserves;
        locationFilter = locFilter;
        reservationFilter = resFilter;
    }
    
    /**
        Handles the clicking of a button
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Update":
                // Do this
                break;
            case "Contact":
                contact();
                break;
            case "Reviewed":
                reviewed(true);
                break;
            case "Reassess":
                reviewed(false);
                break;
            case "Cancel":
                cancel();
                break;
            case "Search":
                break;
            case "Clear":
                break;
            case "Logout":
                System.exit(0);
                break;
        }
    }
    
    /**
        Cancels selected reservations
    */
    
    private void cancel()
    {
        List<Reservation> selectedReservations = view.getSelectedReservations();
        
        if (selectedReservations.size() == 1)
        {
            // Confirm cancellation
            int choice = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to cancel the selected reservation?");
            
            if (choice == JOptionPane.YES_OPTION)
            {
                Reservation reservation = selectedReservations.get(0);
                
                try
                {
                    SystemPreferences prefs = SystemPreferences.getInstance();
                    String user = prefs.getDBUser();
                    String pass = prefs.getDBPass();
                    DatabaseConnection conn = DatabaseConnection.getConnection(
                            user, pass);
                    
                    new RecordDelete(conn).deleteReservation(reservation);
                    
                    conn.close();
                    
                    reservation.cancel();
                    
                    Location loc = reservation.getLocation();
                    reservations.get(loc).remove(reservation);
                    
                    if (reservations.get(loc).isEmpty())
                    {
                        reservations.remove(loc);
                        setLocations();
                    }
                    else
                        setReservations(reservations.get(loc));
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(view,
                        "Error updating database", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        if (selectedReservations.size() > 1)
            JOptionPane.showMessageDialog(view, 
                "Cannot cancel multiple reservations", "Warning",
                    JOptionPane.WARNING_MESSAGE);
    }
    
    /**
        Shows the dialog enabling the administrator to send an email to a
        reserver    
    */
    
    private void contact()
    {
        List<Reservation> selectedReservations = view.getSelectedReservations();
        
        if (selectedReservations.size() == 1)
        {
            Reserver reserver = selectedReservations.get(0).getReserver();
            SendEmailDialog d = new SendEmailDialog(SendEmailDialog.ADMIN,
                reserver);
            d.setVisible(true);
        }
    }
    
    /**
        Marks a reservation as either reviewed or not reviewed
    
        @param reviewed Whether to mark reservation as reviewed or not
    */
    
    private void reviewed(boolean reviewed)
    {
        List<Reservation> reserves = view.getSelectedReservations();
        
        if (reserves.size() == 1)
        {
            try
            {
                SystemPreferences prefs = SystemPreferences.getInstance();
                String user = prefs.getDBUser();
                String pass = prefs.getDBPass();
                DatabaseConnection conn = DatabaseConnection.getConnection(
                        user, pass);
                
                Reservation reservation = reserves.get(0);
                new RecordModify(conn).modifyReservationReviewed(
                        reservation, reviewed);
                
                if (reviewed)
                    reservation.reviewed();
                else
                    reservation.notReviewed();
                
                conn.close();
                
                String btnText = (reviewed) ? "Reassess" : "Reviewed";
                view.setReviewedButtonText(btnText);
                
                setReservations(reservations.get(view.getSelectedLocation()));
            }
            catch (SQLException | RecordNotExistsException ex)
            {
                JOptionPane.showMessageDialog(view, "Error updating database",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(view, 
                "Can only review or reassess one reservation", "Warning",
                    JOptionPane.WARNING_MESSAGE);
    }
    
    /**
        Sets locations on the view to the current location listing
    */
    
    private void setLocations()
    {
        List<Location> locs = new ArrayList<>(reservations.keySet());
        if (locationFilter.getPredicate() != null)
            view.setLocations(locationFilter.filter(locs));
        else
            view.setLocations(locs);
    }
    
    /**
        Sets reservations on the view
    */
    
    private void setReservations(List<Reservation> reservations)
    {
        if (reservationFilter.getPredicate() != null)
            view.setReservations(reservationFilter.filter(reservations));
        else
            view.setReservations(reservations);
    }
}