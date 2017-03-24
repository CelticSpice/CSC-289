/**
    A query of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

import edu.faytechcc.student.burnst9091.data.Location;
import edu.faytechcc.student.burnst9091.data.Reservable;
import edu.faytechcc.student.burnst9091.data.Timeframe;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Query
{
    // Fields
    protected String sql;
    
    /**
        Constructs a new Query
    */
    
    public Query()
    {
        sql = "";
    }
    
    /**
        Returns a string representation of the object
    
        @return A string representation of the object
    */
    
    @Override
    public String toString()
    {
        return sql;
    }
    
    /**
     * QueryReservables - Query reservables from the database based on search
     * filters
     * 
     * @return A ResultSet of reservables meeting search criteria
     * @throws SQLException Error querying the database
     */
    public List<Reservable> queryReservables() throws SQLException
    {
        /**
         * List<Location> locations = new ArrayList<>();
        
        String[] names = queryLocationNames();
        
        int capacity;
        Location location;
        List<Timeframe> timeframes;
        
        for (String name : names)
        {
            capacity = queryLocationCapacity(name);
            timeframes = queryLocationTimeframes(name);
            
            location = new Location(name, capacity, timeframes);
            
            locations.add(location);
        }
        
        return locations.toArray(new Location[locations.size()]);
         */
        
        List<Location> locations = new ArrayList();
        List<Reservable> reservables = new ArrayList();
        
        String[] names = queryLocationNames();
        
        int capacity;
        Location location;
        List<Timeframe> timeframes;
        
        for (String name : names)
        {
            capacity = queryLocationCapacity(name);
            timeframes = queryLocationTimeframes(name);
            
            location = new Location(name, capacity, timeframes);
            
            for (Timeframe t : timeframes)
            {
                reservables.add(new Reservable(location, t));
            }
            
            locations.add(location);
        }
        return reservables;
    }
}