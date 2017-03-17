/**
    Panel to setup system preferences
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.SMTPProperties;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SettingsPanel extends JPanel
{
    // Fields
    private JButton save, updatePasswd, cancel, logout;
    private JComboBox adminSecurity, guestSecurity;
    private JPasswordField adminPass, guestPass, dbPass;
    private JTextField adminSendAddress, adminHost, adminPort,
                       adminUser, adminGetAddress,
                       guestSendAddress, guestHost, guestPort,
                       guestUser, dbUser;
    
    /**
        Constructor
    */
    
    public SettingsPanel()
    {
        super(new BorderLayout());
        
        add(buildBottomPanel(), BorderLayout.SOUTH);
        add(buildMainPanel(), BorderLayout.CENTER);
    }
    
    /**
        Build & return the panel allowing the editing of administrator email
        settings
    
        @return The built panel
    */
    
    private JPanel buildAdminEmailPanel()
    {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Admin Email"));
        
        panel.add(new JLabel("Send Address:"));
        panel.add(adminSendAddress = new JTextField());
        panel.add(new JLabel("Host:"));
        panel.add(adminHost = new JTextField());
        panel.add(new JLabel("Security:"));
        panel.add(adminSecurity = new JComboBox());
        panel.add(new JLabel("Port:"));
        panel.add(adminPort = new JTextField());
        panel.add(new JLabel("User:"));
        panel.add(adminUser = new JTextField());
        panel.add(new JLabel("Pass:"));
        panel.add(adminPass = new JPasswordField());
        panel.add(new JLabel("Get Address:"));
        panel.add(adminGetAddress = new JTextField());
        
        return panel;
    }
    
    /**
        Build & return the bottom panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(buildBottomLeftPanel());
        panel.add(Box.createHorizontalGlue());
        panel.add(buildBottomRightPanel());
        
        return panel;
    }
    
    /**
        Build & return the bottom-left panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomLeftPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        panel.add(save = new JButton("Save"));
        panel.add(updatePasswd = new JButton("Update Password"));
        panel.add(cancel = new JButton("Cancel"));
        
        return panel;
    }
    
    /**
        Build & return the bottom-right panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomRightPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panel.add(logout = new JButton("Logout"));
        return panel;
    }
    
    /**
        Build & return the panel allowing updates to database information
    
        @return The built panel
    */
    
    private JPanel buildDBPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5 ,5));
        panel.setBorder(BorderFactory.createTitledBorder("Database"));
        
        panel.add(new JLabel("Database User:"));
        panel.add(dbUser = new JTextField());
        panel.add(new JLabel("Database Pass:"));
        panel.add(dbPass = new JPasswordField());
                
        return panel;
    }
    
    /**
        Build & return the panel allowing the editing of guest email settings
    
        @return The built panel
    */
    
    private JPanel buildGuestEmailPanel()
    {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Guest Email"));
        
        panel.add(new JLabel("Send Address:"));
        panel.add(guestSendAddress = new JTextField());
        panel.add(new JLabel("Host:"));
        panel.add(guestHost = new JTextField());
        panel.add(new JLabel("Security:"));
        panel.add(guestSecurity = new JComboBox());
        panel.add(new JLabel("Port:"));
        panel.add(guestPort = new JTextField());
        panel.add(new JLabel("User:"));
        panel.add(guestUser = new JTextField());
        panel.add(new JLabel("Pass:"));
        panel.add(guestPass = new JPasswordField());
        
        return panel;
    }
    
    /**
        Build & return the main panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildMainPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(buildAdminEmailPanel(), gbc);
        
        gbc.gridx = 1;
        panel.add(buildGuestEmailPanel(), gbc);
        
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(buildDBPanel(), gbc);
        
        return panel;
    }
    
    /**
        Return the set admin SMTP properties
    
        @return The admin's set SMTP properties
    */
    
    public SMTPProperties getAdminSMTPProperties()
    {
        SMTPProperties props = new SMTPProperties();
        props.setAddress(adminSendAddress.getText());
        props.setHost(adminHost.getText());
        props.setSecurity((SecurityOption) adminSecurity.getSelectedItem());
        props.setPort(adminPort.getText());
        props.setUser(adminUser.getText());
        props.setPassword(String.valueOf(adminPass.getPassword()));
        return props;
    }
    
    /**
        Return the admin's set address to receive email at
    
        @return The admin's set address to receive email at
    */
    
    public String getAdminGetAddress()
    {
        return adminGetAddress.getText();
    }
    
    /**
        Return the set database password
    
        @return The set database password
    */
    
    public char[] getDBPass()
    {
        return dbPass.getPassword();
    }
    
    /**
        Return the set database username
    
        @return The set database username
    */
    
    public String getDBUser()
    {
        return dbUser.getText();
    }
    
    /**
        Return the set guest SMTP properties
    
        @return The guest's set SMTP properties
    */
    
    public SMTPProperties getGuestSMTPProperties()
    {
        SMTPProperties props = new SMTPProperties();
        props.setAddress(guestSendAddress.getText());
        props.setHost(guestHost.getText());
        props.setSecurity((SecurityOption) guestSecurity.getSelectedItem());
        props.setPort(guestPort.getText());
        props.setUser(guestUser.getText());
        props.setPassword(String.valueOf(guestPass.getPassword()));
        return props;
    }
    
    /**
        Register a controller to the buttons on the panel
    
        @param controller The controller to register to the buttons
    */
    
    public void registerController(ActionListener controller)
    {
        save.addActionListener(controller);
        updatePasswd.addActionListener(controller);
        cancel.addActionListener(controller);
        logout.addActionListener(controller);
    }
    
    /**
        Populate the database settings fields with the given data
    
        @param user Database username
        @param pass Database password
    */
    
    public void setDatabaseFields(String user, String pass)
    {
        dbUser.setText(user);
        dbPass.setText(pass);
    }
    
    /**
        Populate the email settings fields with the given data
    
        @param adminSMTP Admin SMTP properties
        @param guestSMTP Guest SMTP properties
        @param adminGet Address for admin to receive email at
    */
    
    public void setEmailFields(SMTPProperties adminSMTP,
                               SMTPProperties guestSMTP, String adminGet)
    {
        adminSendAddress.setText(adminSMTP.getAddress());
        adminHost.setText(adminSMTP.getHost());
        adminSecurity.setSelectedItem(adminSMTP.getSecurity());
        adminPort.setText(adminSMTP.getPort());
        adminUser.setText(adminSMTP.getUser());
        adminPass.setText(adminSMTP.getPassword());
        
        adminGetAddress.setText(adminGet);
        
        guestSendAddress.setText(guestSMTP.getAddress());
        guestHost.setText(guestSMTP.getHost());
        guestSecurity.setSelectedItem(guestSMTP.getSecurity());
        guestPort.setText(guestSMTP.getPort());
        guestUser.setText(guestSMTP.getUser());
        guestPass.setText(guestSMTP.getPassword());
    }
    
    /**
        Set the available SMTP security protocol options to choose from
    
        @param options Security protocol options available to choose from
    */
    
    public void setSecurityOptions(SecurityOption[] options)
    {
        for (SecurityOption option : options)
        {
            adminSecurity.addItem(option);
            guestSecurity.addItem(option);
        }
    }
    
    /**
        Show a message to the user in a dialog box
    
        @param message The message to show
    */
    
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
}