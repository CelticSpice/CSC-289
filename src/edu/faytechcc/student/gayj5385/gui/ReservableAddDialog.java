/**
    Dialog enabling the addition of a reservable
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
    private boolean recordsAdded;
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
        
        recordsAdded = false;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;
        add(buildTopPanel(), gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 15, 15, 15);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buildMiddlePanel(), gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 15, 0, 15);
        add(buildBottomPanel(), gbc);
        
        setSize(645, 335);
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
        
        startYear.setActionCommand("Start Year");
        startMonth.setActionCommand("Start Month");
        startDay.setActionCommand("Start Day");
        endYear.setActionCommand("End Year");
        endMonth.setActionCommand("End Month");
        endDay.setActionCommand("End Day");
        
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
        DefaultComboBoxModel<Location> model = new DefaultComboBoxModel<>();
        panel.add(existingLocation = new JComboBox(model), gbc);
                
        newLocationRadio.setText("New Location");
        existingLocationRadio.setText("Existing Location");
        
        newLocationRadio.setSelected(true);
        
        existingLocation.setEnabled(false);
        existingLocation.setActionCommand("Existing Location");
        
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
        
        startHour.setActionCommand("Start Hour");
        startMinute.setActionCommand("Start Minute");
        endHour.setActionCommand("End Hour");
        endMinute.setActionCommand("End Minute");
        
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
        Get input end day
    
        @return Input end day
    */
    
    public Integer getEndDay()
    {
        return (Integer) endDay.getSelectedItem();
    }
    
    /**
        Get input end hour
    
        @return Input end hour
    */
    
    public Integer getEndHour()
    {
        return (Integer) endHour.getSelectedItem();
    }
    
    /**
        Get input end minute
    
        @return Input end minute
    */
    
    public Integer getEndMinute()
    {
        return (Integer) endMinute.getSelectedItem();
    }
    
    /**
        Get input end month
    
        @return Input end month
    */
    
    public Integer getEndMonth()
    {
        return (Integer) endMonth.getSelectedItem();
    }
    
    /**
        Get input end year
    
        @return Input end year
    */
    
    public Integer getEndYear()
    {
        return (Integer) endYear.getSelectedItem();
    }
    
    /**
        Return the set existing locations
    
        @return The set existing locations
    */
    
    public Location[] getExistingLocations()
    {
        Location[] locs = new Location[existingLocation.getItemCount()];
        for (int i = 0; i < locs.length; i++)
            locs[i] = (Location) existingLocation.getItemAt(i);
        return locs;
    }
    
    /**
        Return if records have been added
    
        @return If records have been added
    */
    
    public boolean getIfRecordsAdded()
    {
        return recordsAdded;
    }
    
    /**
        Get input location
    
        @return Input location
    */

    public String getInputLocation()
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
        Get input start day
    
        @return Input start day
    */
    
    public Integer getStartDay()
    {
        return (Integer) startDay.getSelectedItem();
    }
    
    /**
        Get input start hour
    
        @return Input start hour
    */
    
    public Integer getStartHour()
    {
        return (Integer) startHour.getSelectedItem();
    }
    
    /**
        Get input start minute
    
        @return Input start minute
    */
    
    public Integer getStartMinute()
    {
        return (Integer) startMinute.getSelectedItem();
    }
    
    /**
        Get input start month
    
        @return Input start month
    */
    
    public Integer getStartMonth()
    {
        return (Integer) startMonth.getSelectedItem();
    }
    
    /**
        Get input start year
    
        @return Input start year
    */
    
    public Integer getStartYear()
    {
       return (Integer) startYear.getSelectedItem();
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
        Register a controller to the combo boxes on the dialog
    
        @param controller The controller to register to the combo boxes
    */
    
    public void registerComboBoxController(ActionListener controller)
    {
        existingLocation.addActionListener(controller);
        startYear.addActionListener(controller);
        startMonth.addActionListener(controller);
        startDay.addActionListener(controller);
        startHour.addActionListener(controller);
        startMinute.addActionListener(controller);
        endYear.addActionListener(controller);
        endMonth.addActionListener(controller);
        endDay.addActionListener(controller);
        endHour.addActionListener(controller);
        endMinute.addActionListener(controller);
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
        Set available end days to choose from
    
        @param days Available days to choose from
    */
    
    public void setEndDays(int[] days)
    {
        ActionListener[] als = endDay.getActionListeners();
        for (ActionListener al : als)
            endDay.removeActionListener(al);
        
        endDay.removeAllItems();
        for (int day : days)
            endDay.addItem(day);
        
        for (ActionListener al : als)
            endDay.addActionListener(al);
    }
    
    /**
        Set available end hours to choose from
    
        @param hours Available hours to choose from
    */
    
    public void setEndHours(int[] hours)
    {
        ActionListener[] als = endHour.getActionListeners();
        for (ActionListener al : als)
            endHour.removeActionListener(al);
        
        endHour.removeAllItems();
        for (int hour : hours)
            endHour.addItem(hour);
        
        for (ActionListener al : als)
            endHour.addActionListener(al);
    }
    
    /**
        Set available end minutes to choose from
    
        @param minutes Available minutes to choose from
    */
    
    public void setEndMinutes(int[] minutes)
    {
        ActionListener[] als = endMinute.getActionListeners();
        for (ActionListener al : als)
            endMinute.removeActionListener(al);
        
       endMinute.removeAllItems(); 
       for (int minute : minutes)
           endMinute.addItem(minute);
       
       for (ActionListener al : als)
           endMinute.addActionListener(al);
    }
    
    /**
        Set available end months to choose from
    
        @param months Available months to choose from
    */
    
    public void setEndMonths(int[] months)
    {
        ActionListener[] als = endMonth.getActionListeners();
        for (ActionListener al : als)
            endMonth.removeActionListener(al);
        
       endMonth.removeAllItems(); 
       for (int month : months)
           endMonth.addItem(month);
       
       for (ActionListener al : als)
           endMonth.addActionListener(al);
    }
    
    /**
        Set available end years to choose from
    
        @param years Available years to choose from
    */
    
    public void setEndYears(int[] years)
    {
       ActionListener[] als = endYear.getActionListeners();
        for (ActionListener al : als)
            endYear.removeActionListener(al);
        
       endYear.removeAllItems(); 
       for (int year : years)
           endYear.addItem(year);
       
       for (ActionListener al : als)
           endYear.addActionListener(al);
    }
    
    /**
        Set the existing locations available to choose from
    
        @param locs Locations available to choose from
    */
    
    public void setExistingLocations(Location[] locs)
    {
        ActionListener[] als = existingLocation.getActionListeners();
        for (ActionListener al : als)
            existingLocation.removeActionListener(al);
        
        existingLocation.removeAllItems();
        for (Location loc : locs)
            existingLocation.addItem(loc);
        
        for (ActionListener al : als)
           existingLocation.addActionListener(al);
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
        Set if records have been added
    
        @param added Whether records have been added
    */
    
    public void setIfRecordsAdded(boolean added)
    {
        recordsAdded = added;
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
    
    /**
        Set available start days to choose from
    
        @param days Available days to choose from
    */
    
    public void setStartDays(int[] days)
    {
        ActionListener[] als = startDay.getActionListeners();
        for (ActionListener al : als)
            startDay.removeActionListener(al);
        
       startDay.removeAllItems(); 
       for (int day : days)
           startDay.addItem(day);
       
       for (ActionListener al : als)
           startDay.addActionListener(al);
    }
    
    /**
        Set available start hours to choose from
    
        @param hours Available hours to choose from
    */
    
    public void setStartHours(int[] hours)
    {
        ActionListener[] als = startHour.getActionListeners();
        for (ActionListener al : als)
            startHour.removeActionListener(al);
        
       startHour.removeAllItems(); 
       for (int hour : hours)
           startHour.addItem(hour);
       
       for (ActionListener al : als)
           startHour.addActionListener(al);
    }
    
    /**
        Set available start minutes to choose from
    
        @param minutes Available minutes to choose from
    */
    
    public void setStartMinutes(int[] minutes)
    {
        ActionListener[] als = startMinute.getActionListeners();
        for (ActionListener al : als)
            startMinute.removeActionListener(al);
        
       startMinute.removeAllItems(); 
       for (int minute : minutes)
           startMinute.addItem(minute);
       
       for (ActionListener al : als)
           startMinute.addActionListener(al);
    }
    
    /**
        Set available start months to choose from
    
        @param months Available months to choose from
    */
    
    public void setStartMonths(int[] months)
    {
        ActionListener[] als = startMonth.getActionListeners();
        for (ActionListener al : als)
            startMonth.removeActionListener(al);
        
       startMonth.removeAllItems(); 
       for (int month : months)
           startMonth.addItem(month);
       
       for (ActionListener al : als)
           startMonth.addActionListener(al);
    }
    
    /**
        Set available start years to choose from
    
        @param years Available years to choose from
    */
    
    public void setStartYears(int[] years)
    {
        ActionListener[] als = startYear.getActionListeners();
        for (ActionListener al : als)
            startYear.removeActionListener(al);
        
       startYear.removeAllItems(); 
       for (int year : years)
           startYear.addItem(year);
       
       for (ActionListener al : als)
           startYear.addActionListener(al);
    }
}