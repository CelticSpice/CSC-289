/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.gayj5385.gui.MainFrame;
import edu.faytechcc.student.gayj5385.gui.dialog.InitDBDialog;
import edu.faytechcc.student.mccanns0131.database.DBDemiurge;
import edu.faytechcc.student.mccanns0131.database.DBJanitor;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args)
    {
        setLookAndFeel();
                
        if (!SystemPreferences.getInitSetupRun())
            new InitDBDialog().setVisible(true);
        
        boolean successfulInit = false;
        
        while (!successfulInit)
        {
            try
            {
                DBDemiurge.craft();
                successfulInit = true;
                DBJanitor.run();
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null,
                    "Failed database initialization: Check database settings",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if (!successfulInit)
                new InitDBDialog().setVisible(true);
        }
        
        new MainFrame();
    }
    
    /**
        Sets the look and feel of the interface
    */
    
    private static void setLookAndFeel()
    {
        final String WANTED_LOOK_AND_FEEL = "Nimbus";
                
        LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
        
        boolean done = false;
        int i = 0;
        while (!done && i < lookAndFeels.length)
        {
            if (lookAndFeels[i].getName().equals(WANTED_LOOK_AND_FEEL))
            {
                try
                {
                    UIManager.setLookAndFeel(lookAndFeels[i].getClassName());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Failed setting Nimbus look & feel", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                done = true;
            }
            else
                i++;
        }
    }
}