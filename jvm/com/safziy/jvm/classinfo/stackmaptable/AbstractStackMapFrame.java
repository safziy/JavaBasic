package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;

public abstract class AbstractStackMapFrame implements IStackMapFrame {
	short frame_type;
	ConstantPoolInfo constantPoolInfo;

	public AbstractStackMapFrame(ConstantPoolInfo constantPoolInfo, short frame_type) {
		this.constantPoolInfo = constantPoolInfo;
		this.frame_type = frame_type;
	}

	public final void analyze(InputStream is) throws IOException {
		innerAnalyze(is);
	}

	public abstract void innerAnalyze(InputStream is) throws IOException;

}
