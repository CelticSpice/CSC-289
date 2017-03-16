/**
    The root administrator panel
    CSC-289 - Group 4
    @author Timothy Burns
*/

package GUI;

import Controller.ManageReservableBtnController;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminPanel extends JPanel
{
    /**
        Constructor
    */
    
    public AdminPanel()
    {
        super(new BorderLayout());
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.add("Manage Reservables", buildManageReservablePanel());
        
        add(tabbedPane);
    }
    
    /**
        BuildManageReservablePanel - Build & return the panel to manage
        reservables on
    
        @return panel The built panel
    */
    
    private ManageReservablePanel buildManageReservablePanel()
    {
        ManageReservablePanel panel = new ManageReservablePanel();
        panel.registerButtonController
            (new ManageReservableBtnController(panel));
        return panel;
    }
}