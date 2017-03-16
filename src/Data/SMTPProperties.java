/**
    Properties for using an SMTP server
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.Properties;

public class SMTPProperties
{
    // Fields
    private Properties props;
    
    /**
        Constructor
    */
    
    public SMTPProperties()
    {
        props = new Properties();
    }
    
    /**
        AsSessionProperties - Derive properties suitable for instantiating
        an email session with
    
        @return sessionProps Properties suitable for instantiating an email
                             session with
    */
    
    public Properties asSessionProperties()
    {
        Properties sessionProps = new Properties();
        
        sessionProps.put("mail.smtp.host", props.getProperty("Host", ""));
        sessionProps.put("mail.smtp.port", props.getProperty("Port", ""));
        
        if (props.containsKey("Security"))
        {
            switch ((SecurityOption) props.get("Security"))
            {
                case SSL:
                    sessionProps.put("mail.smtp.ssl.enable", "true");
                    break;
                case TLS:
                    sessionProps.put("mail.smtp.starttls.enable", "true");
                    break;
                default:
                    break;
            }
        }
        
        if (props.containsKey("User") || props.containsKey("Pass"))
            sessionProps.put("mail.smtp.auth", "true");
        
        return sessionProps;
    }
    
    /**
        GetAddress - Return the address of the sender
    
        @return The address of the sender
    */
    
    public String getAddress()
    {
        return props.getProperty("Address", "");
    }
    
    /**
        GetHost - Return the SMTP host
    
        @return The SMTP host
    */
    
    public String getHost()
    {
        return props.getProperty("Host", "");
    }
    
    /**
        GetPassword - Return the password to access the SMTP server
    
        @return Password to access the SMTP server
    */
    
    public String getPassword()
    {
        return props.getProperty("Pass", "");
    }
    
    /**
        GetPort - Return the port of the SMTP server
    
        @return The port of the SMTP server
    */
    
    public String getPort()
    {
        return props.getProperty("Port", "");
    }
    
    /**
        GetSecurity - Return the security protocol to be used
    
        @return The security protocol to be used
    */
    
    public SecurityOption getSecurity()
    {
        return (SecurityOption) props.getOrDefault
            ("Security", SecurityOption.NONE);
    }
    
    /**
        GetUser - Return the username to access the SMTP server
    
        @return The username to access the SMTP server
    */
    
    public String getUser()
    {
        return props.getProperty("User", "");
    }
    
    /**
        SetAddress - Set the address of the sender
    
        @param address Address of sender
    */
    
    public void setAddress(String address)
    {
        props.put("Address", address.toLowerCase());
    }
    
    /**
        SetHost - Set the SMTP host
    
        @param host The SMTP host
    */
    
    public void setHost(String host)
    {
        props.put("Host", host.toLowerCase());
    }
    
    /**
        SetPassword - Set the password to access the SMTP server
    
        @param pass The password to access the SMTP server
    */
    
    public void setPassword(String pass)
    {
        props.put("Pass", pass);
    }
    
    /**
        SetPort - Set the port of the SMTP server
    
        @param port Port of SMTP server
    */
    
    public void setPort(int port)
    {
        props.put("Port", String.valueOf(port));
    }
    
    /**
        SetPort - Set the port of the SMTP server
    
        @param port Port of SMTP server
    */
    
    public void setPort(String port)
    {
        props.put("Port", port);
    }
    
    /**
        SetSecurity - Set the security protocol to be used
    
        @param security The security protocol to be used
    */
    
    public void setSecurity(SecurityOption security)
    {
        props.put("Security", security);
    }
    
    /**
        SetUser - Set the username to access the SMTP server
    
        @param user The username to access the SMTP server
    */
    
    public void setUser(String user)
    {
        props.put("User", user);
    }
}