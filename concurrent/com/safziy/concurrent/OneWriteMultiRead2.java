package com.safziy.concurrent;


public class OneWriteMultiRead2 {
	public static void main(String[] args) {
		IdenObject iden = new IdenObject();
		
		new WriteThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
		new ReadThread(iden).start();
	}
	
	static class IdenObject {
		public ValueObject value;
		
		public IdenObject() {
			value = new ValueObject();
		}
	}
	
	static class ValueObject{
		public int x;
		public int y;
	}

	static class ReadThread extends Thread {

		IdenObject iden;

		public ReadThread(IdenObject iden) {
			this.iden = iden;
		}

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				try {
					sleep(1000);
					System.out.println(getName() + " x is " + iden.value.x + "  y is " + iden.value.y);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class WriteThread extends Thread {

		IdenObject iden;

		public WriteThread(IdenObject iden) {
			this.iden = iden;
		}

		@Override
		public void run() {
			try {
				sleep(3000);
				System.out.println("befor write");
				sleep(3000);
				ValueObject newValue = new ValueObject();
				newValue.x = 10;
				newValue.y = 10;
				iden.value = newValue;
				sleep(3000);
				System.out.println("after write");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
