/**
    Initialization of the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.gayj5385.gui.dialog.InitDBDialog;
import edu.faytechcc.student.mccanns0131.database.DBDemiurge;
import edu.faytechcc.student.mccanns0131.database.DBJanitor;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class SystemInitialization
{
    /**
        Runs the system initialization
    */
    
    public static void run()
    {
        setLookAndFeel();
        
        SystemPreferences prefs = SystemPreferences.getInstance();
        if (!prefs.getInitSetupRun())
            new InitDBDialog().setVisible(true);
        
        boolean connecting = false;
        while (!connecting)
        {
            try
            {
                DBDemiurge.craft();
                connecting = true;
                DBJanitor.run();
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null,
                    "Failed database initialization: Check database settings",
                        "Error", JOptionPane.ERROR_MESSAGE);
                
                new InitDBDialog().setVisible(true);
            }
        }
    }
    
    /**
        Sets the look and feel of the interface
    */
    
    private static void setLookAndFeel()
    {
        final String WANTED = "Nimbus";
                        
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        {
            if (info.getName().equals(WANTED))
            {
                try
                {
                    UIManager.setLookAndFeel(info.getClassName());
                }
                catch (Exception ex)
                {
                    System.err.println("Failed setting " + WANTED +
                            " look and feel");
                }
                break;
            }
        }
    }
}