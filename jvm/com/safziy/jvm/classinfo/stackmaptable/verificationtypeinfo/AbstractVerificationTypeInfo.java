package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;

public abstract class AbstractVerificationTypeInfo implements IVerificationTypeInfo {
	short tag;
	ConstantPoolInfo constantPoolInfo;

	public AbstractVerificationTypeInfo(ConstantPoolInfo constantPoolInfo, short tag) {
		this.constantPoolInfo = constantPoolInfo;
		this.tag = tag;
	}

	public final void analyze(InputStream is) throws IOException {
		innerAnalyze(is);
	}

	public abstract void innerAnalyze(InputStream is) throws IOException;

}
