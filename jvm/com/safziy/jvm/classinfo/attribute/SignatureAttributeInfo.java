package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class SignatureAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short signatrue_index;

	public SignatureAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		signatrue_index = ReadUtils.readShort(is, 2);
	}

	public void showInfo() {
		System.out.println("\tSignatrue:\t" + constantPoolInfo.getConstantValue(signatrue_index));
	}

}
