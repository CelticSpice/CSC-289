/**
    A result set parser
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetParser
{  
    /**
        IsEmpty - Return whether a result set is empty
    
        @param rs Result set to determine if empty
        @throws SQLException Error in working with the result set
        @return Whether the result set is empty
    */
    
    private static boolean isEmpty(ResultSet rs) throws SQLException
    {
        return !rs.isBeforeFirst();
    }
    
    /**
        ParseLocations - Parse a result set containing reservable locations
    
        @param rs The result set to parse
        @throws SQLException There was an error working with the result set
        @return A list of reservable locations
    */
    
    public static ReservableLocation[] parseLocations(ResultSet rs)
            throws SQLException
    {
        ArrayList<ReservableLocation
    }
    
    /**
        ParseLocationNames - Parse a result set containing the names of every
        location
    
        @param rs The result set to parse
        @throws SQLException There was an error working with the result set
        @return The names of every location included in the result set
    */
    
    public static String[] parseLocationNames(ResultSet rs) throws SQLException
    {
        ArrayList<String> names = new ArrayList<>();
        if (!isEmpty(rs))
            while (rs.next())
                names.add(rs.getString(1));
        return names.toArray(new String[names.size()]);
    }
}