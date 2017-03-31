/**
    Preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.prefs.Preferences;

public class SystemPreferences
{
    // Fields
    private final String ROOT = "EventReservationSystem";
    
    private EmailPreferences emailPrefs;
    private Preferences prefs;
    
    /**
        Constructs a new SystemPreferences
    */
    
    private SystemPreferences()
    {
        prefs = Preferences.userRoot().node(ROOT);
        emailPrefs = new EmailPreferences(prefs.absolutePath() + "/Email");
    }
    
    /**
        Returns the address for the administrator to receive email at
    
        @return The address for the administrator to receive email at
    */
    
    public String getAdminGetAddress()
    {
        return emailPrefs.getAdminGetAddress();
    }
    
    /**
        Returns the administrator's password
    
        @return The administrator's password
    */
    
    public String getAdminPassword()
    {
        return prefs.get("AdminPass", "");
    }
    
    /**
        Returns an instance of SystemPreferences
    
        @return A SystemPreferences instance
    */
    
    public static SystemPreferences getInstance()
    {
        return new SystemPreferences();
    }
    
    /**
        Returns properties of the administrator's SMTP server
    
        @return Properties of the administrator's SMTP server
    */
    
    public SMTPProperties getAdminSMTPProperties()
    {
        return emailPrefs.getAdminSMTPProperties();
    }
    
    /**
        Returns the password for the database
    
        @return The password for the database
    */
    
    public String getDBPass()
    {
        return prefs.get("DBPass", "Password");
    }
    
    /**
        Returns the username for the database
    
        @return The username for the database
    */
    
    public String getDBUser()
    {
        return prefs.get("DBUser", "Username");
    }
    
    /**
        Returns properties of the guest's SMTP server
    
        @return Properties of the guest's SMTP server
    */
    
    public SMTPProperties getGuestSMTPProperties()
    {
        return emailPrefs.getGuestSMTPProperties();
    }
    
    /**
        Sets the address the administrator will receive emails at
    
        @param address Address the administrator will receive emails at
    */
    
    public void setAdminGetAddress(String address)
    {
        emailPrefs.setAdminGetAddress(address);
    }
    
    /**
        Sets preferences for the administrator's SMTP server
    
        @param props Preferences of the administrator's SMTP server
    */
    
    public void setAdminSMTPPrefs(SMTPProperties props)
    {
        emailPrefs.setAdminSMTPPrefs(props);
    }
    
    /**
        Sets the password for the database
    
        @param pass The password for the database
    */
    
    public void setDBPass(String pass)
    {
        prefs.put("DBPass", pass);
    }
    
    /**
        Sets the username for the database
    
        @param user The username for the database
    */
    
    public void setDBUser(String user)
    {
        prefs.put("DBUser", user);
    }
    
    /**
        Sets preferences for the guest's SMTP server
    
        @param props Preferences of the guest's SMTP server
    */
    
    public void setGuestSMTPPrefs(SMTPProperties props)
    {
        emailPrefs.setGuestSMTPPrefs(props);
    }
    
    /**
        Updates the administrator's password
    
        @param pass The updated administrator password
    */
    
    public void updateAdminPassword(String pass)
    {
        prefs.put("AdminPass", pass);
    }
}