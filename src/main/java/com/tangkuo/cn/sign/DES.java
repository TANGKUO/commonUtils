package com.tangkuo.cn.sign;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.tangkuo.cn.common.Constants;
import com.tangkuo.cn.encrypt.Base64;
import com.tangkuo.cn.exception.TtyException;

public class DES {

	/**
	 * ECB模式的des加密，以base64的编码输出
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String desEncrypt(String message, String key) throws TtyException {
		if (StringUtils.isEmpty(message) || StringUtils.isEmpty(key)) {
			throw new TtyException("invalid-param-err", "参数不能为空!");
		}

		// 默认就是 DES/ECB/PKCS5Padding
		try {
			Cipher cipher = Cipher.getInstance(Constants.ENCRYPT_DES);
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constants.ENCRYPT_DES);
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			cipher.init(1, secretKey);
			return byte2hex(cipher.doFinal(message.getBytes(Constants.CHARSET_UTF8)));
		} catch (Exception e) {
			throw new TtyException("des加密错误！。", e);
		}
	}

	/**
	 * ECB模式的des加密，以base64的编码输出
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message, String key) throws TtyException {
		if (StringUtils.isEmpty(message) || StringUtils.isEmpty(key)) {
			throw new TtyException("invalid-param-err", "参数不能为空!");
		}

		try {
			Cipher cipher = Cipher.getInstance(Constants.DESEDE);
			DESedeKeySpec desKeySpec = new DESedeKeySpec(Base64.decode(key));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constants.DESEDE);
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.encode(cipher.doFinal(message.getBytes(Constants.CHARSET_UTF8)));
		} catch (Exception e) {
			throw new TtyException("des加密错误！。", e);
		}
	}
	
	/**
	 * ECB模式的des解密
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String desDecrypt(String message, String key) throws TtyException {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(Constants.ENCRYPT_DES);
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constants.ENCRYPT_DES);
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			cipher.init(2, secretKey);
			return new String(cipher.doFinal(hex2byte(message)), Constants.CHARSET_UTF8);
		} catch (Exception e) {
			throw new TtyException("des解密错误！。", e);
		}
	}
	
	/**
	 * ECB模式的des解密，以base64的编码输入
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(String message, String key) throws TtyException {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(Constants.DESEDE);
			DESedeKeySpec desKeySpec = new DESedeKeySpec(Base64.decode(key));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constants.DESEDE);
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(Base64.decode(message));
		} catch (Exception e) {
			throw new TtyException("des解密错误！。", e);
		}
	}

	public static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	public static byte[] hex2byte(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		int len = str.length();
		if (len <= 0 || len % 2 == 1) {
			return null;
		}
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[(i / 2)] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String key = "";//RandomStringUtils.random(32, true, true);
		System.out.println("key: " + key);
		
		String str = "唐空间链接";
		System.out.println("加密前: " + str);

		String str1 = desEncrypt(str, "kiIoeWGSqYmzgSxIysJwSmq2As0KhKrS");
		System.out.println("加密后: " + str1);

		String str2 = desDecrypt(str1, "kiIoeWGSqYmzgSxIysJwSmq2As0KhKrS");
		System.out.println("解密后: " + str2);
	}
}
