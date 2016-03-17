package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class SourceFileAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short sourceFileIndex;

	public SourceFileAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		sourceFileIndex = ReadUtils.readShort(is, 2);
	}

	public void showInfo() {
		System.out.println("\tSourceFile:\t" + constantPoolInfo.getConstantValue(sourceFileIndex));
	}

}
