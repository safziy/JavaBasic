package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * 
 * tag值为[248, 250]。offset_delta = offset_delta。表示当前操作栈为空，而当前局部变量比前一帧的局部变量少后面的251
 * – frame_type个局部变量。
 *
 */
public class ChopFrame extends AbstractStackMapFrame implements IStackMapFrame {
	short offset_delta;

	public ChopFrame(ConstantPoolInfo constantPoolInfo, short frame_type) {
		super(constantPoolInfo, frame_type);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {
		offset_delta = ReadUtils.readShort(is, 2);
	}

	@Override
	public void showInfo() {

	}

}
