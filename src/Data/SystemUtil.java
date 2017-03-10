/**
    System utilities & resources
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

public class SystemUtil
{
    // Fields
    private static final File PASSWD_FILE = new File("passwd");
    private static final File EMAIL_FILE = new File("email.xml");
    private static final SaltHasher SALT_HASHER = new SaltHasher();
    private static final String DEFAULT_PASSWD = "PASSWORD";
    
    /**
        UpdatePassword - Update the admin password
    
        @param password The updated password
        @throws IOException Error updating password file
        @throws NoSuchAlgorithmException Error hashing the password
    */
    
    public static void updatePassword(String password)
            throws IOException, NoSuchAlgorithmException
    {
        FileWriter writer = new FileWriter(PASSWD_FILE);
        writer.write(SALT_HASHER.saltHash(password));
        writer.close();
    }
    
    /**
        GetEmailFile - Return the email XML file
    
        @return The email XML file
    */
    
    public static File getEmailFile()
    {
        return EMAIL_FILE;
    }
    
    /**
        InitResources - Initialize the system resources
        @throws IOException Error creating password file
        @throws NoSuchAlgorithmException Error hashing the password
        @throws ParserConfigurationException Error initializing email XML file
        @throws TransformerException Error initializing email XML file
    */
    
    public static void initResources() 
            throws IOException, NoSuchAlgorithmException,
                    ParserConfigurationException, TransformerException
    {
        if (PASSWD_FILE.createNewFile())
        {
            FileWriter writer = new FileWriter(PASSWD_FILE);
            writer.write(SALT_HASHER.saltHash(DEFAULT_PASSWD));
            writer.close();
        }
        
        if (EMAIL_FILE.createNewFile())
            XMLUtil.initEmailXMLFile(EMAIL_FILE);
    }
}