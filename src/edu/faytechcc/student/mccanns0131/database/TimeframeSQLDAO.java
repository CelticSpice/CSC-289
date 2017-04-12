/**
    DAO (Data Access Object) for accessing timeframe data on the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TimeframeSQLDAO
{
    private Connection connection;
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
    
        @param conn Connection
    */
    
    public TimeframeSQLDAO(Connection conn)
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
        
        String fields = "(StartDate, StartTime, EndDate, EndTime)";
        
        String sql = "INSERT INTO Timeframes " + fields + " " +
                     "VALUES ('" + timeframe.getStartDate() + "', '" +
                                   timeframe.getStartTime() + "', '" +
                                   timeframe.getEndDate()   + "', '" +
                                   timeframe.getEndTime() + "')";
        
        ResultSetParser parser;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            parser = new ResultSetParser(stmt.getGeneratedKeys());
        }
        
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
    
        @param timeframe ReservableTimeframe to add
        @throws SQLException Error removing record
    */
    
    public void removeTimeframe(Timeframe timeframe) throws SQLException
    {
        if (connection == null)
            connection = source.getConnection();
        
        String sql = "DELETE FROM Timeframes " +
                     "WHERE Timeframes.TimeframeID = " + timeframe.getID();
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}