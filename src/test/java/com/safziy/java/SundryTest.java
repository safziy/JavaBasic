package com.safziy.java;

import org.junit.Test;

public class SundryTest {
	@Test
	public void testSwap(){
		int a = Integer.MAX_VALUE - 100;
		int b = 123456;
		
		// 使用临时变量
		int sb = a;
		int sa = b;
		
		System.out.println(sa);
		System.out.println(sb);
		
		// 不使用临时变量
		int pa = a;
		int pb = b;
		pa = pa + pb;
		pb = pa - pb;
		pa = pa - pb;
		System.out.println(pa);
		System.out.println(pb);
		
	}
}
