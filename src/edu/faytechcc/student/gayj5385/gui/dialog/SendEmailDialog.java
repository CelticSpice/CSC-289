/**
    Dialog for sending an email
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.EmailUtil;
import edu.faytechcc.student.burnst9091.data.Reserver;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendEmailDialog extends JDialog
{
    // Fields
    public static final int ADMIN = 0;
    public static final int GUEST = 1;
    
    private JButton send, cancel;
    private JTextArea message;
    private JTextField subject, name, email;
        
    /**
        Constructs a new SendEmailDialog for the specified type of sender &
        a value for the reserver, if emailing a reserver.
    
        @param senderType Field value of either ADMIN or GUEST
        @param r The reserver emailing
    */
    
    public SendEmailDialog(int senderType, Reserver r)
    {
        setLayout(new BorderLayout());
        setTitle("Send Message");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildTopPanel(senderType), BorderLayout.NORTH);
        add(buildMidPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(senderType, r), BorderLayout.SOUTH);
        
        setSize(400, 400);
    }
    
    /**
        Builds & returns the bottom panel
    
        @param sender Sender type
        @param r Reserver emailing, if any
        @return The built panel
    */
    
    private JPanel buildBottomPanel(int sender, Reserver r)
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        panel.add(send = new JButton("Send"));
        panel.add(cancel = new JButton("Cancel"));
        
        ButtonController controller = new ButtonController(sender, r);
        
        send.addActionListener(controller);
        cancel.addActionListener(controller);
        
        return panel;
    }
    
    /**
        Builds & returns the middle panel
    
        @return The built panel
    */
    
    private JPanel buildMidPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        panel.add(message = new JTextArea());
        
        return panel;
    }
    
    /**
        Builds & returns the top panel
    
        @param sender Sender type
        @return The built panel
    */
    
    private JPanel buildTopPanel(int sender)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 5);
        panel.add(new JLabel("Subject:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.ipadx = 150;
        panel.add(subject = new JTextField(), gbc);
        
        if (sender == GUEST)
        {
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 10, 5);
            gbc.ipadx = 0;
            panel.add(new JLabel("Name:"), gbc);
            
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 10, 0);
            gbc.ipadx = 150;
            panel.add(name = new JTextField(), gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 0, 10, 5);
            gbc.ipadx = 0;
            panel.add(new JLabel("Return Email:"), gbc);
            
            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 10, 0);
            gbc.ipadx = 150;
            panel.add(email = new JTextField(), gbc);
        }
        
        return panel;
    }
    
    /**
        Validate the input
    
        @param sender The sender type
        @return If input valid
    */
    
    private boolean validateInput(int sender)
    {
        // Validate subject
        boolean valid = !subject.getText().isEmpty();
        
        if (!valid)
        {
            JOptionPane.showMessageDialog(this, "Subject must be entered",
                "Attention", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validate message
        valid = !message.getText().isEmpty();

        if (!valid)
        {
            JOptionPane.showMessageDialog(this, "Message must be entered",
                "Attention", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (sender == GUEST)
        {
            // Validate name
            valid = !name.getText().isEmpty();

            if (!valid)
            {
                JOptionPane.showMessageDialog(this, "Name must be entered",
                    "Attention", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Validate email
            valid = email.getText().matches(
                "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");

            if (!valid)
            {
                JOptionPane.showMessageDialog(this, "Email invalid",
                    "Attention", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    /**
        Controller for the dialog's buttons
    */
    
    private class ButtonController implements ActionListener
    {
        // Fields
        private int senderType;
        private Reserver r;
        
        /**
            Constructs a new ButtonController with the assumed sender type &
            the reserver emailing, if any
        
            @param s Sender type
            @param reserv The reserver, if any
        */
        
        public ButtonController(int s, Reserver reserv)
        {
            senderType = s;
            r = reserv;
        }
        
        /**
            Handle the clicking of a button
        
            @param e The action event
        */
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == send)
            {
                if (validateInput(senderType))
                {
                    if (senderType == ADMIN)
                    {
                        String subj = subject.getText();
                        String mesg = message.getText();
                        
                        try
                        {
                            EmailUtil.emailReserver(r, subj, mesg);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null,
                                "Failed sending message", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        String subj = subject.getText();
                        String n = name.getText();
                        String em = email.getText();
                        String mesg = message.getText();
                        
                        try
                        {
                            EmailUtil.emailAdmin(subj, n, em, mesg);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null,
                                "Failed sending message", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
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