package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.IVerificationTypeInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.VerificationTypeInfoFactory;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * tag值为[252-254]。offset_delta =
 * offset_delta。表示操作数栈为空，而当前帧的局部变量比前一帧的局部变量多frame_type – 251个。因而它也定义了frame_type
 * – 251项的verification_type_info类型。
 * 
 * @author Administrator
 *
 */
public class AppendFrame extends AbstractStackMapFrame implements IStackMapFrame {
	short offset_delta;
	List<IVerificationTypeInfo> locals;

	public AppendFrame(ConstantPoolInfo constantPoolInfo, short frame_type) {
		super(constantPoolInfo, frame_type);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		offset_delta = ReadUtils.readShort(is, 2);
		for (int i = 0; i < frame_type - 251; i++) {
			locals.add(VerificationTypeInfoFactory.analyze(is, constantPoolInfo));
		}
	}

	@Override
	public void showInfo() {

	}

}
