/**
    Controller for the locations combo box on the panel enabling the
    administrator to manage reservables
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.controller;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import edu.faytechcc.student.burnst9091.data.search.Filter;
import edu.faytechcc.student.burnst9091.data.search.SearchActualizer;
import edu.faytechcc.student.gayj5385.gui.ManageReservablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManageReservableComboBoxController implements ActionListener
{
    // Fields
    private ManageReservablePanel view;
    private Filter filter;
//    private Lis
    
    /**
        Constructor - Accepts the view to manage the combo box of
    
        @param v The view
     * @param f
    */
    
    public ManageReservableComboBoxController(ManageReservablePanel v,
            Filter f)
    {
        view = v;
        filter = f;
    }
    
    /**
        Handle combo box action events
    
        @param e The action event
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Location location = view.getSelectedLocation();
        view.setCapacity(String.valueOf(location.getCapacity()));
        
        List<Timeframe> timeframes;
        
        if (filter.getPredicate() != null)
        {
            SearchActualizer search = new SearchActualizer();
            
            filter.setPredicate(search.searchTimeframes(
                    view.getSearchCriteria()));
            
            timeframes = location.getTimeframes(filter.getPredicate());
        }
        else
            timeframes = location.getTimeframes();
        
         view.setTimeframes(timeframes);
    }
}