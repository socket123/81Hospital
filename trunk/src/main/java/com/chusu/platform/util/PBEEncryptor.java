package com.chusu.platform.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.ApplicationContext;

/**
 * this class is designed to encode/decode text that is part of URLs, such as
 * personId and recruitId.
 * 
 * @author user
 * 
 */
public class PBEEncryptor {
	private static PBEEncryptor me;
	private ApplicationContext ctx;
	private StandardPBEStringEncryptor encryptor;

	private PBEEncryptor() {
		//ctx = WebApplicationContextUtils
		//		.getWebApplicationContext(ServletActionContext
		//				.getServletContext());
		// encryptor =
		// (StandardPBEStringEncryptor)ctx.getBean("configurationEncryptor");
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("SZHR_WEB3");
		encryptor.setSaltGenerator(new ZeroSaltGenerator());
		encryptor.setStringOutputType("hexadecimal"); // the default is base64
	}

	public static PBEEncryptor getInstance() {
		me = new PBEEncryptor();
		return me;
	}

	public String encode(String str) {
		String ret = null;
		if (str == null || str.trim().length() == 0)
			return str;
		try {
			ret = encryptor.encrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public String decode(String str) {
		String ret = null;
		if (str == null || str.trim().length() == 0)
			return str;
		try {
			ret = encryptor.decrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		//PBEWithMD5AndDES
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword("SZHR_PASSWORD_2012");
		String encrypted = encryptor.encrypt("HNUIP");
		System.out.println(encrypted);
	}

}
