package Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorManager {
    public static String encryptPassword(String PASSWORD) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return new String(md.digest(PASSWORD.getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8);
    }
}
