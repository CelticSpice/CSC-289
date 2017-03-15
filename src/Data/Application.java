/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.time.ZonedDateTime;
import java.util.prefs.BackingStoreException;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args) throws BackingStoreException
    {
        SystemUtil.initPreferences();
    }
}