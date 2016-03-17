package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class UndefineAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	String bytes;

	public UndefineAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		bytes = ReadUtils.readHex(is, len);
	}

	public void showInfo() {
		System.out.println("\t\tUndefineAttributeInfo:\t" + name + "\t" + bytes);
	}
}
