/**
    System utility
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class SystemUtil
{
    // Fields
    private static final String SALT = "ShaBuzz556qle+7d??754!+Rw5ar?";
    private static final String PASSWD_FILE = "passwd";
    private static final String EMAIL_FILE = "email.xml";
    private static final String EMAIL_XSD_FILE = "email.xsd";
    private static final String DEFAULT_PASSWD = "PASSWORD";
    
    /**
        UpdatePassword - Update the administrator password
    
        @param password The updated password
        @throws IOException Error updating password file
        @throws NoSuchAlgorithmException Error hashing the password
    */
    
    public static void updatePassword(String password)
            throws IOException, NoSuchAlgorithmException
    {
        FileWriter writer = new FileWriter(new File(PASSWD_FILE));
        SHA256SaltHasher hasher = new SHA256SaltHasher(SALT);
        writer.write(hasher.saltHash(password));
        writer.close();
    }
    
    /**
        GetEmailFile - Return the email XML file
    
        @return The email XML file
    */
    
    public static File getEmailFile()
    {
        return new File(EMAIL_FILE);
    }
    
    /**
        InitResources - Initialize the system resources
        @throws IOException Error creating password file
        @throws NoSuchAlgorithmException Error hashing the password
        @throws ParserConfigurationException Error initializing email XML file
        @throws TransformerException Error initializing email XML file
        @throws SAXException Bad email XML file
    */
    
    public static void initResources() 
            throws IOException, NoSuchAlgorithmException,
                    ParserConfigurationException, TransformerException,
                    SAXException
    {
        File passwdFile = new File(PASSWD_FILE);
        if (passwdFile.createNewFile())
        {
            FileWriter writer = new FileWriter(passwdFile);
            SHA256SaltHasher hasher = new SHA256SaltHasher(SALT);
            writer.write(hasher.saltHash(DEFAULT_PASSWD));
            writer.close();
        }
        
        File emailFile = new File(EMAIL_FILE);
        if (emailFile.createNewFile())
            XMLUtil.initEmailXMLFile(emailFile);
        else
            XMLUtil.validateXML(new File(EMAIL_FILE), new File(EMAIL_XSD_FILE));
    }
    
    /**
        ValidatePassword - Validate that the given string matches the password
        contained in the password file
    
        @param pass The password to validate
        @throws FileNotFoundException Error reading password file
        @throws NoSuchAlgorithmException Error hashing password
        @return valid Whether the password matches the password in the password
                      file
    */
    
    public static boolean validatePassword(String pass)
            throws FileNotFoundException, NoSuchAlgorithmException
    {
        Scanner passwdFile = new Scanner(new File(PASSWD_FILE));
        SHA256SaltHasher hasher = new SHA256SaltHasher(SALT);
        boolean valid = hasher.saltHash(pass).equals(passwdFile.nextLine());
        passwdFile.close();
        return valid;
    }
}