package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * tag值为251。offset_delta = offset_delta。表示当前帧和前一帧有相同的局部变量，并且操作数栈为空。
 * 和samep_frame的区别是same_frame_extended中的offset_delta值直接给出。
 *
 */
public class SameFrameExtended extends AbstractStackMapFrame implements IStackMapFrame {
	short offset_delta;

	public SameFrameExtended(ConstantPoolInfo constantPoolInfo, short frame_type) {
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
