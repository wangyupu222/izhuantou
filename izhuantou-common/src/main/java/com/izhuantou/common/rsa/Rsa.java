package com.izhuantou.common.rsa;



import java.security.Key;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.apache.commons.codec.binary.Base64;




/**
 * 
 * @author Administrator
 * 仅供参考用
 * 生成公钥和私钥
 * 交换公私钥，请见公钥华兴银行，以便验签
 */
public class Rsa {
	
	private static String PUBLIC_KEY="RSAPublicKey";
	
	private static String PRIVATE_KEY="RSAPrivateKey";
	
	private static String ALGORITHM="DESede";
	
	//3des应用加密密码（由华兴银行统一分配)
	private static String Des3Key="AZJ216D4G9849H6GEMJ0K1I3";

	//String str2="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCU7YSYMHFMLZhAcXyOdli9CgucaxgYUur0+0qvBG044VyAOtFTAlPg9K6LC9LXIIEx2fdWXCbWu9ZItqG2XxOWqA8BDetmCGnwhY8boy2XaTDGHLTxyVZa2QtXE8IDh4XyBzLTApjUBrJXOeG6Q0QiOIdTVk/4r1pQ9vm2TajUOQIDAQAB";
	
	//public static String pString="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI5yv2vam3I5BZ1y5vnovsHoj7HLLWMPrSVbAdLVXgpOet45F0PdErP/9Lo7NKFAvy0ThpjJzKfFwMb2uiZFEVXv9fguPb41sPH4ZLbpwYCqlM1nwL7iR3qt7tYCcdFAbzEpe11slT/qnKg+D/gIjDwCVTontOzWZ7r9Hes/HsYHAgMBAAECgYBuQOhiLUilhvcOn5GhGoETCtK1dSIDC24jfKYTrmvkJEw7VgrozuKcCV89CXg52yhdVkCWX5MUxB3qoasA1fKM1r7FPiFMjoyXVlGBjnrWUV/l43N0Ykhmoc0DV4PqAgTd1iM4V3NDrcJR2K/fAGs03HxnwcO86XawhQ3CEWhzIQJBAPE8Pb9DHRboAA5MHIPFwJ6vOix9hNkXlJlo4IXqSyY8468eS+TezGk8inTn2LK9bvPTUdBCTYD6W3nTdN3j1fECQQCXKq0ZrhIYF5R/jGSZbb+xLyLh6CrxMhMJRpSKFXfUi8NpfeKygbYVOiVkK9fuaZxfHSqw+Ia6u+1MrtATfIN3AkBd5lsKpe8eyXOsHQhEaqdNnVuBkeIyNrGK/X3X4pkZ6z2dHqfgELF0jTI/yzdYa9BSbIE+FXDhsZIF279z14mhAkAGIOvNq7n+Eg6qMSXu9n0cEN4oLIJ5Lt+Is7zX8FDgZB4zC7CCLjIUoDvd2M0zVELLtGIDtL+5mF7higDTmWu3AkAICOfZ4XNsFX6thaED2m9P+d/fKr7qnngfG2ek052CmUCMTU6yIpgV+KGuk/qYJb+SOaomwXg4d1iRvzTssXvV";
	
	//public static String pString="MIICWwIBAAKBgQCskLd+4H8WkjWzOCCnGZx8RLKWHce6BwOwfaMpYAMAMji6qUvhJFa8c3FkGm7oZ4Yzmwf1CuMcxiyX2/n9B/4YQhXJXerWpTX1W7ZbfTIUDq5IMReylZCOYISBcH3cr4MGc+Z23+aFGj1nDIHi5+iF8F60vIofxliwgUgsTZUpRwIDAQABAoGAA+23f7f0rLrfZhkdBTDcEygtOKWs6ltGhjoWANciulpqWDWKeLGvF9M4/aJsa/YPZBVfdQCucvVABqUHZ+9KpZElS6S1b528/mYfaXghynxxQVY8VhcAFD+FaYPIchgOgIPBgGZj7yoanCeX44lqGPMD9T36k5q03PbkOad1HQECQQDfFcCRXpeOUBkUA/kpOpHQnepKRlViMlaIkAQjVerPCfipfAfqXeJhr6r9Od+tr9wMjGb2iWBW3NKdt9s/FbCnAkEAxgbDItcAFssR9xX4tj0QnYH95zeSKWMmwQV4SsK7kiEbITu8iFmy19H60uCGx1MGWmaBmhvt4rTja/BPDVY2YQJAXNUodVXvq4EZboLYxJdi5nVQcegiz+IysFF951ZxiAaWO7EpehXnhs8bUJUsx5JO98nVbvJr7Lmny4oe6nmUMQJAFNMpOkS58auxW/anjSKm2KmO9f3WFRwkOzBEEjigWSG6JKiPjxc/w4qFgh+yjMuiY2dcQcSmI606LWO8LH7ggQJASNzYANfQh1BXEgJi/eJitWKZHmXifBv0/UX55uP15tvbyrEycOsWIbV5E4ybe4m1KEYgve7ixRyYcQ0G2XjNVQ==";
	
	//public static String publicStr="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOcr9r2ptyOQWdcub56L7B6I+xyy1jD60lWwHS1V4KTnreORdD3RKz//S6OzShQL8tE4aYycynxcDG9romRRFV7/X4Lj2+NbDx+GS26cGAqpTNZ8C+4kd6re7WAnHRQG8xKXtdbJU/6pyoPg/4CIw8AlU6J7Ts1me6/R3rPx7GBwIDAQAB";
	
	public static Map<String,Object> initKey() throws Exception{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024); //指定密钥的长度，初始化密钥对生成器
        KeyPair kp = kpg.generateKeyPair(); //生成密钥对
        RSAPublicKey puk = (RSAPublicKey) kp.getPublic();   
        RSAPrivateCrtKey prk = (RSAPrivateCrtKey) kp.getPrivate();
        Map<String,Object> keyMap = new HashMap<String,Object>(2);
        keyMap.put(PUBLIC_KEY, puk);
        keyMap.put(PRIVATE_KEY, prk);
        return keyMap;
	}
	
	public static String getPublicKey(Map<String,Object> keyMap) throws Exception{
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		byte[] publicKey = key.getEncoded();
		return encryptBASE64(publicKey); 
	}
	
	public static String getPivateKey(Map<String,Object> keyMap) throws Exception{
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		byte[] pivateKey = key.getEncoded();
		return encryptBASE64(pivateKey); 
	}

	public static byte[] decryptBASE64(String key) throws Exception{
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	
	public static String encryptBASE64(byte[] key) throws Exception{
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	public static String  DES3EncryptMode(String src) throws Exception {

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		DESedeKeySpec desKeySpec = new DESedeKeySpec(Des3Key.getBytes()); 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec); 
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return new String(Base64.encodeBase64(cipher.doFinal(src.getBytes("UTF-8"))));
	}
	
	
	public static String  DES3DecryptMode(String src) throws Exception {
		
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		DESedeKeySpec desKeySpec = new DESedeKeySpec(Des3Key.getBytes()); 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec); 
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte [] dd=cipher.doFinal(Base64.decodeBase64(src.getBytes("UTF-8")));
		return new String(dd,"UTF-8");
	}
/*	
    *//** 余额查询**//*
    public static String ZTSA54Q67() throws Exception{
		String TDATA=""
			+"<TDATA>"
			+"<MERCHANTID>P2P001</MERCHANTID>"
			+"<MERCHANTNAME>P2P测试</MERCHANTNAME>"
			+"<ACNO>6236882299000000435</ACNO>"
			+"<ACNAME>PTP测试7</ACNAME>"
		    +"</TDATA>";
		String XMLPARA=DES3EncryptMode(TDATA);
		return XMLPARA;
    }
    
    public static void main(String args[]) throws Exception {
    	// int keylen = getRandom();
    	Map<String,Object> testMap = initKey();
    	System.out.println("生成公钥：     "+getPublicKey(testMap));
    	System.out.println("生成私钥：     "+getPivateKey(testMap));

    	String encrypt=ZTSA54Q67();
    	
    	System.out.println("加密后的内容：     "+encrypt);
    	System.out.println("解密后的内容：     "+DES3DecryptMode(encrypt));
   }
*/

   
}
