/**
    A connection to the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
{
    // Fields        
    private static final String DB_NAME = "ReserveDB";
    
    private Connection connection;
    
    /**
        Constructs a new DatabaseConnection using the given database settings,
        with a flag indicating whether the DBMS should automatically be set
        to use the correct database
    
        @param settings Database settings
        @param use Whether DBMS should automatically be set to use the database
        @throws SQLException There was an error connecting to the database
    */
    
    private DatabaseConnection(DatabaseSettings settings, boolean use)
            throws SQLException
    {
        String dbOptions = "jdbc:mariadb://" + settings.getDBHost();
        dbOptions += ":" + settings.getDBPort();
        
        if (use)
            dbOptions += "/" + DB_NAME;

        String user = settings.getDBUser();
        String pass = settings.getDBPass();
        
        if (!user.isEmpty() && !pass.isEmpty())
            dbOptions += "?user=" + user + "&password=" + pass;
        
        if (!user.isEmpty() && pass.isEmpty())
            dbOptions += "?user=" + user;
        
        if (!pass.isEmpty() && user.isEmpty())
            dbOptions += "?password=" + pass;
        
        connection = DriverManager.getConnection(dbOptions);
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
        Adds a record to the database, returning the generated ID
    
        @param recordAdd The adding of a record
        @throws SQLException There was an error adding a record
        @return The ID of the newly inserted record
    */
    
    public ResultSet addRecord(RecordAdd recordAdd) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(recordAdd.toString());
        return stmt.getGeneratedKeys();
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
    }
    
    /**
        Gets & returns a connection to the database using the specified
        database settings
    
        @param settings Database settings
        @throws SQLException There was an error connecting to the database
        @return A DatabaseConnection object
    */
    
    public static DatabaseConnection getConnection(DatabaseSettings settings)
            throws SQLException
    {
        return new DatabaseConnection(settings, true);
    }
    
    /**
        Gets & returns a connection to the database using the specified
        database settings, with a flag indicating whether the DBMS should
        automatically set to use the database
    
        @param settings Database settings
        @param use Whether DBMS should automatically be set to use the database
        @throws SQLException There was an error connecting to the database
        @return A DatabaseConnection object
    */
    
    public static DatabaseConnection getConnection(DatabaseSettings settings,
            boolean use) throws SQLException
    {
        return new DatabaseConnection(settings, use);
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
    }
    
    /**
     * RemovePassedTimeframes - Delete timeframes that have passed
     * 
     * @throws SQLException There was an error deleting the timeframe(s)
     */
    public void removePassedTimeframes() throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.execute("USE " + DB_NAME);
        
        // Delete passed Timeframes.
        String sql = "DELETE timeframes" +
                     "FROM Timeframes" +
                     "WHERE Timeframes.EndDate < CURDATE()";
        
        stmt.executeUpdate(sql);
    }
    
    /**
        Runs the query provided & returns the result set
    
        @param query The query to run
        @throws SQLException There was an error running the query
        @return The result set of the query
    */
    
    public ResultSet runQuery(Query query) throws SQLException
    {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query.toString());
    }
}