/**
    The database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import java.sql.SQLException;

public class ReserveDB
{
    private static final String NAME = "ReserveDB";
    
    /**
        Creates the database
    
        @param connection Database connection
        @throws SQLException Error creating the database
    */
    
    private static void create(DatabaseConnection connection)
            throws SQLException
    {
        if (exists(connection))
        {
            String sql = "DROP DATABASE ReserveDB";
            connection.executeSQL(sql);
        }
        
        String sql = "CREATE DATABASE " + NAME;
        connection.executeSQL(sql);
        
        sql = "USE ReserveDB";
        connection.executeSQL(sql);
        
        dropTables(connection);
        createTables(connection);
    }
    
    /**
        Creates the database tables
    
        @param connection Database connection
        @throws SQLException Error creating the database tables
    */
    
    private static void createTables(DatabaseConnection connection) 
            throws SQLException
    {
        // Create Locations table
        String sql = "CREATE TABLE Locations (" +
                     "LocationID INT NOT NULL AUTO_INCREMENT, " +
                     "LocationName VARCHAR(20) NOT NULL UNIQUE, " +
                     "Capacity INT NOT NULL, " +
                     "PRIMARY KEY (LocationID)" +
                     ")";
        
        connection.executeSQL(sql);
        System.out.println("Created Locations table");
        
        // Create Timeframe table
        sql = "CREATE TABLE Timeframes (" +
              "TimeframeID INT NOT NULL AUTO_INCREMENT, " +
              "StartDate DATE NOT NULL, " +
              "EndDate DATE NOT NULL, " +
              "StartTime TIME NOT NULL, " +
              "EndTime TIME NOT NULL, " +
              "PRIMARY KEY (TimeframeID), " +
              "CONSTRAINT UC_Timeframes UNIQUE " +
              "(StartDate, EndDate, StartTime, EndTime)" +
              ")";

        connection.executeSQL(sql);
        System.out.println("Created Timeframes table");
        
        // Create Reservables table        
        sql = "CREATE TABLE Reservables (" +
              "LocationID INT NOT NULL, " +
              "TimeframeID INT NOT NULL, " +
              "Cost DECIMAL(7,2) NOT NULL, " +
              "FOREIGN KEY (LocationID) REFERENCES Locations(LocationID) " +
              "ON UPDATE CASCADE ON DELETE CASCADE, " +
              "FOREIGN KEY (TimeframeID) REFERENCES Timeframes(TimeframeID) " +
              "ON DELETE CASCADE," +
              "CONSTRAINT PK_Reservables PRIMARY KEY " +
              "(LocationID, TimeframeID)" +
              ")";
        
        connection.executeSQL(sql);
        System.out.println("Created Reservables table");
        
        // Create Reservers table        
        sql = "CREATE TABLE Reservers (" +
              "ReserverID INT NOT NULL AUTO_INCREMENT, " +
              "FirstName VARCHAR(35) NOT NULL, " +
              "LastName VARCHAR(35) NOT NULL, " +
              "Email VARCHAR(75) NOT NULL, " +
              "Phone VARCHAR(16) NOT NULL, " +
              "PRIMARY KEY (ReserverID)" +
              ")";
        
        connection.executeSQL(sql);
        System.out.println("Created Reservers table");
        
        // Create Reservervations table        
        sql = "CREATE TABLE Reservations (" +
              "LocationID INT NOT NULL, " +
              "TimeframeID INT NOT NULL, " +
              "ReserverID INT NOT NULL, " +
              "EventType VARCHAR(35) NOT NULL, " +
              "NumberAttending INT NOT NULL, " +
              "Reviewed BOOLEAN NOT NULL DEFAULT 0, " +
              "FOREIGN KEY (LocationID) REFERENCES Reservables(LocationID) " +
              "ON UPDATE CASCADE ON DELETE CASCADE," +
              "FOREIGN KEY (TimeframeID) REFERENCES Reservables(TimeframeID) " +
              "ON DELETE CASCADE," +
              "FOREIGN KEY (ReserverID) REFERENCES Reservers(ReserverID) " +
              "ON DELETE CASCADE," +
              "CONSTRAINT PK_Reservations PRIMARY KEY " +
              "(LocationID, TimeframeID)" +
              ")";
        
        connection.executeSQL(sql);
        System.out.println("Created Reservervations table");
    }
    
    /**
        Drops existing tables
    
        @param connection Database connection
        @throws SQLException Error dropping the database tables
    */
    
    private static void dropTables(DatabaseConnection connection)
            throws SQLException
    {
        String sql = "DROP TABLE IF EXISTS Reservations";
        connection.executeSQL(sql);
        
        sql = "DROP TABLE IF EXISTS Reservables";
        connection.executeSQL(sql);
        
        sql = "DROP TABLE IF EXISTS Reservers";
        connection.executeSQL(sql);
        
        sql = "DROP TABLE IF EXISTS Timeframes";
        connection.executeSQL(sql);
        
        sql = "DROP TABLE IF EXISTS Locations";
        connection.executeSQL(sql);
    }
    
    /**
        Returns if the database exists
    
        @param connection Database connection
        @throws SQLException Error running query
        @return If the database exists
    */
    
    private static boolean exists(DatabaseConnection connection)
            throws SQLException
    {
        String sql = "SELECT SCHEMA_NAME " +
                     "FROM INFORMATION_SCHEMA.SCHEMATA " +
                     "WHERE SCHEMA_NAME = '" + NAME + "'";
        
        Query query = new Query(sql);
        
        ResultSetParser parser = new ResultSetParser();
        parser.setResultSet(connection.runQuery(query));
        return !parser.isResultSetEmpty();
    }
    
    /**
        Initializes the database, connecting using the given database settings
    
        @param settings Database settings
        @throws SQLException Error initializing the database
    */
    
    public static void init(DatabaseSettings settings) throws SQLException
    {
        DatabaseConnection connection = DatabaseConnection
                .getConnection(settings, false);
        
        create(connection);
    }
}