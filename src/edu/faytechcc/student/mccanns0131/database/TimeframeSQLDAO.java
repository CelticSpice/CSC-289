/**
    DAO (Data Access Object) for accessing timeframe data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;

public class TimeframeSQLDAO
{
    private DatabaseConnection connection;
    
    /**
        Constructs a new TimeframeSQLDAO & attempts to establish a connection to
        the database using the given database settings
    
        @param settings Database settings to connect to database with
        @throws SQLException Error connecting to database
    */
    
    public TimeframeSQLDAO(DatabaseSettings settings) throws SQLException
    {
        connection = DatabaseConnection.getConnection(settings);
    }
    
    /**
        Constructs a new TimeframeSQLDAO initialized with the given connection
        to the database
    
        @param conn Connection to database
    */
    
    public TimeframeSQLDAO(DatabaseConnection conn)
    {
        connection = conn;
    }
    
    /**
        Adds a record of a timeframe to the database
    
        @param timeframe Timeframe to add
        @throws SQLException Error adding record
    */
    
    public void addTimeframe(Timeframe timeframe) throws SQLException
    {
        new RecordAdd(connection).addTimeframe(timeframe);
    }
    
    /**
        Removes a record of a timeframe from the database
    
        @param timeframe Timeframe to add
        @throws SQLException Error removing record
    */
    
    public void removeTimeframe(Timeframe timeframe) throws SQLException
    {
        new RecordDelete(connection).deleteTimeframe(timeframe);
    }
}