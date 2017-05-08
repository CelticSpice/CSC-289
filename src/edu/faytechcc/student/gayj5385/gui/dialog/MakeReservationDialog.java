/**
    Dialog for the guest to make a reservation & provide reservation details
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;


import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.Reserver;
import edu.faytechcc.student.burnst9091.data.ReserverInformant;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeReservationDialog extends JDialog
{
    private JButton reserve, cancel;
    private JTextField location, capacity, startDate, startTime, endDate,
            endTime, cost, event, attendence, firstName, lastName, email, phone;
    
    /**
        Constructs a new MakeReservationDialog, initialized with the specified
        reservable that is to be reserved
    
        @param r The reservable to be reserved
        @param repo Data repository
    */
    
    public MakeReservationDialog(Reservable r, DataRepository repo)
    {
        setLayout(new BorderLayout());
        setTitle("Make Reservation");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildMainPanel(r), BorderLayout.CENTER);
        add(buildButtonPanel(r, repo), BorderLayout.SOUTH);
        
        pack();
    }
    
    /**
        Builds & returns the button panel of the dialog, initialized with the
        reservable that is to be reserved
        
        @param r The reservable to be reserved
        @param repo Data repository
        @return The built panel
    */
    
    private JPanel buildButtonPanel(Reservable r, DataRepository repo)
    {
        JPanel panel = new JPanel();
        
        panel.add(reserve = new JButton("Reserve"));
        panel.add(cancel = new JButton("Cancel"));
        
        ButtonController controller = new ButtonController(r, repo);
        
        reserve.addActionListener(controller);
        cancel.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog, initialized with the
        specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildMainPanel(Reservable r)
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        panel.add(buildReservableInfoPanel(r));
        panel.add(buildReservationInfoPanel());
        
        return panel;
    }
    
    /**
        Builds & returns the panel containing information about the reservable,
        initialized with the specified reservable that is to be reserved
    
        @param r The reservable to be reserved
        @return The built panel
    */
    
    private JPanel buildReservableInfoPanel(Reservable r)
    {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Reservable Info"));
        
        panel.add(new JLabel("Location:"));
        panel.add(location = new JTextField(9));
        
        panel.add(new JLabel("Capacity:"));
        panel.add(capacity = new JTextField(9));
        
        panel.add(new JLabel("Start Date:"));
        panel.add(startDate = new JTextField(9));
        
        panel.add(new JLabel("Start Time:"));
        panel.add(startTime = new JTextField(9));
        
        panel.add(new JLabel("End Date:"));
        panel.add(endDate = new JTextField(9));
        
        panel.add(new JLabel("End Time:"));
        panel.add(endTime = new JTextField(9));
        
        panel.add(new JLabel("Cost:"));
        panel.add(cost = new JTextField(9));
        
        location.setText(r.getName());
        capacity.setText(String.valueOf(r.getCapacity()));
        
        location.setEditable(false);
        capacity.setEditable(false);
        
        startDate.setText(r.getStartDate().toString());
        startTime.setText(r.getStartTime().toString());
        endDate.setText(r.getEndDate().toString());
        endTime.setText(r.getEndTime().toString());
        
        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        
        NumberFormat cFmt = NumberFormat.getCurrencyInstance();
        
        cost.setText(cFmt.format(r.getTimeframe().getCost()));
        cost.setEditable(false);
        
        return panel;
    }
    
    /**
        Builds & returns the panel containing information about the reservation
    
        @return The built panel
    */
    
    private JPanel buildReservationInfoPanel()
    {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Reservation Info"));
        
        panel.add(new JLabel("Event Type:"));
        panel.add(event = new JTextField(7));
        
        panel.add(new JLabel("Expected Attendence:"));
        panel.add(attendence = new JTextField(7));
        
        panel.add(new JLabel("First Name:"));
        panel.add(firstName = new JTextField(7));
        
        panel.add(new JLabel("Last Name:"));
        panel.add(lastName = new JTextField(7));
        
        panel.add(new JLabel("Email:"));
        panel.add(email = new JTextField(7));
        
        panel.add(new JLabel("Phone:"));
        panel.add(phone = new JTextField(7));
        
        return panel;
    }
    
    /**
        Controller for the dialog's buttons
    */
    
    private class ButtonController implements ActionListener
    {
        private DataRepository repo;
        private Reservable reservable;
        
        /**
            Constructs a new ButtonController initialized with the specified
            reservable that is to be reserved
        
            @param r The reservable to be reserved
            @param repo Data repository
        */
        
        public ButtonController(Reservable r, DataRepository repo)
        {
            reservable = r;
            this.repo = repo;
        }
        
        /**
            Performs an action on a button being clicked
        
            @param e The ActionEvent
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == reserve)
            {
                if (validateInput())
                {
                    makeReservation();
                    dispose();
                }
            }
            else
                dispose();
        }
        
        /**
            Displays a warning message
        
            @param mesg Warning message to display
        */
        
        private void displayWarning(String mesg)
        {
            JOptionPane.showMessageDialog(null, mesg, "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        /**
            Makes & records a reservation
        */
        
        private void makeReservation()
        {
            try
            {
                // Create reserver
                String fName = firstName.getText();
                String lName = lastName.getText();
                String e = email.getText();
                String p = phone.getText();
                Reserver reserver = new Reserver(fName, lName, e, p);
                
                // Acquire reserver ID
                reserver.setID(repo.acquireReserverID(reserver));
                
                // Get reservation info
                int numAttending = Integer.parseInt(attendence.getText());
                String eventType = event.getText();
                
                // Create reservation
                Reservation reservation = new Reservation(reserver, reservable,
                        numAttending, eventType, false);
                                
                repo.addReservation(reservation);
                
                reservable.reserve();
                
                // Show confirmation
                JOptionPane.showMessageDialog(null, "Reservation made");
                
                new ReserverInformant(repo).informOfReservationMade(
                        reservation);
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Error making reservation",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        /**
            Validates the user's input
        
            @return If the user's input is valid
        */
        
        private boolean validateInput()
        {
            if (event.getText().isEmpty())
            {
                displayWarning("Event type must be entered");
                return false;
            }
            
            String pattern = "\\b\\d+\\b";
            
            if (!attendence.getText().matches(pattern))
            {
                displayWarning("Expected attendence must be entered");
                return false;
            }
            
            pattern = "\\b0+\\b|";
            
            if (attendence.getText().matches(pattern))
            {
                displayWarning("Attendence must be greater than 0");
                return false;
            }
            
            if (!(Integer.parseInt(attendence.getText()) <=
                    reservable.getCapacity()))
            {
                displayWarning("Attendence must not exceed location capacity");
                return false;
            }
            
            if (firstName.getText().isEmpty())
            {
                displayWarning("First name must be entered");
                return false;
            }
            
            if (lastName.getText().isEmpty())
            {
                displayWarning("Last name must be entered");
                return false;
            }
            
            pattern = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
            
            if (!email.getText().matches(pattern))
            {
                displayWarning("Invalid email address entered");
                return false;
            }
            
            pattern =
                "(?:\\d{1}\\s)?\\(?(\\d{3})\\)?-?\\s?(\\d{3})-?\\s?(\\d{4})";
            
            if (!phone.getText().matches(pattern))
            {
                displayWarning("Invalid phone number entered");
                return false;
            }
            
            return true;
        }
    }
}