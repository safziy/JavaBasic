package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;

/**
 * 指定验证类型为null。
 *
 */
public class NullVariableInfo extends AbstractVerificationTypeInfo implements IVerificationTypeInfo {

	public NullVariableInfo(ConstantPoolInfo constantPoolInfo, short tag) {
		super(constantPoolInfo, tag);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {

	}

	@Override
	public void showInfo() {

	}

}
