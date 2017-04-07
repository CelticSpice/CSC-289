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
    
    private static final Preferences PREFS = Preferences.userRoot().node(ROOT);
    
    private static final DatabasePreferences DB_PREFS =
            new DatabasePreferences(ROOT + "/db");
    
    private static final EmailPreferences EMAIL_PREFS =
            new EmailPreferences(ROOT + "/email");
    
    /**
        Returns the administrator's password
    
        @return The administrator's password
    */
    
    public static String getAdminPassword()
    {
        return PREFS.get("AdminPass", "");
    }
    
    /**
        Returns the database settings
    
        @return The database settings
    */
    
    public static DatabaseSettings getDBSettings()
    {
        return DB_PREFS.getDBSettings();
    }
    
    /**
        Returns the administrator's email settings
    
        @return The administrator's email settings
    */
    
    public static EmailSettings getAdminEmailSettings()
    {
        return EMAIL_PREFS.getAdminEmailSettings();
    }
    
    /**
        Returns the guest's email settings
    
        @return The guest's email settings
    */
    
    public static EmailSettings getGuestEmailSettings()
    {
        return EMAIL_PREFS.getGuestEmailSettings();
    }
    
    /**
        Returns if initial setup was run
    
        @return If initial setup was run
    */
    
    public static boolean getInitSetupRun()
    {
        return PREFS.getBoolean("InitSetupRun", false);
    }
    
    /**
        Sets the administrator's password
    
        @param password The administrator's password
    */
    
    public static void setAdminPassword(String password)
    {
        PREFS.put("AdminPass", password);
    }
    
    /**
        Sets the database settings
    
        @param settings The database settings
    */
    
    public static void setDBSettings(DatabaseSettings settings)
    {
        DB_PREFS.setDBSettings(settings);
    }
    
    /**
        Sets if initial setup was run
    
        @param setup If initial setup was run
    */
    
    public static void setInitSetupRun(boolean setup)
    {
        PREFS.putBoolean("InitSetupRun", setup);
    }
    
    /**
        Sets the administrator's email settings
    
        @param settings The administrator's email settings
    */
    
    public static void setAdminEmailSettings(EmailSettings settings)
    {
        EMAIL_PREFS.setAdminEmailSettings(settings);
    }
    
    /**
        Sets the guest's email settings
    
        @param settings The guest's email settings
    */
    
    public static void setGuestEmailSettings(EmailSettings settings)
    {
        EMAIL_PREFS.setGuestEmailSettings(settings);
    }
}