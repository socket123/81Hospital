package com.chusu.platform.util;


import org.bouncycastle.util.encoders.UrlBase64;

/**
 * this class is designed to encode/decode text that is part of URLs, such as
 * personId and recruitId.
 * 
 * @author user
 * 
 */
public class URLEncrypt {
//	public static void main(String[] args) {
//		String str = "1D1B54772B5662B3E7A98D128EF9EC46";
//		String ret = URLEncrypt.decode(str);
//		System.out.println(ret);
//	}
	
	public static String encode(String str) {
		String ret = null;
		if (str == null || str.trim().length() == 0)
			return str;
		try {
			ret = PBEEncryptor.getInstance().encode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String decode(String str) {
		String ret = null;
		if (str == null || str.trim().length() == 0)
			return str;
		try {
			ret = PBEEncryptor.getInstance().decode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String encodeOld(String str) {
		String ret = null;
		if (str == null || str.trim().length() == 0)
			return str;
		try {
			byte[] bytes = UrlBase64.encode(str.getBytes());
			ret = new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String decodeOld(String str) {
		String ret = null;
		if (str == null || str.trim().length() == 0)
			return str;
		try {
			byte[] bytes = UrlBase64.decode(str.getBytes());
			ret = new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
