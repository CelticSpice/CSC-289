/**
    A salt & hashing utility
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaltHasher
{
    // Fields
    private static final String DEFAULT_SALT = "ShaBuzz556qle+7d??754!+Rw5ar?";
    private static final String DEFAULT_ALGO = "SHA-256";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    
    private String salt;
    private String algo;
    
    /**
        Constructor
    */
    
    public SaltHasher()
    {
        salt = null;
        algo = null;
    }
    
    /**
        Constructor - Accepts the salt & algorithm to be used
    
        @param s The salt to be used
        @param a The algorithm to be used
    */
    
    public SaltHasher(String s, String a)
    {
        salt = s;
        algo = a;
    }
    
    /**
        ResetAlgo - Reset algorithm to default
    */
    
    public void resetAlgo()
    {
        algo = null;
    }
    
    /**
        ResetSalt - Reset salt to default
    */
    
    public void resetSalt()
    {
        salt = null;
    }
    
    /**
        SaltHash - Salt & hash the given input, returning the results
    
        @param input Input to hash
        @throws NoSuchAlgorithmException If hashing algorithm cannot be found
        @return Input salted & hashed
    */
    
    public String saltHash(String input) throws NoSuchAlgorithmException
    {
        MessageDigest digest = (algo != null) ? 
                MessageDigest.getInstance(algo) :
                MessageDigest.getInstance(DEFAULT_ALGO);
        
        input += (salt != null) ? salt : DEFAULT_SALT;
        
        byte[] digestResult = digest.digest(input.getBytes());
        
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
        SetAlgo - Set the algorithm to be used
    
        @param a The algorithm
    */
    
    public void setAlgo(String a)
    {
        if (a == null || a.isEmpty())
            algo = null;
        else
            algo = a;
    }
    
    /**
        SetSalt - Set the salt to be used
    
        @param s The salt
    */
    
    public void setSalt(String s)
    {
        if (s == null || s.isEmpty())
            salt = null;
        else
            salt = s;
    }
}