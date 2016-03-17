package com.safziy.jvm.classinfo;

import java.io.IOException;
import java.io.InputStream;

import com.safziy.jvm.classinfo.utils.ReadUtils;

public class ConstantPoolInfo {
	// private List<Constant> constantPool;
	Constant[] constantPool;

	public void analyze(InputStream is) throws IOException {
		// 2个字节的常量池容量
		int constantCount = ReadUtils.readInt(is, 2);
		constantPool = new Constant[constantCount];
		// double和long类型的数据 占两个常量池的未知
		boolean doubleflag;
		for (int i = 1; i < constantCount; i++) {
			doubleflag = false;
			Constant constant = new Constant();
			// 每个常量第一个字节为tag
			constant.tag = ReadUtils.readShort(is, 1);
			switch (constant.tag) {
			case Constant.CONSTANT_Utf8_info:
				constant.type = "Asciz";
				int len = ReadUtils.readInt(is, 2);
				constant.value = ReadUtils.readString(is, len);
				break;
			case Constant.CONSTANT_Integer_info:
				constant.type = "Integer";
				constant.value = ReadUtils.readInt(is, 4);
				break;
			case Constant.CONSTANT_Float_info:
				constant.type = "Float";
				constant.value = ReadUtils.readFloat(is, 4);
				break;
			case Constant.CONSTANT_Long_info:
				constant.type = "Long";
				constant.value = ReadUtils.readLong(is, 8);
				doubleflag = true;
				break;
			case Constant.CONSTANT_Double_info:
				constant.type = "Double";
				constant.value = ReadUtils.readDouble(is, 8);
				doubleflag = true;
				break;
			case Constant.CONSTANT_Class_info:
				constant.type = "class";
				constant.index1 = ReadUtils.readShort(is, 2);
				break;
			case Constant.CONSTANT_String_info:
				constant.type = "String";
				constant.index1 = ReadUtils.readShort(is, 2);
				break;
			case Constant.CONSTANT_Fieldref_info:
				constant.type = "Field";
				constant.index1 = ReadUtils.readShort(is, 2);
				constant.index2 = ReadUtils.readShort(is, 2);
				break;
			case Constant.CONSTANT_Methodref_info:
				constant.type = "Method";
				constant.index1 = ReadUtils.readShort(is, 2);
				constant.index2 = ReadUtils.readShort(is, 2);
				break;
			case Constant.CONSTANT_InterfaceMethodref_info:
				constant.type = "InterfaceMethod";
				constant.index1 = ReadUtils.readShort(is, 2);
				constant.index2 = ReadUtils.readShort(is, 2);
				break;
			case Constant.CONSTANT_NameAndType_info:
				constant.type = "NameAndType";
				constant.index1 = ReadUtils.readShort(is, 2);
				constant.index2 = ReadUtils.readShort(is, 2);
				break;
			case Constant.CONSTANT_MethodHandle_info:
				break;
			case Constant.CONSTANT_MethodType_info:
				break;
			case Constant.CONSTANT_InvokeDynamic_info:
				break;
			default:
				break;
			}
			constantPool[i] = constant;
			if (doubleflag) {
				i++;
			}
		}
	}

	class Constant {
		static final short CONSTANT_Utf8_info = 1;
		static final short CONSTANT_Integer_info = 3;
		static final short CONSTANT_Float_info = 4;
		static final short CONSTANT_Long_info = 5;
		static final short CONSTANT_Double_info = 6;
		static final short CONSTANT_Class_info = 7;
		static final short CONSTANT_String_info = 8;
		static final short CONSTANT_Fieldref_info = 9;
		static final short CONSTANT_Methodref_info = 10;
		static final short CONSTANT_InterfaceMethodref_info = 11;
		static final short CONSTANT_NameAndType_info = 12;
		static final short CONSTANT_MethodHandle_info = 15;
		static final short CONSTANT_MethodType_info = 16;
		static final short CONSTANT_InvokeDynamic_info = 18;

		short tag;
		short index1;
		short index2;
		String type;
		Object value;
	}

	public void showInfo() {
		System.out.println("Constant pool:");
		int i = 0;
		for (Constant constant : constantPool) {
			if (constant == null) {
				i++;
				continue;
			}
			System.out.print("\tconst #" + i + " = " + constant.type + "\t");
			switch (constant.tag) {
			case Constant.CONSTANT_Class_info:
			case Constant.CONSTANT_String_info:
				System.out.print("#" + constant.index1 + ";\t");
				break;
			case Constant.CONSTANT_Fieldref_info:
			case Constant.CONSTANT_Methodref_info:
			case Constant.CONSTANT_InterfaceMethodref_info:
				System.out.print("#" + constant.index1 + "." + "#" + constant.index2 + ";\t");
				break;
			case Constant.CONSTANT_NameAndType_info:
				System.out.print("#" + constant.index1 + ":" + "#" + constant.index2 + ";\t");
				break;
			case Constant.CONSTANT_MethodHandle_info:
				break;
			case Constant.CONSTANT_MethodType_info:
				break;
			case Constant.CONSTANT_InvokeDynamic_info:
				break;
			default:
				break;
			}
			System.out.print(getConstantValue((short) i));
			System.out.println();
			i++;
		}
	}

	public String getConstantValue(int index) {
		Constant constant = constantPool[index];
		if(constant == null){
			return "null";
		}
		switch (constant.tag) {
		case Constant.CONSTANT_Utf8_info:
		case Constant.CONSTANT_Integer_info:
		case Constant.CONSTANT_Float_info:
		case Constant.CONSTANT_Long_info:
		case Constant.CONSTANT_Double_info:
			return String.valueOf(constant.value);
		case Constant.CONSTANT_Class_info:
		case Constant.CONSTANT_String_info:
			return getConstantValue(constant.index1);
		case Constant.CONSTANT_Fieldref_info:
		case Constant.CONSTANT_Methodref_info:
		case Constant.CONSTANT_InterfaceMethodref_info:
			return getConstantValue(constant.index1) + "." + getConstantValue(constant.index2);
		case Constant.CONSTANT_NameAndType_info:
			return getConstantValue(constant.index1) + ":" + getConstantValue(constant.index2);
		case Constant.CONSTANT_MethodHandle_info:
			break;
		case Constant.CONSTANT_MethodType_info:
			break;
		case Constant.CONSTANT_InvokeDynamic_info:
			break;
		default:
			break;
		}
		return null;
	}

	public String getConstantValue(short index) {
		return getConstantValue((int) index);
	}

	public static void main(String[] args) {
		String a = "aaa";
		a.replaceFirst("aaa", "bbbbbb");
	}

}
