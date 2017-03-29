/**
    A salt & hashing utility using the SHA-256 algorithm
    CSC-289 - Group 4
    @author Timothy Burns
*/

package edu.faytechcc.student.burnst9091.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256SaltHasher
{
    // Fields
    private static final String ALGO = "SHA-256";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String SALT = "ShaBuzz556qle+7d??754!+Rw5ar?";    
    
    /**
        Salts & hashes the given input, returning the results
    
        @param input Input to hash
        @throws NoSuchAlgorithmException If SHA-256 algorithm cannot be found
        @return Input salted & hashed
    */
    
    public static String saltHash(String input) throws NoSuchAlgorithmException
    {
        MessageDigest sha256 = MessageDigest.getInstance(ALGO);
        
        if (input == null)
            input = "";
        
        input += SALT;
        
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
}