/**
    A query of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query
{
    // Fields
    private String sql;
    
    /**
        Constructor
    */
    
    public Query()
    {
        sql = "";
    }
    
    /**
        QueryLocationNames - Return the names of every location in the database
    
        @return names The names of every location in the database
    */
    
    public String[] queryLocationNames()
    {
        sql = "SELECT Locations.locationName " +
              "FROM Locations " +
              "ORDER BY locationName";
        
        // Get result
        
        return ResultSetParser.parseLocationNames(rs);
    }
}