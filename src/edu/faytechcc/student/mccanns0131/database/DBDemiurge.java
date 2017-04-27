/**
    Creator of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.DatabaseSettings;
import edu.faytechcc.student.burnst9091.data.SystemPreferences;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.mariadb.jdbc.MariaDbDataSource;

public class DBDemiurge
{
    /**
        Creates the database & its tables
    
        @throws SQLException Error creating database or its tables
    */
    
    public static void craft() throws SQLException
    {
        MariaDbDataSource source = new MariaDbDataSource();
        
        SystemPreferences prefs = SystemPreferences.getInstance();
        DatabaseSettings settings = prefs.getDBSettings();
        
        source.setServerName(settings.getDBHost());
        source.setPort(settings.getDBPort());
        source.setUserName(settings.getDBUser());
        source.setPassword(settings.getDBPass());
        
        try (Connection conn = source.getConnection()) {
            Statement stmt = conn.createStatement();
            craftDB(stmt);
            stmt.close();
        }
    }
    
    /**
        Creates the database
    
        @param stmt Statement
        @throws SQLException Error creating the database
    */
    
    private static void craftDB(Statement stmt) throws SQLException
    {
        final String DB_NAME = "ReserveDB";
        
        String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
        stmt.execute(sql);
        
        sql = "USE " + DB_NAME;
        stmt.execute(sql);
        
        craftTables(stmt);
    }
    
    /**
        Creates the database tables
    
        @param stmt Statement
        @throws SQLException Error creating tables
    */
    
    private static void craftTables(Statement stmt) throws SQLException
    {
        // Create Locations table
        String sql = "CREATE TABLE IF NOT EXISTS Locations (" +
                     "LocationID INT NOT NULL AUTO_INCREMENT, " +
                     "LocationName VARCHAR(20) NOT NULL UNIQUE, " +
                     "Capacity INT NOT NULL, " +
                     "PRIMARY KEY (LocationID)" +
                     ")";
        
        stmt.execute(sql);
        
        // Create Timeframe table
        sql = "CREATE TABLE IF NOT EXISTS Timeframes (" +
              "TimeframeID INT NOT NULL AUTO_INCREMENT, " +
              "StartDate DATE NOT NULL, " +
              "EndDate DATE NOT NULL, " +
              "StartTime TIME NOT NULL, " +
              "EndTime TIME NOT NULL, " +
              "PRIMARY KEY (TimeframeID), " +
              "CONSTRAINT UC_Timeframes UNIQUE " +
              "(StartDate, EndDate, StartTime, EndTime)" +
              ")";

        stmt.execute(sql);
        
        // Create Reservables table        
        sql = "CREATE TABLE IF NOT EXISTS Reservables (" +
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
        
        stmt.execute(sql);
        
        // Create Reservers table        
        sql = "CREATE TABLE IF NOT EXISTS Reservers (" +
              "ReserverID INT NOT NULL AUTO_INCREMENT, " +
              "FirstName VARCHAR(35) NOT NULL, " +
              "LastName VARCHAR(35) NOT NULL, " +
              "Email VARCHAR(255) NOT NULL, " +
              "Phone VARCHAR(16) NOT NULL, " +
              "PRIMARY KEY (ReserverID)" +
              ")";
        
        stmt.execute(sql);
        
        // Create Reservervations table        
        sql = "CREATE TABLE IF NOT EXISTS Reservations (" +
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
        
        stmt.execute(sql);
    }
}