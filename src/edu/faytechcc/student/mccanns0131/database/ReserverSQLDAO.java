/**
    DAO (Data Access Object) for accessing reserver data on a database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.Reserver;
import java.sql.SQLException;

public class ReserverSQLDAO
{
    private DatabaseConnection connection;
    
    /**
        Constructs a new ReserverSQLDAO & attempts to establish a connection to
        a database using the given database settings
    
        @param settings Database settings to connect to database with
        @throws SQLException Error connecting to database
    */
    
    public ReserverSQLDAO(DatabaseSettings settings) throws SQLException
    {
        connection = DatabaseConnection.getConnection(settings);
    }
    
    /**
        Constructs a new ReserverSQLDAO initialized with the given connection
        to a database
    
        @param conn Connection to database
    */
    
    public ReserverSQLDAO(DatabaseConnection conn)
    {
        connection = conn;
    }
    
    /**
        Adds a record of a reserver to a database
    
        @param reserver Reserver to add
        @throws SQLException Error adding record
    */
    
    public void addReserver(Reserver reserver) throws SQLException
    {
        new RecordAdd(connection).addReserver(reserver);
    }
    
    /**
        Removes a record of a reserver from a database
    
        @param reserver Reserver to add
        @throws SQLException Error removing record
    */
    
    public void removeReserver(Reserver reserver) throws SQLException
    {
        new RecordDelete(connection).deleteReserver(reserver);
    }
}