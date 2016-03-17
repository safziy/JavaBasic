package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class StackMapFrameFactory {
	public static IStackMapFrame analyze(InputStream is, ConstantPoolInfo constantPoolInfo) throws IOException {
		IStackMapFrame stackMapFrame;
		short frame_type = ReadUtils.readShort(is, 1);
		if (frame_type >= 0 && frame_type <= 63) {
			stackMapFrame = new SameFrame(constantPoolInfo, frame_type);
		} else if (frame_type >= 64 && frame_type <= 127) {
			stackMapFrame = new SameLocals1StackItemFrame(constantPoolInfo, frame_type);
		} else if (frame_type == 247) {
			stackMapFrame = new SameLocals1StackItemFrameExtended(constantPoolInfo, frame_type);
		} else if (frame_type >= 248 && frame_type <= 250) {
			stackMapFrame = new ChopFrame(constantPoolInfo, frame_type);
		} else if (frame_type == 251) {
			stackMapFrame = new SameFrameExtended(constantPoolInfo, frame_type);
		} else if (frame_type >= 252 && frame_type <= 254) {
			stackMapFrame = new AppendFrame(constantPoolInfo, frame_type);
		} else if (frame_type == 255) {
			stackMapFrame = new FullFrame(constantPoolInfo, frame_type);
		} else {
			stackMapFrame = null;
		}
		stackMapFrame.analyze(is);
		return stackMapFrame;
	}
}
