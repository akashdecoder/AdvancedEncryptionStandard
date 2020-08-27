package aes.encryptdecrypt.utils;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.*;
import java.util.*;

public class CryptoUtils {
    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    public static SecretKey getAESKey(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keySize, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }

    public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secretKey;
    }

    public static String hex(byte[] bytes){
        StringBuilder result = new StringBuilder();
        for(byte b : bytes){
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static String hexWithBlockSize(byte[] bytes, int blockSize){
        String hex = hex(bytes);
        blockSize = blockSize * 2;

        List<String> result = new ArrayList<>();
        int index = 0;
        while(index < hex.length()) {
            result.add(hex.substring(index, Math.min(index+blockSize, hex.length())));
            index += blockSize;
        }
        return result.toString();
    }
}