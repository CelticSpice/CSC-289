package edu.faytechcc.student.gayj5385.gui;

import javax.swing.*;

/**
 *
 * @author gayj5385
 */
public class OpenScreen extends JFrame
{
    
    private JPanel openScreenBtnPanel;
    private JButton logOnBtn, registerBtn, guestReservation, adminView;
    
    public OpenScreen()
    {
      final int WIN_HEIGHT = 750;
      final int WIN_WIDTH =  850;
      
      setTitle("Opening Screen");
      setSize(WIN_WIDTH,WIN_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
    }
    
    public void buildOpenScreenBtnPanel()
    {
        openScreenBtnPanel = new JPanel();
        logOnBtn = new JButton("Log On");
        registerBtn = new JButton("Register");
        guestReservation = new JButton("Guest Reserve");
        adminView = new JButton("AdminView");
        
        
    }
    
}
