/**
    Controller for buttons on the panel to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller.reservation;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.gayj5385.gui.ManageReservationPanel;
import edu.faytechcc.student.gayj5385.gui.dialog.SearchHelpDialog;
import edu.faytechcc.student.gayj5385.gui.dialog.SendEmailDialog;
import edu.faytechcc.student.mccanns0131.database.ReservationSQLDAO;
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
                clear();
                if (!view.getSearchCriteria().isEmpty())
                    search(view.getSearchCriteria());
                break;
            case "Clear":
                view.clearSearch();
                clear();
                break;
            case "Help":
                new SearchHelpDialog().setVisible(true);
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
                    ReservationSQLDAO resDAO = new ReservationSQLDAO();
                    resDAO.removeReservation(reservation);
                    
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
        List<Reservation> reserves = view.getSelectedReservations();
        
        if (reserves.size() == 1)
        {
            try
            {                
                Reservation reservation = reserves.get(0);
                
                if (reviewed)
                    reservation.reviewed();
                else
                    reservation.notReviewed();
                
                ReservationSQLDAO resDAO = new ReservationSQLDAO();
                resDAO.updateReservation(reservation);
                                
                String btnText = (reviewed) ? "Reassess" : "Reviewed";
                view.setReviewedButtonText(btnText);
                
                setReservations(reservations.get(view.getSelectedLocation()));
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
        List<String> acceptedKeys = new ArrayList();
        
        acceptedKeys.add("locationname");
        acceptedKeys.add("location");
        acceptedKeys.add("loc");
        acceptedKeys.add("startdate");
        acceptedKeys.add("starttime");
        acceptedKeys.add("enddate");
        acceptedKeys.add("endtime");
        acceptedKeys.add("cost");
        acceptedKeys.add("price");
        acceptedKeys.add("firstname");
        acceptedKeys.add("first");
        acceptedKeys.add("lastname");
        acceptedKeys.add("last");
        acceptedKeys.add("emailaddress");
        acceptedKeys.add("email");
        acceptedKeys.add("phonenumber");
        acceptedKeys.add("phone");
        
        SearchActualizer search = new SearchActualizer(criteria, acceptedKeys);
        
        if (search.validateSearch())
        {
//            locationFilter.setPredicate(search.searchLocations());
//            setLocations();
//            
//            if (view.getSelectedLocation() != null)
//            {
//                reservationFilter.setPredicate(search.searchReservations());
//                setReservations(reservationFilter.filter(reservations.get(
//                        view.getSelectedLocation())));
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(view, "No locations found");
//                clear();
//            }
//        }
          switch (search.getNumSearchLocations())
            {
                case 0:
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
            view.setReservations(reservationFilter.filter(reservations.get(
                        view.getSelectedLocation())));
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
            view.setReservations(reservationFilter.filter(reservations.get(
                        view.getSelectedLocation())));
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
            
            List<Reservation> reserves = reservations.get(view.getSelectedLocation());

            setReservations(reserves);
        }
        else
            JOptionPane.showMessageDialog(view, "No location selected");
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