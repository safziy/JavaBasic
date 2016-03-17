package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public abstract class AbstractAttributeInfo implements IAttributeInfo {
	ConstantPoolInfo constantPoolInfo;
	protected int len;
	protected String name;

	public AbstractAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		this.constantPoolInfo = constantPoolInfo;
		this.name = name;
	}

	public final void analyze(InputStream is) throws IOException {
		len = ReadUtils.readInt(is, 4);
		innerAnalyze(is);
	}

	public abstract void innerAnalyze(InputStream is) throws IOException;
}
