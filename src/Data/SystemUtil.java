/**
    System utility
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.prefs.BackingStoreException;

public class SystemUtil
{
    // Fields
    private static final String SALT = "ShaBuzz556qle+7d??754!+Rw5ar?";
    
    private static final SHA256SaltHasher SALT_HASHER =
            new SHA256SaltHasher(SALT);
    
    private static final SystemPreferences PREFS =
            new SystemPreferences("EventReservationSystem");
    
    /**
        GetAdminGetAddress - Return the address for the administrator to
        receive email at
    
        @return The address for the administrator to receive email at
    */
    
    public static String getAdminGetAddress()
    {
        return PREFS.getAdminGetAddress();
    }
    
    /**
        GetAdminSMTPProperties - Return properties of the administrator's
        SMTP server setup
    
        @return props Properties of the administrator's SMTP server setup
    */
    
    public static SMTPProperties getAdminSMTPProperties()
    {
        return PREFS.getAdminSMTPProperties();
    }
    
    /**
        GetDBPass - Return the password for the database
    
        @return The password for the database
    */
    
    public static String getDBPass()
    {
        return PREFS.getDBPass();
    }
    
    /**
        GetDBUser - Return the username for the database
    
        @return The username for the database
    */
    
    public static String getDBUser()
    {
        return PREFS.getDBUser();
    }
    
    /**
        GetGuestSMTPProperties - Return properties of the guest's
        SMTP server setup
    
        @return props Properties of the guest's SMTP server setup
    */
    
    public static SMTPProperties getGuestSMTPProperties()
    {
        return PREFS.getGuestSMTPProperties();
    }
    
    /**
        SetAdminGetAddress - Set the address the administrator will receive
        emails at
    
        @param address Address the administrator will receive emails at
    */
    
    public static void setAdminGetAddress(String address)
    {
        PREFS.setAdminGetAddress(address);
    }
    
    /**
        SetAdminSMTPPrefs - Set preferences for the administrator's SMTP server
    
        @param props Preferences of the administrator's SMTP server
    */
    
    public static void setAdminSMTPPrefs(SMTPProperties props)
    {
        PREFS.setAdminSMTPPrefs(props);
    }
    
    /**
        SetDBPass - Set the password for the database
    
        @param pass The password for the database
    */
    
    public static void setDBPass(String pass)
    {
        PREFS.setDBPass(pass);
    }
    
    /**
        SetDBUser - Set the username for the database
    
        @param user The username for the database
    */
    
    public static void setDBUser(String user)
    {
        PREFS.setDBUser(user);
    }
    
    /**
        SetGuestSMTPPrefs - Set preferences for the guest's SMTP server
    
        @param props Preferences of the guest's SMTP server
    */
    
    public static void setGuestSMTPPrefs(SMTPProperties props)
    {
        PREFS.setGuestSMTPPrefs(props);
    }
    
    /**
        UpdateAdminPassword - Update the administrator password
    
        @param pass The updated password
        @throws NoSuchAlgorithmException Error hashing the password
    */
    
    public static void updateAdminPassword(String pass)
            throws NoSuchAlgorithmException
    {
        PREFS.updateAdminPassword(SALT_HASHER.saltHash(pass));
    }
    
    /**
        InitPreferences - Initialize the system preferences
    
        @throws BackingStoreException Error communicating with preferences
    */
    
    public static void initPreferences() throws BackingStoreException
    {
        PREFS.init();
    }
    
    /**
        ValidateAdminPassword - Validate the given string against the
        administrator's password
    
        @param pass The password to validate
        @throws NoSuchAlgorithmException Error hashing password
        @return Whether the string matches the administrator's password
    */
    
    public static boolean validateAdminPassword(String pass)
            throws NoSuchAlgorithmException
    {
        return PREFS.validateAdminPassword(SALT_HASHER.saltHash(pass));
    }
}