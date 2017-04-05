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
    private static final String ROOT = "event_reservation_system";
    
    private static SystemPreferences systemPrefs;
    
    private DatabasePreferences dbPrefs;
    private EmailPreferences emailPrefs;
    private Preferences prefs;
    
    /**
        Constructs a new SystemPreferences with preferences stored at the
        default node, which will be under the user's root
    */
    
    private SystemPreferences()
    {
        prefs = Preferences.userRoot().node(ROOT);
        emailPrefs = new EmailPreferences(ROOT + "/email");
        dbPrefs = new DatabasePreferences(ROOT + "/db");
    }
    
    /**
        Returns an instance of the system preferences
    
        @return A SystemPreferences instance
    */
    
    public static SystemPreferences getInstance()
    {
        if (systemPrefs == null)
            systemPrefs = new SystemPreferences();
        return systemPrefs;
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
        Returns the database settings
    
        @return The database settings
    */
    
    public DatabaseSettings getDBSettings()
    {
        return dbPrefs.getDBSettings();
    }
    
    /**
        Returns the administrator's email settings
    
        @return The administrator's email settings
    */
    
    public EmailSettings getAdminEmailSettings()
    {
        return emailPrefs.getAdminEmailSettings();
    }
    
    /**
        Returns the guest's email settings
    
        @return The guest's email settings
    */
    
    public EmailSettings getGuestEmailSettings()
    {
        return emailPrefs.getGuestEmailSettings();
    }
    
    /**
        Returns if the database has been setup
    
        @return If the database has been setup
    */
    
    public boolean getIsDBSetup()
    {
        return prefs.getBoolean("IsDBSetup", false);
    }
    
    /**
        Sets the administrator's password
    
        @param pass The administrator password
    */
    
    public void setAdminPassword(String pass)
    {
        prefs.put("AdminPass", pass);
    }
    
    /**
        Sets the database settings
    
        @param settings The database settings
    */
    
    public void setDBSettings(DatabaseSettings settings)
    {
        dbPrefs.setDBSettings(settings);
    }
    
    /**
        Sets if the database has been setup
    
        @param setup If the database has been setup
    */
    
    public void setIsDBSetup(boolean setup)
    {
        prefs.putBoolean("IsDBSetup", setup);
    }
    
    /**
        Sets the administrator's email settings
    
        @param settings The administrator's email settings
    */
    
    public void setAdminEmailSettings(EmailSettings settings)
    {
        emailPrefs.setAdminEmailSettings(settings);
    }
    
    /**
        Sets the guest's email settings
    
        @param settings The guest's email settings
    */
    
    public void setGuestEmailSettings(EmailSettings settings)
    {
        emailPrefs.setGuestEmailSettings(settings);
    }
}