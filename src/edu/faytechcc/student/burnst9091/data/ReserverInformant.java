/**
    Informs reservers of changes
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

public class ReserverInformant
{
    private DataRepository repo;
    
    /**
        Constructs a new ReserverInformant
    
        @param repo Data repository
    */
    
    public ReserverInformant(DataRepository repo)
    {
        this.repo = repo;
    }
    
    /**
        Informs reservers of a reserved location's name and/or capacity being
        changed
    
        @param oldName The location's old name
        @param oldCapacity The location's old capacity
        @param location The location, as it is now
    */
    
    public void informOfLocationChange(String oldName, int oldCapacity,
            ReservableLocation location)
    {
        boolean nameChanged = !location.getName().equals(oldName);
        boolean capacityChanged = location.getCapacity() != oldCapacity;
        
        String body = "This message is to inform you of changes made to the " +
                "location you reserved, namely " + oldName + ". ";
        
        if (nameChanged)
            body += "The name of the location has changed to " +
                    location.getName();
        
        if (capacityChanged)
        {
            if (capacityChanged && nameChanged)
            {
                body += ", and its capacity has changed from " + oldCapacity +
                        " to " + location.getCapacity() + ".";
            }
            else
                body += "The capacity of the location has changed from " +
                        oldCapacity + " to " + location.getCapacity() + ".";
        }
        
        List<Integer> reserversInformed = new ArrayList<>();
        
        for (Reservation reservation : repo.getLocationReservations(location))
        {
            Reserver reserver = reservation.getReserver();
            if (!reserversInformed.contains(reserver.getID()))
            {
                reserversInformed.add(reserver.getID());
                
                String intro = reserver.getName() + ",\n\n" + body;
                
                try
                {
                    EmailUtil.emailReserver(reserver,
                            "Reserved Location Change", body);
                }
                catch (MessagingException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
    /**
        Informs a reserver of a reservation being canceled
    
        @param reservation The canceled reservation
    */
    
    public void informOfReservationCancellation(Reservation reservation)
    {
        String body = reservation.getReserverName() + ",\n\n";
        body += "This message is to inform you that your reservation at " +
                reservation.getLocationName() + ", at the timeframe of " +
                reservation.getTimeframe() + ", has been cancelled.";
        
        try
        {
            EmailUtil.emailReserver(reservation.getReserver(),
                    "Reservation Cancelled", body);
        }
        catch (MessagingException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
        Informs a reserver of a reservation being made successfully
    
        @param reservation Reservation made
    */
    
    public void informOfReservationMade(Reservation reservation)
    {
        String body = reservation.getReserverName() + ",\n\n";
        body += "This message is to inform you that your reservation at " +
                reservation.getLocationName() + ", at the timeframe of " +
                reservation.getTimeframe() + ", has been reserved " +
                "successfully.";
        
        try
        {
            EmailUtil.emailReserver(reservation.getReserver(),
                    "Reservation Made", body);
        }
        catch (MessagingException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}