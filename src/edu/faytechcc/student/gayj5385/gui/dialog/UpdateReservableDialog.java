/**
    Dialog for updating a reservable
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.gayj5385.gui.dialog;

import edu.faytechcc.student.burnst9091.data.Reservable;
import javax.swing.JDialog;

public class UpdateReservableDialog extends JDialog
{
    /**
        Constructs a new UpdateReservableDialog
    
        @param reservable Reservable being updated
    */
    
    public UpdateReservableDialog(Reservable reservable)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Update Reservable");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}