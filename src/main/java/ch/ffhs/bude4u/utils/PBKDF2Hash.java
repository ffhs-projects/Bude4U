package ch.ffhs.bude4u.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PBKDF2Hash {

    /**
     * Creates a hash from a password
     *
     * @param password String
     * @return String
     */
    public static String CreateHash(String password) {
        byte[] salt = "12345678".getBytes();

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception ignored) {
            // TODO: what happens if pw could not be encrypted?
        }
        throw new IllegalArgumentException();
    }

    /**
     * Checks if a password is valid
     *
     * @param hash     String
     * @param password String
     * @return boolean
     */
    public static boolean CheckPassword(String hash, String password) {
        String hashInsertedPassword = PBKDF2Hash.CreateHash(password);
        return hashInsertedPassword.equals(hash);
    }
}
