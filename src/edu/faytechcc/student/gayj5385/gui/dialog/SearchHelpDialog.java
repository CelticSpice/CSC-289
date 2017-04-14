/**
 * 
 * @author Shane McCann
 */
package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;

public class SearchHelpDialog extends JDialog
{
    // Fields
    JTextField text;
    JList keys;
    
    public SearchHelpDialog()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Search Help");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;
        add(text, gbc);
    }
}
