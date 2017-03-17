/**
    The email preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

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
        return prefs.get("AdminGetAddress", "foo@bar.com");
    }
    
    /**
        GetAdminSMTPProperties - Return properties of the administrator's
        SMTP server setup
    
        @return props Properties of the administrator's SMTP server setup
    */
    
    public SMTPProperties getAdminSMTPProperties()
    {
        SMTPProperties props = new SMTPProperties();
        props.setAddress(prefs.get("AdminSendAddress", "foo@bar.com"));
        props.setHost(prefs.get("AdminSMTPHost", "smtp.foo.com"));
        
        props.setSecurity(SecurityOption.valueOf
            (prefs.get("AdminSMTPSecurity", "NONE")));
        
        props.setPort(prefs.get("AdminSMTPPort", "587"));
        props.setUser(prefs.get("AdminSMTPUser", "foobar"));
        props.setPassword(prefs.get("AdminSMTPPass", "password"));
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
        props.setAddress(prefs.get("GuestSendAddress", "foo@bar.com"));
        props.setHost(prefs.get("GuestSMTPHost", "smtp.foo.com"));
        props.setSecurity(SecurityOption.valueOf
            (prefs.get("GuestSMTPSecurity", "NONE")));
        props.setPort(prefs.get("GuestSMTPPort", "587"));
        props.setUser(prefs.get("GuestSMTPUser", "foobar"));
        props.setPassword(prefs.get("GuestSMTPPass", "password"));
        return props;
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
        prefs.put("AdminSMTPSecurity", props.getSecurity().toString());
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
        prefs.put("GuestSMTPSecurity", props.getSecurity().toString());
        prefs.put("GuestSMTPPort", props.getPort());
        prefs.put("GuestSMTPUser", props.getUser());
        prefs.put("GuestSMTPPass", props.getPassword());
    }
}