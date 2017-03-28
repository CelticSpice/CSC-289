/**
    The administrator login
    CSC-289 - Group 4
    @author Jessica Gay, Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog
{
    // Fields
    private boolean credentialsValid;
    private JTextField username;
    private JPasswordField password;
    private JButton login, cancel;
    
    /**
        Constructs a new LoginDialog
    */
    
    private LoginDialog()
    {
        validCredentials = false;
        
        setLayout(new BorderLayout());
        setTitle("Login");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildMainPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
        
        pack();
        
        setVisible(true);
       
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
    
    /**
        Builds & returns the bottom panel of the dialog
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(login = new JButton("Login"));
        panel.add(cancel = new JButton("Cancel"));
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog
    
        @return The built panel
    */
    
    private JPanel buildMainPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Enter Credentials"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        panel.add(new JLabel("Username:"));
        panel.add(username = new JTextField(30));
        
        panel.add(new JLabel("Password:"));
        panel.add(password = new JPasswordField(30));
        
        return panel;
    }
    
    /**
        Shows the dialog, prompting the user to input the administrator's
        credentials & returning the user's input
    
        @return The user's input
    */
    
    public static CredentialInput prompt()
    {
        return new LoginDialog().credentialsValid;
    }
    
    /**
        Controller for the dialog's buttons
    */
    
    private class ButtonListener implements ActionListener
    {
        /**
            Responds to button being clicked
        
            @param e The action event
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "Login":
                    
                    break;
                case "Cancel":
                    setVisible(false);
                    dispose();
                    break;
            }
        }
    }
}