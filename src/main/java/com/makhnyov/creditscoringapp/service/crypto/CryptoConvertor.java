package com.makhnyov.creditscoringapp.service.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.Key;
import java.util.Base64;


@Converter(autoApply = false)
public class CryptoConvertor implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "MySuperCryptoKey".getBytes();

    //шифрование данных
    @Override
    public String convertToDatabaseColumn(String string) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            return  Base64.getEncoder().encodeToString(c.doFinal(string.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //расшифрование данных
    @Override
    public String convertToEntityAttribute(String string) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            return  new String(c.doFinal(Base64.getDecoder().decode(string)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
