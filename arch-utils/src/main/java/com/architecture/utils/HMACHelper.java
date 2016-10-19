package com.architecture.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * MAC算法加密解密
 * 
 * @author Fish Exp
 */
public class HMACHelper {
	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_HMAC = "HmacMD5";

	/**
	 * @Title: initMacKey
	 * @Description:初始化HMAC密钥
	 * @return
	 * @throws Exception
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_HMAC);
		// 产生密钥
		SecretKey secretKey = keyGenerator.generateKey();
		// 获得密钥
		return new String(secretKey.getEncoded());
	}

	/**
	 * @Title: encryptHMAC
	 * @Description:HMAC加密
	 * @param data
	 * @param value
	 * @return
	 * @throws Exception
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static byte[] encryptHMAC(byte[] data, String value) throws Exception {

		SecretKey secretKey = new SecretKeySpec(value.getBytes(), KEY_HMAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}
}
