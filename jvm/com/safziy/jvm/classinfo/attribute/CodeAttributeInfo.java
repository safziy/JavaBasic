package com.safziy.jvm.classinfo.attribute;

import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_BOOLEAN;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_BYTE;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_CHAR;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_CLASS;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_DOUBLE;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_FLOAT;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_INT;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_LONG;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.T_SHORT;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_aload;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_anewarray;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_astore;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_bipush;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_checkcast;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_dload;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_dstore;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_fload;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_fstore;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_getfield;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_getstatic;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_goto;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_goto_w;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_acmpeq;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_acmpne;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_icmpeq;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_icmpge;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_icmpgt;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_icmple;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_icmplt;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_if_icmpne;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifeq;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifge;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifgt;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifle;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_iflt;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifne;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifnonnull;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ifnull;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_iinc;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_iload;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_instanceof;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_invokeinterface;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_invokespecial;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_invokestatic;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_invokevirtual;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_istore;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_jsr;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_jsr_w;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ldc;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ldc2_w;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ldc_w;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_lload;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_lookupswitch;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_lstore;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_multianewarray;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_new;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_newarray;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_nonpriv;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_priv;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_putfield;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_putstatic;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_ret;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_sipush;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_tableswitch;
import static com.safziy.jvm.classinfo.bytecode.RuntimeConstants.opc_wide;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.bytecode.ByteCode;
import com.safziy.jvm.classinfo.bytecode.Tables;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class CodeAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	boolean isDescFlag = true;
	
	short maxStack;
	short maxLocals;
	int codeLen;
	byte[] codes;
	short exceptionTableLen;
	List<ExceptionTable> exceptionTableList;
	short attributesLen;
	List<IAttributeInfo> attributesList;

	String lP = "";

	public CodeAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		maxStack = ReadUtils.readShort(is, 2);
		maxLocals = ReadUtils.readShort(is, 2);
		codeLen = ReadUtils.readInt(is, 4);
		codes = ReadUtils.readBytes(is, codeLen);
		exceptionTableList = new LinkedList<ExceptionTable>();
		exceptionTableLen = ReadUtils.readShort(is, 2);
		for (int i = 0; i < exceptionTableLen; i++) {
			ExceptionTable exceptionTable = new ExceptionTable();
			exceptionTable.analyze(is);
			exceptionTableList.add(exceptionTable);
		}
		attributesList = new LinkedList<IAttributeInfo>();
		attributesLen = ReadUtils.readShort(is, 2);
		for (int i = 0; i < attributesLen; i++) {
			IAttributeInfo attribute = AttributeInfoFactory.analyze(is, constantPoolInfo);
			attributesList.add(attribute);
		}
	}

	public void showInfo() {
		System.out.println("\tCode:\tmaxStack:" + maxStack + "\tmaxLocals:" + maxLocals);
		for (int pc = 0; pc < codes.length;) {
			System.out.print("\t\t" + pc + ":\t");
			pc = pc + printCode(pc);
			System.out.println();
		}
		System.out.println();
		if (exceptionTableList.size() > 0) {
			System.out.println("\tException Table:");
			System.out.println("\t\tform\tto\ttarget\ttype");
			for (ExceptionTable exceptionTable : exceptionTableList) {
				exceptionTable.showInfo();
			}
		}
		for (IAttributeInfo attributeInfo : attributesList) {
			attributeInfo.showInfo();
		}
	}

	class ExceptionTable {
		short startPC;
		short endPC;
		short handlerPC;
		short catchType;

		public void analyze(InputStream is) throws IOException {
			startPC = ReadUtils.readShort(is, 2);
			endPC = ReadUtils.readShort(is, 2);
			handlerPC = ReadUtils.readShort(is, 2);
			catchType = ReadUtils.readShort(is, 2);
		}

		public void showInfo() {
			String catchTypeValue;
			if (catchType == 0) {
				catchTypeValue = "any";
			} else {
				catchTypeValue = constantPoolInfo.getConstantValue(catchType);
			}
			System.out.println("\t\t" + startPC + "\t" + endPC + "\t" + handlerPC + "\t" + catchTypeValue);
		}
	}

	private int printCode(int pc) {
		int opcode = getUbyte(pc);
		int opcode2;
		String mnem;
		String desc;
		switch (opcode) {
		case opc_nonpriv:
		case opc_priv:
			opcode2 = getUbyte(pc + 1);
			mnem = Tables.opcName((opcode << 8) + opcode2);
			desc = ByteCode.getDesc(mnem);
			if (mnem == null){
				// assume all (even nonexistent) priv and nonpriv instructions
				// are 2 bytes long
				mnem = Tables.opcName(opcode);
				desc = ByteCode.getDesc(mnem);
				mnem = mnem + " " + opcode2;
			}
			System.out.print(mnem);
			printDesc(desc);
			return 2;
		case opc_wide: {
			opcode2 = getUbyte(pc + 1);
			mnem = Tables.opcName((opcode << 8) + opcode2);
			desc = ByteCode.getDesc(mnem);
			if (mnem == null) {
				// nonexistent opcode - but we have to print something
				System.out.print("bytecode " + opcode);
				printDesc(desc);
				return 1;
			}
			System.out.print(mnem + " " + getUShort(pc + 2));
			if (opcode2 == opc_iinc) {
				System.out.print(", " + getShort(pc + 4));
				printDesc(desc);
				return 6;
			}
			printDesc(desc);
			return 4;
		}
		}
		mnem = Tables.opcName(opcode);
		desc = ByteCode.getDesc(mnem);
		if (mnem == null) {
			// nonexistent opcode - but we have to print something
			System.out.print("bytecode " + opcode);
			printDesc(desc);
			return 1;
		}
		if (opcode > opc_jsr_w) {
			// pseudo opcodes should be printed as bytecodes
			System.out.print("bytecode " + opcode);
			printDesc(desc);
			return 1;
		}
		mnem = Tables.opcName(opcode);
		desc = ByteCode.getDesc(mnem);
		System.out.print(mnem);
		switch (opcode) {
		case opc_aload:
		case opc_astore:
		case opc_fload:
		case opc_fstore:
		case opc_iload:
		case opc_istore:
		case opc_lload:
		case opc_lstore:
		case opc_dload:
		case opc_dstore:
		case opc_ret:
			System.out.print("\t" + getUbyte(pc + 1));
			printDesc(desc);
			return 2;
		case opc_iinc:
			System.out.print("\t" + getUbyte(pc + 1) + ", " + getbyte(pc + 2));
			printDesc(desc);
			return 3;
		case opc_tableswitch: {
			int tb = align(pc + 1);
			int default_skip = getInt(tb); /* default skip pamount */
			int low = getInt(tb + 4);
			int high = getInt(tb + 8);
			int count = high - low;
			System.out.print("{ //" + low + " to " + high);
			for (int i = 0; i <= count; i++)
				System.out.print("\n\t\t" + (i + low) + ": " + lP + (pc + getInt(tb + 12 + 4 * i)) + ";");
			System.out.print("\n\t\tdefault: " + lP + (default_skip + pc) + " }");
			printDesc(desc);
			return tb - pc + 16 + count * 4;
		}

		case opc_lookupswitch: {
			int tb = align(pc + 1);
			int default_skip = getInt(tb);
			int npairs = getInt(tb + 4);
			System.out.print("{ //" + npairs);
			for (int i = 1; i <= npairs; i++)
				System.out.print("\n\t\t" + getInt(tb + i * 8) + ": " + lP + (pc + getInt(tb + 4 + i * 8)) + ";");
			System.out.print("\n\t\tdefault: " + lP + (default_skip + pc) + " }");
			printDesc(desc);
			return tb - pc + (npairs + 1) * 8;
		}
		case opc_newarray:
			int type = getUbyte(pc + 1);
			switch (type) {
			case T_BOOLEAN:
				System.out.print(" boolean");
				break;
			case T_BYTE:
				System.out.print(" byte");
				break;
			case T_CHAR:
				System.out.print(" char");
				break;
			case T_SHORT:
				System.out.print(" short");
				break;
			case T_INT:
				System.out.print(" int");
				break;
			case T_LONG:
				System.out.print(" long");
				break;
			case T_FLOAT:
				System.out.print(" float");
				break;
			case T_DOUBLE:
				System.out.print(" double");
				break;
			case T_CLASS:
				System.out.print(" class");
				break;
			default:
				System.out.print(" BOGUS TYPE:" + type);
			}
			printDesc(desc);
			return 2;

		case opc_anewarray: {
			int index = getUShort(pc + 1);
			System.out.print("\t#" + index + "; //");
			System.out.print(constantPoolInfo.getConstantValue(index));
			printDesc(desc);
			return 3;
		}
		case opc_sipush:
			System.out.print("\t" + getShort(pc + 1));
			printDesc(desc);
			return 3;
		case opc_bipush:
			System.out.print("\t" + getbyte(pc + 1));
			printDesc(desc);
			return 2;
		case opc_ldc: {
			int index = getUbyte(pc + 1);
			System.out.print("\t#" + index + "; //");
			System.out.print(constantPoolInfo.getConstantValue(index));
			printDesc(desc);
			return 2;
		}
		case opc_ldc_w:
		case opc_ldc2_w:
		case opc_instanceof:
		case opc_checkcast:
		case opc_new:
		case opc_putstatic:
		case opc_getstatic:
		case opc_putfield:
		case opc_getfield:
		case opc_invokevirtual:
		case opc_invokespecial:
		case opc_invokestatic: {
			int index = getUShort(pc + 1);
			System.out.print("\t#" + index + "; //");
			System.out.print(constantPoolInfo.getConstantValue(index));
			printDesc(desc);
			return 3;
		}
		case opc_invokeinterface: {
			int index = getUShort(pc + 1), nargs = getUbyte(pc + 3);
			System.out.print("\t#" + index + ",  " + nargs + "; //");
			System.out.print(constantPoolInfo.getConstantValue(index));
			printDesc(desc);
			return 5;
		}
		case opc_multianewarray: {
			int index = getUShort(pc + 1), dimensions = getUbyte(pc + 3);
			System.out.print("\t#" + index + ",  " + dimensions + "; //");
			System.out.print(constantPoolInfo.getConstantValue(index));
			printDesc(desc);
			return 4;
		}
		case opc_jsr:
		case opc_goto:
		case opc_ifeq:
		case opc_ifge:
		case opc_ifgt:
		case opc_ifle:
		case opc_iflt:
		case opc_ifne:
		case opc_if_icmpeq:
		case opc_if_icmpne:
		case opc_if_icmpge:
		case opc_if_icmpgt:
		case opc_if_icmple:
		case opc_if_icmplt:
		case opc_if_acmpeq:
		case opc_if_acmpne:
		case opc_ifnull:
		case opc_ifnonnull:
			System.out.print("\t" + lP + (pc + getShort(pc + 1)));
			printDesc(desc);
			return 3;
		case opc_jsr_w:
		case opc_goto_w:
			System.out.print("\t" + lP + (pc + getInt(pc + 1)));
			printDesc(desc);
			return 5;

		default:
			printDesc(desc);
			return 1;
		}

	}

	protected int getbyte(int pc) {
		return codes[pc];
	}

	protected int getUbyte(int pc) {
		return codes[pc] & 0xFF;
	}

	int getShort(int pc) {
		return (codes[pc] << 8) | (codes[pc + 1] & 0xFF);
	}

	int getUShort(int pc) {
		return ((codes[pc] << 8) | (codes[pc + 1] & 0xFF)) & 0xFFFF;
	}

	protected int getInt(int pc) {
		return (getShort(pc) << 16) | (getShort(pc + 2) & 0xFFFF);
	}

	protected static int align(int n) {
		return (n + 3) & ~3;
	}
	
	public void printDesc(String desc){
		if(isDescFlag && desc != null){
			System.out.print("\t\t" + desc);
		}
	}

}
