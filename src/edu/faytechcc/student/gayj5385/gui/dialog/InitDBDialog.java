/**
    Dialog for initializing the database settings
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InitDBDialog extends JDialog
{
    private JButton ok;
    private JPasswordField password;
    private JTextField host, port, username;
    
    /**
        Constructs a new InitDBDialog
    */
    
    public InitDBDialog()
    {
        setLayout(new FlowLayout());
        setTitle("Set Database Settings");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(buildMainPanel(), BorderLayout.CENTER);
        panel.add(buildBottomPanel(), BorderLayout.SOUTH);
        
        add(panel);
        
        pack();
    }
    
    /**
        Builds & returns the bottom panel of this dialog
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        
        panel.add(ok = new JButton("OK"));
                
        ok.addActionListener(new ButtonController());
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of the dialog
    
        @return The built panel
    */
    
    private JPanel buildMainPanel()
    {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Database Settings"));
        
        panel.add(new JLabel("Host:"));
        panel.add(host = new JTextField(7));
        host.setText("localhost");
        
        panel.add(new JLabel("Port:"));
        panel.add(port = new JTextField(7));
        port.setText("3306");
        
        panel.add(new JLabel("Username:"));
        panel.add(username = new JTextField(7));
        
        panel.add(new JLabel("Password:"));
        panel.add(password = new JPasswordField(7));
        
        return panel;
    }
    
    /**
        Controller for the dialog's buttons
    */
    
    private class ButtonController implements ActionListener
    {
        /**
            Performs an action on a button being clicked
        
            @param e The ActionEvent
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (validateInput())
            {
                DatabaseSettings settings = new DatabaseSettings();

                settings.setDBHost(host.getText());
                settings.setDBPort(Integer.parseInt(port.getText()));
                settings.setDBUser(username.getText());
                settings.setDBPass(new String(password.getPassword()));

                SystemPreferences prefs = SystemPreferences.getInstance();
                prefs.setDBSettings(settings);
                prefs.setInitSetupRun(true);

                dispose();
            }
        }
        
        /**
            Displays an error message
        
            @param mesg Message to display
        */
        
        private void displayError(String mesg)
        {
            JOptionPane.showMessageDialog(null, mesg, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        /**
            Validates user input
        
            @return If user input is valid
        */
        
        private boolean validateInput()
        {
            if (host.getText().isEmpty())
            {
                displayError("Host must be entered");
                return false;
            }
            
            String pattern = "\\b\\d+\\b";
            if (port.getText().isEmpty() || !port.getText().matches(pattern))
            {
                displayError("Port number must be entered");
                return false;
            }
            
            return true;
        }
    }
}