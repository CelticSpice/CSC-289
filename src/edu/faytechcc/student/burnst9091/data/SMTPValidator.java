/**
    A validator for SMTP properties
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMTPValidator
{
    // Fields
    private SMTPProperties props;
    
    /**
        Constructor - Accepts the SMTP properties to validate
    
        @param smtpProps SMTP properties to validate
    */
    
    public SMTPValidator(SMTPProperties smtpProps)
    {
        props = smtpProps;
    }
    
    /**
        Set the SMTP properties to validate
    
        @param smtpProps The SMTP properties to validate
    */
    
    public void setSMTPProperties(SMTPProperties smtpProps)
    {
        props = smtpProps;
    }
    
    /**
        Return if the sending address is valid
    
        @return If the sending address is valid
    */
    
    public boolean validateAddress()
    {
        Pattern addressPattern = Pattern.compile(
                "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
        
        Matcher matcher = addressPattern.matcher(props.getAddress());
        
        return matcher.matches();
    }
    
    /**
        Return if the SMTP host address is valid
    
        @return If the SMTP host address is valid
    */
    
    public boolean validateHost()
    {
        Pattern hostPattern = Pattern.compile(
                "([A-Za-z0-9-]+\\.[A-Za-z]+)(\\.\\w+)?((\\.[A-Za-z]+)?)?");
        
        Matcher matcher = hostPattern.matcher(props.getHost());
        
        return matcher.matches();
    }
    
    /**
        Return if the port number is valid
    
        @return If the port number is valid
    */
    
    public boolean validatePort()
    {
        return props.getPort().matches("\\b\\d+\\b");
    }
}