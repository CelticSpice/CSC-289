/**
    The email preferences of the system
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
        Constructs a new EmailPreferences with preferences stored at the given
        node
    
        @param node The node of the email preferences
    */
    
    public EmailPreferences(String node)
    {
        prefs = Preferences.userRoot().node(node);
    }
    
    /**
        Returns the settings for the administrator's email
    
        @return The administrator's email settings
    */
    
    public EmailSettings getAdminEmailSettings()
    {
        EmailSettings settings = new EmailSettings();
        
        settings.setSendAddress(prefs.get("AdminSendAddress", "foo@bar.com"));
        settings.setSMTPHost(prefs.get("AdminSMTPHost", "smtp.foo.com"));
        
        settings.setSMTPSecurity(SecurityOption.valueOf
            (prefs.get("AdminSMTPSecurity", "NONE")));
        
        settings.setSMTPPort(prefs.getInt("AdminSMTPPort", 587));
        settings.setSMTPUser(prefs.get("AdminSMTPUser", "foobar"));
        settings.setSMTPPass(prefs.get("AdminSMTPPass", "password"));
        settings.setGetAddress(prefs.get("AdminGetAddress", "foo@bar.com"));
        
        return settings;
    }
    
    /**
        Returns the settings for the guest's email
    
        @return The guest's email settings
    */
    
    public EmailSettings getGuestEmailSettings()
    {
        EmailSettings settings = new EmailSettings();
        
        settings.setSendAddress(prefs.get("GuestSendAddress", "foo@bar.com"));
        settings.setSMTPHost(prefs.get("GuestSMTPHost", "smtp.foo.com"));
        
        settings.setSMTPSecurity(SecurityOption.valueOf
            (prefs.get("GuestSMTPSecurity", "NONE")));
        
        settings.setSMTPPort(prefs.getInt("GuestSMTPPort", 587));
        settings.setSMTPUser(prefs.get("GuestSMTPUser", "foobar"));
        settings.setSMTPPass(prefs.get("GuestSMTPPass", "password"));
        
        return settings;
    }
    
    /**
        Sets the administrator's email settings
    
        @param settings The administrator's email settings
    */
    
    public void setAdminEmailSettings(EmailSettings settings)
    {
        prefs.put("AdminSendAddress", settings.getSendAddress());
        prefs.put("AdminSMTPHost", settings.getSMTPHost());
        prefs.put("AdminSMTPSecurity", settings.getSMTPSecurity().toString());
        prefs.putInt("AdminSMTPPort", settings.getSMTPPort());
        prefs.put("AdminSMTPUser", settings.getSMTPUser());
        prefs.put("AdminSMTPPass", settings.getSMTPPass());
        prefs.put("AdminGetAddress", settings.getGetAddress());
    }
    
    /**
        Sets the guest's email settings
    
        @param settings The guest's email settings
    */
    
    public void setGuestEmailSettings(EmailSettings settings)
    {
        prefs.put("GuestSendAddress", settings.getSendAddress());
        prefs.put("GuestSMTPHost", settings.getSMTPHost());
        prefs.put("GuestSMTPSecurity", settings.getSMTPSecurity().toString());
        prefs.putInt("GuestSMTPPort", settings.getSMTPPort());
        prefs.put("GuestSMTPUser", settings.getSMTPUser());
        prefs.put("GuestSMTPPass", settings.getSMTPPass());
    }
}