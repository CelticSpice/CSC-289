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
        
        // Construct elements with information on sending email
        Element address = doc.createElement("SendAddress");
        address.appendChild(doc.createTextNode("foo@bar.com"));
        adminRoot.appendChild(address);
        
        Element host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        adminRoot.appendChild(host);
        
        Element security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("SSL|TLS"));
        adminRoot.appendChild(security);
        
        Element port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        adminRoot.appendChild(port);
        
        Element user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        adminRoot.appendChild(user);
        
        Element pass = doc.createElement("Pass");
        pass.appendChild(doc.createTextNode("password"));
        adminRoot.appendChild(pass);

        // Construct element containing information to receive email
        Element getEmail = doc.createElement("GetAddress");
        getEmail.appendChild(doc.createTextNode("foo@bar.com"));
        adminRoot.appendChild(getEmail);
    }
    
    /**
        BuildGuestEmailInfo - Build the guest's portion of the XML file
        containing email info
        
        @param doc The document object
        @param root The root element of the document
    */
    
    private static void buildGuestEmailInfo(Document doc, Element root)
    {
        // Root guest element
        Element guestRoot = doc.createElement("Guest");
        root.appendChild(guestRoot);
        
        // Construct elements containing information to send email
        Element address = doc.createElement("SendAddress");
        address.appendChild(doc.createTextNode("foo@bar.com"));
        guestRoot.appendChild(address);
        
        Element host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        guestRoot.appendChild(host);
        
        Element security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("SSL|TLS"));
        guestRoot.appendChild(security);
        
        Element port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        guestRoot.appendChild(port);
        
        Element user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        guestRoot.appendChild(user);
        
        Element pass = doc.createElement("Pass");
        pass.appendChild(doc.createTextNode("password"));
        guestRoot.appendChild(pass);
    }
    
    /**
        GetAdminEmailNodeList - Return the list of nodes with information on
        the administrator's email
    
        @param xml XML file containing the info
        @throws IOException Failed reading XML file
        @throws ParserConfigurationException Bad internal configuration
        @return list List of nodes with information on administrator's email
    */
    
    public static NodeList getAdminEmailNodeList(File xml)
            throws IOException, ParserConfigurationException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        NodeList list = null;
        
        try
        {
            Document doc = db.parse(xml);
            
            list = doc.getElementsByTagName("Admin").item(0).getChildNodes();
        }
        catch (SAXException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        return list;
    }
    
    /**
        GetGuestEmailNodeList - Return the list of nodes containing
        information on the guest's email
    
        @param xml XML file containing the info
        @throws IOException Failed reading XML file
        @throws ParserConfigurationException Bad internal configuration
        @return list List of nodes with information on guest's email
    */
    
    public static NodeList getGuestEmailNodeList(File xml)
            throws IOException, ParserConfigurationException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        NodeList list = null;
        
        try
        {
            Document doc = db.parse(xml);
            
            list = doc.getElementsByTagName("Guest").item(0).getChildNodes();
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