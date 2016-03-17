package com.safziy.jvm.classinfo.accessflag;

public enum FunctionAccessFlags {
	/**
	 * 是否是public类型
	 */
	ACC_PUBLIC((short) 0x0001),
	/**
	 * 是否是private类型
	 */
	ACC_PRIVATE((short) 0x0002),
	/**
	 * 是否是protected类型
	 */
	ACC_PROTECTED((short) 0x0004),
	/**
	 * 是否是static类型
	 */
	ACC_STATIC((short) 0x0008),
	/**
	 * 是否被声明为final
	 */
	ACC_FINAL((short) 0x0010),
	/**
	 * 是否是synchronized
	 */
	ACC_SYNCHRONIZED((short) 0x0020),
	/**
	 * bridge,是否由编译器生成的桥接方法
	 */
	ACC_BRIDGE((short) 0x0040),
	/**
	 * 是否接受不定参数
	 */
	ACC_VARARGS((short) 0x0080),
	/**
	 * 是否为native
	 */
	ACC_NATIVE((short) 0x0100),
	/**
	 * 是否为abstract
	 */
	ACC_ABSTRACT((short) 0x0400),
	/**
	 * 是否为strictfp
	 */
	ACC_STRICTFP((short) 0x0800),
	/**
	 * 是否由编译器自动生成的
	 */
	ACC_SYNTHETIC((short) 0x1000);

	short value;

	FunctionAccessFlags(short value) {
		this.value = value;
	}
	
	public static void showInfo(short accessFlag){
		for (FunctionAccessFlags flag : values()) {
			if((flag.value & accessFlag) != 0){
				System.out.print(" " + flag.name());
			}
		}
		System.out.println();
	}
}
