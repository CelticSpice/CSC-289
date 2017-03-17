/**
    Contact information
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

public class ContactInfo
{
    // Fields
    private String firstName, lastName, email, phone;
    
    /**
        Constructor - Accepts the specific information, namely, the first name,
        last name, email address, and phone number of the contact
    
        @param fName First name of contact
        @param lName Last name of contact
        @param e Email address of contact
        @param p Phone number of contact
    */
    
    public ContactInfo(String fName, String lName, String e, String p)
    {
        firstName = fName;
        lastName = lName;
        email = e;
        phone = p;
    }
    
    /**
        GetEmail - Return the email address of the contact
    
        @return Email address of contact
    */
    
    public String getEmail()
    {
        return email;
    }
    
    /**
        GetFirstName - Return the first name of the contact
    
        @return First name of contact
    */
    
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
        GetFullName - Return full name of contact
    
        @param lastFirst Whether to return in format last, first
        @return Full name of contact
    */
    
    public String getFullName(boolean lastFirst)
    {
        if (lastFirst)
            return lastName + ", " + firstName;
        else
            return firstName + " " + lastName;
    }
    
    /**
        GetLastName - Return the last name of the contact
    
        @return Last name of contact
    */
    
    public String getLastName()
    {
        return lastName;
    }
    
    /**
        GetPhoneNumber - Return the phone number of the contact
    
        @return Phone number of contact
    */
    
    public String getPhoneNumber()
    {
        return phone;
    }
}