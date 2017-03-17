/**
    Dialog enabling the updating of a password
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class UpdatePasswordDialog extends JDialog
{
    // Fields
    private JButton update, exit;
    private JLabel message;
    private JPasswordField oldPass, newPass, verifiedNewPass;
    
    /**
        Constructor
    */
    
    public UpdatePasswordDialog()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Update Password");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(buildInputPanel(), gbc);
        
        gbc.gridy = 1;
        add(buildMessagePanel(), gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 10, 10);
        add(buildButtonPanel(), gbc);
        
        pack();
    }
    
    /**
        Build & return the panel containing the buttons
    
        @return The built panel
    */
    
    private JPanel buildButtonPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(update = new JButton("Update"));
        panel.add(exit = new JButton("Exit"));
        
        return panel;
    }
    
    /**
        Build & return the panel allowing the input of the old & new passwords
    
        @return The built panel
    */
    
    private JPanel buildInputPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Enter Info"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.ipadx = 0;
        panel.add(new JLabel("Current Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipadx = 150;
        panel.add(oldPass = new JPasswordField(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.ipadx = 0;
        panel.add(new JLabel("New Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipadx = 150;
        panel.add(newPass = new JPasswordField(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.ipadx = 0;
        panel.add(new JLabel("Confirm Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipadx = 150;
        panel.add(verifiedNewPass = new JPasswordField(), gbc);
        
        return panel;
    }
    
    /**
        Build & return the panel for displaying messages
    
        @return The built panel
    */
    
    private JPanel buildMessagePanel()
    {
        JPanel panel = new JPanel();
        panel.add(message = new JLabel(""));
        return panel;
    }
    
    /**
        Return the input new password
    
        @return The input new password
    */
    
    public char[] getNewPass()
    {
        return newPass.getPassword();
    }
    
    /**
        Return the input old password
    
        @return The input old password
    */
    
    public char[] getOldPass()
    {
        return oldPass.getPassword();
    }
    
    /**
        Return the input verified new password
    
        @return The input verified new password
    */
    
    public char[] getVerifiedNewPass()
    {
        return verifiedNewPass.getPassword();
    }
    
    /**
        Register a controller to the buttons
    
        @param controller The controller to register to the buttons
    */
    
    public void registerController(ActionListener controller)
    {
        update.addActionListener(controller);
        exit.addActionListener(controller);
    }
    
    /**
        Set the message to be displayed
    
        @param mes The message to display
    */
    
    public void setMessage(String mes)
    {
        message.setText(mes);
        message.setForeground(Color.RED);
    }
}