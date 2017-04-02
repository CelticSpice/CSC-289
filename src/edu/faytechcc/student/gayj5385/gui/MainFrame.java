/**
    The main frame of the application
    CSC-289 - Group 4
    @author Jessica Gay, Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame
{    
    /**
        Constructs a new MainFrame
    */
    
    public MainFrame()
    {
        setTitle("Event Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();

        add(new MainPanel(this));

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
        
        boolean done = false;
        int i = 0;
        while (!done && i < lookAndFeels.length)
        {
            if (lookAndFeels[i].getName().equals(WANTED_LOOK_AND_FEEL))
            {
                try
                {
                    UIManager.setLookAndFeel(lookAndFeels[i].getClassName());
                    done = true;
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Failed setting Nimbus look & feel", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    done = true;
                }
            }
            else
                i++;
        }
    }
}