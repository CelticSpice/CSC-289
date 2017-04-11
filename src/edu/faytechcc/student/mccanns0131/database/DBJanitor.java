/**
    Maintains the sanitization of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import java.sql.SQLException;

public class DBJanitor
{
    /**
        Runs the janitor, cleaning up old records from the database
    
        @throws SQLException Database access error
    */
    
    public static void run() throws SQLException
    {
        DBDataSource source = DBDataSource.getInstance();
        DBConnection connection = source.getConnection();
        
        String sql = "DELETE FROM Timeframes " +
                     "WHERE Timeframes.EndDate <= CURDATE() " +
                     "AND Timeframes.EndTime < CURTIME()";
        
        connection.executeSQL(sql);
        
        sql = "DELETE Timeframes FROM Timeframes " +
              "INNER JOIN Reservables " +
              "ON Timeframes.TimeframeID = Reservables.TimeframeID " +
              "LEFT JOIN Reservations " +
              "ON Reservables.LocationID = Reservations.LocationID " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "WHERE Timeframes.StartDate <= CURDATE() " +
              "AND Timeframes.StartTime < CURTIME() " +
              "AND Reservations.TimeframeID IS NULL";
        
        connection.executeSQL(sql);
        
        sql = "DELETE Reservers FROM Reservers " +
              "LEFT JOIN Reservations " +
              "ON Reservers.ReserverID = Reservations.ReserverID " +
              "WHERE Reservations.ReserverID IS NULL";
        
        connection.executeSQL(sql);
        
        sql = "DELETE Locations FROM Locations " +
              "INNER JOIN Reservables " +
              "ON Locations.LocationID = Reservables.LocationID " +
              "LEFT JOIN Reservations " +
              "ON Reservables.LocationID = Reservations.LocationID " +
              "AND Reservables.TimeframeID = Reservations.TimeframeID " +
              "WHERE Reservations.LocationID IS NULL";
        
        connection.close();
    }
}