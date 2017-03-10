/**
    An email
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Email
{
    // Fields
    private InternetAddress from, to;
    private String body, subject;
    
    /**
        Constructor - Accepts the sender, recipient, body, & subject of the
        email
    
        @param sender Address from
        @param recipient Address to
        @param message Message body
        @param subj Email subject
        @throws AddressException Error parsing the from and/or to addresses
    */
    
    public Email(String sender, String recipient, String message, String subj)
            throws AddressException
    {
        from = new InternetAddress(sender);
        to = new InternetAddress(recipient);
        body = message;
        subject = subj;
    }
    
    /**
        GetBody - Return the body of the email
    
        @return The body of the email
    */
    
    public String getBody()
    {
        return body;
    }
    
    /**
        GetRecipient - Return the recipient of the email
    
        @return The recipient of the email
    */
    
    public InternetAddress getRecipient()
    {
        return to;
    }
    
    /**
        GetSender - Return the sender of the email
    
        @return The sender of the email
    */
    
    public InternetAddress getSender()
    {
        return from;
    }
    
    /**
        GetSubject - Return the subject of the email
    
        @return The subject of the email
    */
    
    public String getSubject()
    {
        return subject;
    }
}