/**
    A resource for the event reservation system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class SystemResource
{
    // Fields
    private static final File PASSWD_FILE = new File("passwd");
    private static final String DEFAULT_PASSWD = "PASSWORD";
    private static final File EMAIL_FILE = new File("email.xml");
    
    /**
        InitResources - Initialize the system resources
        @throws IOException Error initialize system resources
        @throws NoSuchAlgorithmException Error hashing the password
        @throws ParserConfigurationException Error initializing email XML
        @throws TransformerException Error initializing email XML
    */
    
    public static void initResources() throws IOException,
                                              NoSuchAlgorithmException,
                                              ParserConfigurationException,
                                              TransformerException
    {
        if (PASSWD_FILE.createNewFile())
        {
            FileWriter writer = new FileWriter(PASSWD_FILE);
            writer.write(PasswordHasher.hash(DEFAULT_PASSWD));
            writer.close();
        }
        
        if (EMAIL_FILE.createNewFile())
            XMLUtil.initEmailXMLFile(EMAIL_FILE);
    }
}