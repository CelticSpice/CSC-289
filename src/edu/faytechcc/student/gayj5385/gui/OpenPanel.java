/**
    The initial view
    CSC-289 - Group 4
    @author Jessica Gay
*/

package edu.faytechcc.student.gayj5385.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OpenPanel extends JPanel
{
    // Fields
    private JButton logOnBtn, contactAdminBtn, guestReservation, exitBtn;
 
    /**
        Constructor
    */
    
    public OpenPanel()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        logOnBtn = new JButton("Log On");
        c.gridx =1;
        c.gridy = 8;
        c.weightx = 0.5;
        c.weighty = .5;
        add(logOnBtn,c);

        contactAdminBtn = new JButton("Contact Admin");
        c.gridx = 5;
        c.gridy = 2;
        c.weightx = 0.5;

        add(contactAdminBtn,c);

        guestReservation = new JButton("Guest Reserve");
        c.gridx = 3;
        c.gridy= 2;
        c.weightx = 0.5;

        add(guestReservation,c);

        exitBtn = new JButton("Exit");
        c.gridx = 6;
        c.gridy = 8;
        c.weightx = .5;
        c.weighty = 0.5;
        add(exitBtn,c);
    }
}