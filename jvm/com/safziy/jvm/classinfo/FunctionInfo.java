package com.safziy.jvm.classinfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.accessflag.FieldAccessFlag;
import com.safziy.jvm.classinfo.attribute.AttributeInfoFactory;
import com.safziy.jvm.classinfo.attribute.IAttributeInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class FunctionInfo {
	ConstantPoolInfo constantPoolInfo;

	private short accessFlags;
	private short nameIndex;
	private short descriptorIndex;
	private List<IAttributeInfo> attributes;

	public FunctionInfo(ConstantPoolInfo constantPoolInfo) {
		this.constantPoolInfo = constantPoolInfo;
	}

	public void analyze(InputStream is) throws IOException {
		accessFlags = ReadUtils.readShort(is, 2);
		nameIndex = ReadUtils.readShort(is, 2);
		descriptorIndex = ReadUtils.readShort(is, 2);
		int attributeCount = ReadUtils.readInt(is, 2);
		attributes = new LinkedList<IAttributeInfo>();
		for (int i = 0; i < attributeCount; i++) {
			IAttributeInfo attribute = AttributeInfoFactory.analyze(is, constantPoolInfo);
			attributes.add(attribute);
		}
	}

	public void showInfo() {
		System.out.println("\tdescriptor:\t" + constantPoolInfo.getConstantValue(descriptorIndex));
		System.out.println("\tname:\t" + constantPoolInfo.getConstantValue(nameIndex));
		System.out.print("\tFunctionAccessFlag:\t");
		FieldAccessFlag.showInfo(accessFlags);
		for (IAttributeInfo attributeInfo : attributes) {
			attributeInfo.showInfo();
		}
	}
}
