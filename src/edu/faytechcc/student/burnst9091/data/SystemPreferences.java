/**
    Preferences for the system
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.prefs.Preferences;

public class SystemPreferences
{
    private static final String ROOT = "event_reservation_system";
    
    private static SystemPreferences systemPrefs;
    
    private Preferences prefs;
    private DatabasePreferences dbPrefs;
    private EmailPreferences emailPrefs;
    
    /**
        Constructs a new SystemPreferences
    */
    
    private SystemPreferences()
    {
        prefs = Preferences.userRoot().node(ROOT);
        dbPrefs = new DatabasePreferences(ROOT + "/db");
        emailPrefs = new EmailPreferences(ROOT + "/email");
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
        Returns if initial setup was run
    
        @return If initial setup was run
    */
    
    public boolean getInitSetupRun()
    {
        return prefs.getBoolean("InitSetupRun", false);
    }
    
    /**
        Returns an instance of the system preferences
    
        @return Instance of system preferences
    */
    
    public static SystemPreferences getInstance()
    {
        if (systemPrefs == null)
            systemPrefs = new SystemPreferences();
        return systemPrefs;
    }
    
    /**
        Sets the administrator's password
    
        @param password The administrator's password
    */
    
    public void setAdminPassword(String password)
    {
        prefs.put("AdminPass", password);
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
        Sets if initial setup was run
    
        @param setup If initial setup was run
    */
    
    public void setInitSetupRun(boolean setup)
    {
        prefs.putBoolean("InitSetupRun", setup);
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