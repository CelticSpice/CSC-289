/**
    Utility for sending email
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.File;
import java.io.IOException;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class EmailUtil
{
    /**
        EmailAdmin - Send an email to the administrator of the system, with
        information parsed from the email XML file
    
        @param name Name of sender
        @param address Email address of sender
        @param body Body of message
        @throws AddressException Error parsing addresses
    */
    
    public static void emailAdmin(String name, String address, String body)
            throws AddressException, IOException, ParserConfigurationException,
                    SAXException, XPathExpressionException
    {
        InternetAddress guestFrom = new InternetAddress(address);
        if (name != null && !name.isEmpty())
            guestFrom.setPersonal(name);
        
        // Get the properties for the guest to send email
        File emailXML = SystemUtil.getEmailFile();
        Properties props = XMLParser.parseSendEmailProps(emailXML, "Guest");
        
        // Get address, username, password from properties
        InternetAddress from = new InternetAddress(props
                .getProperty("Address"));
        String username = props.getProperty("User");
        String password = props.getProperty("Pass");
        
        // Transform the properties
        props = transformProps(props);
        
        // Get the address for the administrator to receive email at
        InternetAddress to = new InternetAddress(XMLParser
                .parseAdminGetAddress(emailXML));
        
        // Build authenticator
        Authenticator auth = new Authenticator() {
            private PasswordAuthentication pa = new PasswordAuthentication
                    (username, password);
            
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        };
        
        // Create session
        Session session = Session.getInstance(props, auth);
        
        // Create & send message
        MimeMessage message = new MimeMessage(session);
        try
        {
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject("Message from Guest " + name);
            message.setSentDate(new Date());
            message.setText("Message from " + guestFrom.toString() +
                    "\n\n" + body);
            Transport.send(message);
        }
        catch (MessagingException ex)
        {
            System.err.println(ex);
        }
        
    }
    
    /**
        TransformProps - Transform email properties into names suitable for
        email
    
        @param props Email properties to transform
        @return transformedProps Properties transformed from the given
                                 properties
    */
    
    private static Properties transformProps(Properties props)
    {
        Properties transformedProps = new Properties();
        
        if (!props.getProperty("Security").equals("NONE"))
        {
            transformedProps.setProperty("mail.smtp.auth", "true");
            transformedProps.setProperty("mail.smtp.starttls.enable", "true");
        }
        
        transformedProps
                .setProperty("mail.smtp.host", props.getProperty("Host"));
        transformedProps
                .setProperty("mail.smtp.port", props.getProperty("Port"));
        
        return transformedProps;
    }
}