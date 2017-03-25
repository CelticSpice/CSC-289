/**
    Renderer for a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.renderer;

import edu.faytechcc.student.burnst9091.data.Reservation;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ReservationRenderer extends DefaultListCellRenderer
{
    @Override
    public Component getListCellRendererComponent(JList<?> list,
        Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);
        
        Reservation reservation = (Reservation) value;
        DateTimeFormatter dFmt = DateTimeFormatter.ofPattern("y-MMM-d");
        DateTimeFormatter tFmt = DateTimeFormatter.ofPattern("H:mm");
        
        setText("<html>" +
                "Start: " + dFmt.format(reservation.getStartDate()) + ", " +
                            tFmt.format(reservation.getStartTime()) + "<br>" +
                "End: "   + dFmt.format(reservation.getEndDate())   + ", " +
                            tFmt.format(reservation.getEndTime())   + "<br>" +
                "Event: " + reservation.getEventType() +
                "</html>");
        
        if (reservation.isReviewed())
            setForeground(Color.GREEN);
        
        return this;
    }
}