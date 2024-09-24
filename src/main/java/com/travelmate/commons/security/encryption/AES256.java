package com.travelmate.commons.security.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.util.Base64;

/**
 * 양방향-대칭키 AES256 암/복호화를 담당
 */
@Component
public class AES256 implements Crypto {

    private final String alg = "AES/CBC/PKCS5Padding";

    private String iv; // 16byte
    private SecretKeySpec keySpec ;
    private IvParameterSpec ivParamSpec ;

    /**
     * 생성자
     * @param key  32byte 길이를 가져야 함.
     * @throws InvalidKeyException
     */
    public AES256(@Value("${site.aes256.secret.key}")String key) throws InvalidKeyException {
        byte[] bytes = key.getBytes();
        if( bytes.length != 32 ){
            throw new InvalidKeyException(String.format( "Invalid AES key length: Must 32 bytes, but [%d] bytes", bytes.length ));
        }

        iv = key.substring(0, 16); // 16byte
        keySpec = new SecretKeySpec(key.getBytes(), "AES");
        ivParamSpec = new IvParameterSpec(iv.getBytes());
    }

    public String encrypt(String text) {

        try {
            Cipher cipher = Cipher.getInstance(alg);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String decrypt(String cipherText)  {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 샘플
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // SecretKeyGenerator 를 통해서 만들거나, 직접 생성하여 저장한 32byte 문자열.
        String secretKey = "503333c289b3b0f07e8c67f5eae2f05d";
        AES256 aes256 = new AES256( secretKey );

        String text = "이것은 평문 입니다. 771111-1234567";
        String cipherText = aes256.encrypt(text);
        System.out.println( "평문 :" + text);
        System.out.println( "암호화:" + cipherText);
        System.out.println( "복호화:" + aes256.decrypt(cipherText));
    }

}
