/**
    Settings for the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.Properties;

public class DatabaseSettings
{
    private Properties props;
    
    /**
        Constructs a new DatabaseSettings
    */
    
    public DatabaseSettings()
    {
        props = new Properties();
    }
    
    /**
        Gets & returns the database host name
    
        @return The database host name
    */
    
    public String getDBHost()
    {
        return props.getProperty("DBHost", "");
    }
    
    /**
        Gets & returns the database password
    
        @return The database password
    */
    
    public String getDBPass()
    {
        return props.getProperty("DBPass", "");
    }
    
    /**
        Gets & returns the database port
    
        @return The database port
    */
    
    public Integer getDBPort()
    {
        return (Integer) props.getOrDefault("DBPort", 0);
    }
    
    /**
        Gets & returns the database username
    
        @return The database username
    */
    
    public String getDBUser()
    {
        return props.getProperty("DBUser", "");
    }
    
    /**
        Sets the database host name
    
        @param host The database host name
    */
    
    public void setDBHost(String host)
    {
        props.put("DBHost", host);
    }
    
    /**
        Sets the database password
    
        @param pass The database password
    */
    
    public void setDBPass(String pass)
    {
        props.put("DBPass", pass);
    }
    
    /**
        Sets the database port
    
        @param port The database port
    */
    
    public void setDBPort(Integer port)
    {
        props.put("DBPort", port);
    }
    
    /**
        Sets the database username
    
        @param user The database username
    */
    
    public void setDBUser(String user)
    {
        props.put("DBUser", user);
    }
}