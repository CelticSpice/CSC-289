/**
 * Enables an admin user to create a report of Reservables
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CreateReportDialog extends JDialog
{
    // Field(s)
    private JButton create, cancel;
    private JCheckBox locationName, capacity, startDate, endDate, startTime,
            endTime, cost, firstName, lastName, emailAddress, phoneNumber;
    private JLabel instructionLabel, fileNameLabel;
    private JTextField fileNameField;
    
    /**
     * Constructs a new CreateReportDialog
     * 
     * @param reservation Whether the parent view is the ManageReservationPanel
     *        or not
     */
    public CreateReportDialog(boolean reservation)
    {
        setLayout(new BorderLayout());
        setTitle("Create A Report");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildCheckBoxPanel(reservation), BorderLayout.NORTH);
        
        setSize(450, 300);
        setVisible(true);
    }
    
    private JPanel buildCheckBoxPanel(boolean reservation)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 7, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(instructionLabel = new JLabel("Select the fields you would " +
                "like to include in the report:"), gbc);
        
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
        
        if (reservation)
        {
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
        }        
        return panel;
    }
    
    private class CheckBoxListener implements ChangeListener
    {
        public CheckBoxListener(){}
        
        @Override
        public void stateChanged(ChangeEvent e){}
    }
}
