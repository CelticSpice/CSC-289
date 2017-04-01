/**
    Settings of the email
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.Properties;

public class EmailSettings
{
    private Properties props;
    
    /**
        Constructs a new EmailSettings
    */
    
    public EmailSettings()
    {
        props = new Properties();
    }
    
    /**
        Derives properties from this EmailSettings suitable for instantiating an
        email session
    
        @return Properties suitable for instantiating an email session
    */
    
    public Properties deriveSessionProperties()
    {
        Properties sessionProps = new Properties();
        
        sessionProps.put("mail.smtp.host", getSMTPHost());
        sessionProps.put("mail.smtp.port", getSMTPPort());
        
        if (props.containsKey("SMTPSecurity"))
        {
            switch (getSMTPSecurity())
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
        
        if (props.containsKey("SMTPUser") || props.containsKey("SMTPPass"))
            sessionProps.put("mail.smtp.auth", "true");
        
        return sessionProps;
    }
    
    /**
        Gets & returns the getting address
    
        @return The getting address
    */
    
    public String getGetAddress()
    {
        return props.getProperty("GetAddress", "");
    }
    
    /**
        Gets & returns the sending address
    
        @return The sending address
    */
    
    public String getSendAddress()
    {
        return props.getProperty("SendAddress", "");
    }
    
    /**
        Gets & returns the SMTP host name
    
        @return The SMTP host name
    */
    
    public String getSMTPHost()
    {
        return props.getProperty("SMTPHost", "");
    }
    
    /**
        Gets & returns the SMTP password
    
        @return The SMTP password
    */
    
    public String getSMTPPass()
    {
        return props.getProperty("SMTPPass", "");
    }
    
    /**
        Gets & returns the SMTP port
    
        @return The SMTP port
    */
    
    public Integer getSMTPPort()
    {
        return (Integer) props.getOrDefault("SMTPPort", 0);
    }
    
    /**
        Gets & returns the SMTP security protocol
    
        @return The SMTP security protocol
    */
    
    public SecurityOption getSMTPSecurity()
    {
        return (SecurityOption) props.getOrDefault("SMTPSecurity",
                SecurityOption.NONE);
    }
    
    /**
        Gets & returns the SMTP username
    
        @return The SMTP username
    */
    
    public String getSMTPUser()
    {
        return props.getProperty("SMTPUser", "");
    }
    
    /**
        Sets the getting address
    
        @param getAddress The getting address
    */
    
    public void setGetAddress(String getAddress)
    {
        props.put("GetAddress", getAddress);
    }
    
    /**
        Sets the sending address
    
        @param sendAddress The sending address
    */
    
    public void setSendAddress(String sendAddress)
    {
        props.put("SendAddress", sendAddress);
    }
    
    /**
        Sets the SMTP host name
    
        @param host The SMTP host name
    */
    
    public void setSMTPHost(String host)
    {
        props.put("SMTPHost", host);
    }
    
    /**
        Sets the SMTP password
    
        @param pass The SMTP password
    */
    
    public void setSMTPPass(String pass)
    {
        props.put("SMTPPass", pass);
    }
    
    /**
        Sets the SMTP port
    
        @param port The SMTP port
    */
    
    public void setSMTPPort(Integer port)
    {
        props.put("SMTPPort", port);
    }
    
    /**
        Sets the SMTP security protocol
    
        @param protocol The SMTP security protocol
    */
    
    public void setSMTPSecurity(SecurityOption protocol)
    {
        props.put("SMTPSecurity", protocol);
    }
    
    /**
        Sets the SMTP username
    
        @param user The SMTP username
    */
    
    public void setSMTPUser(String user)
    {
        props.put("SMTPUser", user);
    }
}