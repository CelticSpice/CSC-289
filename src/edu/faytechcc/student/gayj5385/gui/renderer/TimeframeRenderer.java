/**
    Renderer for a timeframe
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.renderer;

import edu.faytechcc.student.burnst9091.data.ReservableTimeframe;
import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class TimeframeRenderer extends DefaultListCellRenderer
{
    @Override
    public Component getListCellRendererComponent(JList<?> list,
        Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);
        
        ReservableTimeframe timeframe = (ReservableTimeframe) value;
        DateTimeFormatter dFmt = DateTimeFormatter.ofPattern("y-MMM-d");
        DateTimeFormatter tFmt = DateTimeFormatter.ofPattern("H:mm");
        NumberFormat cFmt = NumberFormat.getCurrencyInstance();
        
        setText("<html>" +
                "Start: " + dFmt.format(timeframe.getStartDate()) + ", " +
                            tFmt.format(timeframe.getStartTime()) + "<br>" +
                "End: "   + dFmt.format(timeframe.getEndDate())   + ", " +
                            tFmt.format(timeframe.getEndTime())   + "<br>" +
                "Cost: " +  cFmt.format(timeframe.getCost()) +
                "</html>");
        
        if (timeframe.isReserved())
            setForeground(Color.RED);
        
        return this;
    }
}