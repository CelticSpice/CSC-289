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
    private DBConnection connection;
    private DBDataSource source;
    
    /**
        Constructs a new ReserverSQLDAO
    */
    
    public ReserverSQLDAO()
    {
        source = DBDataSource.getInstance();
        connection = null;
    }
    
    /**
        Constructs a new ReserverSQLDAO initialized with the given connection
    
        @param conn DBConnection
    */
    
    public ReserverSQLDAO(DBConnection conn)
    {
        source = null;
        connection = conn;
    }
    
    /**
        Adds a record of a reserver to the database
    
        @param reserver Reserver to add
        @throws SQLException Error adding record
    */
    
    public void addReserver(Reserver reserver) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        ResultSetParser parser = new ResultSetParser();
        parser.setResultSet(new RecordAdd(connection).addReserver(reserver));
        int id = parser.parseID();
        reserver.setID(id);
    }
    
    /**
        Closes this SQLDAO resource
    
        @throws SQLException Error closing the SQLDAO connection
    */
    
    public void close() throws SQLException
    {
        connection.close();
    }
    
    /**
        Removes a record of a reserver from the database
    
        @param reserver Reserver to add
        @throws SQLException Error removing record
    */
    
    public void removeReserver(Reserver reserver) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        new RecordDelete(connection).deleteReserver(reserver);
    }
    
    /**
        Updates a record of a reserver on the database
    
        @param reserver Updated reserver
        @throws SQLException Error updating record
    */
    
    public void updateReserver(Reserver reserver) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        new RecordUpdate(connection).updateReserver(reserver);
    }
}