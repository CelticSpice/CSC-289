/**
    A connection to the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{
    // Fields            
    private Connection connection;
    
    /**
        Constructs a new DatabaseConnection
    
        @param conn Connection
    */
    
    public DBConnection(Connection conn)
    {
        connection = conn;
    }
    
    /*
    If implemented, move to SystemUtils
    */
    
    /**
     * AddFutureTimeframes - Add timeframes a set time into the future
     * 
     * @throws SQLException Error inserting timeframes
     */
//    public void addFutureTimeframes() throws SQLException
//    {
//        /*
//        This is incomplete; by no means is it near finished. Just laying some
//        foundational work.
//        */
//        Statement stmt = connection.createStatement();
//        stmt.execute("USE " + DB_NAME);
//        
//        String sql = "SELECT Timeframes.StartDate, Timeframes.EndDate, " +
//                     "Timeframes.StartTime, Timeframes.EndTime" +
//                     "FROM Timeframes" +
//                     "WHERE StartDate = CURDATE()";
//        
//        ResultSet rs = stmt.executeQuery(sql);
//        
//        List<Timeframe> referenceTimes = ResultSetParser.parseTimeframes(rs);
//        
//        for (Timeframe t : referenceTimes)
//        {
//            sql = "INSERT INTO Timeframes(StartDate, EndDate, StartTime, " +
//                  "EndTime)" +
//                  "VALUES('" + t.getStartDate().plusYears(1) + "', " +
//                  "'" + t.getEndDate().plusYears(1) + "', " +
//                  "'" + t.getStartTime() + "', '" + t.getEndTime() + "')";
//            
//            stmt.executeUpdate(sql);
//        }
//    }
    
    /**
        Adds a record to the database, returning the generated ID, if any
    
        @param recordAdd The adding of a record
        @throws SQLException There was an error adding a record
        @return The ID of the newly inserted record, if any
    */
    
    public ResultSet addRecord(RecordAdd recordAdd) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(recordAdd.toString());
        ResultSet rs = stmt.getGeneratedKeys();
        stmt.close();
        return rs;
    }
    
    /**
        Closes the connection
    
        @throws SQLException Error closing connection
    */
    
    public void close() throws SQLException
    {
        connection.close();
    }
    
    /**
        Deletes a record from the database
    
        @param recordDelete The deletion of a record
        @throws SQLException Error deleting record from database
    */
    
    public void deleteRecord(RecordDelete recordDelete) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(recordDelete.toString());
        stmt.close();
    }
    
    /**
        Executes arbitrary SQL
    
        @param sql The SQL to execute
        @throws SQLException Error executing SQL
    */
    
    public void executeSQL(String sql) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        stmt.close();
    }
    
    /**
          Updates a record in the database

          @param recordUpdate The record update
          @throws SQLException Error updating record in the database
    */
    
    public void updateRecord(RecordUpdate recordUpdate) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(recordUpdate.toString());
        stmt.close();
    }
    
    /**
     * RemovePassedTimeframes - Delete timeframes that have passed
     * 
     * @throws SQLException There was an error deleting the timeframe(s)
     */
//    public void removePassedTimeframes() throws SQLException
//    {
//        Statement stmt = connection.createStatement();
//        stmt.execute("USE " + DB_NAME);
//        
//        // Delete passed Timeframes.
//        String sql = "DELETE timeframes" +
//                     "FROM Timeframes" +
//                     "WHERE Timeframes.EndDate < CURDATE()";
//        
//        stmt.executeUpdate(sql);
//    }
    
    /**
        Runs the query provided
    
        @param query The query to run
        @throws SQLException There was an error running the query
        @return The result set of the query
    */
    
    public ResultSet runQuery(Query query) throws SQLException
    {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query.toString());
        stmt.close();
        return rs;
    }
}