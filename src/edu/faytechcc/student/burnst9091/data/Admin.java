/**
    The administrator of the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.mccanns0131.database.RecordAdd;
import edu.faytechcc.student.mccanns0131.database.RecordDelete;
import edu.faytechcc.student.burnst9091.exception.RecordExistsException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Admin
{
    /**
        Adds a new reservable that can be reserved
    
        @param reservable The new reservable
        @throws SQLException Error adding the record of the reservable to the
                             database
        @throws RecordExistsException A record of an identical reservable
                                      exists in the database
    */

    public static void addReservable(Reservable reservable) 
            throws SQLException, RecordExistsException
    {
        new RecordAdd().addReservable(reservable);
    }
    
    /**
        Cancels a reservation
    
        @param reservation Reservation to cancel
        @throws SQLException Error removing record of reservation
    */
    
    public static void cancelReservation(Reservation reservation)
            throws SQLException
    {
        reservation.cancel();
        new RecordDelete().deleteReservation(reservation);
    }

    /**
        RemoveReservable - Remove a reservable that can be reserved

        @param reservable The reservable to remove
        @throws SQLException Error removing record from database
    */

    public static void removeReservable(Reservable reservable)
            throws SQLException
    {
        new RecordDelete().deleteReservable(reservable);
    }
    
    /**
        Removes a record of a reservation
    
        @param reservation Reservation to remove
        @throws SQLException Error removing record from database
    */
    
    public static void removeReservation(Reservation reservation)
            throws SQLException
    {
        new RecordDelete().deleteReservation(reservation);
    }
    
    /**
        EmailReserver - Send an email to a reserver
    
        @param reserver The reserver to email
        @param subject The subject of the email
        @param message The message to send
        @throws AddressException Malformed email address
        @throws MessagingException Error sending message
    */
    
    public static void emailReserver(Reserver reserver, String subject,
                                     String message)
            throws AddressException, MessagingException
    {
        EmailUtil.emailReserver(reserver, subject, message);
    }
    
    /**
        UpdatePassword - Update the administrator's password
    
        @param pass The updated password
        @throws NoSuchAlgorithmException Error hashing password
    */
    
    public static void updatePassword(String pass)
            throws NoSuchAlgorithmException
    {
        SystemUtil.updateAdminPassword(pass);
    }
}
