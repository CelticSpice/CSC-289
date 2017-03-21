/**
    Panel enabling the administrator to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ManageReservationPanel extends JPanel
{
    // Fields
    private DefaultListModel<Reservation> reservations;
    private JButton searchBtn, update, contact, approve, cancel, logout;
    private JComboBox<Location> locations;
    private JList<Reservation> reservationList;
    private JTextField capacity, search, startDate, startTime, endDate, endTime,
                       cost, attendees, event, contactName, contactEmail,
                       contactPhone;
    
    /**
        Constructor
    */
    
    public ManageReservationPanel()
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
        btnPanel.add(update = new JButton("Update"));
        btnPanel.add(contact = new JButton("Contact"));
        btnPanel.add(approve = new JButton("Approve"));
        btnPanel.add(cancel = new JButton("Cancel"));
        
        // Build logout button panel
        JPanel logoutBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        logoutBtnPanel.add(logout = new JButton("Logout"));
        
        panel.add(btnPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(logoutBtnPanel);
        
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
        
        reservationList = new JList(reservations = new DefaultListModel());
        JScrollPane scrollPane = new JScrollPane(reservationList);
        scrollPane.setPreferredSize(new Dimension(255, 225));
        
        // Build reservation detail panels
        JPanel reservationPanel1 = new JPanel(new GridLayout(5, 2, 5, 10));        
        reservationPanel1.add(new JLabel("Start Date:"));
        reservationPanel1.add(startDate = new JTextField());
        reservationPanel1.add(new JLabel("Start Time:"));
        reservationPanel1.add(startTime = new JTextField());
        reservationPanel1.add(new JLabel("End Date:"));
        reservationPanel1.add(endDate = new JTextField());
        reservationPanel1.add(new JLabel("End Time:"));
        reservationPanel1.add(endTime = new JTextField());
        reservationPanel1.add(new JLabel("Cost:"));
        reservationPanel1.add(cost = new JTextField());
        
        JPanel reservationPanel2 = new JPanel(new GridLayout(5, 2, 5, 10));
        reservationPanel2.add(new JLabel("Attendees:"));
        reservationPanel2.add(attendees = new JTextField());
        reservationPanel2.add(new JLabel("Event:"));
        reservationPanel2.add(event = new JTextField());
        reservationPanel2.add(new JLabel("Contact Name:"));
        reservationPanel2.add(contactName = new JTextField());
        reservationPanel2.add(new JLabel("Contact Email:"));
        reservationPanel2.add(contactEmail = new JTextField());
        reservationPanel2.add(new JLabel("Contact Phone:"));
        reservationPanel2.add(contactPhone = new JTextField());
        
        startDate.setEditable(false);
        startTime.setEditable(false);
        endDate.setEditable(false);
        endTime.setEditable(false);
        cost.setEditable(false);
        attendees.setEditable(false);
        event.setEditable(false);
        contactName.setEditable(false);
        contactEmail.setEditable(false);
        contactPhone.setEditable(false);
        
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(15, 0)));
        panel.add(reservationPanel1);
        panel.add(Box.createRigidArea(new Dimension(30, 0)));
        panel.add(reservationPanel2);
        
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
}