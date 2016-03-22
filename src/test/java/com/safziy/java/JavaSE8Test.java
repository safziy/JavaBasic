package com.safziy.java;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class JavaSE8Test {
	@Test
	public void testForEach() {
		List<String> list = new LinkedList<String>();
		list.add("bbb");
		list.add("ccc");
		list.add("aaa");
		list.forEach(name -> System.out.println(name));
	}
}
