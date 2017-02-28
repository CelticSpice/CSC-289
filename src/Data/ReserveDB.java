/**
    The database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReserveDB
{
    // Fields
    private static final String DB = "jdbc:mariadb://localhost?" +
                                     "user=burnst_test&password=PASSWORD";
    
    private static final String DB_NAME = "ReserveDB";
    
    /**
        Create - Create the database
    
        @throws SQLException There was an error creating the database
    */
    
    public static void create() throws SQLException
    {
        Connection conn = DriverManager.getConnection(DB);
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE ReserveDB");
        stmt.execute("USE ReserveDB");
        createTables(stmt);
        System.out.println("Successfully created database");
    }
    
    /**
        CreateTables - Create the database tables, if they do not exist
    
        @param statement Statement object
        @throws SQLException There was an error creating the tables
    */
    
    private static void createTables(Statement statement) throws SQLException
    {
        // Create Reservers table
        String sql = "CREATE TABLE Reservers ("   +
                     "reserverID int NOT NULL AUTO_INCREMENT, " +
                     "firstName varchar(35) NOT NULL, "         +
                     "lastName varchar(35) NOT NULL, "          +
                     "email varchar(255) NOT NULL, "            +
                     "phone varchar(16) NOT NULL, "             +
                     "PRIMARY KEY (reserverID)"                 +
                     ")";
        
        statement.execute(sql);
        System.out.println("Created Reservers table");
        
        // Create Reservables table
        sql = "CREATE TABLE Reservables ("    +
              "reservableID int NOT NULL AUTO_INCREMENT, "  +
              "locationName varchar(35) NOT NULL, "         +
              "capacity int NOT NULL, "                     +
              "startDate date NOT NULL, "                   +
              "endDate date NOT NULL, "                     +
              "startTime time NOT NULL, "                   +
              "endTime time NOT NULL, "                     +
              "cost decimal(7,2) NOT NULL, "                +
              "PRIMARY KEY (reservableID)"                  +
              ")";

        statement.execute(sql);
        System.out.println("Created Reservables table");
        
        // Create Reservations table        
        sql = "CREATE TABLE Reservations ("   +
              "reservableID int NOT NULL, "                 +
              "reserverID int NOT NULL, "                   +
              "eventType varchar(35) NOT NULL, "            +
              "numberAttending int NOT NULL, "              +
              "PRIMARY KEY (reservableID), "                +
              "FOREIGN KEY (reservableID) "                 +
              "REFERENCES Reservables(reservableID), "      +
              "FOREIGN KEY (reserverID) "                   +
              "REFERENCES Reservers(reserverID)"            +
              ")";
        
        statement.execute(sql);
        System.out.println("Created Reservations table");
    }
    
    /**
        DropTables - Drops the tables from the database
    
        @throws SQLException Error working with the database
    */
    
    public static void dropTables() throws SQLException
    {
        Connection conn = DriverManager.getConnection(DB);
        Statement stmt = conn.createStatement();
        stmt.execute("USE ReserveDB");
        
        stmt.execute("DROP TABLE IF EXISTS Reservations");
        stmt.execute("DROP TABLE IF EXISTS Reservables");
        stmt.execute("DROP TABLE IF EXISTS Reservers");
    }
    
    /**
        Exists - Return whether the database exists
    
        @return Whether the database exists
        @throws SQLException Error connecting to database
    */
    
    public static boolean exists() throws SQLException
    {
        Connection conn = DriverManager.getConnection(DB);
        Statement stmt = conn.createStatement();
        
        String sql = "SELECT SCHEMA_NAME " +
                     "FROM INFORMATION_SCHEMA.SCHEMATA " +
                     "WHERE SCHEMA_NAME = '" + DB_NAME + "'";
        
        ResultSet rSet = stmt.executeQuery(sql);
                
        return rSet.isBeforeFirst();
    }
}