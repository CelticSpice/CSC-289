/**
    An exception signaling that an identical record does not exist
    CSC-289 - Group 4
    @author Shane McCann
*/

package Exception;

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
