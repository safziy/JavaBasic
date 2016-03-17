package com.safziy.jvm.classinfo.test;

public class TestClass {
	// private int m = 1;
	// public final float x = 0.9f;
	// public long l = 1L;
	// public List<String> list;
	//
	// @Deprecated
	// public int inc() {
	// return m + 1;
	// }
	//
	// public int ca() throws IOException {
	// int a = 1;
	// int b;
	// try {
	// a++;
	// } catch (Exception e) {
	// throw new IOException("aaa");
	// } finally {
	// b = a;
	// }
	// return b;
	// }
	//
	// class TestInnerClass {
	// int a;
	//
	// public void dis() {
	// System.out.println("TestInnerClass!");
	// }
	// }

	private static boolean stop = false;

	public static void main(String[] args) throws InterruptedException {
		Thread workThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
				}
			}
		});
		workThread.start();
		Thread.sleep(3000);
		stop = true;
	}

}
