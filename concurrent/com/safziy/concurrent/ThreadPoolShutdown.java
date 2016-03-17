package com.safziy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPoolShutdown {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		
		Future<?> f = exec.submit(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					System.out.println("I am run ...");
					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		exec.shutdown();
//		exec.shutdownNow();
		f.cancel(true);
	}
}
