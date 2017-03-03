/**
    The database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
        createTables();
        System.out.println("Successfully created database");
    }
    
    /**
        CreateTables - Create the database tables, if they do not exist
    
        @param statement Statement object
        @throws SQLException There was an error creating the tables
    */
    
    public static void createTables() throws SQLException
    {
        Connection conn = DriverManager.getConnection(DB);
        Statement statement = conn.createStatement();
        
        statement.execute("USE ReserveDB");
        
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
    
    /**
        GetLocationNames - Return the unique names of every location in the
        database
    
        @throws SQLException There was an error working with the database
        @return The unique names of every location
    */
    
    public static String[] getLocationNames() throws SQLException
    {
        Connection conn = DriverManager.getConnection(DB);
        Statement stmt = conn.createStatement();
        
        stmt.execute("USE ReserveDB");
        
        String sql = "SELECT UNIQUE name FROM Reservables";
        
        ResultSet rSet = stmt.executeQuery(sql);
        
        ArrayList<String> names = new ArrayList<>();
        
        if (isData(rSet))
            while (rSet.next())
                names.add(rSet.getString(1));
        
        return names.toArray(new String[names.size()]);
    }
    
    /**
        GetReservableLocation - Return the reservable location with the
        specified name. If no location with the given name exists, null is
        returned
    
        @param name Name of location to retrieve
        @throws SQLException There was an error working with the database
        @return location The location with the specified name
    */
    
    public static ReservableLocation getReservableLocation(String name)
            throws SQLException
    {
        Connection conn = DriverManager.getConnection(DB);
        Statement stmt = conn.createStatement();
        
        stmt.execute("USE ReserveDB");
        
        String sql = "SELECT DISTINCT capacity FROM Reservables " +
                     "WHERE locationName = '" + name + "'";
        
        ResultSet rSet = stmt.executeQuery(sql);
        if (isData(rSet))
        {
            rSet.next();
            int capacity = rSet.getInt(1);
            
            sql = "SELECT startDate, endDate, startTime, endTime, cost " +
                  "FROM Reservables " +
                  "WHERE locationName = '" + name + "'";
            
            rSet = stmt.executeQuery(sql);
            
            ArrayList<ReservableTimeframe> timeframes = new ArrayList<>();
            
            BigDecimal cost;
            Date startDate, endDate;
            Time startTime, endTime;
            
            while (rSet.next())
            {
                startDate = rSet.getDate(1);
                endDate = rSet.getDate(2);
                startTime = rSet.getTime(3);
                endTime = rSet.getTime(4);
                cost = rSet.getBigDecimal(5);

                
                GregorianCalendar startDateTime = new GregorianCalendar();
                startDateTime.setTimeInMillis(startDate.getTime() +
                                              startTime.getTime());
                
                GregorianCalendar endDateTime = new GregorianCalendar();
                endDateTime.setTimeInMillis(endDate.getTime() +
                                            endTime.getTime());
                
                timeframes.add(new ReservableTimeframe(startDateTime,
                                                       endDateTime, cost));
            }
            
            return new ReservableLocation(name, capacity,
                timeframes.toArray(new ReservableTimeframe[timeframes.size()]));
        }
        else
            return null;
            
    }
    
    /**
        IsData - Return whether data was returned from a query
    
        @param rSet ResultSet to determine if data was returned in
        @throws SQLException Error in working with the ResultSet
        @return Whether there is data in the result set
    */
    
    private static boolean isData(ResultSet rSet) throws SQLException
    {
        return (rSet.isBeforeFirst());
    }
}