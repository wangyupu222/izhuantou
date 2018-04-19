package com.izhuantou.common.rsa;

import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 加密算法RSA 
 * 包括公私钥对生成、公钥加密、私钥解密
 */
/**
 * @author Administrator
 *
 */
public class RsaCodeTool {
	
	/** */
	/**
	 * 私钥集合操作类
	 */
	private RsaEnum rsaenum=null;
	/** */
	/**
	 * 私钥集合
	 */
	private static Map<String, Object> pkversion_map=null;
	
	/** */
	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/** */
	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	/** */
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	public RSAPublicKey mPublicKey;
	public RSAPrivateKey mPrivateKey;
	
	
	/** */
	/**
	 * 最终私钥字符串
	 */
	public static String privateKey_Str = null;

	/** */
	/**
	 * 最终公钥字符串
	 */
	public static String publicKey_Str = null;

	/** */
	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/** */
	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	/** */
	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	/**
	 * 公私钥匙对生成
	 */
	public static void generater() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);

			publicKey_Str = getPublicKey(keyMap);
			privateKey_Str = getPrivateKey(keyMap);
			System.err.println("生成的公钥: \n\r" + publicKey_Str);
			System.err.println("生成的私钥： \n\r" + privateKey_Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************
	 * 函数说明：getPublicKey 取得公钥
	 * 
	 * @param key
	 *            公钥字符串
	 * @throws Exception
	 * @return PublicKey 返回公钥
	 * @author zhangmin
	 ***************************************/
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes = Base64Utils.decode(key);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;

	}

	/****************************************
	 * 函数说明：getPrivateKey 取得私钥
	 * 
	 * @param key
	 *            私钥字符串
	 * @throws Exception
	 * @return PrivateKey 返回私钥
	 * @author zhangmin
	 ***************************************/
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = Base64Utils.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

	/**
	 * 公钥加密
	 * 
	 * @param passwd
	 *            (公钥)
	 * @return
	 */
	@SuppressWarnings("finally")
	public String encrypt(String passwd,PrivateKey key) {
		String strEncrypt = null;
		try {
			// 实例化加解密类
			Cipher cipher = Cipher.getInstance("RSA");

			// 明文
			byte[] plainText = passwd.getBytes();

			// 加密(使用私钥加密)
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 将明文转化为根据私钥加密的密文，为byte数组格式
			byte[] enBytes = cipher.doFinal(plainText);
			// 为了方便传输我们可以将byte数组转化为base64的编码
			strEncrypt = Base64Utils.encode(enBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (InvalidKeyException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (BadPaddingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (NoSuchPaddingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			return strEncrypt;
		}
	}

	/**
	 * 私钥解密
	 * 
	 * @param encString（公钥加密完的字符串）
	 * @param key（解密用的私钥）
	 * @return
	 */
	@SuppressWarnings("finally")
	public String decrypt(String encString, PrivateKey key) {
		Cipher cipher = null;
		String strDecrypt = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 先将转为base64编码的加密后的数据转化为byte数组
			byte[] enBytes = Base64Utils.decode(encString);
			// 解密称为byte数组，应该为字符串数组最后转化为字符串
			byte[] deBytes = cipher.doFinal(enBytes);

			strDecrypt = new String(deBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (NoSuchPaddingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (BadPaddingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (InvalidKeyException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			return strDecrypt;
		}

	}


	/**
	 * 测试私钥解密方法 ，中文解码会出现乱码
	 * 
	 * @throws Exception
	 */
	public String test() throws Exception {
		String tem = "iBexXB3picQEYp6ycizFRAYg14bCDN6jo8qskng9KXRa0RARRndcyZxk+yVKp3VFEs0ibRRj5WWu12aOTYTCKu32Vf3X01k8AURDHI8aERQyeKSqXLHfDA308k/Tx9NMPV32BfKGv/oJuAEzRVHrZ6Td0vnUPwALpT9EfCzjKzw=";
		System.out.println(decrypt(new String(tem.getBytes(), "utf-8"), this.getPrivateKey(privateKey_Str)));
		return decrypt(new String(tem.getBytes(), "utf-8"), this.getPrivateKey(privateKey_Str));
	}

	/**
	 * 客户端公钥加密后，服务端解密方案
	 * 
	 * @param tem
	 *            （钥解密的字符串-从客户端，包括APP端，经过公钥加密后的）
	 * @return
	 * @throws Exception
	 */
	public String serverDecrypt(String tem,String pkversion) throws Exception {
//		System.out.println("版本号："+pkversion);
		rsaenum=new RsaEnum();
		pkversion_map=rsaenum.pkversion_map;
//		System.out.println(pkversion_map.size());
		
		privateKey_Str=String.valueOf(pkversion_map.get(pkversion));
//		System.out.println("解密钥匙："+ this.getPrivateKey(privateKey_Str));
		tem = decrypt(new String(tem.getBytes(), "UTF-8"), this.getPrivateKey(privateKey_Str));
		if(tem == null) {
			return tem;
		}
		return URLDecoder.decode(tem, "UTF-8");
	}
	/**
	 * 服务端私钥加密方案
	 * 
	 * @param tem
	 *            （要加密的字符串-目前是服务端生成的随机数验证码）
	 * @return
	 * @throws Exception
	 */
	public String serverEncrypt(String tem,String pkversion) throws Exception {
//		System.out.println("版本号："+pkversion);
		rsaenum=new RsaEnum();
		pkversion_map=rsaenum.pkversion_map;
//		System.out.println(pkversion_map.size());
		
		privateKey_Str=String.valueOf(pkversion_map.get(pkversion));
//		System.out.println("解密钥匙："+ this.getPrivateKey(privateKey_Str));
		tem = encrypt(new String(tem.getBytes(), "UTF-8"), this.getPrivateKey(privateKey_Str));
		if(tem == null) {
			return tem;
		}
//		return URLDecoder.decode(tem, "UTF-8");
		return tem;
	}
	/**
	 * 版本号解码
	 * 
	 * @param tem
	 * @return
	 * @throws Exception
	 */
	public String getPkversion(String tem) throws Exception {
		return URLDecoder.decode(tem, "UTF-8");
	}
	/**
	 * 测试私钥解密方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		generater();
	}
}
