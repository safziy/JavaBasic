package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * 指定验证类型为uninitialized（这种类型是指什么？）。offset记录了用于创建实例的new指令的偏移量。（The offset item
 * indicates the offset of the new instruction that created the object being
 * stored in the location.这段话是什么意思？）
 *
 */
public class UninitializedVariableInfo extends AbstractVerificationTypeInfo implements IVerificationTypeInfo {
	short offset;

	public UninitializedVariableInfo(ConstantPoolInfo constantPoolInfo, short tag) {
		super(constantPoolInfo, tag);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		offset = ReadUtils.readShort(is, 2);
	}

	@Override
	public void showInfo() {

	}

}
