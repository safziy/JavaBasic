package com.safziy.jvm.classinfo.accessflag;

public enum FieldAccessFlag {
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
	 * 是否被声明为final,只有类可以设置
	 */
	ACC_FINAL((short) 0x0010),
	/**
	 * 是否是volalite
	 */
	ACC_VOLATILE((short) 0x0040),
	/**
	 * 是否是transient
	 */
	ACC_TRANSIENT((short) 0x0080),
	/**
	 * 标识这个类并非用户代码生成的
	 */
	ACC_SYNTHETIC((short) 0x1000),
	/**
	 * 标识这是一个枚举
	 */
	ACC_ENUM((short) 0x4000);

	short value;

	FieldAccessFlag(short value) {
		this.value = value;
	}
	
	public static void showInfo(short accessFlag){
		for (FieldAccessFlag flag : values()) {
			if((flag.value & accessFlag) != 0){
				System.out.print(" " + flag.name());
			}
		}
		System.out.println();
	}
}
