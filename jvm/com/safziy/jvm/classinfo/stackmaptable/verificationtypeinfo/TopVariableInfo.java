package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;

/**
 * 指定局部变量的验证类型为Top。
 *
 */
public class TopVariableInfo extends AbstractVerificationTypeInfo implements IVerificationTypeInfo {

	public TopVariableInfo(ConstantPoolInfo constantPoolInfo, short tag) {
		super(constantPoolInfo, tag);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {

	}

	@Override
	public void showInfo() {

	}

}
