/**
    Who makes a reservation
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

public class Reserver
{
    // Fields
    private int id;
    private String firstName, lastName, email, phone;
    
    /**
        Constructs a new Reserver initialized with the given first & last names,
        email address, & phone number
    
        @param fName First name
        @param lName Last name
        @param e Email address
        @param p Phone number
    */
    
    public Reserver(String fName, String lName, String e, String p)
    {
        id = -1;
        firstName = fName;
        lastName = lName;
        email = e;
        phone = p;
    }
    
    /**
        Constructs a new Reserver initialized with the given first & last names,
        email address, phone number, & ID
    
        @param fName First name
        @param lName Last name
        @param e Email address
        @param p Phone number
        @param id ID
    */
    
    public Reserver(String fName, String lName, String e, String p, int id)
    {
        this.id = id;
        firstName = fName;
        lastName = lName;
        email = e;
        phone = p;
    }
    
    /**
        Returns the email address of the Reserver
    
        @return The email address of the Reserver
    */
    
    public String getEmailAddress()
    {
        return email;
    }
    
    /**
        Returns the first name of the reserver
    
        @return The first name of the reserver
    */
    
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
        Returns the reserver's ID
    
        @return The reserver's ID
    */
    
    public int getID()
    {
        return id;
    }
    
    /**
        Returns the last name of the reserver
    
        @return The last name of the reserver
    */
    
    public String getLastName()
    {
        return lastName;
    }
    
    /**
        Returns the full name of the reserver
    
        @return The full name of the Reserver
    */
    
    public String getName()
    {
        return firstName + " " + lastName;
    }
    
    /**
        Returns the phone number of the Reserver
    
        @return The phone number of the Reserver
    */
    
    public String getPhoneNumber()
    {
        return phone;
    }
    
    /**
        Sets the reserver's email
    
        @param e Reserver's email
    */
    
    public void setEmail(String e)
    {
        email = e;
    }
    
    /**
        Sets the reserver's first name
    
        @param name Reserver's first name
    */
    
    public void setFirstName(String name)
    {
        firstName = name;
    }
    
    /**
        Sets the reserver's ID
    
        @param id The reserver's ID
    */
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
        Sets the reserver's last name
    
        @param name Reserver's last name
    */
    
    public void setLastName(String name)
    {
        lastName = name;
    }
    
    /**
        Sets the reserver's phone number
    
        @param p Reserver's phone number
    */
    
    public void setPhoneNumber(String p)
    {
        phone = p;
    }
}