package com.legendshop.cas.auth;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.lang.StringUtils;

/**
 * A MD5 password encoder with salt, keep consistent with the formal legendShop
 * implementation.
 * 
 * @author George Guo
 * 
 */
public class DelegatePasswordEncoder implements org.jasig.cas.authentication.handler.PasswordEncoder {

	/**
	 * The MD5 password encoder.
	 */
	private Md5PasswordEncoder md5PasswordEncoder;

	/**
	 * The secret key as the 'salt' to encrypt.
	 */
	private String saltKey;

	@Override
	public String encode(String password) {
		if (password == null || StringUtils.isBlank(password)) {
			return password;
		}
		return md5PasswordEncoder.encodePassword(password, saltKey);
	}

	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder) {
		this.md5PasswordEncoder = md5PasswordEncoder;
	}

	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey;
	}

}
