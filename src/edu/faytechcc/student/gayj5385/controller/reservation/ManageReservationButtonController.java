/**
    Controller for buttons on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservation;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import edu.faytechcc.student.gayj5385.gui.dialog.CreateReservationReportDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.SearchHelpDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.SendEmailDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ManageReservationButtonController implements ActionListener
{
    // Fields
    private DataRepository repo;
    private Filter<ReservableLocation> locationFilter;
    private Filter<Reservation> reservationFilter;
    private ManageReservationPanel view;
    
    /**
        Constructs a new ManageReservationButtonController
    
        @param v The view
        @param repo Data repository
        @param locFilter ReservableLocation filter
        @param resFilter Reservation filter
    */
    
    public ManageReservationButtonController(ManageReservationPanel v,
            DataRepository repo, Filter<ReservableLocation> locFilter,
            Filter<Reservation> resFilter)
    {
        view = v;
        this.repo = repo;
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
                clear();
                if (!view.getSearchCriteria().isEmpty())
                    search(view.getSearchCriteria());
                break;
            case "Clear":
                view.clearSearch();
                clear();
                break;
            case "Help":
                new SearchHelpDialog(true).setVisible(true);
                break;
            case "Create Report":
                new CreateReservationReportDialog(getAllMatchingReservations());
                break;
            case "Logout":
                System.exit(0);
                break;
        }
    }
    
    /**
        Cancels selected reservation
    */
    
    private void cancel()
    {
        List<Reservation> selectedReservations = view.getSelectedReservations();
        
        if (selectedReservations.size() == 1)
        {
            // Confirm cancellation
            int choice = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to cancel the selected reservation?",
                "Select an Option", JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION)
            {
                Reservation reservation = selectedReservations.get(0);
                
                try
                {                    
                    repo.removeReservation(reservation);
                    reservation.cancel();
                    
//                    ReserverInformant informant = new ReserverInformant(repo);
//                    informant.informOfReservationCancellation(reservation);
                    
                    // Updating from database at the moment because
                    // there is an issue with the timeframe objects in the
                    // reservations not referring to the same timeframe objects
                    // in locations
                    repo.update();
                    setLocations();
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
     * Clears search results
     */
    private void clear()
    {
        reservationFilter.setPredicate(null);
        locationFilter.setPredicate(null);
        
        setLocations();
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
     * Gets all reservations that match the location and reservation filters
     */
    private List<Reservation> getAllMatchingReservations()
    {
        List<Reservation> reservations = new ArrayList();
        
        if (locationFilter.getPredicate() != null)
        {
            if (reservationFilter.getPredicate() != null)
            {
                for (ReservableLocation l : locationFilter.filter(
                        repo.getReservedLocations()))
                {            
                    for (Reservation r : reservationFilter.filter(
                            repo.getLocationReservations(l)))
                    {
                        reservations.add(r);
                    }
                }
            }
            else
            {
                for (ReservableLocation l : locationFilter.filter(
                        repo.getReservedLocations()))
                {            
                    for (Reservation r : repo.getLocationReservations(l))
                    {
                        reservations.add(r);
                    }
                }
            }
        }
        else
        {
            if (reservationFilter.getPredicate() != null)
            {
                for (ReservableLocation l : repo.getAvailableLocations())
                {            
                    for (Reservation r : reservationFilter.filter(
                            repo.getLocationReservations(l)))
                    {
                        reservations.add(r);
                    }
                }
            }
            else
            {
                for (ReservableLocation l : repo.getAvailableLocations())
                {            
                    for (Reservation r : repo.getLocationReservations(l))
                    {
                        reservations.add(r);
                    }
                }
            }
        }
        return reservations;
    }
    
    /**
        Marks a reservation as either reviewed or not reviewed
    
        @param reviewed Whether to mark reservation as reviewed or not
    */
    
    private void reviewed(boolean reviewed)
    {
        List<Reservation> selectedReservations = view.getSelectedReservations();
        
        if (selectedReservations.size() == 1)
        {
            try
            {                
                Reservation reservation = selectedReservations.get(0);
                
                if (reviewed)
                    reservation.reviewed();
                else
                    reservation.notReviewed();
                
                repo.updateReservation(reservation);
                                
                String btnText = (reviewed) ? "Reassess" : "Reviewed";
                view.setReviewedButtonText(btnText);
                
                setReservations();
            }
            catch (SQLException ex)
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
     * Searches existing reservations that match search criteria
     */
    private void search(String criteria)
    {
        SearchActualizer search = new SearchActualizer(criteria.toLowerCase());
        
        // Get relevant locations
        locationFilter.setPredicate(search.searchLocations());
        List<ReservableLocation> locs = repo.getReservedLocations();
        
        if (locationFilter.getPredicate() != null)
        {
            locs = locationFilter.filter(repo.getReservedLocations());
        }
        
        // Get relevant reservations
        reservationFilter.setPredicate(search.searchReservations());
        List<Reservation> reserves = new ArrayList();
            
        if (reservationFilter.getPredicate() != null)
        {
            // Used to specify locations to remove from locs
            List<ReservableLocation> locsToRemove = new ArrayList();
            
            for (ReservableLocation l : locs)
            {
                reserves = reservationFilter.filter(
                        repo.getLocationReservations(l));
            
                if (reserves.isEmpty())
                {
                    // Add to locsToRemove since there is no matching
                    // reservations
                    locsToRemove.add(l);
                }
            }
            // Remove each location in locsToRemove from locs
            for (ReservableLocation l : locsToRemove)
            {
                locs.remove(l);
            }
        }
        setReservations();
        view.setLocations(locs);
    }
    
    /**
        Sets locations on the view
    */
    
    private void setLocations()
    {
        if (locationFilter.getPredicate() != null)
            view.setLocations(
                    locationFilter.filter(repo.getReservedLocations()));
        else
            view.setLocations(repo.getReservedLocations());
    }
    
    /**
        Sets reservations on the view
    */
    
    private void setReservations()
    {
        ReservableLocation loc = view.getSelectedLocation();
        
        List<Reservation> reserves = repo.getLocationReservations(loc);
        
        if (reservationFilter.getPredicate() != null)
            reserves = reservationFilter.filter(reserves);
        
        view.setReservations(reserves);
    }
}