package edu.faytechcc.student.gayj5385.gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * this will be the card layout panel that will 
 * allow us to switch between the different screens 
 * @author Jessica
 */
public class ScreenCards extends JPanel
{
   
    
    public ScreenCards()
    {
        setLayout(new CardLayout());
        
        OpenScreen open = new OpenScreen();
        //GuestReservations guest = new GuestReservations();
        LoginDialog log = new LoginDialog();
        
        //add(guest,"guestReservations");
        add(open,"openScreen");
        add(log,"logOnScreen");
    }
    public void switchScreens(String card)
    {
         CardLayout cl = (CardLayout)(getLayout());
        switch(card)
        {
            case "logOnScreen":
                cl.show(this,"logOnScreen");
                
            case "openScreen":
                cl.show(this,"openScreen");
            case "guestReservations":
                cl.show(this,"guestReservations");
        }
    }
}
