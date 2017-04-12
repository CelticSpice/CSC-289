/**
    Maintains the sanitization of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBJanitor
{
    /**
        Runs the janitor, cleaning up old records from the database
    
        @throws SQLException Database access error
    */
    
    public static void run() throws SQLException
    {
        DBDataSource source = DBDataSource.getInstance();
        
        try (Connection connection = source.getConnection()) {
            Statement stmt = connection.createStatement();
            
            String sql = "DELETE FROM Timeframes " +
                         "WHERE Timeframes.EndDate <= CURDATE() " +
                         "AND Timeframes.EndTime < CURTIME()";
            
            stmt.executeUpdate(sql);
            
            sql = "DELETE Timeframes FROM Timeframes " +
                  "INNER JOIN Reservables " +
                  "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
                  "LEFT JOIN Reservations " +
                  "ON Reservables.LocationID = Reservations.LocationID " +
                  "AND Reservables.TimeframeID = Reservations.TimeframeID " +
                  "WHERE Timeframes.StartDate <= CURDATE() " +
                  "AND Timeframes.StartTime < CURTIME() " +
                  "AND Reservations.TimeframeID IS NULL";
            
            stmt.executeUpdate(sql);
            
            sql = "DELETE Reservers FROM Reservers " +
                  "LEFT JOIN Reservations " +
                  "ON Reservers.ReserverID = Reservations.ReserverID " +
                  "WHERE Reservations.ReserverID IS NULL";
            
            stmt.executeUpdate(sql);
            
            sql = "DELETE Locations FROM Locations " +
                  "LEFT JOIN Reservables " +
                  "ON Locations.LocationID = Reservables.LocationID " +
                  "WHERE Reservables.LocationID IS NULL";
            
            stmt.executeUpdate(sql);
            
            stmt.close();
        }
    }
}