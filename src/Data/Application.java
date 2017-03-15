/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import Exception.RecordExistsException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.prefs.BackingStoreException;

public class Application
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args)
            throws BackingStoreException, SQLException, RecordExistsException
    {
        SystemUtil.initPreferences();
        ReserveDB.getInstance();
    }
}