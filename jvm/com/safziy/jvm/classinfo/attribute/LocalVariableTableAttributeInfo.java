package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class LocalVariableTableAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short localVariableTableLen;
	List<LocalVariableTable> localVariableTableList;

	public LocalVariableTableAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		localVariableTableLen = ReadUtils.readShort(is, 2);
		localVariableTableList = new LinkedList<LocalVariableTable>();
		for (int i = 0; i < localVariableTableLen; i++) {
			LocalVariableTable localVariableTable = new LocalVariableTable();
			localVariableTable.analyze(is);
			localVariableTableList.add(localVariableTable);
		}
	}

	public void showInfo() {
		if (localVariableTableList.size() > 0) {
			System.out.println("\tLocalVariableTable:");
			System.out.println("\t\tstart\tlen\tslot\tname\tsignature");
			for (LocalVariableTable localVariableTable : localVariableTableList) {
				localVariableTable.showInfo();
			}
		}
		System.out.println();
	}

	class LocalVariableTable {
		short startPC;
		short len;
		short nameIndex;
		short descriptorIndex;
		short index;

		public void analyze(InputStream is) throws IOException {
			startPC = ReadUtils.readShort(is, 2);
			len = ReadUtils.readShort(is, 2);
			nameIndex = ReadUtils.readShort(is, 2);
			descriptorIndex = ReadUtils.readShort(is, 2);
			index = ReadUtils.readShort(is, 2);
		}

		public void showInfo() {
			System.out.println("\t\t" + startPC + "\t" + len + "\t" + index + "\t"
					+ constantPoolInfo.getConstantValue(nameIndex) + "\t"
					+ constantPoolInfo.getConstantValue(descriptorIndex));
		}
	}

}
