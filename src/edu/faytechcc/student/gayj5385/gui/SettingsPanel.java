/**
    Panel to set system settings
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.EmailSettings;
import edu.faytechcc.student.burnst9091.data.SecurityOption;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class SettingsPanel extends JPanel
{
    // Fields
    private JButton save, updatePasswd, cancel;
    private JComboBox<SecurityOption> adminSecurity, guestSecurity;
    private JPasswordField adminPass, guestPass, dbPass;
    private JTextField adminSendAddress, adminHost, adminUser, adminGetAddress,
                       guestSendAddress, guestHost, guestUser, dbHost, dbUser;
    private JFormattedTextField adminPort, guestPort, dbPort;
    
    /**
        Constructs a new SettingsPanel
    */
    
    public SettingsPanel()
    {
        super(new BorderLayout());
        
        add(buildBottomPanel(), BorderLayout.SOUTH);
        add(buildMainPanel(), BorderLayout.CENTER);
    }
    
    /**
        Builds & returns the panel allowing the editing of administrator email
        settings
    
        @return The built panel
    */
    
    private JPanel buildAdminEmailPanel()
    {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Admin Email"));
        
        MaskFormatter fmtr = null;
        try
        {
            fmtr = new MaskFormatter("#****");
            fmtr.setPlaceholder("0\0\0\0\0");
            fmtr.setValidCharacters("0123456789\0 ");
        }
        catch (ParseException ex)
        {
            // Do nothing - we know all is well, and no exception will throw
        }
        
        panel.add(new JLabel("Send Address:"));
        panel.add(adminSendAddress = new JTextField(12));
        
        panel.add(new JLabel("Host:"));
        panel.add(adminHost = new JTextField(12));
        
        panel.add(new JLabel("Security:"));
        panel.add(adminSecurity = new JComboBox());
        
        panel.add(new JLabel("Port:"));
        panel.add(adminPort = new JFormattedTextField(fmtr));
        
        panel.add(new JLabel("Username:"));
        panel.add(adminUser = new JTextField(12));
        
        panel.add(new JLabel("Password:"));
        panel.add(adminPass = new JPasswordField(12));
        
        panel.add(new JLabel("Get Address:"));
        panel.add(adminGetAddress = new JTextField(12));
        
        return panel;
    }
    
    /**
        Builds & returns the bottom panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(save = new JButton("Save"));
        panel.add(updatePasswd = new JButton("Update Password"));
        panel.add(cancel = new JButton("Cancel"));
        
        return panel;
    }
    
    /**
        Builds & returns the panel allowing updates to database information
    
        @return The built panel
    */
    
    private JPanel buildDBPanel()
    {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5 ,5));
        panel.setBorder(BorderFactory.createTitledBorder("Database"));
        
        MaskFormatter fmtr = null;
        try
        {
            fmtr = new MaskFormatter("#****");
            fmtr.setPlaceholder("0\0\0\0\0");
            fmtr.setValidCharacters("0123456789\0 ");
        }
        catch (ParseException ex)
        {
            // Do nothing - we know all is well, and no exception will throw
        }
        
        panel.add(new JLabel("Host:"));
        panel.add(dbHost = new JTextField(12));
        
        panel.add(new JLabel("Port:"));
        panel.add(dbPort = new JFormattedTextField(fmtr));
        
        panel.add(new JLabel("Username:"));
        panel.add(dbUser = new JTextField(12));
        
        panel.add(new JLabel("Password:"));
        panel.add(dbPass = new JPasswordField(12));
                
        return panel;
    }
    
    /**
        Builds & returns the panel allowing the editing of guest email settings
    
        @return The built panel
    */
    
    private JPanel buildGuestEmailPanel()
    {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Guest Email"));
        
        MaskFormatter fmtr = null;
        try
        {
            fmtr = new MaskFormatter("#****");
            fmtr.setPlaceholder("0\0\0\0\0");
            fmtr.setValidCharacters("0123456789\0 ");
        }
        catch (ParseException ex)
        {
            // Do nothing - we know all is well, and no exception will throw
        }
        
        panel.add(new JLabel("Send Address:"));
        panel.add(guestSendAddress = new JTextField(12));
        
        panel.add(new JLabel("Host:"));
        panel.add(guestHost = new JTextField(12));
        
        panel.add(new JLabel("Security:"));
        panel.add(guestSecurity = new JComboBox());
        
        panel.add(new JLabel("Port:"));
        panel.add(guestPort = new JFormattedTextField(fmtr));
        
        panel.add(new JLabel("Username:"));
        panel.add(guestUser = new JTextField(12));
        
        panel.add(new JLabel("Password:"));
        panel.add(guestPass = new JPasswordField(12));
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildMainPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
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
        Returns the input administrator email settings
    
        @return The input administrator email settings
    */
    
    public EmailSettings getAdminEmailSettings()
    {
        EmailSettings settings = new EmailSettings();
        
        settings.setSendAddress(adminSendAddress.getText());
        settings.setSMTPHost(adminHost.getText());
        settings.setSMTPPort(Integer.parseInt(adminPort.getText().trim()));
        
        settings.setSMTPSecurity(
                (SecurityOption) adminSecurity.getSelectedItem());
        
        settings.setSMTPUser(adminUser.getText());
        settings.setSMTPPass(new String(adminPass.getPassword()));
        settings.setGetAddress(adminGetAddress.getText());
        
        return settings;
    }
    
    /**
        Returns the input database settings
    
        @return The input database settings
    */
    
    public DatabaseSettings getDBSettings()
    {
        DatabaseSettings settings = new DatabaseSettings();
        
        settings.setDBHost(dbHost.getText());
        settings.setDBPort(Integer.parseInt(dbPort.getText().trim()));
        settings.setDBUser(dbUser.getText());
        settings.setDBPass(new String(dbPass.getPassword()));
        
        return settings;
    }
    
    /**
        Returns the input guest email settings
    
        @return The input guest email settings
    */
    
    public EmailSettings getGuestEmailSettings()
    {
        EmailSettings settings = new EmailSettings();
        
        settings.setSendAddress(guestSendAddress.getText());
        settings.setSMTPHost(guestHost.getText());
        settings.setSMTPPort(Integer.parseInt(guestPort.getText().trim()));
        
        settings.setSMTPSecurity(
                (SecurityOption) guestSecurity.getSelectedItem());
        
        settings.setSMTPUser(guestUser.getText());
        settings.setSMTPPass(new String(guestPass.getPassword()));
        
        return settings;
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
    }
    
    /**
        Sets the input administrator email settings
    
        @param settings Input administrator email settings
    */
    
    public void setAdminEmailSettings(EmailSettings settings)
    {
        adminSendAddress.setText(settings.getSendAddress());
        adminHost.setText(settings.getSMTPHost());
        adminPort.setText(settings.getSMTPPort().toString());
        adminSecurity.setSelectedItem(settings.getSMTPSecurity());
        adminUser.setText(settings.getSMTPUser());
        adminPass.setText(settings.getSMTPPass());
        adminGetAddress.setText(settings.getGetAddress());
    }
    
    /**
        Sets the input database settings
    
        @param settings Input database settings
    */
    
    public void setDBSettings(DatabaseSettings settings)
    {
        dbHost.setText(settings.getDBHost());
        dbPort.setText(settings.getDBPort().toString());
        dbUser.setText(settings.getDBUser());
        dbPass.setText(settings.getDBPass());
    }
    
     /**
        Sets the input guest email settings
    
        @param settings Input guest email settings
    */
    
    public void setGuestEmailSettings(EmailSettings settings)
    {
        guestSendAddress.setText(settings.getSendAddress());
        guestHost.setText(settings.getSMTPHost());
        guestPort.setText(settings.getSMTPPort().toString());
        guestSecurity.setSelectedItem(settings.getSMTPSecurity());
        guestUser.setText(settings.getSMTPUser());
        guestPass.setText(settings.getSMTPPass());
    }
    
    /**
        Sets the available SMTP security protocol options to choose from
    
        @param options Security protocol options available to choose from
    */
    
    public void setSecurityOptions(SecurityOption[] options)
    {
        adminSecurity.removeAllItems();
        guestSecurity.removeAllItems();
        
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