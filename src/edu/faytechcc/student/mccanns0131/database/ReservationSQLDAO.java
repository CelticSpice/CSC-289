/**
    DAO (Data Access Object) for accessing reservation data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservation;
import java.sql.SQLException;
import java.util.List;

public class ReservationSQLDAO
{
    private DatabaseDataSource source;
    
    /**
        Constructs a new ReservationSQLDAO
    */
    
    public ReservationSQLDAO()
    {
        source = new DatabaseDataSource();
    }
    
    /**
        Adds a record of a reservation to the database
    
        @param reservation Reservation to add
        @throws SQLException Error adding record
    */
    
    public void addReservation(Reservation reservation) throws SQLException
    {        
        DatabaseConnection connection = source.getDBConnection();
        
        // Check if a record of a reserver should be added
        ReservationQuery query = new ReservationQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryDistinctByReserver(reservation.getReserver());
        parser.setResultSet(connection.runQuery(query));
        
        if (parser.isResultSetEmpty())
        {
            ReserverSQLDAO reserverDAO = new ReserverSQLDAO();
            reserverDAO.addReserver(reservation.getReserver());
        }
        
        // Add reservation
        new RecordAdd(connection).addReservation(reservation);
        
        connection.close();
    }
    
    /**
        Retrieves reservations made at the specified location from the database
    
        @param loc The location to retrieve reservations made at
        @throws SQLException Error retrieving reservations
        @return List of reservations made at the location
    */
    
    public List<Reservation> getByLocation(Location loc) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        
        ReservationQuery query = new ReservationQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryByLocation(loc);
        parser.setResultSet(connection.runQuery(query));
        connection.close();
        
        return parser.parseReservations(loc);
    }
    
    /**
        Removes a record of a reservation from the database
    
        @param reservation Reservation to remove
        @throws SQLException Error removing record
    */
    
    public void removeReservation(Reservation reservation) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        
        new RecordDelete(connection).deleteReservation(reservation);
        
        // Check if a record of a reserver should also be removed
        ReservationQuery query = new ReservationQuery();
        ResultSetParser parser = new ResultSetParser();
        query.queryDistinctByReserver(reservation.getReserver());
        parser.setResultSet(connection.runQuery(query));
        connection.close();
        
        if (parser.isResultSetEmpty())
        {
            ReserverSQLDAO reserverDAO = new ReserverSQLDAO();
            reserverDAO.removeReserver(reservation.getReserver());
        }
    }
    
    /**
        Updates a record of a reservation in the database
    
        @param reservation Reservation to update
        @throws SQLException Error removing record
    */
    
    public void updateReservation(Reservation reservation) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        new RecordUpdate(connection).updateReservation(reservation);
        connection.close();
    }
}