/**
    Repository for accessing location & reservation data
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.mccanns0131.database.LocationSQLDAO;
import edu.faytechcc.student.mccanns0131.database.ReservationSQLDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DataRepository
{
    private HashMap<Integer, Location> locations;
    private HashMap<Integer, List<Reservation>> reservations;
    
    /**
        Constructs a new DataRepository
    */
    
    public DataRepository()
    {
        locations = new HashMap<>();
        reservations = new HashMap<>();
    }
    
    /**
        Removes a location from the repository
    
        @param loc Location to remove
    */
    
    public void removeLocation(Location loc)
    {
        
    }
    
    /**
        Updates the repository from the database
    
        @throws SQLException Error updating repository
    */
    
    public void update() throws SQLException
    {
        LocationSQLDAO locationDAO = new LocationSQLDAO();
        ReservationSQLDAO reservationDAO = new ReservationSQLDAO();
        
        locations.clear();
        List<Location> locs = locationDAO.getAll();
        
        for (Location loc : locs)
            locations.put(loc.getID(), loc);
        
        reservations.clear();
        for (Location loc : locs)
        {
            List<Reservation> reserves = reservationDAO.getByLocation(loc);
            if (!reserves.isEmpty())
                reservations.put(loc.getID(), reserves);
        }
    }
}