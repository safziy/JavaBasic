package com.safziy.jvm.classinfo.test;

import com.safziy.jvm.classinfo.ClassInfo;

public class ClassExplain {
	public static void main(String[] args) {
		String classBinPath = ClassExplain.class.getResource("/").getPath();
		String path = classBinPath + "com/safziy/jvm/classinfo/test/TestClass.class";
		new ClassInfo(path).analyze().showInfo();
	}

}
