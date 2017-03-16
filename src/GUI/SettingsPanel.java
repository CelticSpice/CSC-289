/**
    Panel to setup system preferences
    CSC-289 - Group 4
    @author Timothy Burns
*/

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SettingsPanel extends JPanel
{
    // Fields
    private JButton saveBtn, exitBtn;
    private JComboBox adminSecurity, guestSecurity;
    
    private JTextField adminSendAddress, adminHost, adminPort,
                       adminUser, adminGetAddress,
                       guestSendAddress, guestHost, guestPort,
                       guestUser;
    
    private JTextField dbUser;
    
    
    private JPasswordField adminPass, guestPass, adminLogPass,
                           adminVerifyLogPass, dbPass;
    
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
        BuildAdminEmailPanel - Build & return the panel allowing the editing
        of administrator email settings
    
        @return panel The built panel
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
        BuildAdminPassPanel - Build & return the panel allowing updates to
        the administrator's password
    
        @return panel The built panel
    */
    
    private JPanel buildAdminPassPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Admin Password"));
        
        panel.add(new JLabel("Admin Pass:"));
        panel.add(adminLogPass = new JPasswordField());
        panel.add(new JLabel("Verify Pass:"));
        panel.add(adminVerifyLogPass = new JPasswordField());
        
        return panel;
    }
    
    /**
        BuildBottomPanel - Build & return the bottom panel of this panel
    
        @return panel The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Build save button panel
        JPanel saveBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        saveBtnPanel.add(saveBtn = new JButton("Save"));
        
        // Build exit button panel
        JPanel exitBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        exitBtnPanel.add(exitBtn = new JButton("Exit"));
        
        panel.add(saveBtnPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(exitBtnPanel);
        
        return panel;
    }
    
    /**
        BuildDBPanel - Build & return the panel allowing updates to database
        information
    
        @return panel The built panel
    */
    
    private JPanel buildDBPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Database"));
        
        panel.add(new JLabel("Database User:"));
        panel.add(dbUser = new JTextField());
        panel.add(new JLabel("Database Pass:"));
        panel.add(dbPass = new JPasswordField());
        
        return panel;
    }
    
    /**
        BuildEastPanel - Build & return the panel allowing database &
        administrator password updates
    
        @param panel The built panel
    */
    
    private JPanel buildEastPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
        
        panel.add(buildDBPanel());
        panel.add(buildAdminPassPanel());
        
        return panel;
    }
    
    /**
        BuildGuestEmailPanel - Build & return the panel allowing the editing
        of guest email settings
    
        @return panel The built panel
    */
    
    private JPanel buildGuestEmailPanel()
    {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
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
        BuildMainPanel - Build & return the main panel of this panel
    
        @return panel The built panel
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
        panel.add(buildEastPanel(), gbc);
        
        return panel;
    }
    
    /**
        PopulateFields - Populate the fields with the given data
    
        @param adminEmailProps Properties for the admin's email
        @param adminGet Admin's get address
        @param guestEmailProps Properties for the guest's email
        @param dbProps Properties for the database
        @param adminPassword The current administrator password
    */
    
    public void populateFields(Properties adminEmailProps,
                               String adminGet, 
                               Properties guestEmailProps, Properties dbProps,
                               String adminPassword)
    {
        adminSendAddress.setText(adminEmailProps.getProperty("Address"));
        adminHost.setText(adminEmailProps.getProperty("Host"));
        adminSecurity.setSelectedItem(adminEmailProps.getProperty("Security"));
        adminPort.setText(adminEmailProps.getProperty("Port"));
        adminUser.setText(adminEmailProps.getProperty("User"));
        adminPass.setText(adminEmailProps.getProperty("Pass"));
        adminGetAddress.setText(adminGet);
        
        guestSendAddress.setText(guestEmailProps.getProperty("Address"));
        guestHost.setText(guestEmailProps.getProperty("Host"));
        guestSecurity.setSelectedItem(guestEmailProps.getProperty("Security"));
        guestPort.setText(guestEmailProps.getProperty("Port"));
        guestUser.setText(guestEmailProps.getProperty("User"));
        guestPass.setText(guestEmailProps.getProperty("Pass"));
        
        dbUser.setText(dbProps.getProperty("User"));
        dbPass.setText(dbProps.getProperty("Pass"));
        
        adminLogPass.setText(adminPassword);
        adminVerifyLogPass.setText(adminPassword);
    }
    
    /**
        SetSecurityOptions - Set the available security options to choose from
    
        @param options Security options available to choose from
    */
    
    public void setSecurityOptions(String[] options)
    {
        for (String option : options)
        {
            adminSecurity.addItem(option);
            guestSecurity.addItem(option);
        }
    }
}