/**
    DAO (Data Access Object) for accessing reserver data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Reserver;
import java.sql.SQLException;

public class ReserverSQLDAO
{
    private DatabaseDataSource source;
    
    /**
        Constructs a new ReserverSQLDAO
    */
    
    public ReserverSQLDAO()
    {
        source = new DatabaseDataSource();
    }
    
    /**
        Adds a record of a reserver to the database
    
        @param reserver Reserver to add
        @throws SQLException Error adding record
    */
    
    public void addReserver(Reserver reserver) throws SQLException
    {
        ResultSetParser parser = new ResultSetParser();
        DatabaseConnection connection = source.getDBConnection();
        parser.setResultSet(new RecordAdd(connection).addReserver(reserver));
        connection.close();
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
        DatabaseConnection connection = source.getDBConnection();
        new RecordDelete(connection).deleteReserver(reserver);
        connection.close();
    }
    
    /**
        Updates a record of a reserver on the database
    
        @param reserver Updated reserver
        @throws SQLException Error updating record
    */
    
    public void updateReserver(Reserver reserver) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        new RecordUpdate(connection).updateReserver(reserver);
        connection.close();
    }
}