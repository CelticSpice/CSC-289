/**
 * A reserver search
 * CSC-289
 * @author Shane McCann
 */
package edu.faytechcc.student.burnst9091.data.search;

import edu.faytechcc.student.burnst9091.data.Reserver;
import java.util.function.Predicate;

public class ReserverSearch
{
    // Fields
    private Predicate<Reserver> firstName,
                                lastName,
                                emailAddress,
                                phoneNumber,
                                finalPredicate;
    
    public ReserverSearch()
    {
        firstName = null;
        lastName = null;
        emailAddress = null;
        phoneNumber = null;
        finalPredicate = null;
    }
    
    /**
     * Gather search constraints and create predicates to filter by
     * with those constraints
     * 
     * @param criteria The search criteria
     * @return A predicate containing all relevant filter based on search
     *         constraints
     */
    public Predicate<Reserver> search(String criteria)
    {
        // Split search criteria
        String[] filters = criteria.split(";");

        for (String filter : filters)
        {
            // Split keys and values
            String[] constraint = filter.split("=");
            
            String key = constraint[0].trim(), 
                   val = constraint[1].trim();

            switch(key.toLowerCase())
            {
                case "firstname":
                case "first name":
                case "first":
//                    firstName = filterByFirstName(val);
                    break;
                case "lastname":
                case "last name":
                case "last":
//                    lastName = filterByLastName(val);
                    break;
                case "emailaddress":
                case "email address":
                case "email":
                case "e-mail":
//                    emailAddress = filterByEmailAddress(val);
                    break;
                case "phonenumber":
                case "phone number":
                case "phone":
//                    phoneNumber = filterByPhoneNumber(val);
                    break;
            }
        }
        
        return finalPredicate;
    }
}
