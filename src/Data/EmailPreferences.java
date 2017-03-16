/**
    The email preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class EmailPreferences
{
    // Fields
    private Preferences prefs;
    
    /**
        Constructor - Accepts the location of the email preferences
    
        @param prefsLocation The location of the email preferences
    */
    
    public EmailPreferences(String prefsLocation)
    {
        prefs = Preferences.userRoot().node(prefsLocation);
    }
    
    /**
        GetAdminGetAddress - Return the address the administrator will receive
        email at
    
        @return The address the administrator will receive email at
    */
    
    public String getAdminGetAddress()
    {
        return prefs.get("AdminGetAddress", "");
    }
    
    /**
        GetAdminSMTPProperties - Return properties of the administrator's
        SMTP server setup
    
        @return props Properties of the administrator's SMTP server setup
    */
    
    public Properties getAdminSMTPProperties()
    {
        Properties props = new Properties();
        props.put("Address", prefs.get("AdminSendAddress", ""));
        props.put("Host", prefs.get("AdminSMTPHost", ""));
        props.put("Security", prefs.get("AdminSMTPSecurity", ""));
        props.put("Port", prefs.get("AdminSMTPPort", ""));
        props.put("User", prefs.get("AdminSMTPUser", ""));
        props.put("Pass", prefs.get("AdminSMTPPass", ""));
        return props;
    }
    
    /**
        GetGuestSMTPProperties - Return properties of the guest's
        SMTP server setup
    
        @return props Properties of the guest's SMTP server setup
    */
    
    public Properties getGuestSMTPProperties()
    {
        Properties props = new Properties();
        props.put("Address", prefs.get("GuestSendAddress", ""));
        props.put("Host", prefs.get("GuestSMTPHost", ""));
        props.put("Security", prefs.get("GuestSMTPSecurity", ""));
        props.put("Port", prefs.get("GuestSMTPPort", ""));
        props.put("User", prefs.get("GuestSMTPUser", ""));
        props.put("Pass", prefs.get("GuestSMTPPass", ""));
        return props;
    }
    
    /**
        Init - Initialize the email preferences with default values
        
        @throws BackingStoreException Error communicating with preferences
    */
    
    public void init() throws BackingStoreException
    {
        if (!prefs.nodeExists("AdminGetAddress"))
            prefs.put("AdminGetAddress", "foo@bar.com");
        
        if (!prefs.nodeExists("AdminSendAddress"))
            prefs.put("AdminSendAddress", "foo@bar.com");
        
        if (!prefs.nodeExists("AdminSMTPHost"))
            prefs.put("AdminSMTPHost", "smtp.foo.com");
        
        if (!prefs.nodeExists("AdminSMTPSecurity"))
            prefs.put("AdminSMTPSecurity", "SSL|TLS|None");
        
        if (!prefs.nodeExists("AdminSMTPPort"))
            prefs.put("AdminSMTPPort", "587");
        
        if (!prefs.nodeExists("AdminSMTPUser"))
            prefs.put("AdminSMTPUser", "foobar");
        
        if (!prefs.nodeExists("AdminSMTPPass"))
            prefs.put("AdminSMTPPass", "password");
        
        if (!prefs.nodeExists("GuestSendAddress"))
            prefs.put("GuestSendAddress", "foo@bar.com");
        
        if (!prefs.nodeExists("GuestSMTPHost"))
            prefs.put("GuestSMTPHost", "smtp.foo.com");
        
        if (!prefs.nodeExists("GuestSMTPSecurity"))
            prefs.put("GuestSMTPSecurity", "SSL|TLS|None");
        
        if (!prefs.nodeExists("GuestSMTPPort"))
            prefs.put("GuestSMTPPort", "587");
        
        if (!prefs.nodeExists("GuestSMTPUser"))
            prefs.put("GuestSMTPUser", "foobar");
        
        if (!prefs.nodeExists("GuestSMTPPass"))
            prefs.put("GuestSMTPPass", "password");
    }
    
    /**
        SetAdminGetAddress - Set the address the administrator will receive
        emails at
    
        @param address Address the administrator will receive emails at
    */
    
    public void setAdminGetAddress(String address)
    {
        prefs.put("AdminGetAddress", address);
    }
    
    /**
        SetAdminSMTPPrefs - Set preferences for the administrator's SMTP server
    
        @param props Preferences of the administrator's SMTP server
    */
    
    public void setAdminSMTPPrefs(Properties props)
    {
        prefs.put("AdminSendAddress", props.getProperty("Address", ""));
        prefs.put("AdminSMTPHost", props.getProperty("Host", ""));
        prefs.put("AdminSMTPSecurity", props.getProperty("Security", ""));
        prefs.put("AdminSMTPPort", props.getProperty("Port", ""));
        prefs.put("AdminSMTPUser", props.getProperty("User", ""));
        prefs.put("AdminSMTPPass", props.getProperty("Pass", ""));
    }
    
    /**
        SetGuestSMTPPrefs - Set preferences for the guest's SMTP server
    
        @param props Preferences of the guest's SMTP server
    */
    
    public void setGuestSMTPPrefs(Properties props)
    {
        prefs.put("GuestSendAddress", props.getProperty("Address", ""));
        prefs.put("GuestSMTPHost", props.getProperty("Host", ""));
        prefs.put("GuestSMTPSecurity", props.getProperty("Security", ""));
        prefs.put("GuestSMTPPort", props.getProperty("Port", ""));
        prefs.put("GuestSMTPUser", props.getProperty("User", ""));
        prefs.put("GuestSMTPPass", props.getProperty("Pass", ""));
    }
}