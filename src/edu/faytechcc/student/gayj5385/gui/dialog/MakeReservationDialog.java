/**
    Dialog for the guest to make a reservation & provide reservation details
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MakeReservationDialog extends JDialog
{
    private JButton reserve, cancel;
    
    /**
        Constructs a new MakeReservationDialog
    */
    
    public MakeReservationDialog()
    {
        setLayout(new BorderLayout());
        setTitle("Add Reservable");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildMainPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);
        
        pack();
    }
    
    /**
        Builds & returns the button panel of the dialog
        
        @return The built panel
    */
    
    private JPanel buildButtonPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(reserve = new JButton("Reserve"));
        panel.add(cancel = new JButton("Cancel"));
        
        ButtonController controller = new ButtonController();
        
        reserve.addActionListener(controller);
        cancel.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog
    
        @return The built panel
    */
    
    private JPanel buildMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        add(buildReservableInfoPanel());
        add(buildReservationInfoPanel());
        
        return panel;
    }
    
    /**
        Builds & returns the panel containing information about the reservable
    
        @return The built panel
    */
    
    private JPanel buildReservableInfoPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Reservable Info"));
        
        add(buildUpperReservableInfoPanel());
        add(buildMidReservableInfoPanel());
        add(buildLowerReservableInfoPanel());
        
        return panel;
    }
    
    /**
        Builds & returns the upper panel of the reservable info panel
    
        @return The built panel
    */
    
    private JPanel buildUpperReservableInfoPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        panel.add(new JLabel("Location:"));
        panel.add()
    }
}