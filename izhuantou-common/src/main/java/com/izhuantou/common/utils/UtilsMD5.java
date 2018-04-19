package com.izhuantou.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilsMD5 {

    public String Md5(String password) {
	MessageDigest md;
	char[] cha = null;
	try {
	    md = MessageDigest.getInstance("MD5");
	    md.update(password.getBytes());
	    byte[] result = md.digest();
	    cha = encodeHex(result);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}

	return new String(cha);
    }

    private static char[] encodeHex(byte[] bytes) {
	char chars[] = new char[32];// 存32个字节
	for (int i = 0; i < chars.length; i = i + 2) {
	    byte b = bytes[i / 2];// 从数组中取出0--15字节
	    // 先将高4为转换为16进制
	    chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
	    // 再将低4位转换为16进制
	    chars[i + 1] = HEX_CHARS[b & 0xf];
	}
	return chars;
    }

    private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	    'f' };
}
