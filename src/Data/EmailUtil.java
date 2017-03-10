/**
    Utility for sending email
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.IOException;
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;

public class EmailUtil
{
    /**
        EmailAdmin - Send an email to the administrator of the system, with
        information parsed from the email XML file
    
        @param name Name of sender
        @param address Provided email address of sender
        @param body Body of message
        @throws AddressException Error parsing provided address
    */
    
    public static void emailAdmin(String name, String address, String body)
            throws AddressException, IOException, ParserConfigurationException
    {
        NodeList nodeList = XMLUtil
            .getAdminGetEmailNodeList(SystemUtil
                .getEmailFile());
        
        Properties adminGetEmailProps = XMLParser
                .parseAdminGetEmailProps(nodeList);
        
        
    }
}