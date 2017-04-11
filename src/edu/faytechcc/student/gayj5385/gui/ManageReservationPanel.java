/**
    Panel enabling the administrator to manage reservations
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import edu.faytechcc.student.gayj5385.gui.renderer.ReservationRenderer;
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
    private JButton searchBtn, clear, update, contact, reviewed, cancel;
    private JComboBox<Location> locations;
    private JList<Reservation> reservationList;
    private JTextField capacity, search, startDate, startTime, endDate, endTime,
                       cost, attendees, event, contactName, contactEmail,
                       contactPhone;
    
    /**
        Constructs a new ManageReservationPanel
    */
    
    public ManageReservationPanel()
    {
        super(new BorderLayout());
        
        
        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildMidPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }
    
    /**
        Builds & return the bottom panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(update = new JButton("Update"));
        panel.add(contact = new JButton("Contact"));
        panel.add(reviewed = new JButton("Reviewed"));
        panel.add(cancel = new JButton("Cancel"));
        
        return panel;
    }
    
    /**
        Builds & return the middle panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildMidPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        reservationList = new JList(reservations = new DefaultListModel());
        reservationList.setCellRenderer(new ReservationRenderer());
        JScrollPane scrollPane = new JScrollPane(reservationList);
        scrollPane.setPreferredSize(new Dimension(255, 225));
        
        // Build reservation detail panels
        JPanel reservationDetailPanel1 = new JPanel(new GridLayout(5, 2, 0, 5));        
        reservationDetailPanel1.add(new JLabel("Start Date:"));
        reservationDetailPanel1.add(startDate = new JTextField(7));
        reservationDetailPanel1.add(new JLabel("Start Time:"));
        reservationDetailPanel1.add(startTime = new JTextField(7));
        reservationDetailPanel1.add(new JLabel("End Date:"));
        reservationDetailPanel1.add(endDate = new JTextField(7));
        reservationDetailPanel1.add(new JLabel("End Time:"));
        reservationDetailPanel1.add(endTime = new JTextField(7));
        reservationDetailPanel1.add(new JLabel("Cost:"));
        reservationDetailPanel1.add(cost = new JTextField(7));
        
        JPanel reservationDetailPanel2 = new JPanel(new GridLayout(5, 2, 0, 5));
        reservationDetailPanel2.add(new JLabel("Attendees:"));
        reservationDetailPanel2.add(attendees = new JTextField(15));
        reservationDetailPanel2.add(new JLabel("Event:"));
        reservationDetailPanel2.add(event = new JTextField(15));
        reservationDetailPanel2.add(new JLabel("Contact Name:"));
        reservationDetailPanel2.add(contactName = new JTextField(15));
        reservationDetailPanel2.add(new JLabel("Contact Email:"));
        reservationDetailPanel2.add(contactEmail = new JTextField(15));
        reservationDetailPanel2.add(new JLabel("Contact Phone:"));
        reservationDetailPanel2.add(contactPhone = new JTextField(15));
        
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
        panel.add(reservationDetailPanel1);
        panel.add(Box.createRigidArea(new Dimension(30, 0)));
        panel.add(reservationDetailPanel2);
        
        return panel;
    }
    
    /**
        Builds & returns the top panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildTopPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Build location selection panel
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JPanel locationComponentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 5);
        locationComponentPanel.add(new JLabel("Location:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.ipadx = 125;
        locationComponentPanel.add(locations = new JComboBox(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.ipadx = 0;
        locationComponentPanel.add(new JLabel("Capacity:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        locationComponentPanel.add(capacity = new JTextField(5), gbc);
        
        capacity.setEditable(false);
        
        locationPanel.add(locationComponentPanel);
        
        // Build search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JPanel searchComponentPanel = new JPanel(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        searchComponentPanel.add(search = new JTextField(15), gbc);
        
        JPanel searchButtonPanel = new JPanel();
        
        searchButtonPanel.add(searchBtn = new JButton("Search"));
        searchButtonPanel.add(clear = new JButton("Clear"));
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        searchComponentPanel.add(searchButtonPanel, gbc);
        
        searchPanel.add(searchComponentPanel);
                
        panel.add(locationPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(searchPanel);
        
        return panel;
    }
    
    public void clearSearch()
    {
        search.setText(null);
    }
    
    /**
     * Returns the search criteria in the search text field
     * 
     * @return search criteria
     */
    public String getSearchCriteria()
    {
        return search.getText();
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
        searchBtn.addActionListener(controller);
        clear.addActionListener(controller);
        update.addActionListener(controller);
        contact.addActionListener(controller);
        reviewed.addActionListener(controller);
        cancel.addActionListener(controller);
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
        locations.removeAllItems();
        for (Location loc : locs)
            locations.addItem(loc);
    }
    
    /**
        Set the reservations displayed in the list
    
        @param reserves Reservations to display in the list
    */
    
    public void setReservations(List<Reservation> reserves)
    {
        reservations.removeAllElements();
        if (reserves != null)
            for (Reservation reservation : reserves)
                reservations.addElement(reservation);
    }
    
    /**
        Sets the text of the reviewed button
    
        @param text Text to set
    */
    
    public void setReviewedButtonText(String text)
    {
        reviewed.setText(text);
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