package com.safziy.jvm.hotswap;

/**
 * for Windows:
 * -javaagent:C:\Users\Administrator\.m2\repositoryLocal\com\safziy\
 * safziy-agent\0.0.1-SNAPSHOT\safziy-agent-0.0.1-SNAPSHOT.jar
 * 
 * @author Administrator
 *
 */
public class HotSwap {
	public static void main(String[] args) {
		sayHello();
		sayHello2();
	}

	public static void sayHello() {
		System.out.println("Hello World!!!");
	}

	public static void sayHello2() {
		System.out.println("Hello World too!!!");
	}
}
