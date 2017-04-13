/**
 * 
 * @author Shane McCann
 */
package edu.faytechcc.student.gayj5385.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SearchHelpDialog extends JDialog
{
    // Fields
    DefaultListModel keys;
    JLabel info, acceptedKeys;
    JList keyList;
    JButton okay;
    
    public SearchHelpDialog(List<String> ks)
    {
        setLayout(new BorderLayout());
        setTitle("Search Help");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(buildInfoPanel(), BorderLayout.WEST);
        add(buildKeyListPanel(ks), BorderLayout.EAST);
        add(okay = new JButton("Okay"), BorderLayout.SOUTH);
        
        ButtonController controller = new ButtonController();
        okay.addActionListener(controller);
        
        setSize(450, 300);
    }
    
    private JPanel buildInfoPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 15, 15, 15);
        
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
                     "startdate :: 2017-12-31; starttime :: 12:00");
        
        panel.add(info, gbc);
        
        return panel;
    }
    
    private JPanel buildKeyListPanel(List<String> ks)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        acceptedKeys = new JLabel();
        acceptedKeys.setText("<html><b>Accepted Keys:</b>");
                
        panel.add(acceptedKeys, gbc);
        
        keyList = new JList(keys = new DefaultListModel());
        
        for (String k : ks)
        {
            keys.addElement(k);
        }
        
        JScrollPane scrollPane = new JScrollPane(keyList);
        scrollPane.setPreferredSize(new Dimension(125, 175));
        
        gbc.gridy = 1;        
        gbc.insets = new Insets(7, 15, 15, 15);
        
        panel.add(scrollPane, gbc);
        
        return panel;
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
