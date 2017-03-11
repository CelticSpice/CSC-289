/**
    A parser for XML
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser
{    
    /**
        ParseAdminGetAddress - Parse XML file containing the administrator's
        email address to receive email with
    
        @param xml XML file containing the administrator's email address to
                   receive email on
        @throws ParserConfigurationException Bad internal configuration
        @throws SAXException Failed reading XML file
        @throws IOException Failed reading XML file
        @throws XPathExpressionException Expressions malformed
        @return address The address the administrator received email with
    */
    
    public static String parseAdminGetAddress(File xml)
            throws ParserConfigurationException, SAXException, IOException,
                   XPathExpressionException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xml);
        
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        
        return xPath.evaluate("/Email/Admin/GetAddress", doc).trim();
    }
    
    /**
        ParseSendEmailProps - Parse XML file containing properties
        on the either the administrator or guest's email address & host to send
        email to the administrator with
    
        @param xml XML file containing properties on the guest's email
                   address & host to send email to the administrator with
        @param user The user to get the email properties of, either admin or
                    guest
        @throws ParserConfigurationException Bad internal configuration
        @throws SAXException Failed reading XML file
        @throws IOException Failed reading XML file
        @throws XPathExpressionException Expressions malformed
        @return props A list of the properties parsed
    */
    
    public static Properties parseSendEmailProps(File xml, String user)
            throws ParserConfigurationException, SAXException, IOException,
                   XPathExpressionException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xml);
        
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        
        Properties props = new Properties();
        
        // Ensure proper case
        user = user.toLowerCase();
        char[] userChars = user.toCharArray();
        userChars[0] = Character.toUpperCase(userChars[0]);
        user = String.valueOf(userChars);
        
        // Information to get
        String address, host, security, port, userName, pass;
        
        // Get address
        XPathExpression expr = xPath.compile("/Email/" + user + "/SendAddress");
        address = expr.evaluate(doc).trim();
        props.put("Address", address);
        
        // Get host
        expr = xPath.compile("/Email/" + user + "/Host");
        host = expr.evaluate(doc).trim();
        props.put("Host", host);
        
        // Get security
        expr = xPath.compile("/Email/" + user + "/Security");
        security = expr.evaluate(doc).trim();
        props.put("Security", security);
        
        // Get port
        expr = xPath.compile("/Email/" + user + "/Port");
        port = expr.evaluate(doc).trim();
        props.put("Port", port);
        
        // Get user
        expr = xPath.compile("/Email/" + user + "/User");
        userName = expr.evaluate(doc).trim();
        props.put("User", userName);
        
        // Get pass
        expr = xPath.compile("/Email/" + user + "/Pass");
        pass = expr.evaluate(doc).trim();
        props.put("Pass", pass);
        
        return props;
    }
}