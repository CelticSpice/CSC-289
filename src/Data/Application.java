/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    public static void main(String[] args) throws SQLException, IOException,
                                                  NoSuchAlgorithmException,
                                                  ParserConfigurationException,
                                                  TransformerException
    {
        SystemUtil.initResources();
        System.out.println(XMLParser.parseAdminGetEmailProps(XMLUtil
                .getAdminGetEmailNodeList(SystemUtil.getEmailFile())));
    }
}