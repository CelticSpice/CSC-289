/**
    Repository of data
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import edu.faytechcc.student.mccanns0131.database.LocationSQLDAO;
import edu.faytechcc.student.mccanns0131.database.ReservableSQLDAO;
import edu.faytechcc.student.mccanns0131.database.ReservationSQLDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataRepository
{
    private HashMap<Integer, ReservableLocation> locations;
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
        Acquires the ID for a reserver
    
        @param reserver Reserver to acquire ID of
        @return Acquired ID of reserver
    */
    
    public int acquireReserverID(Reserver reserver)
    {
        int id = -1;
        Reserver r;
        
        for (List<Reservation> reserves : reservations.values())
        {
            for (Reservation reservation : reserves)
            {
                r = reservation.getReserver();
                if (r.getFirstName().equalsIgnoreCase(
                          reserver.getFirstName())
                        && r.getLastName().equalsIgnoreCase(
                          reserver.getLastName())
                        && r.getEmailAddress().equalsIgnoreCase(
                          reserver.getEmailAddress())
                        && r.getPhoneNumber().equals(
                          reserver.getPhoneNumber()))
                {
                    id = r.getID();
                    break;
                }
            }
        }
        
        return id;
    }
    
    /**
        Acquires the ID for a timeframe
    
        @param timeframe Timeframe to acquire ID of
        @return Acquired ID of timeframe
    */
    
    public int acquireTimeframeID(ReservableTimeframe timeframe)
    {
        int id = -1;
        ReservableTimeframe time;
        
        for (ReservableLocation loc : locations.values())
        {
            time = loc.getTimeframe(t ->
                    t.startsOnDatetime(timeframe.getStartDatetime()) &&
                    t.endsOnDatetime(timeframe.getEndDatetime()));
            
            if (time != null)
            {
                id = time.getID();
                break;
            }
        }
        
        return id;
    }
    
    /**
        Adds a reservable to the repository
    
        @param reservable Reservable to add
        @throws SQLException Error updating the backing database
    */
    
    public void addReservable(Reservable reservable) throws SQLException
    {
        ReservableSQLDAO resDAO = new ReservableSQLDAO();
        resDAO.addReservable(reservable);
        resDAO.close();
        
        ReservableLocation loc = reservable.getLocation();
        loc.addTimeframe(reservable.getTimeframe());
        
        if (!locations.containsKey(reservable.getLocationID()))
            locations.put(reservable.getLocationID(), loc);
    }
    
    /**
        Adds a reservation to the repository
    
        @param reservation Reservation to add
        @throws SQLException Error updating the backing database
    */
    
    public void addReservation(Reservation reservation) throws SQLException
    {
        ReservationSQLDAO resDAO = new ReservationSQLDAO();
        resDAO.addReservation(reservation);
        resDAO.close();
        
        int locID = reservation.getLocationID();
        
        if (reservations.containsKey(locID))
            reservations.get(locID).add(reservation);
        else
        {
            List<Reservation> reserves = new ArrayList<>();
            reserves.add(reservation);
            reservations.put(locID, reserves);
        }
    }
    
    /**
        Gets locations available to be reserved from the repository
    
        @return locations available to be reserved
    */
    
    public List<ReservableLocation> getAvailableLocations()
    {
        List<ReservableLocation> availLocs = new ArrayList<>();
        for (ReservableLocation loc : locations.values())
        {
            if (loc.getReservableTimeframes().size() > 0)
                availLocs.add(loc);
        }
        return availLocs;
    }
    
    /**
        Gets locations from the repository
    
        @return Reservable locations
    */
    
    public List<ReservableLocation> getLocations()
    {
        List<ReservableLocation> locs = new ArrayList<>(locations.values());
        locs.sort((l1, l2) -> l1.getName().compareTo(l2.getName()));
        return locs;
    }
    
    /**
        Returns the names of every location in the repository
    
        @return Name of every location in the repository
    */
    
    public List<String> getLocationNames()
    {
        List<String> locNames = new ArrayList<>();
        for (Location loc : locations.values())
            locNames.add(loc.getName());
        return locNames;
    }
    
    /**
        Gets reservations made from the repository
    
        @param loc Location to get reservations made at
        @return Reservations
    */
    
    public List<Reservation> getLocationReservations(ReservableLocation loc)
    {
        if (reservations.containsKey(loc.getID()))
            return new ArrayList<>(reservations.get(loc.getID()));
        else
            return new ArrayList<>();
    }
    
    /**
        Gets reserved locations from the repository
    
        @return Reserved locations
    */
    
    public List<ReservableLocation> getReservedLocations()
    {
        List<ReservableLocation> reservedLocs = new ArrayList<>();
        
        for (Integer locID : reservations.keySet())
            reservedLocs.add(locations.get(locID));
        
        reservedLocs.sort((l1, l2) -> l1.getName().compareTo(l2.getName()));
        
        return reservedLocs;
    }
    
    /**
        Removes a reservable from the repository
    
        @param reservable The reservable to remove from the repository
        @throws SQLException Error updating backing database
    */
    
    public void removeReservable(Reservable reservable) throws SQLException
    {
        ReservableSQLDAO resDAO = new ReservableSQLDAO();
        resDAO.removeReservable(reservable);
        resDAO.close();
        
        ReservableLocation loc = reservable.getLocation();
        loc.removeTimeframe(reservable.getTimeframe());
        
        int locID = reservable.getLocationID();
        
        if (locations.get(locID).getNumTimeframes() == 0)
            locations.remove(locID);
    }
    
    /**
        Removes a reservation from the repository
    
        @param reservation The reservation to remove from the repository
        @throws SQLException Error updating backing database
    */
    
    public void removeReservation(Reservation reservation) throws SQLException
    {
        ReservationSQLDAO resDAO = new ReservationSQLDAO();
        resDAO.removeReservation(reservation);
        resDAO.close();
                
        int locID = reservation.getLocationID();
        
        reservations.get(locID).remove(reservation);
        if (reservations.get(locID).isEmpty())
            reservations.remove(locID);
    }
    
    /**
        Updates the repository from the database
    
        @throws SQLException Error updating repository
    */
    
    public void update() throws SQLException
    {
        LocationSQLDAO locDAO = new LocationSQLDAO();
        ReservationSQLDAO resDAO = new ReservationSQLDAO();
        
        locations.clear();
        reservations.clear();
        
        for (ReservableLocation loc : locDAO.getAll())
        {
            locations.put(loc.getID(), loc);
            
            List<Reservation> locReservations = resDAO.getByLocation(loc);
            
            if (!locReservations.isEmpty())
                reservations.put(loc.getID(), locReservations);
        }
        
        locDAO.close();
        
        if (reservations.size() > 0)
            resDAO.close();
    }
    
    /**
        Updates a reservable in the repository
    
        @param reservable Reservable to be updated
        @throws SQLException Error updating backing database
    */
    
    public void updateReservable(Reservable reservable) throws SQLException
    {
        ReservableSQLDAO resDAO = new ReservableSQLDAO();
        resDAO.updateReservable(reservable);
        resDAO.close();
    }
    
    /**
        Updates a reservation in the repository
    
        @param reservation Reservation to be updated
        @throws SQLException Error updating backing database
    */
    
    public void updateReservation(Reservation reservation) throws SQLException
    {
        ReservationSQLDAO resDAO = new ReservationSQLDAO();
        resDAO.updateReservation(reservation);
        resDAO.close();
    }
}