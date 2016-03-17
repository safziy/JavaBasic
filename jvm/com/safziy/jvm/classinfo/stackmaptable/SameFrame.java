package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;

/**
 * 
 * tag的值为[0-63]。offset_delta = frame_type。表示当前帧和前一帧有相同的局部变量，并且当前操作数栈为空。
 *
 */
public class SameFrame extends AbstractStackMapFrame implements IStackMapFrame {

	public SameFrame(ConstantPoolInfo constantPoolInfo, short frame_type) {
		super(constantPoolInfo, frame_type);
	}

	@Override
	public void innerAnalyze(InputStream is) throws IOException {

	}

	@Override
	public void showInfo() {

	}

}
