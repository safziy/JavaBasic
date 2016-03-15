package com.safziy.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClass {
	public static void main(String[] args) {
		AtomicInteger aint = new AtomicInteger();
		
		aint.getAndAdd(1);
	}
}
