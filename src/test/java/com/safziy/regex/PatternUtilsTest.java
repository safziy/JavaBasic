package com.safziy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class PatternUtilsTest {
	
	@Test
	public void test(){
		Pattern pattern = Pattern.compile("\"[a-zA-Z0-9_]+\":\"?[a-zA-Z0-9_]+\"?");
		Matcher matcher = pattern.matcher("\"Dimension1\":17.70,\"Dimension2\":16.70,\"Level\":\"MN\",\"Type\":\"LVGRM\"");
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		
		// 分组匹配
		Pattern p2 = Pattern.compile("(aa)(bb)\\1");
		System.out.println(p2.matcher("aabbaa").matches());
		
		Pattern p3 = Pattern.compile("(aa)(bb)");
		System.out.println(p3.matcher("aabbaa").matches());
		
		Pattern p4 = Pattern.compile("\\w+(?=ing)");
		System.out.println(p4.matcher("doing").matches());
	}

	@Test
	public void testCheckMailAddr() {
		Assert.assertTrue(PatternUtils.checkMailAddr("safziy@qq.com"));
		Assert.assertTrue(PatternUtils.checkMailAddr("787395455@qq.com"));
		Assert.assertFalse(PatternUtils.checkMailAddr("787395455@qqcom")); 
		Assert.assertFalse(PatternUtils.checkMailAddr("78739%5455@qq.com"));
		Assert.assertTrue(PatternUtils.checkMailAddr("saf_ziy@qq.com"));
		Assert.assertTrue(PatternUtils.checkMailAddr("saf__ziy@qq.com"));
		Assert.assertTrue(PatternUtils.checkMailAddr("_safziy@qq.com"));
		Assert.assertTrue(PatternUtils.checkMailAddr("saf+ziy@qq.com"));
		Assert.assertFalse(PatternUtils.checkMailAddr("+safziy@qq.com"));
	}
	
	@Test
	public void testCheckHasChinese() {
		Assert.assertTrue(PatternUtils.checkHasChinese("我的macbook"));
		Assert.assertFalse(PatternUtils.checkHasChinese("My macbook"));
	}

}
