/**
    The email preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

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
    
    public SMTPProperties getAdminSMTPProperties()
    {
        SMTPProperties props = new SMTPProperties();
        props.setAddress(prefs.get("AdminSendAddress", ""));
        props.setHost(prefs.get("AdminSMTPHost", ""));
        props.setSecurity(prefs.get("AdminSMTPSecurity", ""));
        props.setPort(prefs.get("AdminSMTPPort", ""));
        props.setUser(prefs.get("AdminSMTPUser", ""));
        props.setPassword(prefs.get("AdminSMTPPass", ""));
        return props;
    }
    
    /**
        GetGuestSMTPProperties - Return properties of the guest's
        SMTP server setup
    
        @return props Properties of the guest's SMTP server setup
    */
    
    public SMTPProperties getGuestSMTPProperties()
    {
        SMTPProperties props = new SMTPProperties();
        props.setAddress(prefs.get("GuestSendAddress", ""));
        props.setHost(prefs.get("GuestSMTPHost", ""));
        props.setSecurity(prefs.get("GuestSMTPSecurity", ""));
        props.setPort(prefs.get("GuestSMTPPort", ""));
        props.setUser(prefs.get("GuestSMTPUser", ""));
        props.setPassword(prefs.get("GuestSMTPPass", ""));
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
    
    public void setAdminSMTPPrefs(SMTPProperties props)
    {
        prefs.put("AdminSendAddress", props.getAddress());
        prefs.put("AdminSMTPHost", props.getHost());
        prefs.put("AdminSMTPSecurity", props.getSecurity());
        prefs.put("AdminSMTPPort", props.getPort());
        prefs.put("AdminSMTPUser", props.getUser());
        prefs.put("AdminSMTPPass", props.getPassword());
    }
    
    /**
        SetGuestSMTPPrefs - Set preferences for the guest's SMTP server
    
        @param props Preferences of the guest's SMTP server
    */
    
    public void setGuestSMTPPrefs(SMTPProperties props)
    {
        prefs.put("GuestSendAddress", props.getAddress());
        prefs.put("GuestSMTPHost", props.getHost());
        prefs.put("GuestSMTPSecurity", props.getSecurity());
        prefs.put("GuestSMTPPort", props.getPort());
        prefs.put("GuestSMTPUser", props.getUser());
        prefs.put("GuestSMTPPass", props.getPassword());
    }
}