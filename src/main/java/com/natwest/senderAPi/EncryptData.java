package com.natwest.senderAPi;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptData {
  private static Cipher ecipher;

  private static SecretKey key;
  private  String transaction;

    public EncryptData(String transactionString) {
        this.transaction = transactionString;
    }

    public String encodeBase64(String data){
        String encodedString = Base64.getEncoder().encodeToString(data.getBytes());

        return encodedString;
    }
}
