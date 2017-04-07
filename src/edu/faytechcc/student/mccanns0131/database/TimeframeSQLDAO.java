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
    private DatabaseDataSource source;
    
    /**
        Constructs a new TimeframeSQLDAO
    */
    
    public TimeframeSQLDAO()
    {
        source = new DatabaseDataSource();
    }
    
    /**
        Adds a record of a timeframe to the database
    
        @param timeframe Timeframe to add
        @throws SQLException Error adding record
    */
    
    public void addTimeframe(Timeframe timeframe) throws SQLException
    {
        ResultSetParser parser = new ResultSetParser();
        DatabaseConnection connection = source.getDBConnection();
        parser.setResultSet(new RecordAdd(connection).addTimeframe(timeframe));
        connection.close();
        int id = parser.parseID();
        timeframe.setID(id);
    }
    
    /**
        Removes a record of a timeframe from the database
    
        @param timeframe Timeframe to add
        @throws SQLException Error removing record
    */
    
    public void removeTimeframe(Timeframe timeframe) throws SQLException
    {
        DatabaseConnection connection = source.getDBConnection();
        new RecordDelete(connection).deleteTimeframe(timeframe);
        connection.close();
    }
}