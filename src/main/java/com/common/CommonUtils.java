package com.common;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * ���ù����෽��
 * @author Administrator
 *
 */
public class CommonUtils {
	
	/**
	 * md5����
	 * @param args
	 */
	public static String md5Entrypt(String str){
		Md5PasswordEncoder encode = new Md5PasswordEncoder();
		return encode.encodePassword(str, null);
	}
	public static void main(String[] args) {
		Md5PasswordEncoder encode = new Md5PasswordEncoder();
		System.out.println(encode.encodePassword("abc", null));
		//System.out.println(md5Entrypt("abc"));

	}

}
