/**
    Dialog enabling the addition of a reservable
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ReservableAddDialog extends JDialog
{
    // Fields
    private JButton add, exit;
    private JComboBox startYear, startMonth, startDay, startHour, startMinute,
                      endYear, endMonth, endDay, endHour, endMinute,
                      existingLocation;
    private JRadioButton newLocationRadio, existingLocationRadio;
    private JTextField location, capacity, cost;
    
    /**
        Constructor
    */
    
    public ReservableAddDialog()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Add Reservable");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 15, 5);
        gbc.anchor = GridBagConstraints.WEST;
        add(buildTopPanel(), gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buildMiddlePanel(), gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 0);
        add(buildBottomPanel(), gbc);
        
        pack();
    }
    
    /**
        Build & return the bottom panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panel.add(add = new JButton("Add"));
        panel.add(exit = new JButton("Exit"));
        
        return panel;
    }
    
    /**
        Build & return the panel containing the capacity
    
        @return The built panel
    */
    
    private JPanel buildCapacityPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(new JLabel("Capacity:"));
        panel.add(capacity = new JTextField(5));
        
        return panel;
    }
    
    /**
        Build & return the panel containing the cost
    
        @return The built panel
    */
    
    private JPanel buildCostPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(new JLabel("Cost:"));
        panel.add(cost = new JTextField(5));
        
        return panel;
    }
    
    /**
        Build & return the panel containing the dates
    
        @return The built panel
    */
    
    private JPanel buildDatePanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Build start date fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Start Date:"), gbc);
        
        gbc.gridx = 1;
        gbc.ipadx = 30;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(startYear = new JComboBox(), gbc);
        
        gbc.gridx = 2;
        gbc.ipadx = 15;
        panel.add(startMonth = new JComboBox(), gbc);
        
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(startDay = new JComboBox(), gbc);
        
        // Build end date fields
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("End Date:"), gbc);
        
        gbc.gridx = 1;
        gbc.ipadx = 30;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(endYear = new JComboBox(), gbc);
        
        gbc.gridx = 2;
        gbc.ipadx = 15;
        panel.add(endMonth = new JComboBox(), gbc);
        
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(endDay = new JComboBox(), gbc);
        
        return panel;
    }
    
    /**
        Build & return the panel containing the datetimes
    
        @return The build panel
    */
    
    private JPanel buildDateTimePanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        
        panel.add(buildDatePanel());
        panel.add(buildTimePanel());
        
        return panel;
    }
    
    /**
        Build & return the panel with reservable info besides datetimes
    
        @return The built panel
    */
    
    private JPanel buildInfoPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        
        panel.add(buildLocationPanel());
        panel.add(buildCapacityPanel());
        panel.add(buildCostPanel());
        
        return panel;
    }
    
    /**
        Build & return the panel containing the location
    
        @return The built panel
    */
    
    private JPanel buildLocationPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(new JLabel("Location:"));
        panel.add(location = new JTextField(10));
        
        return panel;
    }
    
    /**
        Build & return the middle panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildMiddlePanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Reservable Info"));
        
        panel.add(buildInfoPanel());
        panel.add(buildDateTimePanel());
        
        return panel;
    }
    
    /**
        Build & return the panel containing radio buttons
    
        @return The built panel
    */
    
    private JPanel buildRadioPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(newLocationRadio = new JRadioButton(), gbc);
        
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        panel.add(existingLocationRadio = new JRadioButton(), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 75;
        panel.add(existingLocation = new JComboBox(), gbc);
        
        newLocationRadio.setText("New Location");
        existingLocationRadio.setText("Existing Location");
        
        newLocationRadio.setSelected(true);
        
        existingLocation.setEnabled(false);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(newLocationRadio);
        bg.add(existingLocationRadio);
        
        return panel;
    }
    
    /**
        Build & return the panel containing the times
    
        @return The built panel
    */
    
    private JPanel buildTimePanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Build start time fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Start Time:"), gbc);
        
        gbc.gridx = 1;
        gbc.ipadx = 15;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(startHour = new JComboBox(), gbc);
        
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(startMinute = new JComboBox(), gbc);
        
        // Build end time fields
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("End Time:"), gbc);
        
        gbc.gridx = 1;
        gbc.ipadx = 15;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(endHour = new JComboBox(), gbc);
        
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(endMinute = new JComboBox(), gbc);
        
        return panel;
    }
    
    /**
        Build & return the top panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildTopPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panel.add(buildRadioPanel());
        
        return panel;
    }
    
    /**
        Get input capacity
    
        @return Input capacity
    */
    
    public String getCapacity()
    {
        return capacity.getText();
    }
    
    /**
        Get input cost
    
        @return Input cost
    */
    
    public String getCost()
    {
        return cost.getText();
    }
    
    /**
        Get input location
    
        @return Input location
    */

    public Location getInputLocation()
    {
        return new Location(location.getText(),
                Integer.parseInt(capacity.getText()));
    }
    
    /**
        Get input location name
    
        @return Input location name
    */
    
    public String getLocationName()
    {
        return location.getText();
    }
    
    /**
        Get the selected existing location
    
        @return The selected existing location
    */
    
    public Location getSelectedLocation()
    {
        return (Location) existingLocation.getSelectedItem();
    }
    
    /**
        Get input timeframe
    
        @return The input timeframe
    */
    
    public Timeframe getTimeframe()
    {
        int y = Integer.parseInt((String)startYear.getSelectedItem());
        int mon = Integer.parseInt((String)startMonth.getSelectedItem());
        int d = Integer.parseInt((String)startMinute.getSelectedItem());
        int h = Integer.parseInt((String)startHour.getSelectedItem());
        int m = Integer.parseInt((String)startMinute.getSelectedItem());
        
        LocalDateTime startDateTime = LocalDateTime.of(y, mon, d, h, m);
        
        y = Integer.parseInt((String)endYear.getSelectedItem());
        mon = Integer.parseInt((String)endMonth.getSelectedItem());
        d = Integer.parseInt((String)endMinute.getSelectedItem());
        h = Integer.parseInt((String)endHour.getSelectedItem());
        m = Integer.parseInt((String)endMinute.getSelectedItem());
        
        LocalDateTime endDateTime = LocalDateTime.of(y, mon, d, h, m);
        
        BigDecimal c = new BigDecimal(cost.getText());
        
        return new Timeframe(startDateTime, endDateTime, c);
    }
    
    /**
        Return if existing location radio button is selected
    
        @return If existing location radio button is selected
    */
    
    public boolean isExistingLocationRadioSelected()
    {
        return existingLocationRadio.isSelected();
    }
    
    /**
        Register a controller to the buttons on the dialog
    
        @param controller The controller to register to the buttons
    */
    
    public void registerButtonController(ActionListener controller)
    {
        add.addActionListener(controller);
        exit.addActionListener(controller);
    }
    
    /**
        Register a controller to the existing location combo box on the dialog
    
        @param controller The controller to register to the existing location
                          combo box
    */
    
    public void registerComboBoxController(ActionListener controller)
    {
        existingLocation.addActionListener(controller);
    }
    
    /**
        Register a controller to the radio buttons on the dialog
    
        @param controller The controller to register to the radio buttons
    */
    
    public void registerRadioButtonController(ActionListener controller)
    {
        newLocationRadio.addActionListener(controller);
        existingLocationRadio.addActionListener(controller);
    }
    
    /**
        Set the text of the capacity text field
    
        @param text Text of capacity text field
    */
    
    public void setCapacity(String text)
    {
        capacity.setText(text);
    }
    
    /**
        Set whether the capacity text field is enabled
    
        @param enabled Whether the capacity text field is enabled
    */
    
    public void setCapacityEnabled(boolean enabled)
    {
        capacity.setEnabled(enabled);
    }
    
    /**
        Set the existing locations available to choose from
    
        @param locs Locations available to choose from
    */
    
    public void setExistingLocations(Location[] locs)
    {
        existingLocation.removeAllItems();
        for (Location loc : locs)
            existingLocation.addItem(loc);
    }
    
    /**
        Set whether the existing location combo box is enabled
    
        @param enabled Whether the existing location combo box is enabled
    */
    
    public void setExistingLocationEnabled(boolean enabled)
    {
        existingLocation.setEnabled(enabled);
    }
    
    /**
        Set the text of the location text field
    
        @param text Text of location text field
    */
    
    public void setLocation(String text)
    {
        location.setText(text);
    }
    
    /**
        Set whether the location text field is enabled
    
        @param enabled Whether the location text field is enabled
    */
    
    public void setLocationEnabled(boolean enabled)
    {
        location.setEnabled(enabled);
    }
}