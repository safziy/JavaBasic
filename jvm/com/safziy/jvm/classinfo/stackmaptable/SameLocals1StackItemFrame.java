package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.IVerificationTypeInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.VerificationTypeInfoFactory;

/**
 * tag值为[64-127]。offset_delta = frame_type –
 * 64。表示当前帧和前一帧有相同的局部变量，并且操作栈内的操作数条目数为1，
 * 因而它为该操作数栈内的操作数保存了一项verification_type_info。（[128-246]的tag值保留）
 */
public class SameLocals1StackItemFrame extends AbstractStackMapFrame implements IStackMapFrame {
	IVerificationTypeInfo stack;

	public SameLocals1StackItemFrame(ConstantPoolInfo constantPoolInfo, short frame_type) {
		super(constantPoolInfo, frame_type);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		stack = VerificationTypeInfoFactory.analyze(is, constantPoolInfo);
	}

	@Override
	public void showInfo() {

	}

}
