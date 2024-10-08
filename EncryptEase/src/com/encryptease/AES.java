package com.encryptease;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

  private static SecretKeySpec secretKey;
  private static byte[] key;
  String encryptedString;
  String secret;
  static String originalString;
  public static void setKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    MessageDigest sha = null;
    
      key = myKey.getBytes("UTF-8");
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);
      secretKey = new SecretKeySpec(key, "AES");
   
    }
  
  public static String encrypt( String strToEncrypt, String secret) {
    try {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

  public static String decrypt( String strToDecrypt, String secret) {
    try {
      setKey(secret);
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }
  public static void main(String[] args) {
	  final String secretKey = "sohail";
	  
	  Scanner scan= new Scanner(System.in);
	  System.out.println("enter the text you want to encrypt");
	  originalString=scan.next();
	 
	  
	  String encryptedString = AES.encrypt(originalString, secretKey) ;
	  //String decryptedString = AES.decrypt(encryptedString, secretKey) ;

	 //ss System.out.println(originalString);
	  System.out.println(encryptedString);
	  
	  //System.out.println(decryptedString);
  }
}