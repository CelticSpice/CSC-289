/**
    An exception signaling that an identical record does not exist
    CSC-289 - Group 4
    @author Shane McCann
*/

package edu.faytechcc.student.burnst9091.exception;

public class RecordNotExistsException extends Exception
{
    /**
        Constructor
    */
    
    public RecordNotExistsException()
    {
        super("Error: Record does not exist");
    }
}
