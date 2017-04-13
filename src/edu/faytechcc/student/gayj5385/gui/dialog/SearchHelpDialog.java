/**
 * 
 * @author Shane McCann
 */
package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class SearchHelpDialog extends JDialog
{
    // Fields
    DefaultListModel keys;
    JLabel info;
    JList keyList;
    JButton okay;
    
    public SearchHelpDialog(List<String> k)
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
        gbc.anchor = GridBagConstraints.NORTH;
        
        info = new JLabel();
        info.setText("<html>" +
                     "<b>Search Help</b><br>" +
                     "<br>" +
                     "To perform a search, type the keyword followed by<br>" +
                     "two colons and finally the value that you are<br>" +
                     "searching for. If you are searching for multiple<br>" +
                     "values, enter a semi-colon after each value.<br>" +
                     "<br>" +
                     "<b>Example:</b><br>" +
                     "startdate::2017-12-31; starttime::12:00");
        add(info, gbc);
        
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        
        keyList = new JList(keys = new DefaultListModel());
        JScrollPane scrollPane = new JScrollPane(keyList);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.anchor = GridBagConstraints.SOUTH;
        add(okay = new JButton("Okay"), gbc);
        
        ButtonController controller = new ButtonController();
        okay.addActionListener(controller);
        
        setSize(530, 425);
    }
    
    private class ButtonController implements ActionListener
    {
        /**
         * Constructs a new ButtonController
         */
        public ButtonController(){}
        
        /**
         * Performs an action on a button being clicked
         * 
         * @param e The ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }
}
