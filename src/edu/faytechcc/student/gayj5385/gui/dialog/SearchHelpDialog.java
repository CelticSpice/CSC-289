/**
 * A dialog to assist a user with searching
 * 
 * @author Shane McCann
 */
package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchHelpDialog extends JDialog
{
    // Fields
    DefaultListModel keys;
    JLabel info, acceptedKeys;
    JList keyList;
    JButton okay;
    
    /**
     * Constructor
     * @param reservation Whether it is a reservation being search or not
     */
    public SearchHelpDialog(boolean reservation)
    {
        setLayout(new BorderLayout());
        setTitle("Search Help");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildInfoPanel(), BorderLayout.EAST);
        add(buildKeyListPanel(reservation), BorderLayout.WEST);
        add(okay = new JButton("Okay"), BorderLayout.SOUTH);
        
        ButtonController controller = new ButtonController();
        okay.addActionListener(controller);
        
        setSize(475, 300);
    }
    
    /**
     * Build the panel that displays help information
     * @return A panel that displays help information
     */
    private JPanel buildInfoPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 15, 15, 15);
        
        info = new JLabel();
        
        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setPreferredSize(new Dimension(285, 175));
        
        gbc.gridy = 1;        
        gbc.insets = new Insets(7, 15, 15, 25);
        
        panel.add(scrollPane, gbc);
        
        return panel;
    }
    
    /**
     * Build the list that displays help options
     * 
     * @param reservation Whether it is a reservation search or not
     * @return A panel containing the list
     */
    private JPanel buildKeyListPanel(boolean reservation)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        keyList = new JList(keys = new DefaultListModel());
        keyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        keys.addElement("General");
        keys.addElement("Location");
        keys.addElement("Date and Time");
        keys.addElement("Cost");
        
        if (reservation)
            keys.addElement("Reserver");
        
        JScrollPane scrollPane = new JScrollPane(keyList);
        scrollPane.setPreferredSize(new Dimension(125, 175));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 15, 15, 15);
        
        panel.add(scrollPane, gbc);
        
        ListController controller = new ListController(reservation);
        keyList.addListSelectionListener(controller);
        keyList.setSelectedIndex(0);
        
        return panel;
    }
    
    /**
     * A button controller
     */
    private class ButtonController implements ActionListener
    {
        /**
         * Constructs a new ButtonController
         */
        public ButtonController(){}
        
        /**
         * Performs an action on a button being clicked
         * 
         * @param e The ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }
    
    /**
     * A list controller
     */
    private class ListController implements ListSelectionListener
    {
        // Fields
        private boolean reservation;
        
        /**
         * Constructs a new ListController
         * @param r 
         */
        public ListController(boolean r)
        {
            reservation = r;
        }
        
        /**
         * Displays help text pertaining to the item selected
         * 
         * @param e ActionEvent 
         */
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            switch ((String)keyList.getSelectedValue())
            {
                case "General":
                    info.setText("<html>" +
                     "<b>General</b><br>" +
                     "<br>" +
                     "To perform a search, type the keyword followed by<br>" +
                     "two colons and finally the value that you are<br>" +
                     "searching for. If you are searching for multiple<br>" +
                     "values, enter a semi-colon after each value.<br>" +
                     "<br>" +
                     "<b>Example:</b><br>" +
                     "StartDate :: 2017-01-01; StartTime :: 15:00");
                    break;
                case "Location":
                    info.setText("<html>" +
                        "<b>Location</b><br>" +
                        "<br>" +
                        "To search by location name, type one the<br>" +
                        "following keywords:<br>" +
                        "<br>" +
                        "LocationName<br>" +
                        "Location<br>" +
                        "Loc<br>" +
                        "<br>" +
                        "Enter the name of the location for the value.<br>" +
                        "<br>" +
                        "<b>Example:</b><br>" +
                        "LocationName :: Cabin<br>");
                    if (!reservation)
                    {
                        info.setText(info.getText() + "<br>" +
                        "You can also search for locations based on<br>" +
                        "their capacity using one of the following<br>" +
                        "keywords:<br>" +
                        "<br>" +
                        "Capacity<br>" +
                        "Cap<br>" +
                        "<br>" +
                        "Enter >, &lt, >=, &lt=, or = followed by the<br>"+
                        "capacity you are searching for.<br>" +
                        "<br>" +
                        "<b>Example:</b><br>" +
                        "Capacity :: >= 15");
                        // Must use html escape characters for '>'
                     }
                    break;
                case "Date and Time":
                    info.setText("<html>" +
                        "<b>Date and Time</b><br>" +
                        "<br>" +
                        "To search by date, use one of these keywords:<br>" +
                        "<br>" +
                        "StartDate<br>" +
                        "StartTime<br>" +
                        "EndDate<br>" +
                        "EndTime<br>" +
                        "<br>" +
                        "Enter the date in the format of yyyy-mm-dd.<br>" +
                        "Enter the time in the format of hh:mm in the<br>" +
                        "24-hour clock format.<br>" +
                        "<br>" +
                        "<b>Example:</b><br>" +
                        "StartDate :: 2017-01-01; StartTime :: 15:00");
                    break;
                case "Cost":
                    info.setText("<html>" +
                        "<b>Cost</b><br>" +
                        "<br>" +
                        "To search by cost, use these keywords:<br>" +
                        "<br>" +
                        "Cost<br>" +
                        "Price<br>" +
                        "<br>" +
                        "Enter the dollar value is being searched for.<br>" +
                        "<b>You must include the amount of cents.</b><br>" +
                        "<br>" +
                        "<b>Example:</b><br>" +
                        "Cost :: 70.00");
                    break;
                case "Reserver":
                    info.setText("<html>" +
                        "<b>Reserver</b><br>" +
                        "<br>" +
                        "To search by a reserver, use these keywords:<br>" +
                        "<br>" +
                        "FirstName<br>" +
                        "First<br>" +
                        "LastName<br>" +
                        "Last<br>" +
                        "EmailAddress<br>" +
                        "Email<br>" +
                        "PhoneNumber<br>" +
                        "Phone<br>" +
                        "<br>" +
                        "For the first and last names and the<br>" +
                        "email address, enter it as it is. For<br>" +
                        "the phone number enter just the numbers.<br>" +
                        "<br>" +
                        "<b>Example:</b><br>" +
                        "PhoneNumber :: 5551234567");
                    break;
            }
        }
    }
}
