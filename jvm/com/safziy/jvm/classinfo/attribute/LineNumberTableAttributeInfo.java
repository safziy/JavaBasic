package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class LineNumberTableAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short lineNunberTableLen;
	List<LineNumberTable> lineNumberTableList;

	public LineNumberTableAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		lineNunberTableLen = ReadUtils.readShort(is, 2);
		lineNumberTableList = new LinkedList<LineNumberTable>();
		for (int i = 0; i < lineNunberTableLen; i++) {
			LineNumberTable lineNumberTable = new LineNumberTable();
			lineNumberTable.analyze(is);
			lineNumberTableList.add(lineNumberTable);
		}
	}

	public void showInfo() {
		if (lineNumberTableList.size() > 0) {
			System.out.println("\tLineNumberTable:");
			System.out.println("\t\tstart\tline");
			for (LineNumberTable lineNumberTable : lineNumberTableList) {
				lineNumberTable.showInfo();
			}
		}
		System.out.println();
	}

	class LineNumberTable {
		short startPC;
		short lineNum;

		public void analyze(InputStream is) throws IOException {
			startPC = ReadUtils.readShort(is, 2);
			lineNum = ReadUtils.readShort(is, 2);
		}

		public void showInfo() {
			System.out.println("\t\t" + startPC + "\t" + lineNum);
		}
	}

}
