package com.safziy.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OneWriteMultiRead {
	public static void main(String[] args) {
		IntObject iden = new IntObject();
		
		new WriteThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
	}
	
	static class IntObject {
		public int value;
		public ReadWriteLock lock = new ReentrantReadWriteLock();
	}

	static class ReadThread extends Thread {

		IntObject iden;

		public ReadThread(IntObject iden) {
			this.iden = iden;
		}

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				iden.lock.readLock().lock();
				try {
					sleep(1000);
					System.out.println(getName() + " value is " + iden.value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				iden.lock.readLock().unlock();
			}
		}
	}

	static class WriteThread extends Thread {

		IntObject iden;

		public WriteThread(IntObject iden) {
			this.iden = iden;
		}

		@Override
		public void run() {
			try {
				sleep(3000);
				iden.lock.writeLock().lock();
				System.out.println("befor write");
				sleep(3000);
				iden.value = 10;
				sleep(3000);
				System.out.println("after write");
				iden.lock.writeLock().unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
