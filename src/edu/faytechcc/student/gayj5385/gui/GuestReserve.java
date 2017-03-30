package edu.faytechcc.student.gayj5385.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

/**
 * this screen card will have the guest Reservation form
 * it will be able to be populated from the data base with
 * the places and times of each reservable.
 * 
 * @author Jessica
 */
public class GuestReserve extends JPanel
{
    private JLabel lblName, lblEmail,lblLoc,lblDate, lblType, lblTime;
    private JTextField txtName, txtEmail;
    private JComboBox cbLoc, cbMonth, cbDay, cbYear, cbType;
    private JList jlTime;
    private JButton reserveBtn;
    
    public GuestReserve()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        lblName = new JLabel("Name: ");
        c.gridx = 1;
        c.gridy = 1;
        add(lblName,c);
        
        txtName = new JTextField(25);
        c.gridx = 2;
        c.gridy = 1;
        add(txtName,c);
        
        lblEmail = new JLabel("Email: ");
        c.gridx = 1;
        c.gridy = 3;
        add(lblEmail,c);
        
        txtEmail = new JTextField(25);
        c.gridx = 2;
        c.gridy = 3;
        add(txtEmail,c);
        
        lblLoc = new JLabel("Location:");
        c.gridx = 1;
        c.gridy = 4;
        add(lblLoc,c);
        
        cbLoc = new JComboBox();
        c.gridx = 2;
        c.gridy = 4;
        add(cbLoc,c);
        
        lblDate = new JLabel("Date: ");
        c.gridx = 1;
        c.gridy = 5;
        add(lblDate,c);
        
        JPanel date = new JPanel();
        c.gridx = 2;
        c.gridy = 5;
        add(date,c);
        
        cbMonth = new JComboBox();
        c.gridx = 2;
        c.gridy = 5;
        date.add(cbMonth,c);
        
        cbDay = new JComboBox();
        c.gridx = 3;
        c.gridy = 5;
        date.add(cbDay,c);
        
        cbYear = new JComboBox();
        c.gridx = 4;
        c.gridy = 5;
        date.add(cbYear,c);
        
        lblType = new JLabel("Type: ");
        c.gridx = 1;
        c.gridy = 6;
        add(lblType,c);
        
        cbType = new JComboBox();
        c.gridx = 2;
        c.gridy = 6;
        add(cbType,c);
        
        lblTime = new JLabel("Time:");
        c.gridx = 1;
        c.gridy = 7;
        add(lblTime,c);
        
        //This is suppose to be where the times are suppose to be
       //I cant get it to show up  so the layout it a bit off now
       
        jlTime = new JList();
        c.gridx = 2;
        c.gridy = 7;
        add(jlTime,c);
        
        JPanel bottom = new JPanel();
        reserveBtn = new JButton("Make Reservation");
        bottom.add(reserveBtn);
        c.gridx = 2;
        c.gridy = 9;
        add(bottom,c);
    }
}
