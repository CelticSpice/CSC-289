/**
    Utility for reading & writing XML
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtil
{
    /**
        BuildAdminEmailInfo - Build the admin's portion of the XML file
        containing email info
        
        @param doc The document object
        @param root The root element of the document
    */
    
    private static void buildAdminEmailInfo(Document doc, Element root)
    {
        // Root admin element
        Element adminRoot = doc.createElement("Admin");
        root.appendChild(adminRoot);
        
        // SendEmail element
        Element sendEmail = doc.createElement("SendEmail");
        adminRoot.appendChild(sendEmail);
        
        // Construct SendEmail element
        Element address = doc.createElement("Address");
        address.appendChild(doc.createTextNode("foo@bar.com"));
        sendEmail.appendChild(address);
        
        Element host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        sendEmail.appendChild(host);
        
        Element security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("SSL|TLS"));
        sendEmail.appendChild(security);
        
        Element port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        sendEmail.appendChild(port);
        
        Element user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        sendEmail.appendChild(user);
        
        Element pass = doc.createElement("Pass");
        pass.appendChild(doc.createTextNode("password"));
        sendEmail.appendChild(pass);

        // GetEmail element
        Element getEmail = doc.createElement("GetEmail");
        adminRoot.appendChild(getEmail);
        
        // Construct GetEmail element
        address = doc.createElement("Address");
        address.appendChild(doc.createTextNode("foo@bar.com"));
        getEmail.appendChild(address);
        
        host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        getEmail.appendChild(host);
        
        security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("SSL|TLS"));
        getEmail.appendChild(security);
        
        port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        getEmail.appendChild(port);
        
        user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        getEmail.appendChild(user);
        
        pass = doc.createElement("Pass");
        pass.appendChild(doc.createTextNode("password"));
        getEmail.appendChild(pass);
    }
    
    /**
        BuildGuestEmailInfo - Build the guest's portion of the XML file
        containing email info
        
        @param doc The document object
        @param root The root element of the document
    */
    
    private static void buildGuestEmailInfo(Document doc, Element root)
    {
        // Root element
        Element guestRoot = doc.createElement("Guest");
        root.appendChild(guestRoot);
        
        // SendEmail element
        Element sendEmail = doc.createElement("SendEmail");
        guestRoot.appendChild(sendEmail);
        
        // Construct SendEmail element
        Element address = doc.createElement("Address");
        address.appendChild(doc.createTextNode("foo@bar.com"));
        sendEmail.appendChild(address);
        
        Element host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        sendEmail.appendChild(host);
        
        Element security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("SSL|TLS"));
        sendEmail.appendChild(security);
        
        Element port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        sendEmail.appendChild(port);
        
        Element user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        sendEmail.appendChild(user);
        
        Element pass = doc.createElement("Pass");
        pass.appendChild(doc.createTextNode("password"));
        sendEmail.appendChild(pass);
    }
    
    /**
        GetAdminGetEmailNodeList - Return the list of nodes with information on
        the administrator's address & host to receive email on
    
        @param xml XML file containing the info
        @throws IOException Failed reading XML file
        @throws ParserConfigurationException Bad internal configuration
        @return list List of nodes with information on administrator's address
                     & host to receive email on
    */
    
    public static NodeList getAdminGetEmailNodeList(File xml)
            throws IOException, ParserConfigurationException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        NodeList list = null;
        
        try
        {
            Document doc = db.parse(xml);
            
            list = doc.getElementsByTagName("GetEmail").item(0).getChildNodes();
        }
        catch (SAXException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        return list;
    }
    
    /**
        InitEmailXMLFile - Create the XML file containing email info
        anew
    
        @param xml The XML file to contain the email info
        @throws TransformerException Error creating the XML file
        @throws ParserConfigurationException Bad internal configuration
    */
    
    public static void initEmailXMLFile(File xml) 
            throws TransformerException, ParserConfigurationException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        
        Element root = doc.createElement("Email");
        doc.appendChild(root);
      
        buildAdminEmailInfo(doc, root);
        buildGuestEmailInfo(doc, root);
        
        TransformerFactory trf = TransformerFactory.newInstance();
        Transformer tr = trf.newTransformer();
        
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.setOutputProperty(OutputKeys.METHOD, "xml");
        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xml);
        
        tr.transform(source, result);
    }
}