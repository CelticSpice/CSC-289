/**
    A salt & hashing utility using the SHA-256 algorithm
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256SaltHasher
{
    // Fields
    private static final String ALGO = "SHA-256";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    
    private String salt;
    
    /**
        Constructor
    */
    
    public SHA256SaltHasher()
    {
        salt = "";
    }
    
    /**
        Constructor - Accepts the salt to be used
    
        @param s The salt to be used
    */
    
    public SHA256SaltHasher(String s)
    {
        if (s != null)
            salt = s;
        else
            salt = "";
    }
    
    /**
        GetSalt - Return the salt
    
        @return The salt
    */
    
    public String getSalt()
    {
        return salt;
    }
    
    /**
        SaltHash - Salt & hash the given input, returning the results
    
        @param input Input to hash
        @throws NoSuchAlgorithmException If SHA-256 algorithm cannot be found
        @return Input salted & hashed
    */
    
    public String saltHash(String input) throws NoSuchAlgorithmException
    {
        MessageDigest sha256 = MessageDigest.getInstance(ALGO);
        
        if (input == null)
            input = "";
        
        input += salt;
        
        byte[] digestResult = sha256.digest(input.getBytes());
        
        char[] hexChars = new char[digestResult.length * 2];
        for (int j = 0; j < digestResult.length; j++)
        {
            int v = digestResult[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        
        return new String(hexChars);
    }
    
    /**
        SetSalt - Set the salt to be used
    
        @param s The salt
    */
    
    public void setSalt(String s)
    {
        if (s != null)
            salt = s;
        else
            salt = "";
    }
}