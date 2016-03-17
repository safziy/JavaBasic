package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.stackmaptable.IStackMapFrame;
import com.safziy.jvm.classinfo.stackmaptable.StackMapFrameFactory;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class StackMapTableAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short number_of_entries;
	List<IStackMapFrame> stack_map_frame_entries;

	public StackMapTableAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		number_of_entries = ReadUtils.readShort(is, 2);
		stack_map_frame_entries = new LinkedList<IStackMapFrame>();
		// System.out.println(ReadUtils.readHex(is, len - 2));
		for (int i = 0; i < number_of_entries; i++) {
			stack_map_frame_entries.add(StackMapFrameFactory.analyze(is, constantPoolInfo));
		}
	}

	public void showInfo() {
		System.out.println("\tStackMapTable:");
		System.out.println("\t\tnumber_of_entries:\t" + number_of_entries);
		System.out.println();
	}

}
