/**
    Dialog for updating a reservable
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.mccanns0131.database.LocationSQLDAO;
import edu.faytechcc.student.mccanns0131.database.ReservableSQLDAO;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private NumberFormat moneyFmt;
    
    /**
        Constructs a new UpdateReservableDialog
    
        @param reservable Reservable to be updated
    */
    
    public UpdateReservableDialog(Reservable reservable)
    {
        moneyFmt = NumberFormat.getCurrencyInstance();
        
        setLayout(new BorderLayout());
        setTitle("Update Reservable");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildMainPanel(reservable), BorderLayout.CENTER);
        add(buildButtonPanel(reservable), BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }
    
    /**
        Builds & returns the button panel of the dialog
    
        @param reservable The reservable to be updated
        @return The built panel
    */
    
    private JPanel buildButtonPanel(Reservable reservable)
    {
        JPanel panel = new JPanel();
        
        panel.add(update = new JButton("Update"));
        panel.add(exit = new JButton("Exit"));
        
        ButtonController controller = new ButtonController(reservable);
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
        
        /**
            Constructs a new ButtonController
        
            @param reservable Reservable to be updated
        */
        
        public ButtonController(Reservable reservable)
        {
            this.reservable = reservable;
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
                    boolean nameOrCapacityChanged = nameOrCapacityChanged();
                    boolean proceed = true;
                    if (nameOrCapacityChanged)
                    {
                        int choice = JOptionPane.showConfirmDialog(null,
                                "Changing reservable location name or " +
                                "capacity - Continue?", "Confirm",
                                JOptionPane.YES_NO_OPTION);
                        
                        if (choice == JOptionPane.NO_OPTION)
                            proceed = false;
                    }
                    
                    if (proceed)
                    {
                        try
                        {
                            if (nameOrCapacityChanged)
                            {
                                LocationSQLDAO locDAO = new LocationSQLDAO();
                                locDAO.updateLocation(reservable.getLocation());
                                locDAO.close();
                            }
                            
                            ReservableSQLDAO resDAO = new ReservableSQLDAO();
                            resDAO.updateReservable(reservable);
                            resDAO.close();
                            
                            JOptionPane.showMessageDialog(null,
                                    "Reservable updated");
                            
                            updateModel(reservable, nameOrCapacityChanged);
                        }
                        catch (SQLException ex)
                        {
                            JOptionPane.showMessageDialog(null,
                                    "Error updating reservable", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            else
                dispose();
        }
        
        /**
            Validates user input
        
            @return If the user's input is valid
        */
        
        private boolean validateInput()
        {
            if (locationName.)
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