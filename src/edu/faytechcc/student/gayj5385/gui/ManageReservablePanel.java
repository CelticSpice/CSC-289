/**
    Admin panel with components enabling the administrator to manage
    reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ManageReservablePanel extends JPanel
{
    // Fields
    private JButton addBtn, modBtn, delBtn, exitBtn, searchBtn;
    private JComboBox locations;
    private JList timeframeList;
    private JTextField capacity, search, startDate, startTime, endDate, endTime,
                       cost, reserved;
    
    /**
        Constructor
    */
    
    public ManageReservablePanel()
    {
        super(new BorderLayout());
        
        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildMidPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }
    
    /**
        Build & return the bottom panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Build main button panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        btnPanel.add(addBtn = new JButton("Add"));
        btnPanel.add(modBtn = new JButton("Modify"));
        btnPanel.add(delBtn = new JButton("Delete"));
        
        // Build exit button panel
        JPanel exitBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        exitBtnPanel.add(exitBtn = new JButton("Exit"));
        
        panel.add(btnPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(exitBtnPanel);
        
        return panel;
    }
    
    /**
        Build & return the middle panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildMidPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        timeframeList = new JList();
        JScrollPane scrollPane = new JScrollPane(timeframeList);
        scrollPane.setPreferredSize(new Dimension(225, 225));
        
        // Build timeframe detail panel
        JPanel timeframePanel = new JPanel(new GridLayout(6, 2, 5, 10));        
        timeframePanel.add(new JLabel("Start Date:"));
        timeframePanel.add(startDate = new JTextField());
        timeframePanel.add(new JLabel("Start Time:"));
        timeframePanel.add(startTime = new JTextField());
        timeframePanel.add(new JLabel("End Date:"));
        timeframePanel.add(endDate = new JTextField());
        timeframePanel.add(new JLabel("End Time:"));
        timeframePanel.add(endTime = new JTextField());
        timeframePanel.add(new JLabel("Cost:"));
        timeframePanel.add(cost = new JTextField());
        timeframePanel.add(new JLabel("Reserved:"));
        timeframePanel.add(reserved = new JTextField());
        
        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        cost.setEditable(false);
        reserved.setEditable(false);
        
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(15, 0)));
        panel.add(timeframePanel);
        
        return panel;
    }
    
    /**
        Build & return the top panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildTopPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Build location selection panel
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        
        JPanel locationComponentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 5);
        locationComponentPanel.add(new JLabel("Location:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.ipadx = 125;
        locationComponentPanel.add(locations = new JComboBox(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.ipadx = 0;
        locationComponentPanel.add(new JLabel("Capacity:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 55;
        gbc.anchor = GridBagConstraints.WEST;
        locationComponentPanel.add(capacity = new JTextField(), gbc);
        
        capacity.setEditable(false);
        
        locationPanel.add(locationComponentPanel);
        
        // Build search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        
        JPanel searchComponentPanel = new JPanel(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.ipadx = 225;
        gbc.ipady = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        searchComponentPanel.add(search = new JTextField(), gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;
        searchComponentPanel.add(searchBtn = new JButton("Search"), gbc);
        
        searchPanel.add(searchComponentPanel);
                
        panel.add(locationPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(searchPanel);
        
        return panel;
    }
    
    /**
        Register a button controller to the panel
    
        @param controller The controller to register to the buttons on the panel
    */
    
    public void registerButtonController(ActionListener controller)
    {
        addBtn.addActionListener(controller);
        modBtn.addActionListener(controller);
        delBtn.addActionListener(controller);
        exitBtn.addActionListener(controller);
        searchBtn.addActionListener(controller);
    }
}