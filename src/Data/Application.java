/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args) throws BackingStoreException,
                AddressException, MessagingException, UnsupportedEncodingException
    {
        SystemUtil.initPreferences();
    }
}