/**
    A guest using the system
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

public class Guest
{
    /**
        EmailAdmin - Send an email to the administrator of the system
    
        @param name The name to include in the email
        @param address The email address that may be replied to
        @param message The message to send to the administrator
        @throws AddressException Malformed email address
        @throws IOException Error reading email information from email XML file
        @throws ParserConfigurationException Error reading email information
                                             from email XML file
        @throws SAXException Bad internal configuration for XML parser
        @throws XPathExpressionException Error parsing information from email
                                         XML file
    */
    
    public static void emailAdmin(String name, String address, String message)
            throws AddressException, IOException, ParserConfigurationException,
                   SAXException, XPathExpressionException
    {
        EmailUtil.emailAdmin(name, address, message);
    }
    
    /**
        MakeReservation - Guest makes a reservation at the given reservable
        & provides contact information, an expected number of attendees, &
        the type of event the reservation entails
    
        @param reservable The reservable the guest reserves
        @param contactInfo The guest's provided contact information
        @param attendence The expected number of attendees
        @param type The event type
        @throws SQLException Error adding the record of the reservation to the
                             database
        @throws RecordExistsException A record of an identical reservation
                                      exists in the database
        @return reservation The reservation made
    */
    
    public static Reservation makeReservation(Reservable reservable,
            ContactInfo contactInfo, int attendence, String type)
            throws SQLException, RecordExistsException
    {
        Reserver reserver = new Reserver(contactInfo);
        Reservation reservation = new Reservation(reserver, reservable,
                                                  attendence, type);
        new RecordAdd().addReservation(reservation);
        return reservation;
    }
}