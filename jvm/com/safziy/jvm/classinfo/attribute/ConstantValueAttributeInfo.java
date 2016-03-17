package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class ConstantValueAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short constantValueIndex;

	public ConstantValueAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		constantValueIndex = ReadUtils.readShort(is, 2);
	}

	public void showInfo() {
		System.out.println("\tConstantValue:\t" + constantPoolInfo.getConstantValue(constantValueIndex));
	}

}
