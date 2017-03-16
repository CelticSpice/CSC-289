/**
    Utility for working with XML
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLUtil
{
    /**
        BuildAdminEmailInfo - Build the administrator's portion of the XML file
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
        Element sendAddress = doc.createElement("SendAddress");
        sendAddress.appendChild(doc.createTextNode("foo@bar.com"));
        adminRoot.appendChild(sendAddress);
        
        Element host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        adminRoot.appendChild(host);
        
        Element security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("TLS|None"));
        adminRoot.appendChild(security);
        
        Element port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        adminRoot.appendChild(port);
        
        Element user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        adminRoot.appendChild(user);
        
        Element pass = doc.createElement("Pass");
        pass.appendChild(doc.createCDATASection("PasswordHere"));
        adminRoot.appendChild(pass);

        // Construct element containing information to receive email
        Element getAddress = doc.createElement("GetAddress");
        getAddress.appendChild(doc.createTextNode("foo@bar.com"));
        adminRoot.appendChild(getAddress);
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
        Element sendAddress = doc.createElement("SendAddress");
        sendAddress.appendChild(doc.createTextNode("foo@bar.com"));
        guestRoot.appendChild(sendAddress);
        
        Element host = doc.createElement("Host");
        host.appendChild(doc.createTextNode("smtp.foo.bar"));
        guestRoot.appendChild(host);
        
        Element security = doc.createElement("Security");
        security.appendChild(doc.createTextNode("TLS|None"));
        guestRoot.appendChild(security);
        
        Element port = doc.createElement("Port");
        port.appendChild(doc.createTextNode("587"));
        guestRoot.appendChild(port);
        
        Element user = doc.createElement("User");
        user.appendChild(doc.createTextNode("Mr. Foobar"));
        guestRoot.appendChild(user);
        
        Element pass = doc.createElement("Pass");
        pass.appendChild(doc.createCDATASection("PasswordHere"));
        guestRoot.appendChild(pass);
    }
    
    /**
        InitEmailXMLFile - Create the XML file containing email info
    
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
    
    /**
        InitEmailXSDFile - Create the XSD file describing the email XML file
    
        @param xsd The XSD file to contain the description of the email XML file
        @throws TransformerException Error creating the XSD file
        @throws ParserConfigurationException Bad internal configuration
    */
    
    
    
    /**
        ValidateXML - Validate the given XML file with the given XSD
    
        @param xml The XML file to validate
        @param xsd The XSD file to validate the XML file against
        @throws IOException Error reading XML or XSD file
        @throws SAXException Internal error
        @return valid If the XML file conforms to the XSD
    */
    
    public static boolean validateXML(File xml, File xsd)
            throws IOException, SAXException
    {
        SchemaFactory sf = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(xsd);
        Validator validator = schema.newValidator();
        
        boolean valid = false;
        
        try
        {
            validator.validate(new StreamSource(xml));
            valid = true;
        }
        catch (SAXException ex)
        {
            System.err.println(ex);
        }
        
        return valid;
    }
}