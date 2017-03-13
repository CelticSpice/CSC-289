/**
    The administrator of the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import Exception.RecordExistsException;
import java.io.IOException;
import java.sql.SQLException;
import javax.mail.internet.AddressException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class Admin
{
    /**
        AddReservable - Add a new reservable that can be reserved
    
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
        EmailReserver - Send an email to a reserver
    
        @param reserver The reserver to email
        @param subject The subject of the email
        @param message The message to send
        @throws AddressException Malformed email address
        @throws IOException Error reading email information from email XML file
        @throws ParserConfigurationException Error reading email information
                                             from email XML file
        @throws SAXException Bad internal configuration for XML parser
        @throws XPathExpressionException Error parsing information from email
                                         XML file
    */
    
    public static void emailReserver(Reserver reserver, String subject,
                                     String message)
            throws AddressException, IOException, ParserConfigurationException,
                   SAXException, XPathExpressionException
    {
        EmailUtil.emailReserver(reserver, subject, message);
    }
}