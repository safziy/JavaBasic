package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class ExceptionsAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short exceptionLen;
	List<Short> exceptionList;

	public ExceptionsAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		exceptionLen = ReadUtils.readShort(is, 2);
		exceptionList = new LinkedList<Short>();
		for (int i = 0; i < exceptionLen; i++) {
			short exception = ReadUtils.readShort(is, 2);
			exceptionList.add(exception);
		}
	}

	public void showInfo() {
		System.out.print("\tExceptions:\t");
		for (Short exception : exceptionList) {
			System.out.print(constantPoolInfo.getConstantValue(exception) + " ");
		}
		System.out.println();
	}

}
