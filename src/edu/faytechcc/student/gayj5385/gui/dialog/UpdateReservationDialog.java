/**
    Dialog for updating the information of a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.Reservation;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateReservationDialog extends JDialog
{
    private JButton update, exit;
    private JComboBox<String> reviewed;
    private JTextField locName, locCap, cost, startDate, startTime, endDate,
            endTime, eventType, numAttending, reserverFName, reserverLName,
            reserverEmail, reserverPhone;
    
    /**
        Constructs a new UpdateReservationDialog
    
        @param reservation Reservation to be updated
        @param repo Data repository
    */
    
    public UpdateReservationDialog(Reservation reservation, DataRepository repo)
    {
        setLayout(new BorderLayout());
        setTitle("Update Reservation");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);

        add(buildMainPanel(reservation), BorderLayout.CENTER);
        add(buildButtonPanel(reservation, repo), BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
    
    /**
        Builds & returns the button panel of the dialog
    
        @param reservation The reservation to be updated
        @param repo Data repository
        @return The built panel
    */
    
    private JPanel buildButtonPanel(Reservation reservation,
            DataRepository repo)
    {
        JPanel panel = new JPanel();
        
        panel.add(update = new JButton("Update"));
        panel.add(exit = new JButton("Exit"));
        
        ButtonController controller = new ButtonController(reservation, repo);
        
        update.addActionListener(controller);
        exit.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog
    
        @param reservation Reservation to be updated
        @return The built panel
    */
    
    private JPanel buildMainPanel(Reservation reservation)
    {
        JPanel panel = new JPanel();
        
        // Build reservable info panel
        JPanel reservableInfoPane = new JPanel(new GridLayout(7, 2));
        reservableInfoPane.setBorder(
                BorderFactory.createTitledBorder("Reservable Info"));
        
        reservableInfoPane.add(new JLabel("Location Name:"));
        reservableInfoPane.add(locName = new JTextField(7));
        locName.setText(reservation.getLocationName());
        
        reservableInfoPane.add(new JLabel("Location Capacity:"));
        reservableInfoPane.add(locCap = new JTextField(7));
        locCap.setText(String.valueOf(reservation.getLocationCapacity()));
        
        reservableInfoPane.add(new JLabel("Cost:"));
        reservableInfoPane.add(cost = new JTextField(7));
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        cost.setText(fmt.format(reservation.getCost()));
        
        reservableInfoPane.add(new JLabel("Start Date:"));
        reservableInfoPane.add(startDate = new JTextField(7));
        startDate.setText(reservation.getStartDate().toString());
        
        reservableInfoPane.add(new JLabel("Start Time:"));
        reservableInfoPane.add(startTime = new JTextField(7));
        startTime.setText(reservation.getStartTime().toString());
        
        reservableInfoPane.add(new JLabel("End Date:"));
        reservableInfoPane.add(endDate = new JTextField(7));
        endDate.setText(reservation.getEndDate().toString());
        
        reservableInfoPane.add(new JLabel("End Time:"));
        reservableInfoPane.add(endTime = new JTextField(7));
        endTime.setText(reservation.getEndTime().toString());
        
        locName.setEditable(false);
        locCap.setEditable(false);
        cost.setEditable(false);
        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        
        // Build reservation info panel
        JPanel reservationInfoPane = new JPanel(new GridLayout(7, 2));
        reservationInfoPane.setBorder(
                BorderFactory.createTitledBorder("Reservation Info"));
        
        reservationInfoPane.add(new JLabel("Event Type:"));
        reservationInfoPane.add(eventType = new JTextField(7));
        eventType.setText(reservation.getEventType());
        
        reservationInfoPane.add(new JLabel("Number Attending:"));
        reservationInfoPane.add(numAttending = new JTextField(7));
        numAttending.setText(String.valueOf(reservation.getNumberAttending()));
        
        reservationInfoPane.add(new JLabel("Reviewed:"));
        String[] options = { "Reviewed", "Not Reviewed" };
        reservationInfoPane.add(reviewed = new JComboBox<>(options));
        reviewed.setSelectedIndex((reservation.isReviewed()) ? 0 : 1);
        
        reservationInfoPane.add(new JLabel("Reserver First Name:"));
        reservationInfoPane.add(reserverFName = new JTextField(7));
        reserverFName.setText(reservation.getReserverFirstName());
        
        reservationInfoPane.add(new JLabel("Reserver Last Name:"));
        reservationInfoPane.add(reserverLName = new JTextField(7));
        reserverLName.setText(reservation.getReserverLastName());
        
        reservationInfoPane.add(new JLabel("Reserver Email:"));
        reservationInfoPane.add(reserverEmail = new JTextField(7));
        reserverEmail.setText(reservation.getReserverEmail());
        
        reservationInfoPane.add(new JLabel("Reserver Phone:"));
        reservationInfoPane.add(reserverPhone = new JTextField(7));
        reserverPhone.setText(reservation.getReserverPhone());
        
        // Add built subpanels to main panel
        panel.add(reservableInfoPane);
        panel.add(reservationInfoPane);
        
        return panel;
    }
    
    /**
        Controller for buttons on the dialog
    */
    
    private class ButtonController implements ActionListener
    {
        private DataRepository repo;
        private Reservation reservation;
        
        /**
            Constructs a new ButtonController
        
            @param reservation Reservation to be updated
            @param repo Data repository
        */
        
        public ButtonController(Reservation reservation, DataRepository repo)
        {
            this.reservation = reservation;
            this.repo = repo;
        }
        
        /**
            Responds to an action event
        
            @param e The action event
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == update)
            {
                if (validateInput())
                {
                    reservation.setEventType(eventType.getText().trim());
                    reservation.setNumAttending(Integer.parseInt(
                            numAttending.getText()));
                    
                    if (reviewed.getSelectedItem().equals("Reviewed"))
                        reservation.reviewed();
                    else
                        reservation.notReviewed();
                    
                    reservation.setReserverFirstName(
                            reserverFName.getText().trim());
                    reservation.setReserverLastName(
                            reserverLName.getText().trim());
                    reservation.setReserverEmail(
                            reserverEmail.getText().trim());
                    reservation.setReserverPhone(
                            reserverPhone.getText().trim());
                    
                    try
                    {
                        repo.updateReservation(reservation);
                        JOptionPane.showMessageDialog(null,
                                "Reservation updated");
                    }
                    catch (SQLException ex)
                    {
                        ErrorDialog.show(null, "Error updating reservation");
                    }
                }
            }
            else
                dispose();
        }
        
        /**
            Validates user input
        
            @return If user input is valid
        */
        
        private boolean validateInput()
        {
            if (eventType.getText().isEmpty())
            {
                ErrorDialog.show(null, "Event type must be input");
                return false;
            }
            if (!numAttending.getText().trim().matches("^[1-9][0-9]*$"))
            {
                ErrorDialog.show(null, "Invalid number attending entered");
                return false;
            }
            if (!(Integer.parseInt(numAttending.getText()) <=
                    reservation.getLocationCapacity()))
            {
                ErrorDialog.show(null,
                        "Number attending exceeds location capacity");
                return false;
            }
            if (reserverFName.getText().isEmpty())
            {
                ErrorDialog.show(null, "Reserver first name must be entered");
                return false;
            }
            if (reserverLName.getText().isEmpty())
            {
                ErrorDialog.show(null, "Reserver last name must be entered");
                return false;
            }
            if (reserverEmail.getText().isEmpty() ||
                    !reserverEmail.getText().trim().matches(
                            "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b"))
            {
                ErrorDialog.show(null, "Invalid email address entered");
                return false;
            }
            if (reserverPhone.getText().isEmpty() ||
                !reserverPhone.getText().trim().matches(
                "(?:\\d{1}\\s)?\\(?(\\d{3})\\)?-?\\s?(\\d{3})-?\\s?(\\d{4})"))
            {
                ErrorDialog.show(null, "Invalid phone number entered");
                return false;
            }
            
            return true;
        }
    }
}