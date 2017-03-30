/**
    Utility for sending email
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil
{
    /**
        EmailAdmin - Send an email to the administrator of the system
    
        @param subject The subject
        @param senderName Name of sender
        @param senderAddress Email address of sender
        @param body Body of message
        @throws AddressException Error parsing addresses
        @throws MessagingException Error sending message
        @throws UnsupportedEncodingException Failed to encode name in address
    */
    
    public static void emailAdmin(String subject, String senderName,
        String senderAddress, String body)
            throws AddressException, MessagingException,
                UnsupportedEncodingException
    {
        // Build address to display in message
        InternetAddress address = new InternetAddress(senderAddress);
        if (senderName != null && !senderName.isEmpty())
            address.setPersonal(senderName);
        
        // Get the properties for the guest to send email
        SystemPreferences prefs = new SystemPreferences();
        SMTPProperties props = prefs.getGuestSMTPProperties();
        
        // Get address, username, password from properties
        InternetAddress from = new InternetAddress(props.getAddress());
        String username = props.getUser();
        String password = props.getPassword();
        
        // Get the address for the administrator to receive email at
        InternetAddress to = new InternetAddress
            (prefs.getAdminGetAddress());
        
        // Prepare SMTP properties for session
        Properties smtpProps = props.asSessionProperties();
        
        // Build authenticator
        Authenticator auth = null;
        if (smtpProps.containsKey("mail.smtp.auth"))
        {
            auth = new Authenticator()
            {
                private PasswordAuthentication pa = new PasswordAuthentication
                        (username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }
        
        // Create session
        Session session = Session.getInstance(smtpProps, auth);
        
        // Create & send message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(from);
        message.setRecipient(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText("Message from guest " + address.toString() +
                "\n\n" + body);
        Transport.send(message);
    }
    
    /**
        EmailReserver - Send an email to a reserver
    
        @param reserver The reserver to send an email message to
        @param subject The subject of the message
        @param body The body of the message
        @throws AddressException Error parsing addresses
        @throws MessagingException Error sending message
    */
    
    public static void emailReserver(Reserver reserver, String subject,
                                     String body)
            throws AddressException, MessagingException
    {
        // Get the properties for the admin to send email
        SystemPreferences prefs = new SystemPreferences();
        SMTPProperties props = prefs.getAdminSMTPProperties();
        
        // Get address, username, password from properties
        InternetAddress from = new InternetAddress(props.getAddress());
        String username = props.getUser();
        String password = props.getPassword();
        
        // Get the address for the reserver to receive email at
        InternetAddress to = new InternetAddress(reserver.getEmailAddress());
        
        // Prepare SMTP properties for session
        Properties smtpProps = props.asSessionProperties();
        
        // Build authenticator
        Authenticator auth = null;
        if (smtpProps.containsKey("mail.smtp.auth"))
        {
            auth = new Authenticator()
            {
                private PasswordAuthentication pa = new PasswordAuthentication
                        (username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }
        
        // Create session
        Session session = Session.getInstance(smtpProps, auth);
        
        // Create & send message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(from);
        message.setRecipient(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(body);
        Transport.send(message);
    }
}