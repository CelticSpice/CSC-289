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
    
    private Connection connection;
    private static ReserveDB db;
    
    /**
        Constructor
        @throws SQLException There was an error connecting to the database
    */
    
    private ReserveDB() throws SQLException
    {
        connection = DriverManager.getConnection(DB);
    }
    
    /**
        AddRecord - Add a record to the database
    
        @param recordAdd The adding of a record
        @throws SQLException There was an error adding a record
    */
    
    public void addRecord(RecordAdd recordAdd) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.execute("USE " + DB_NAME);
        
        stmt.executeUpdate(recordAdd.toString());
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
        CreateTables - Create the database tables, if they do not exist
    
        @param statement Statement object for working with the database
        @throws SQLException There was an error creating the tables
    */
    
    public void createTables(Statement statement) throws SQLException
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
        GetInstance - Return an instance of the database
    
        @throws SQLException There was an error getting an instance of the
                             database
        @return An instance of the database
    */
    
    public static ReserveDB getInstance() throws SQLException
    {
        if (db == null)
            db = new ReserveDB();
        
        return db;
    }
    
    /**
        RunQuery - Run the query provided and return the result set
    
        @param query The query to run
        @throws SQLException There was an error running the query
        @return The result set of the query
    */
    
    public ResultSet runQuery(Query query) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.execute("USE " + DB_NAME);
        
        return stmt.executeQuery(query.toString());
    }
}