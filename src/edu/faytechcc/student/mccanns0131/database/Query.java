/**
    A query of the database
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.mccanns0131.database;

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
}