package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.IVerificationTypeInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.VerificationTypeInfoFactory;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * 
 * tag值为247。offset_delta = offset_delta。表示当前值帧和前一帧有相同的局部变量，并且操作栈内的操作数条目数为1，
 * 因而它为该操作数栈内的操作数保存了一项verification_type_info
 * （详见下表）。和上一类型的区别是这里的offset_delta是直接给出的。
 *
 */
public class SameLocals1StackItemFrameExtended extends AbstractStackMapFrame implements IStackMapFrame {
	short offset_delta;
	IVerificationTypeInfo stack;

	public SameLocals1StackItemFrameExtended(ConstantPoolInfo constantPoolInfo, short frame_type) {
		super(constantPoolInfo, frame_type);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		offset_delta = ReadUtils.readShort(is, 2);
		stack = VerificationTypeInfoFactory.analyze(is, constantPoolInfo);
	}

	@Override
	public void showInfo() {

	}

}
