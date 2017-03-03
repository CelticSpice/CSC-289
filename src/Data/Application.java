/**
    Entry point of application
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.sql.SQLException;

public class Application
{
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args) throws SQLException
    {
        ReservableLocation loc = ReserveDB.getReservableLocation("Lake");
        System.out.println("Got location " + loc.getName());
        System.out.println("It has a capacity of " + loc.getCapacity());
        
        System.out.println("Here are its timeframes:\n");
        
        for (ReservableTimeframe timeframe : loc.getTimeframes())
            System.out.println(timeframe);
    }
}