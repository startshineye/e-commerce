package com.common.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author GZZ
 * @date 20130416
 */
@Component
public class MD5Util {

	/**
	 * 根据传入的字符串，生成md5加密后的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String genMd5(String str) {
		String s = str;
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			String md5value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException ex) {
				Logger.getLogger(MessageDigest.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				md5value = BASE64Encoder.encode(md5.digest(s.getBytes("utf-8")));
			} catch (Exception ex) {
				Logger.getLogger(MessageDigest.class.getName()).log(Level.SEVERE, null, ex);
			}
			return md5value;
		}
	}
}
