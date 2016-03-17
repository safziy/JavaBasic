package com.safziy.jvm.classinfo.accessflag;

public enum ClassAccessFlags {

	/**
	 * 是否是public类型
	 */
	ACC_PUBLIC((short) 0x0001),
	/**
	 * 是否被声明为final,只有类可以设置
	 */
	ACC_FINAL((short) 0x0010),
	/**
	 * 是否允许使用invokespecial字节码指令,JDK 1.2之后编译出来的这个标识为真
	 */
	ACC_SUPER((short) 0x0020),
	/**
	 * 标识这是一个接口
	 */
	ACC_INTERFACE((short) 0x0200),
	/**
	 * 是否为abstract类型,对于接口和抽象类来说,此标识为真
	 */
	ACC_ABSTRACT((short) 0x0400),
	/**
	 * 标识这个类并非用户代码生成的
	 */
	ACC_SYNTHETIC((short) 0x1000),
	/**
	 * 标识这是一个注解
	 */
	ACC_ANNOTATION((short) 0x2000),
	/**
	 * 标识这是一个枚举
	 */
	ACC_ENUM((short) 0x4000);

	short value;

	ClassAccessFlags(short value) {
		this.value = value;
	}
	
	public static void showInfo(short accessFlag){
		for (ClassAccessFlags flag : values()) {
			if((flag.value & accessFlag) != 0){
				System.out.print(" " + flag.name());
			}
		}
		System.out.println();
	}

}
