package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * 指定验证类型为cpool_index中指定的类型实例。
 *
 */
public class ObjectVariableInfo extends AbstractVerificationTypeInfo implements IVerificationTypeInfo {
	short cpool_index;

	public ObjectVariableInfo(ConstantPoolInfo constantPoolInfo, short tag) {
		super(constantPoolInfo, tag);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		cpool_index = ReadUtils.readShort(is, 2);
	}

	@Override
	public void showInfo() {

	}

}
