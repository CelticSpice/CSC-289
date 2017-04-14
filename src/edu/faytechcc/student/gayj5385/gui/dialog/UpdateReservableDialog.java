/**
    Dialog for updating a reservable
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.DataRepository;
import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.ReservableLocation;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.burnst9091.data.ReserverInformant;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UpdateReservableDialog extends JDialog
{
    private JButton update, exit;
    private JTextField locationName, locationCapacity, cost, startDate,
                       startTime, endDate, endTime;
    
    /**
        Constructs a new UpdateReservableDialog
    
        @param reservable The reservable to be updated
        @param repo Data repository
    */
    
    public UpdateReservableDialog(Reservable reservable, DataRepository repo)
    {
        setLayout(new BorderLayout());
        setTitle("Update Reservable");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildMainPanel(reservable), BorderLayout.CENTER);
        add(buildButtonPanel(reservable, repo), BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }
    
    /**
        Builds & returns the button panel of the dialog
    
        @param reservable The reservable to be updated
        @param repo Data repository
        @return The built panel
    */
    
    private JPanel buildButtonPanel(Reservable reservable, DataRepository repo)
    {
        JPanel panel = new JPanel();
        
        panel.add(update = new JButton("Update"));
        panel.add(exit = new JButton("Exit"));
        
        ButtonController controller = new ButtonController(reservable, repo);
        
        update.addActionListener(controller);
        exit.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog
    
        @param reservable The reservable to be updated
        @return The built panel
    */
    
    private JPanel buildMainPanel(Reservable reservable)
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        // Build subpanel 1
        JPanel subPanel1 = new JPanel(new GridLayout(3, 2));
        
        subPanel1.add(new JLabel("Location Name:"));
        subPanel1.add(locationName = new JTextField(7));
        locationName.setText(reservable.getName());
        
        subPanel1.add(new JLabel("Location Capacity:"));
        subPanel1.add(locationCapacity = new JTextField(7));
        locationCapacity.setText(String.valueOf(reservable.getCapacity()));
        
        subPanel1.add(new JLabel("Cost:"));
        subPanel1.add(cost = new JTextField(7));
        
        NumberFormat moneyFmt = NumberFormat.getCurrencyInstance();
        cost.setText(moneyFmt.format(reservable.getCost()));
        
        TextFieldListener fieldListener = new TextFieldListener();
        locationName.getDocument().addDocumentListener(fieldListener);
        locationCapacity.getDocument().addDocumentListener(fieldListener);
        
        // Build subpanel 2
        JPanel subPanel2 = new JPanel(new GridLayout(4, 2));
        subPanel2.setBorder(BorderFactory.createTitledBorder("Timeframe"));
        
        subPanel2.add(new JLabel("Start Date:"));
        subPanel2.add(startDate = new JTextField(7));
        startDate.setText(reservable.getStartDate().toString());
        
        subPanel2.add(new JLabel("Start Time:"));
        subPanel2.add(startTime = new JTextField(7));
        startTime.setText(reservable.getStartTime().toString());
        
        subPanel2.add(new JLabel("End Date:"));
        subPanel2.add(endDate = new JTextField(7));
        endDate.setText(reservable.getEndDate().toString());
        
        subPanel2.add(new JLabel("End Time:"));
        subPanel2.add(endTime = new JTextField(7));
        endTime.setText(reservable.getEndTime().toString());
        
        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        
        panel.add(subPanel1);
        panel.add(subPanel2);
        
        return panel;
    }
    
    /**
        Listener for buttons
    */
    
    private class ButtonController implements ActionListener
    {
        private Reservable reservable;
        private DataRepository repo;
        
        /**
            Constructs a new ButtonController
        
            @param reservable Reservable to be updated
            @param repo Data repository
        */
        
        public ButtonController(Reservable reservable, DataRepository repo)
        {
            this.reservable = reservable;
            this.repo = repo;
        }
        
        /**
            Responds to a button being clicked
        
            @param e ActionEvent
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == update)
            {
                if (validateInput())
                {
                    boolean nameOrCapacityChanged = isNameOrCapacityChanged();
                    boolean proceed = true;
                    
                    if (nameOrCapacityChanged)
                    {
                        int choice = JOptionPane.showConfirmDialog(null,
                                "Changing reservable location name or " +
                                "capacity - Continue?", "Confirm",
                                JOptionPane.YES_NO_OPTION);
                        
                        if (!(choice == JOptionPane.YES_OPTION))
                            proceed = false;
                    }
                    
                    if (proceed)
                    {
                        try
                        {
                            reservable.setCost(parseCost());
                            
                            String oldName = "";
                            int oldCapacity = 0;
                            if (nameOrCapacityChanged)
                            {
                                oldName = reservable.getName();
                                oldCapacity = reservable.getCapacity();
                                
                                String locName = locationName.getText();
                                reservable.setLocationName(locName);
                                
                                reservable.setLocationCapacity(parseCapacity());
                            }
                            
                            repo.updateReservable(reservable);
                            
                            if (nameOrCapacityChanged)
                            {
                                ReserverInformant informant =
                                        new ReserverInformant(repo);
                                
                                informant.informOfLocationChange(oldName,
                                        oldCapacity, reservable.getLocation());
                            }
                            
                            JOptionPane.showMessageDialog(null,
                                    "Reservable updated");
                        }
                        catch (SQLException ex)
                        {
                            displayError("Error updating reservable");
                        }
                    }
                }
            }
            else
                dispose();
        }
        
        /**
            Checks if the user's input capacity renders present
            reservations at the same location as the reservable's attendance
            invalid
        
            @param capacity Input capacity
            @return If the input capacity is unacceptable
        */
        
        private boolean capacityInvalidatesAttendance(int capacity)
        {
            ReservableLocation loc = reservable.getLocation();
            for (Reservation reservation : repo.getLocationReservations(loc))
            {
                int attendance = reservation.getNumberAttending();
                
                if (capacity < attendance)
                {
                    displayError("Input capacity invalidates attendance of " +
                            attendance + " at reservable location");
                    return true;
                }
            }
            return false;
        }
        
        /**
            Displays an error message
        
            @param mesg Message to display
        */
        
        private void displayError(String mesg)
        {
            JOptionPane.showMessageDialog(null, mesg, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        /**
            Returns if the reservable's location's name or capacity has been
            changed
        
            @return If the reservable's location's name or capacity has been
                    changed
        */
        
        private boolean isNameOrCapacityChanged()
        {
            String inputName = locationName.getText();
            int inputCapacity = parseCapacity();
            
            return (!inputName.equals(reservable.getName()) ||
                    inputCapacity != reservable.getCapacity());
        }
        
        /**
            Returns if a location with a given name exists
        
            @param name Location name to check if already exists
            @return If a location with the given name exists
        */
        
        private boolean nameMatchesExisting(String name)
        {
            for (Location loc : repo.getLocations())
            {
                if (loc.getName().equals(name))
                    return true;
            }
            return false;
        }
        
        /**
            Parses the input location capacity
        
            @return Input location capacity
        */
        
        private int parseCapacity()
        {
            return Integer.parseInt(locationCapacity.getText());
        }
        
        /**
            Returns the input reservable cost
        
            @return Input reservable cost
        */
        
        private BigDecimal parseCost()
        {
            String input = cost.getText().trim();
            input = input.replace("$", "");
            return new BigDecimal(input);
        }
        
        /**
            Validates user input of location capacity
        
            @return If the user's input of location capacity is valid
        */
        
        private boolean validateCapacity()
        {
            String input = locationCapacity.getText().trim();
            
            if (!input.matches("\\d+"))
            {
                displayError("Invalid capacity entered");
                return false;
            }
            
            int parsedCapacity = parseCapacity();
            
            if (capacityInvalidatesAttendance(parsedCapacity))
                return false;
            
            if (parsedCapacity <= 0)
            {
                displayError("Capacity must be at least 1");
                return false;
            }
            
            return true;
        }
        
        /**
            Validates user input
        
            @return If the user's input is valid
        */
        
        private boolean validateInput()
        {
            String input = locationName.getText().trim();
            
            if (input.isEmpty())
            {
                displayError("Location name cannot be empty");
                return false;
            }
            
            if (nameMatchesExisting(input))
            {
                displayError("A location with that name already exists");
                return false;
            }
            
            input = cost.getText().trim();
            String pattern = "\\$?(\\d+(\\.\\d{1,2})?)|\\$?(\\.\\d{1,2})";
            
            if (!input.matches(pattern))
            {
                displayError("Invalid cost entered");
                return false;
            }
                        
            if (parseCost().doubleValue() < 0)
            {
                displayError("Cost must be at least $0.00");
                return false;
            }
            
            if (!validateCapacity())
                return false;
            
            return true;
        }
    }
    
    /**
        Listener for JTextFields
    */
    
    private class TextFieldListener implements DocumentListener
    {
        private boolean editMade;
        
        /**
            Constructs a new TextFieldListener
        */
        
        public TextFieldListener()
        {
            editMade = false;
        }
        
        /**
            Responds to text being updated
        
            @param e DocumentEvent
        */
        
        @Override
        public void changedUpdate(DocumentEvent e)
        {
            if (!editMade)
            {
                editMade = true;
                JOptionPane.showMessageDialog(null,
                        "Changing name or capacity of reservable location " +
                        "will affect ALL reservables at the location",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        /**
            Not implemented
        
            @param e DocumentEvent
        */
        
        @Override
        public void insertUpdate(DocumentEvent e) {};
        
        /**
            Not implemented
            
            @param e DocumentEvent
        */
        
        @Override
        public void removeUpdate(DocumentEvent e) {};
    }
}