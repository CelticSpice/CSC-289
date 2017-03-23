/**
    Contact information
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.Objects;

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
        Returns if this object is equal to another object
    
        @param o The object to test for equality with
        @return If the two objects are equals
    */
    
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof ContactInfo))
            return false;
        
        final ContactInfo other = (ContactInfo) o;
        
        return firstName.equals(other.firstName) &&
               lastName.equals(other.lastName)   &&
               email.equals(other.email)         &&
               phone.equals(other.phone);
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
    
    /**
        Returns the hash code for the object
    
        @return The object's hash code
    */
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.phone);
        return hash;
    }
}