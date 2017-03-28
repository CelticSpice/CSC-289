/**
    The opening panel
    CSC-289 - Group 4
    @author Jessica Gay, Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OpenPanel extends JPanel
{
    // Fields
    private JButton login, makeReservation, contactAdmin, exit;
 
    /**
        Constructs a new OpenPanel
    */
    
    public OpenPanel()
    {        
        super(new BorderLayout());
        
        add(buildMainPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }
    
    /**
        Builds & returns the bottom panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        
        panel.add(buildBottomLeftPanel());
        panel.add(Box.createHorizontalGlue());
        panel.add(buildBottomRightPanel());
        
        return panel;
    }
    
    /**
        Builds & returns the bottom left panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomLeftPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        panel.add(login = new JButton("Login"));
        
        return panel;
    }
    
    /**
        Builds & returns the bottom right panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildBottomRightPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        panel.add(exit = new JButton("Exit"));
        
        return panel;
    }
    
    /**
        Builds & returns the main panel of this panel
    
        @return The built panel
    */
    
    private JPanel buildMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        panel.add(makeReservation = new JButton("Make Reservation"));
        panel.add(contactAdmin = new JButton("Contact Administrator"));
        
        return panel;
    }
    
    /**
        Registers a listener to the buttons on the panel
    
        @param listener The listener to register to the buttons
    */
    
    public void registerButtonListener(ActionListener listener)
    {
        login.addActionListener(listener);
        makeReservation.addActionListener(listener);
        contactAdmin.addActionListener(listener);
        exit.addActionListener(listener);
    }
}