/**
    DAO (Data Access Object) for accessing reserver data on the database
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
        the database using the given database settings
    
        @param settings Database settings to connect to database with
        @throws SQLException Error connecting to database
    */
    
    public ReserverSQLDAO(DatabaseSettings settings) throws SQLException
    {
        connection = DatabaseConnection.getConnection(settings);
    }
    
    /**
        Constructs a new ReserverSQLDAO initialized with the given connection
        to the database
    
        @param conn Connection to database
    */
    
    public ReserverSQLDAO(DatabaseConnection conn)
    {
        connection = conn;
    }
    
    /**
        Adds a record of a reserver to the database
    
        @param reserver Reserver to add
        @throws SQLException Error adding record
    */
    
    public void addReserver(Reserver reserver) throws SQLException
    {
        ResultSetParser parser = new ResultSetParser();
        parser.setResultSet(new RecordAdd(connection).addReserver(reserver));
        int id = parser.parseID();
        reserver.setID(id);
    }
    
    /**
        Removes a record of a reserver from the database
    
        @param reserver Reserver to add
        @throws SQLException Error removing record
    */
    
    public void removeReserver(Reserver reserver) throws SQLException
    {
        new RecordDelete(connection).deleteReserver(reserver);
    }
    
    /**
        Updates a record of a reserver on the database
    
        @param reserver Updated reserver
        @throws SQLException Error updating record
    */
    
    public void updateReserver(Reserver reserver) throws SQLException
    {
        new RecordUpdate(connection).updateReserver(reserver);
    }
}