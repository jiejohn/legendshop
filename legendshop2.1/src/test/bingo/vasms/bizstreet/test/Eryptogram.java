package bingo.vasms.bizstreet.test;

import java.security.*;
import javax.crypto.*;

/**
 * 加密解密类
 * 
 * @author WangHu
 * @version 1.00 2004年03月18日
 */
public class Eryptogram {
	private static String Algorithm = "DES";

	// 定义 加密算法,可用 DES,DESede,Blowfish
	static boolean debug = false;

	/**
	 * 构造子注解.
	 */
	public Eryptogram() {

	}

	/**
	 * 生成密钥
	 * 
	 * @return byte[] 返回生成的密钥
	 * @throws exception
	 *             扔出异常.
	 */
	public static byte[] getSecretKey() throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
		SecretKey deskey = keygen.generateKey();
		if (debug)
			System.out.println("生成密钥:" + byte2hex(deskey.getEncoded()));
		return deskey.getEncoded();

	}

	/**
	 * 将指定的数据根据提供的密钥进行加密
	 * 
	 * @param input
	 *            需要加密的数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encryptData(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug) {
			System.out.println("加密前的二进串:" + byte2hex(input));
			System.out.println("加密前的字符串:" + new String(input));

		}
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(input);
		if (debug)
			System.out.println("加密后的二进串:" + byte2hex(cipherByte));
		return cipherByte;

	}

	/**
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param input
	 *            待解密的数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decryptData(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug)
			System.out.println("解密前的信息:" + byte2hex(input));
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input);
		if (debug) {
			System.out.println("解密后的二进串:" + byte2hex(clearByte));
			System.out.println("解密后的字符串:" + (new String(clearByte)));

		}
		return clearByte;

	}

	/**
	 * 字节码转换成16进制字符串
	 * 
	 * @param byte[]
	 *            b 输入要转换的字节码
	 * @return String 返回转换后的16进制字符串
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";

		}
		return hs.toUpperCase();

	}

	public static void main(String[] args) {
		try {
			debug = true;
			Eryptogram etg = new Eryptogram();
			byte[] key = etg.getSecretKey();
			System.out.println("key = " + key);
			String aa = "1234567";
			byte[] data = aa.getBytes();
			System.out.println(data);
			byte[] en = etg.encryptData(data, key);
			System.out.println("encryptData = " + new String(en));
			byte[] de = etg.decryptData(en, key);
			System.out.println("decryptData = " + new String(de));

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
