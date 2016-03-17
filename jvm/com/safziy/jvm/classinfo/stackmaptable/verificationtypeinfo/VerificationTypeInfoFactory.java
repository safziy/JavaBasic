package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class VerificationTypeInfoFactory {

	public static IVerificationTypeInfo analyze(InputStream is, ConstantPoolInfo constantPoolInfo) throws IOException {
		IVerificationTypeInfo verificationTypeInfo;
		short tag = ReadUtils.readShort(is, 1);
		switch (tag) {
		case 0:
			verificationTypeInfo = new TopVariableInfo(constantPoolInfo, tag);
			break;
		case 1:
			verificationTypeInfo = new IntegerVariableInfo(constantPoolInfo, tag);
			break;
		case 2:
			verificationTypeInfo = new FloatVariableInfo(constantPoolInfo, tag);
			break;
		case 3:
			verificationTypeInfo = new DoubleVariableInfo(constantPoolInfo, tag);
			break;
		case 4:
			verificationTypeInfo = new LongVariableInfo(constantPoolInfo, tag);
			break;
		case 5:
			verificationTypeInfo = new NullVariableInfo(constantPoolInfo, tag);
			break;
		case 6:
			verificationTypeInfo = new UninitializedThisVariableInfo(constantPoolInfo, tag);
			break;
		case 7:
			verificationTypeInfo = new ObjectVariableInfo(constantPoolInfo, tag);
			break;
		case 8:
			verificationTypeInfo = new UninitializedVariableInfo(constantPoolInfo, tag);
			break;
		default:
			verificationTypeInfo = null;
			break;
		}
		verificationTypeInfo.analyze(is);
		return verificationTypeInfo;
	}

}
