package com.example.lab01.service;

import com.example.lab01.config.Lab01Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Value("${crypto.key}")
    private String strKey;


    private final Lab01Properties properties;

    public CryptoServiceImpl(Lab01Properties properties) {
        this.properties = properties;
    }


    @Override
    public String encrypt(String strClearText) {
        String strData="";


        try {
            SecretKeySpec skeyspec=new SecretKeySpec(properties.getCrypto().getKey().getBytes(),"Blowfish");
            Cipher cipher= Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(strClearText.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return strData;
    }

    @Override
    public String decrypt(String strEncrypted) {
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(properties.getCrypto().getKey().getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return strData;
    }
}
