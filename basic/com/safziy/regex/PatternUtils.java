package com.safziy.regex;

import java.util.regex.Pattern;

/**
 * 
 * @author safziy 
 * 787395455@qq.com
 *
 */
public class PatternUtils {
	/**
	 * 验证邮件地址的合法性
	 */
	private final static Pattern mailAddrPattern = Pattern
			.compile("^\\w+([-\\+\\.]\\w+)*@\\w+([-\\.]\\w+)*\\.\\w+([-\\.]\\w+)*$");

	/**
	 * 中文提取
	 */
	private static Pattern chinesePattern = Pattern.compile("[\\u4E00-\\u9FFF]+");

	/**
	 * 邮件地址的验证方法
	 */
	public static boolean checkMailAddr(CharSequence mailAddr) {
		return mailAddrPattern.matcher(mailAddr).matches();
	}

	/**
	 * 检查是否有中文
	 */
	public static boolean checkHasChinese(CharSequence string) {
		return chinesePattern.matcher(string).find();
	}
}
