/**
    Preferences for the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.prefs.Preferences;

public class DatabasePreferences
{
    private Preferences prefs;
    
    /**
        Constructs a new DatabasePreferences with preferences stored at the
        given node
    
        @param node The node of the database preferences
    */
    
    public DatabasePreferences(String node)
    {
        prefs = Preferences.userRoot().node(node);
    }
    
    /**
        Returns the settings for the database
    
        @return The database settings
    */
    
    public DatabaseSettings getDBSettings()
    {
        DatabaseSettings settings = new DatabaseSettings();
        
        settings.setDBHost(prefs.get("DBHost", "localhost"));
        settings.setDBName(prefs.get("DBName", "ReserveDB"));
        settings.setDBPort(prefs.getInt("DBPort", 3306));
        settings.setDBUser(prefs.get("DBUser", ""));
        settings.setDBPass(prefs.get("DBPass", ""));
        
        return settings;
    }
    
    /**
        Sets the settings for the database
    
        @param settings The database settings
    */
    
    public void setDBSettings(DatabaseSettings settings)
    {
        prefs.put("DBHost", settings.getDBHost());
        prefs.put("DBName", settings.getDBName());
        prefs.putInt("DBPort", settings.getDBPort());
        prefs.put("DBUser", settings.getDBUser());
        prefs.put("DBPass", settings.getDBPass());
    }
}