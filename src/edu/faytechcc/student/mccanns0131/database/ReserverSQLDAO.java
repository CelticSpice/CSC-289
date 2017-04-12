/**
    DAO (Data Access Object) for accessing reserver data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Reserver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ReserverSQLDAO
{
    private Connection connection;
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
    
        @param conn Connection
    */
    
    public ReserverSQLDAO(Connection conn)
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
        
        String fields = "(FirstName, LastName, Email, Phone)";
        
        String sql = "INSERT INTO Reservers " + fields + " "     +
                     "VALUES ('" + reserver.getFirstName()     + "', '" +
                                   reserver.getLastName()      + "', '" +
                                   reserver.getEmailAddress()  + "', '" +
                                   reserver.getPhoneNumber()   + "')";
        
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.close();
        
        ResultSetParser parser = new ResultSetParser(stmt.getGeneratedKeys());
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
        
        String sql = "DELETE FROM Reservers " +
                     "WHERE Reservers.ReserverID = " + reserver.getID();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
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
        
        String sql = "UPDATE Reservers " +
                     "SET Reservers.FirstName = '" +
                        reserver.getFirstName() + "', " +
                     "Reservers.LastName = '" +
                        reserver.getLastName() + "', " +
                     "Reservers.Email = '" +
                        reserver.getEmailAddress() + "', " +
                     "Reservers.Phone = '" +
                        reserver.getPhoneNumber() + "' " +
                     "WHERE Reservers.ReserverID = " +
                        reserver.getID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }        
    }
}