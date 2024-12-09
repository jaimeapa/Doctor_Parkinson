package Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for encrypting passwords using the MD5 hashing algorithm.
 */
public class EncryptPassword {

    /**
     * Encrypts a password using the MD5 hashing algorithm.
     *
     * @param password the password to be encrypted.
     * @return a byte array containing the MD5 hash of the password.
     * @throws NoSuchAlgorithmException if the MD5 algorithm is not available in the current environment.
     */
    public static byte[] encryptPassword(String password) throws NoSuchAlgorithmException {
        // Get an instance of the MD5 MessageDigest.
        MessageDigest md = MessageDigest.getInstance("MD5");
        // Convert the password to bytes.
        byte[] passwordBytes = password.getBytes();
        // Update the digest with the bytes of the password.
        md.update(passwordBytes);
        // Return the hash as a byte array.
        return md.digest();
    }
}

