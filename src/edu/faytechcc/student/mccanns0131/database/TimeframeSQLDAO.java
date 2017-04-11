/**
    DAO (Data Access Object) for accessing timeframe data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;

public class TimeframeSQLDAO
{
    private DBConnection connection;
    private DBDataSource source;
    
    /**
        Constructs a new TimeframeSQLDAO
    */
    
    public TimeframeSQLDAO()
    {
        source = DBDataSource.getInstance();
        connection = null;
    }
    
    /**
        Constructs a new TimeframeSQLDAO initialized with the given connection
    
        @param conn DBConnection
    */
    
    public TimeframeSQLDAO(DBConnection conn)
    {
        source = null;
        connection = conn;
    }
    
    /**
        Adds a record of a timeframe to the database
    
        @param timeframe Timeframe to add
        @throws SQLException Error adding record
    */
    
    public void addTimeframe(Timeframe timeframe) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        ResultSetParser parser = new ResultSetParser();
        parser.setResultSet(new RecordAdd(connection).addTimeframe(timeframe));
        int id = parser.parseID();
        timeframe.setID(id);
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
        Removes a record of a timeframe from the database
    
        @param timeframe Timeframe to add
        @throws SQLException Error removing record
    */
    
    public void removeTimeframe(Timeframe timeframe) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        new RecordDelete(connection).deleteTimeframe(timeframe);
    }
}