package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.IVerificationTypeInfo;
import com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo.VerificationTypeInfoFactory;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * tag值255。offset_delta =
 * offset_delta。full_frame则定义了所有的信息，包括offset_delta的值，以及当前帧和前一帧不同的所有局部变量和操作数
 * 。locals[0]表示0号局部变量；stack[0]表示栈底操作数。
 *
 */
public class FullFrame extends AbstractStackMapFrame implements IStackMapFrame {
	short offset_delta;
	short number_od_locals;
	List<IVerificationTypeInfo> locals;
	short number_od_stack_items;
	List<IVerificationTypeInfo> stack;

	public FullFrame(ConstantPoolInfo constantPoolInfo, short frame_type) {
		super(constantPoolInfo, frame_type);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		offset_delta = ReadUtils.readShort(is, 2);
		number_od_locals = ReadUtils.readShort(is, 2);
		locals = new LinkedList<IVerificationTypeInfo>();
		for (int i = 0; i < number_od_locals; i++) {
			locals.add(VerificationTypeInfoFactory.analyze(is, constantPoolInfo));
		}
		stack = new LinkedList<IVerificationTypeInfo>();
		number_od_stack_items = ReadUtils.readShort(is, 2);
		for (int i = 0; i < number_od_stack_items; i++) {
			stack.add(VerificationTypeInfoFactory.analyze(is, constantPoolInfo));
		}
	}

	@Override
	public void showInfo() {

	}

}
