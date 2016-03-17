package com.safziy.concurrent;

public class BasicThread extends Thread {
	
	BasicThread(){
		System.out.println("BasicThread constructor!");
	}
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + " method run  " + i);
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName() + " method run  over");
	}

	public static void main(String[] args) {
		new BasicThread().start();
		new BasicThread().start();
		new BasicThread().start();
		new BasicThread().start();
		new BasicThread().start();
		new BasicThread().start();
		new BasicThread().start();
		new BasicThread().start();
	}
}
