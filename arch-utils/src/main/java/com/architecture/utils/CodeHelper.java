package com.architecture.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @FileName CodeHelper.java
 * @Description 生成格式化的UUID<br>
 *              生成数字随机码<br>
 *              生成字符随机码<br>
 *              生成字符+数字的随机码<br>
 * @author Fish Exp
 * @version V1.0
 * 
 */
public class CodeHelper {
	/** 默认最大值 */
	private final static int DEFAULT_MAX_VALUE = 9;

	private CodeHelper() {

	}

	/**
	 * @Title: generateUUID
	 * @Description:生成UUID
	 * @return
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉"-"符号
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}

	/**
	 * @Title: digitalValidateCode
	 * @Description:生成指定位数的数字验证码
	 * @param num
	 *            验证码位数
	 * @param maxValue
	 *            数字的最大值
	 * @return
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static String digitalValidateCode(Integer num, Integer maxValue) {
		return digitalCode(num, maxValue);
	}

	/**
	 * @Title: digitalValidateCode
	 * @Description:生成指定位数的数字验证码
	 * @param num
	 *            验证码位数
	 * @return
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static String digitalValidateCode(Integer num) {
		return digitalCode(num, null);
	}

	private static String digitalCode(Integer num, Integer maxValue) {
		if (maxValue == null) {
			maxValue = DEFAULT_MAX_VALUE;
		}
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			String rand = String.valueOf(random.nextInt(maxValue));
			sb.append(rand);
		}
		return sb.toString();
	}

	/**
	 * @Title: charValidateCode
	 * @Description:生成指定位数的字符和字符的验证码
	 * @param num
	 *            验证码位数
	 * @return
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static String charValidateCode(Integer num) {
		return charCode(num);
	}

	private static String charCode(Integer num) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			int itmp = random.nextInt(26) + 65;
			char ctmp = (char) itmp;
			sb.append(ctmp);
		}
		return sb.toString();
	}

	/**
	 * @Title: charAndDigitalValidateCode
	 * @Description:生成指定位数的数字和字符的验证码
	 * @param num
	 *            验证码位数
	 * @return
	 * @author Fish
	 * @date 2016年3月5日
	 */
	public static String charAndDigitalValidateCode(Integer num) {
		return charAndDigitalCode(num);
	}

	private static String charAndDigitalCode(Integer num) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		String temp = null;
		for (int i = 0; i < num; i++) {
			char charOrNum = random.nextInt(2) % 2 == 0 ? 'c' : 'd';
			if (charOrNum == 'c') {
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				char itmp = (char) (choice + random.nextInt(26));
				char ctmp = (char) itmp;
				sb.append(ctmp);
			} else if ('d' == charOrNum) {
				temp = String.valueOf(random.nextInt(9));
				sb.append(temp);
			}
		}
		return sb.toString();
	}

}
