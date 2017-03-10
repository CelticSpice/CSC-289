/**
    A parser for XML
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.Properties;
import org.w3c.dom.NodeList;

public class XMLParser
{    
    /**
        ParseAdminGetEmailProps - Parse XML nodelist containing properties
        on the administrator's email address & host to receive email on
    
        @param nl Nodelist containing properties on the administrator's email
                  address & host to receive email on
        @return props A list of the properties parsed
    */
    
    public static Properties parseAdminGetEmailProps(NodeList nl)
    {
        final String[] PROPS_TO_READ = { "Address", "Host", "Security",
                                         "Port", "User", "Pass" };     
        
        Properties props = new Properties();
        
        int prop = 0;
        
        for (int i = 0; i < nl.getLength() && prop < PROPS_TO_READ.length; i++)
        {
            if (nl.item(i).hasChildNodes())
            {
                props.put(PROPS_TO_READ[prop++], nl.item(i)
                        .getFirstChild().getNodeValue());
            }
        }
        
        return props;
    }
}