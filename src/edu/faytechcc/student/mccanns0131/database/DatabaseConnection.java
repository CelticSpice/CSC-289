/**
    A connection to the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
{
    // Fields        
    private static final String DB_NAME = "ReserveDB";
    
    private Connection connection;
    
    /**
        Constructs a new DatabaseConnection using the given database settings
    
        @param settings Database settings
        @throws SQLException There was an error connecting to the database
    */
    
    private DatabaseConnection(DatabaseSettings settings) throws SQLException
    {
        String dbOptions = "jdbc:mariadb://" + settings.getDBHost();
        dbOptions += ":" + settings.getDBPort();
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
        Adds a record to the database
    
        @param recordAdd The adding of a record
        @throws SQLException There was an error adding a record
    */
    
    public void addRecord(RecordAdd recordAdd) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(recordAdd.toString());
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
        Create - Create the database
    
        @throws SQLException There was an error creating the database
    */
    
    public void create() throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE DATABASE " + DB_NAME);
        stmt.execute("USE " + DB_NAME);
        createTables(stmt);
        System.out.println("Successfully created database");
    }
    
    /**
        CreateTables - Create the database tables
    
        @param statement Statement object for working with the database
        @throws SQLException There was an error creating the tables
    */
    
    public void createTables(Statement statement) throws SQLException
    {        
        // Create Locations table
        String sql = "CREATE TABLE Locations(" +
                     "locationName VARCHAR(20) NOT NULL," +
                     "capacity INT NOT NULL," +
                     "PRIMARY KEY (locationName)" +
                     ")";
        
        statement.execute(sql);
        System.out.println("Created Locations table");
        
        // Create Timeframe table
        sql = "CREATE TABLE Timeframes(" +
              "timeframeID INT NOT NULL AUTO_INCREMENT," +
              "startDate DATE NOT NULL," +
              "endDate DATE NOT NULL," +
              "startTime TIME NOT NULL," +
              "endTime TIME NOT NULL," +
              "PRIMARY KEY (timeframeID)" +
              ")";

        statement.execute(sql);
        System.out.println("Created Timeframes table");
        
        // Create Reservables table        
        sql = "CREATE TABLE Reservables(" +
              "locationName VARCHAR(20) NOT NULL," +
              "timeframeID INT NOT NULL," +
              "cost DECIMAL(7,2) NOT NULL," +
              "FOREIGN KEY (locationName) REFERENCES Locations(locationName) " +
              "ON UPDATE ON DELETE CASCADE," +
              "FOREIGN KEY (timeframeID) REFERENCES Timeframes(timeframeID) " +
              "ON DELETE CASCADE," +
              "CONSTRAINT locTime PRIMARY KEY (locationName, timeframeID)" +
              ")";
        
        statement.execute(sql);
        System.out.println("Created Reservables table");
        
        // Create Reservers table        
        sql = "CREATE TABLE Reservers(" +
              "reserverID INT NOT NULL AUTO_INCREMENT," +
              "firstName VARCHAR(35) NOT NULL," +
              "lastName VARCHAR(35) NOT NULL," +
              "email VARCHAR(75) NOT NULL," +
              "phone VARCHAR(16) NOT NULL," +
              "PRIMARY KEY (reserverID)" +
              ")";
        
        statement.execute(sql);
        System.out.println("Created Reservers table");
        
        // Create Reservervations table        
        sql = "CREATE TABLE Reservations(" +
              "locationName VARCHAR(20) NOT NULL," +
              "timeframeID INT NOT NULL," +
              "reserverID INT NOT NULL," +
              "eventType VARCHAR(35) NOT NULL," +
              "numberAttending INT NOT NULL," +
              "reviewed BOOLEAN NOT NULL DEFAULT 0," +
              "FOREIGN KEY (locationName) REFERENCES Locations(locationName) " +
              "ON UPDATE ON DELETE CASCADE," +
              "FOREIGN KEY (timeframeID) REFERENCES Timeframes(timeframeID) " +
              "ON DELETE CASCADE," +
              "FOREIGN KEY (reserverID) REFERENCES Reservers(reserverID) " +
              "ON DELETE CASCADE," +
              "CONSTRAINT locTime PRIMARY KEY (locationName, timeframeID)" +
              ")";
        
        statement.execute(sql);
        System.out.println("Created Reservervations table");
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
        Exists - Return whether the database exists
    
        @return Whether the database exists
        @throws SQLException Error working with the database
    */
    
    public boolean exists() throws SQLException
    {
        Statement stmt = connection.createStatement();
        
        String sql = "SELECT SCHEMA_NAME " +
                     "FROM INFORMATION_SCHEMA.SCHEMATA " +
                     "WHERE SCHEMA_NAME = '" + DB_NAME + "'";
        
        ResultSet rSet = stmt.executeQuery(sql);
                
        return rSet.isBeforeFirst();
    }
    
    /**
        Gets & returns a connection to a database using the specified
        database settings
    
        @param settings Database settings
        @throws SQLException There was an error connecting to the database
        @return A DatabaseConnection object
    */
    
    public static DatabaseConnection getConnection(DatabaseSettings settings)
            throws SQLException
    {
        return new DatabaseConnection(settings);
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