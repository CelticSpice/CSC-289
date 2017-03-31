/**
    Dialog enabling the updating of a password
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.SHA256SaltHasher;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class UpdatePasswordDialog extends JDialog
{
    // Fields
    private JButton update, exit;
    private JLabel message;
    private JPasswordField oldPass, newPass, verifiedNewPass;
    
    /**
        Constructs a new UpdatePasswordDialog
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
        Builds & returns the panel containing the buttons
    */
    
    private JPanel buildButtonPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(update = new JButton("Update"));
        panel.add(exit = new JButton("Exit"));
        
        ButtonController controller = new ButtonController();
        
        update.addActionListener(controller);
        exit.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the panel allowing the input of the old & new passwords
    
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
        Builds & returns the panel for displaying messages
    
        @return The built panel
    */
    
    private JPanel buildMessagePanel()
    {
        JPanel panel = new JPanel();
        panel.add(message = new JLabel(""));
        return panel;
    }
    
    /**
        Sets the message to be displayed
    
        @param mesg The message to display
    */
    
    public void setMessage(String mesg)
    {
        message.setText(mesg);
        message.setForeground(Color.RED);
    }
    
    /**
        Controller class for dialog buttons
    */
    
    private class ButtonController implements ActionListener
    {
        /**
            Responds to an action event
        
            @param e The action event
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == update)
            {
                SystemPreferences prefs = SystemPreferences.getInstance();
                SHA256SaltHasher saltHasher = new SHA256SaltHasher();
                
                // Check that old password matches
                String oldPassword = new String(oldPass.getPassword());
                
                try
                {
                    oldPassword = saltHasher.saltHash(oldPassword);
                    
                    String currentPassword = prefs.getAdminPassword();
                    if (currentPassword.isEmpty())
                        currentPassword = saltHasher.saltHash(currentPassword);
                    

                    if (oldPassword.equals(currentPassword))
                    {
                        // Check that new passwords entered & matching
                        String newPassword = new String(newPass.getPassword());
                        String verifiedNewPassword = new String(
                                verifiedNewPass.getPassword());
                        
                        newPassword = saltHasher.saltHash(newPassword);
                        verifiedNewPassword = saltHasher.saltHash(
                                verifiedNewPassword);

                        if (newPassword.equals(verifiedNewPassword))
                        {
                            prefs.updateAdminPassword(newPassword);
                            setMessage("Password updated");
                        }
                        else
                            setMessage("Passwords do not match");
                    }
                    else
                        setMessage("Incorrect current password entered");
                }
                catch (NoSuchAlgorithmException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Failed to apply salt & hashing operation", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                setVisible(false);
                dispose();
            }
        }
    }
}