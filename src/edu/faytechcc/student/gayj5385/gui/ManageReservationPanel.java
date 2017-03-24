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
import java.awt.event.ActionListener;
import java.util.List;
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
import javax.swing.event.ListSelectionListener;

public class ManageReservationPanel extends JPanel
{
    // Fields
    private DefaultListModel<Reservation> reservations;
    private JButton searchBtn, update, contact, reviewed, cancel, logout;
    private JComboBox<Location> locations;
    private JList<Reservation> reservationList;
    private JTextField capacity, search, startDate, startTime, endDate, endTime,
                       cost, attendees, event, contactName, contactEmail,
                       contactPhone;
    
    /**
        Constructs a new ManageReservationPanel initialized with the given
        list of locations
    
        @param locs The locations
    */
    
    public ManageReservationPanel(List<Location> locs)
    {
        super(new BorderLayout());
        
        Location[] locArray = locs.toArray(new Location[locs.size()]);
        add(buildTopPanel(locArray), BorderLayout.NORTH);
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
        btnPanel.add(reviewed = new JButton("Reviewed"));
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
        Builds & return the top panel of this panel, initialized with the
        given array of locations
    
        @param locs The locations
        @return The built panel
    */
    
    private JPanel buildTopPanel(Location[] locs)
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
        locationComponentPanel.add(locations = new JComboBox(locs), gbc);
        
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
        Return the selected location
    
        @return The selected location
    */
    
    public Location getSelectedLocation()
    {
        return (Location) locations.getSelectedItem();
    }
    
    /**
        Returns the selected reservations
    
        @return Selected reservations
    */
    
    public List<Reservation> getSelectedReservations()
    {
        return reservationList.getSelectedValuesList();
    }
    
    /**
        Register a button controller to the panel
    
        @param controller The controller to register to the buttons on the panel
    */
    
    public void registerButtonController(ActionListener controller)
    {
        update.addActionListener(controller);
        contact.addActionListener(controller);
        reviewed.addActionListener(controller);
        cancel.addActionListener(controller);
        logout.addActionListener(controller);
        searchBtn.addActionListener(controller);
    }
    
    /**
        Registers a controller to the locations combo box
    
        @param controller The controller to register to the locations combo box
    */
    
    public void registerComboBoxController(ActionListener controller)
    {
        locations.addActionListener(controller);
    }
    
    /**
        Registers a controller to the reservation list
    
        @param controller The controller to register to the reservation list
    */
    
    public void registerReservationListController(
            ListSelectionListener controller)
    {
        reservationList.addListSelectionListener(controller);
    }
    
    /**
        Sets the attendees field
    
        @param attend The attendees to display
    */
    
    public void setAttendees(String attend)
    {
        attendees.setText(attend);
    }
    
    /**
        Set the capacity of the selected location
    
        @param cap Capacity to display
    */
    
    public void setCapacity(String cap)
    {
        capacity.setText(cap);
    }
    
    /**
        Set contact email
    
        @param email Contact email
    */
    
    public void setEmail(String email)
    {
        contactEmail.setText(email);
    }
    
    /**
        Set contact name
    
        @param name Contact name to set
    */
    
    public void setContactName(String name)
    {
        contactName.setText(name);
    }
    
    /**
        Set contact phone
    
        @param phone Contact phone
    */
    
    public void setPhone(String phone)
    {
        contactPhone.setText(phone);
    }
    
    /**
        Set the cost field
    
        @param c Cost to set in the cost field
    */
    
    public void setCost(String c)
    {
        cost.setText(c);
    }
    
    /**
        Set the end date field
    
        @param date Date to set in the end date field
    */
    
    public void setEndDate(String date)
    {
        endDate.setText(date);
    }
    
    /**
        Set the end time field
    
        @param time Time to set in the end time field
    */
    
    public void setEndTime(String time)
    {
        endTime.setText(time);
    }
    
    /**
        Sets the event field
    
        @param e The event to set
    */
    
    public void setEvent(String e)
    {
        event.setText(e);
    }
    
    /**
        Set the locations that have been reserved
    
        @param locs Locations that have been reserved
    */
    
    public void setLocations(List<Location> locs)
    {
        ActionListener[] als = locations.getActionListeners();
        for (ActionListener al : als)
            locations.removeActionListener(al);
        
        locations.removeAllItems();
        for (Location loc : locs)
            locations.addItem(loc);
        
        for (ActionListener al : als)
            locations.addActionListener(al);
    }
    
    /**
        Set the reservations displayed in the list
    
        @param reserves Reservations to display in the list
    */
    
    public void setReservations(List<Reservation> reserves)
    {
        reservations.removeAllElements();
        for (Reservation reservation : reserves)
            reservations.addElement(reservation);
    }
    
    /**
        Set the start date field
    
        @param date Date to set in the start date field
    */
    
    public void setStartDate(String date)
    {
        startDate.setText(date);
    }
    
    /**
        Set the start time field
    
        @param time Time to set in the start time field
    */
    
    public void setStartTime(String time)
    {
        startTime.setText(time);
    }
}