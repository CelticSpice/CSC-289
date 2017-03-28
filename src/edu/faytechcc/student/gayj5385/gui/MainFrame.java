/**
    The main frame of the application
    CSC-289 - Group 4
    @author Jessica Gay, Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame
{    
    /**
        Constructs a new MainFrame with children initialized with the given
        list of locations & mapping of reservations
           
        @param locations The locations
        @param reservations The reservations
    */
    
    public MainFrame(List<Location> locations,
        HashMap<Location, List<Reservation>> reservations)
    {
        setTitle("Event Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();

        add(new MainPanel(this, locations, reservations));

        pack();
        setVisible(true);
    }
    
    /**
        Sets the look and feel of the interface
    */
    
    private void setLookAndFeel()
    {
        final String WANTED_LOOK_AND_FEEL = "Nimbus";
                
        LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
        
        boolean lookAndFeelSet = false;
        int i = 0;
        while (!lookAndFeelSet && i < lookAndFeels.length)
        {
            if (lookAndFeels[i].getName().equals(WANTED_LOOK_AND_FEEL))
            {
                try
                {
                    UIManager.setLookAndFeel(lookAndFeels[i].getClassName());
                    lookAndFeelSet = true;
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Failed setting Nimbus look & feel", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
            i++;
        }
    }
}