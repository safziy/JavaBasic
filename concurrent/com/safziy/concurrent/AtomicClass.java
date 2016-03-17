package com.safziy.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicClass {
	public static void main(String[] args) {
		AtomicInteger aint = new AtomicInteger();
		
		aint.getAndAdd(1);
		
		AtomicLong along = new AtomicLong();
		along.getAndAdd(1);
	}
}
