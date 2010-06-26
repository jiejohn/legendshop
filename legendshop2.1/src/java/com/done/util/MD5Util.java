package com.done.util;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

/**
 * 2009-8-17
 * 
 * @author hewq
 * 
 */
public class MD5Util {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(MD5Util.Md5Password("admin", "admin"));

    }

    /**
     * MD5加盐（私钥是name,参数是password,name)
     */
    public static String Md5Password(String name, String password) {
        if (password != null) {
            Md5PasswordEncoder coder = new Md5PasswordEncoder();
            coder.setEncodeHashAsBase64(false);
            return coder.encodePassword(password, name);
        }
        return null;
    }

}
