package com.safziy.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 利用阻塞队列来实现生产者和消费者模型 
 * 
 * @see BlockingQueueTest
 * 
 * 分析测试ArrayBlockingQueue和LinkedBlockingQueue的区别，得出结论如下：
 * 
 * 1. 队列中锁的实现不同 ArrayBlockingQueue实现的队列中的锁是没有分离的，即生产和消费用的是同一个锁；
 * LinkedBlockingQueue实现的队列中的锁是分离的，即生产用的是putLock，消费是takeLock
 * 
 * 2. 在生产或消费时操作不同 ArrayBlockingQueue实现的队列中在生产和消费的时候，是直接将枚举对象插入或移除的；
 * LinkedBlockingQueue实现的队列中在生产和消费的时候，需要把枚举对象转换为Node<E>进行插入或移除，会影响性能
 * 
 * 3. 队列大小初始化方式不同 ArrayBlockingQueue实现的队列中必须指定队列的大小；
 * LinkedBlockingQueue实现的队列中可以不指定队列的大小，但是默认是Integer.MAX_VALUE
 * 
 * 注意: 1. 在使用LinkedBlockingQueue时，若用默认大小且当生产速度大于消费速度时候，有可能会内存溢出 2.
 * 在使用ArrayBlockingQueue和LinkedBlockingQueue分别对1000000个简单字符做入队操作时，
 * LinkedBlockingQueue的消耗是ArrayBlockingQueue消耗的10倍左右，
 * 即LinkedBlockingQueue消耗在1500毫秒左右，而ArrayBlockingQueue只需150毫秒左右。
 * 
 * @author safziy
 */
public class BlockingQueueClass {
	public static void main(String[] args) throws InterruptedException {
		// BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		BlockingQueue<Integer> queue = new LinkedBlockingDeque<Integer>(10);

		new Consumer(queue, "Consumer-1").start();
		new Producer(queue, "Producer-1").start();

		TimeUnit.SECONDS.sleep(5);

		new Producer(queue, "Producer-2").start();

		TimeUnit.SECONDS.sleep(5);

		new Producer(queue, "Producer-3").start();
	}

	/**
	 * 消费者
	 */
	static class Consumer extends Thread {
		BlockingQueue<Integer> queue;

		public Consumer(BlockingQueue<Integer> queue, String name) {
			super(name);
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Integer value = queue.take();
					System.out.println(getName() + " take value=" + value);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 生产者
	 */
	static class Producer extends Thread {
		BlockingQueue<Integer> queue;

		public Producer(BlockingQueue<Integer> queue, String name) {
			super(name);
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					int value = (int) Math.ceil(Math.random() * 100);
					queue.put(value);
					System.out.println(getName() + " put value=" + value);
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
