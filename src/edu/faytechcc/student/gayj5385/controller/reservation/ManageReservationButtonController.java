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
import edu.faytechcc.student.burnst9091.data.ReserverInformant;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
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
    private List<String> searchKeys;
    
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
        searchKeys = new ArrayList();
        
        searchKeys.add("locationname");
        searchKeys.add("location");
        searchKeys.add("loc");
        searchKeys.add("startdate");
        searchKeys.add("starttime");
        searchKeys.add("enddate");
        searchKeys.add("endtime");
        searchKeys.add("cost");
        searchKeys.add("price");
        searchKeys.add("firstname");
        searchKeys.add("first");
        searchKeys.add("lastname");
        searchKeys.add("last");
        searchKeys.add("emailaddress");
        searchKeys.add("email");
        searchKeys.add("phonenumber");
        searchKeys.add("phone");
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
        SearchActualizer search = new SearchActualizer(criteria, searchKeys);
        
        if (search.validateSearch())
        {
          switch (search.getNumSearchLocations())
            {
                case 0:
                    criteria = criteria.toLowerCase();
                    if (criteria.contains("firstname::")    ||
                        criteria.contains("first::")        ||
                        criteria.contains("lastname::")     ||
                        criteria.contains("last::")         ||
                        criteria.contains("emailaddress::") ||
                        criteria.contains("email::")        ||
                        criteria.contains("phonenumber::")  ||
                        criteria.contains("phone::"))
                        searchOnMultipleLocations(search);
                    else
                        searchOnSelectedLocation(search);
                    break;
                case 1:
                    searchOnOneLocation(search);
                    break;
                default:
                    searchOnMultipleLocations(search);
                    break;
            }
        }
    }
    
    /**
<<<<<<< HEAD
     * Performs a search on two or more locations
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnMultipleLocations(SearchActualizer s)
    {
        locationFilter.setPredicate(s.searchLocations());
        setLocations();
        
        reservationFilter.setPredicate(s.searchReservations());
            
        if (reservationFilter.getPredicate() != null)
        {
            view.setReservations(reservationFilter.filter(
                    repo.getLocationReservations(view.getSelectedLocation())));
        }
    }
    
    /**
     * Performs a search on one specified location
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnOneLocation(SearchActualizer s)
    {
        locationFilter.setPredicate(s.searchLocations());
        setLocations();
        
        reservationFilter.setPredicate(s.searchReservations());
            
        if (view.getSelectedLocation() != null)
        {
            reservationFilter.setPredicate(s.searchReservations());
            view.setReservations(reservationFilter.filter(
                    repo.getLocationReservations(view.getSelectedLocation())));
        }
        else
        {
            JOptionPane.showMessageDialog(view, "No such location exists");
            clear();
        }
    }
    
    /**
     * Performs a search on the currently selected location
     * 
     * @param s The SearchActualizer to perform the search with
     * @param criteria The search criteria
     */
    private void searchOnSelectedLocation(SearchActualizer s)
    {
        if (view.getSelectedLocation() != null)
        {
            reservationFilter.setPredicate(s.searchReservations());
            
            view.setReservations(reservationFilter.filter(
                    repo.getLocationReservations(view.getSelectedLocation())));
        }
        else
            JOptionPane.showMessageDialog(view, "No location selected");
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
        
        if (reservationFilter.getPredicate() != null)
            view.setReservations(reservationFilter.filter(
                    repo.getLocationReservations(loc)));
        else
            view.setReservations(repo.getLocationReservations(loc));
    }
}