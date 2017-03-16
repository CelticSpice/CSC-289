/**
    Preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SystemPreferences
{
    // Fields
    private EmailPreferences emailPrefs;
    private Preferences prefs;
    
    /**
        Constructor - Accepts the root node of the system preferences
    
        @param root Root node of system preferences
    */
    
    public SystemPreferences(String root)
    {
        prefs = Preferences.userRoot().node(root);
        emailPrefs = new EmailPreferences(prefs.absolutePath() + "/Email");
    }
    
    /**
        GetAdminGetAddress - Return the address for the administrator to
        receive email at
    
        @return The address for the administrator to receive email at
    */
    
    public String getAdminGetAddress()
    {
        return emailPrefs.getAdminGetAddress();
    }
    
    /**
        GetAdminSMTPProperties - Return properties of the administrator's
        SMTP server setup
    
        @return props Properties of the administrator's SMTP server setup
    */
    
    public Properties getAdminSMTPProperties()
    {
        return emailPrefs.getAdminSMTPProperties();
    }
    
    /**
        GetDBPass - Return the password for the database
    
        @return The password for the database
    */
    
    public String getDBPass()
    {
        return prefs.get("DBPass", "");
    }
    
    /**
        GetDBUser - Return the username for the database
    
        @return The username for the database
    */
    
    public String getDBUser()
    {
        return prefs.get("DBUser", "");
    }
    
    /**
        GetGuestSMTPProperties - Return properties of the guest's
        SMTP server setup
    
        @return props Properties of the guest's SMTP server setup
    */
    
    public Properties getGuestSMTPProperties()
    {
        return emailPrefs.getGuestSMTPProperties();
    }
    
    /**
        Init - Initialize the system preferences with default values
    
        @throws BackingStoreException Error communicating with preferences
    */
    
    public void init() throws BackingStoreException
    {
        if (!prefs.nodeExists("AdminPass"))
            prefs.put("AdminPass", "");
        if (!prefs.nodeExists("DBUser"))
            prefs.put("DBUser", "Username");
        if (!prefs.nodeExists("DBPass"))
            prefs.put("DBPass", "Password");
        emailPrefs.init();
    }
    
    /**
        SetAdminGetAddress - Set the address the administrator will receive
        emails at
    
        @param address Address the administrator will receive emails at
    */
    
    public void setAdminGetAddress(String address)
    {
        emailPrefs.setAdminGetAddress(address);
    }
    
    /**
        SetAdminSMTPPrefs - Set preferences for the administrator's SMTP server
    
        @param props Preferences of the administrator's SMTP server
    */
    
    public void setAdminSMTPPrefs(Properties props)
    {
        emailPrefs.setAdminSMTPPrefs(props);
    }
    
    /**
        SetDBPass - Set the password for the database
    
        @param pass The password for the database
    */
    
    public void setDBPass(String pass)
    {
        prefs.put("DBPass", pass);
    }
    
    /**
        SetDBUser - Set the username for the database
    
        @param user The username for the database
    */
    
    public void setDBUser(String user)
    {
        prefs.put("DBUser", user);
    }
    
    /**
        SetGuestSMTPPrefs - Set preferences for the guest's SMTP server
    
        @param props Preferences of the guest's SMTP server
    */
    
    public void setGuestSMTPPrefs(Properties props)
    {
        emailPrefs.setGuestSMTPPrefs(props);
    }
    
    /**
        UpdateAdminPassword - Update the administrator's password
    
        @param pass The updated administrator password
    */
    
    public void updateAdminPassword(String pass)
    {
        prefs.put("AdminPass", pass);
    }
    
    /**
        ValidateAdminPassword - Validate a string against the stored
        administrator password
    
        @param pass The string to validate against the stored administrator
                    password
        @return Whether the string & the stored administrator password match
    */
    
    public boolean validateAdminPassword(String pass)
    {
        return pass.equals(prefs.get("AdminPass", ""));
    }
}