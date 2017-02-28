/**
    An application of the SHA-512 hashing algorithm
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512
{
    // Fields
    private static final String ALGO = "SHA-512";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    
    /**
        Digest - Digest & return a hex-string of the given input, hashed using
        SHA-512
    
        @param input Input to hash
        @throws NoSuchAlgorithmException If SHA-512 cannot be found
        @return hashedInput Hashed input
    */
    
    public static String digest(String input) throws NoSuchAlgorithmException
    {
        MessageDigest sha512 = MessageDigest.getInstance(ALGO);
        
        byte[] digestResult = sha512.digest(input.getBytes());
        
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