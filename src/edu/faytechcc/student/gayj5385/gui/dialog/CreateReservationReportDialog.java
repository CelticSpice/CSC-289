/**
 * Enables an admin user to create a report of Reservations
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.CSVWriter;
import edu.faytechcc.student.burnst9091.data.Reservation;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CreateReservationReportDialog extends JDialog
{
    // Field(s)
    private JButton create, cancel;
    private JCheckBox locationName, capacity, startDate, endDate, startTime,
            endTime, cost, firstName, lastName, emailAddress, phoneNumber,
            eventType, numberAttending;
    
    /**
     * Constructs a dialog that allows the user to create a report of
     * reservations
     * 
     * @param reserves Reservations to input to the report
     */
    public CreateReservationReportDialog(List<Reservation> reserves)
    {
        setLayout(new BorderLayout());
        setTitle("Create a Reservation Report");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildCheckBoxPanel(), BorderLayout.NORTH);
        add(buildButtonPanel(reserves), BorderLayout.SOUTH);
        
        setSize(450, 275);
        setVisible(true);
    }
    
    /**
     * Builds the panel containing check boxes which determine what fields to
     * include in the report
     * 
     * @return The built panel
     */
    private JPanel buildCheckBoxPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 7, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Select the fields you would like to include " +
                "in the report:"), gbc);
        
        CheckBoxListener listener = new CheckBoxListener();
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(locationName = new JCheckBox("Location Name", true), gbc);
        locationName.addChangeListener(listener);
        
        gbc.gridy = 2;
        panel.add(capacity = new JCheckBox("Capacity", true), gbc);
        capacity.addChangeListener(listener);
        
        gbc.gridy = 3;
        panel.add(startDate = new JCheckBox("Start Date", true), gbc);
        startDate.addChangeListener(listener);
        
        gbc.gridy = 4;
        panel.add(endDate = new JCheckBox("End Date", true), gbc);
        endDate.addChangeListener(listener);
        
        gbc.gridy = 5;
        panel.add(startTime = new JCheckBox("Start Time", true), gbc);
        startTime.addChangeListener(listener);
        
        gbc.gridy = 6;
        panel.add(endTime = new JCheckBox("End Time", true), gbc);
        endTime.addChangeListener(listener);
        
        gbc.gridy = 7;
        panel.add(cost = new JCheckBox("Cost", true), gbc);
        cost.addChangeListener(listener);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(firstName = new JCheckBox("First Name", true), gbc);
        firstName.addChangeListener(listener);

        gbc.gridy = 2;
        panel.add(lastName = new JCheckBox("Last Name", true), gbc);
        lastName.addChangeListener(listener);

        gbc.gridy = 3;
        panel.add(emailAddress = new JCheckBox("Email Address", true), gbc);
        emailAddress.addChangeListener(listener);

        gbc.gridy = 4;
        panel.add(phoneNumber = new JCheckBox("Phone Number", true), gbc);
        phoneNumber.addChangeListener(listener);
        
        gbc.gridy = 5;
        panel.add(eventType = new JCheckBox("Event Type", true), gbc);
        eventType.addChangeListener(listener);
        
        gbc.gridy = 6;
        panel.add(numberAttending = new JCheckBox("Number Attending", true),
                gbc);
        numberAttending.addChangeListener(listener);
            
        return panel;
    }
    
    /**
     * A basic ChangeListener to register with check boxes
     */
    private class CheckBoxListener implements ChangeListener
    {        
        public CheckBoxListener(){}
        
        @Override
        public void stateChanged(ChangeEvent e){}
    }
    
    /**
     * Builds the panel that holds the create and cancel buttons
     * 
     * @param reserves Reservations to input to the report
     * @return The built panel
     */
    private JPanel buildButtonPanel(List<Reservation> reserves)
    {
        JPanel panel = new JPanel();
        
        ButtonController controller = new ButtonController(this, reserves);
        
        panel.add(create = new JButton("Create"));
        create.addActionListener(controller);
        panel.add(cancel = new JButton("Cancel"));
        cancel.addActionListener(controller);
        
        return panel;
    }
    
    /**
     * A controller for buttons; Generates reservation reports or cancels
     * that process.
     */
    private class ButtonController implements ActionListener
    {
        private CreateReservationReportDialog dialog;
        private List<Reservation> reservations;
        
        public ButtonController(CreateReservationReportDialog d,
                List<Reservation> r)
        {
            dialog = d;
            reservations = r;
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == create)
            {
                JFileChooser chooser = new JFileChooser();
                
                FileNameExtensionFilter filter =
                        new FileNameExtensionFilter("CSV Files", "csv");
                chooser.setFileFilter(filter);

                int choice = chooser.showSaveDialog(create);

                if (choice == JFileChooser.APPROVE_OPTION)
                {
                    String filename =
                        chooser.getName(chooser.getSelectedFile());

                    if (validFilename(filename))
                    {
                        CSVWriter writer = new CSVWriter(filename);

                        writer.writeReservationReport(dialog, reservations);
                        
                        dispose();
                    }
                }
            }
            else
                dispose();
        }
    }
    
    /**
     * Returns if capacity is selected
     * 
     * @return If capacity is selected
     */
    public boolean isCapacitySelected()
    {
        return capacity.isSelected();
    }
    
    /**
     * Returns if cost is selected
     * 
     * @return If cost is selected
     */
    public boolean isCostSelected()
    {
        return cost.isSelected();
    }
    
    /**
     * Returns if emailAddress is selected
     * 
     * @return If emailAddress is selected
     */
    public boolean isEmailAddressSelected()
    {
        return emailAddress.isSelected();
    }
    
    /**
     * Returns if endDate is selected
     * 
     * @return If endDate is selected
     */
    public boolean isEndDateSelected()
    {
        return endDate.isSelected();
    }
    
    /**
     * Returns if endTime is selected
     * 
     * @return If endTime is selected
     */
    public boolean isEndTimeSelected()
    {
        return endTime.isSelected();
    }
    
    /**
     * Returns if eventType is selected
     * 
     * @return If eventType is selected
     */
    public boolean isEventTypeSelected()
    {
        return eventType.isSelected();
    }
    
    /**
     * Returns if firstName is selected
     * 
     * @return If firstName is selected
     */
    public boolean isFirstNameSelected()
    {
        return firstName.isSelected();
    }
    
    /**
     * Returns if lastName is selected
     * 
     * @return If lastName is selected
     */
    public boolean isLastNameSelected()
    {
        return lastName.isSelected();
    }
    
    /**
     * Returns if locationName is selected
     * 
     * @return If locationName is selected
     */
    public boolean isLocationNameSelected()
    {
        return locationName.isSelected();
    }
    
    /**
     * Returns if numberAttending is selected
     * 
     * @return If numberAttending is selected
     */
    public boolean isNumberAttendingSelected()
    {
        return numberAttending.isSelected();
    }
    
    /**
     * Returns if phoneNumber is selected
     * 
     * @return If phoneNumber is selected
     */
    public boolean isPhoneNumberSelected()
    {
        return phoneNumber.isSelected();
    }
    
    /**
     * Returns if startDate is selected
     * 
     * @return If startDate is selected
     */
    public boolean isStartDateSelected()
    {
        return startDate.isSelected();
    }
    
    /**
     * Returns if startTime is selected
     * 
     * @return If startTime is selected
     */
    public boolean isStartTimeSelected()
    {
        return startTime.isSelected();
    }
    
    /**
     * Validate a filename
     * 
     * @param filename The filename to validate
     * @return Whether the filename is valid or not
     */
    private boolean validFilename(String filename)
    {
        boolean valid = false;
        
        if (filename.endsWith(".csv"))
        {
            String[] tokens = filename.split("\\.");
            
            ArrayList<String> tokenList = new ArrayList();
            
            // Remove empty tokens
            for (String t : tokens)
            {
                if (!t.isEmpty())
                    tokenList.add(t);
            }
            
            switch (tokenList.size())
            {
                case 2:
                    if (!tokenList.get(0).matches("[\\*\"/\\\\\\[\\]:;\\|=,]"))
                        valid = true;
                    else
                    {
                        JOptionPane.showMessageDialog(create,
                                "Invalid filename\n\n" +
                                "Cannot use the following characters:\n" +
                                "* . \" / \\ [ ] : ; | = ,");
                        valid = false;
                    }
                    break;
                case 1:
                    JOptionPane.showMessageDialog(create,
                            "Invalid filename\n\n" +
                            "Missing a filename");
                    valid = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(create,
                            "Invalid filename\n\n" +
                            "Cannot use the following characters:\n" +
                            "* . \" / \\ [ ] : ; | = ,");
                    valid = false;
                    break;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(create,
                    "Invalid file name\n\n" +
                    "Must use the .csv extension");
            valid = false;
        }
        return valid;
    }
}
