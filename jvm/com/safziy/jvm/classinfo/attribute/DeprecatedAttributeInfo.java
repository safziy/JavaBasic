package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;

public class DeprecatedAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {

	public DeprecatedAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
	}

	public void showInfo() {
		System.out.println("\tDeprecated");
	}

}
