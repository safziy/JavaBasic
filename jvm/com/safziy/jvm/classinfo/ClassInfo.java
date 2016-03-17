package com.safziy.jvm.classinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.accessflag.ClassAccessFlags;
import com.safziy.jvm.classinfo.attribute.AttributeInfoFactory;
import com.safziy.jvm.classinfo.attribute.IAttributeInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class ClassInfo {
	private String sourceFile;
	// 魔数
	private String magicNumber;
	// 主次版本号
	private short minorVersion;
	private short majorVersion;
	// 常量池
	private ConstantPoolInfo constantPoolInfo;
	// class文件访问标志
	private short classAccessFlags;
	private short thisClass;
	private short superClass;
	// 接口
	private List<Short> interfaceList;
	// Fields
	private List<FieldInfo> fieldList;
	// Functions
	private List<FunctionInfo> functionList;
	// Attributes
	private List<IAttributeInfo> attributes;

	public ClassInfo(String file) {
		this.sourceFile = file;
	}

	public ClassInfo analyze() {
		InputStream is = null;
		try {
			File file = new File(sourceFile);
			is = new FileInputStream(file);
			// 4个字节的魔数
			magicNumber = ReadUtils.readHex(is, 4);
			// 2个字节的次版本号 和 主版本号
			minorVersion = ReadUtils.readShort(is, 2);
			majorVersion = ReadUtils.readShort(is, 2);
			// 常量池
			constantPoolInfo = new ConstantPoolInfo();
			constantPoolInfo.analyze(is);
			// class访问标志
			classAccessFlags = ReadUtils.readShort(is, 2);
			thisClass = ReadUtils.readShort(is, 2);
			superClass = ReadUtils.readShort(is, 2);
			int interfaceCount = ReadUtils.readInt(is, 2);
			interfaceList = new LinkedList<Short>();
			for (int i = 0; i < interfaceCount; i++) {
				interfaceList.add(ReadUtils.readShort(is, 2));
			}
			fieldList = new LinkedList<FieldInfo>();
			int filedCount = ReadUtils.readInt(is, 2);
			for (int i = 0; i < filedCount; i++) {
				FieldInfo field = new FieldInfo(constantPoolInfo);
				field.analyze(is);
				fieldList.add(field);
			}
			functionList = new LinkedList<FunctionInfo>();
			int functionCount = ReadUtils.readInt(is, 2);
			for (int i = 0; i < functionCount; i++) {
				FunctionInfo function = new FunctionInfo(constantPoolInfo);
				function.analyze(is);
				functionList.add(function);
			}
			attributes = new LinkedList<IAttributeInfo>();
			int attributeCount = ReadUtils.readInt(is, 2);
			for (int i = 0; i < attributeCount; i++) {
				IAttributeInfo attribute = AttributeInfoFactory.analyze(is, constantPoolInfo);
				attributes.add(attribute);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return this;
	}

	public void showInfo() {
		System.out.println("magic number:\t" + magicNumber);
		System.out.println("minor version:\t" + minorVersion);
		System.out.println("major version:\t" + majorVersion);
		constantPoolInfo.showInfo();
		System.out.print("ClassAccessFlag:\t");
		ClassAccessFlags.showInfo(classAccessFlags);
		System.out.println("this class:\t" + constantPoolInfo.getConstantValue(thisClass));
		System.out.println("super class:\t" + constantPoolInfo.getConstantValue(superClass));
		// show interfaces
		System.out.println("interfaces:");
		if (interfaceList.size() == 0) {
			System.out.println("\tnull");
		} else {
			for (Short interfaceIndex : interfaceList) {
				String indexStr = "#" + interfaceIndex;
				System.out.println("\t" + indexStr + "\t" + constantPoolInfo.getConstantValue(interfaceIndex));
			}
		}
		// show field
		System.out.println("Fields:");
		System.out.println("\t-------------------------------------------------------");
		for (FieldInfo fieldInfo : fieldList) {
			fieldInfo.showInfo();
			System.out.println("\t-------------------------------------------------------");
		}
		// show Function
		System.out.println("Functions:");
		System.out.println("\t-------------------------------------------------------");
		for (FunctionInfo functionInfo : functionList) {
			functionInfo.showInfo();
			System.out.println("\t-------------------------------------------------------");
		}
		// show Attributes
		System.out.println("Attributes:");
		System.out.println("\t-------------------------------------------------------");
		for (IAttributeInfo attributeInfo : attributes) {
			attributeInfo.showInfo();
			System.out.println("\t-------------------------------------------------------");
		}
	}

}
