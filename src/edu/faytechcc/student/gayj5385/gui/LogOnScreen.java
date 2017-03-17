/**
    The administrator login
    CSC-289 - Group 4
    @author Jessica Gay
*/

package edu.faytechcc.student.gayj5385.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogOnScreen extends JPanel
{
    // Fields
    private JTextField username;
    private JPasswordField password;
    private JLabel unDisplay,pwDisplay;
    private JButton logOnBtn;
    
    /**
        Constructor
    */
    
    public LogOnScreen()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
       
        unDisplay = new JLabel("Username:");
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = .1;
        c.weighty = 0;
        add(unDisplay,c);
        
        pwDisplay = new JLabel("Password:");
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = .1;
        c.weighty = 0;
        add(pwDisplay,c);
        
        username = new JTextField(25);
        username.setSize(14, 24);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.weightx = .1;
        c.weighty = 0;
        add(username,c);
        
        password = new JPasswordField(25);
        c.gridx = 2;
        c.gridy = 5;
        c.weightx = .1;
        c.weighty = 0;
        add(password,c);
        
        logOnBtn = new JButton("Log On");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(50,25,25,15);
	add(logOnBtn,c);
    }
}