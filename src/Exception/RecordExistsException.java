/**
    An exception signaling that an identical record already exists
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Exception;

public class RecordExistsException extends Exception
{
    /**
        Constructor
    */
    
    public RecordExistsException()
    {
        super("Error: Record already exists");
    }
}