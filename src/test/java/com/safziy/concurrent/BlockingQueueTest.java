package com.safziy.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

public class BlockingQueueTest {
	
	@Test
	public void testLinkedBlockingQueue() throws InterruptedException{
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(1000005);
		for (int i = 0; i < 1000000; i++) {
			queue.put(i);
		}
	}
	
	@Test
	public void testArrayBlockingQueue() throws InterruptedException{
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1000005);
		for (int i = 0; i < 1000000; i++) {
			queue.put(i);
		}
	}
}
