package me.hao0.basic.concurrent.tests;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TestingThreadFactory implements ThreadFactory {
	public final AtomicInteger numCreated = 
			new AtomicInteger(); //记录创建的线程数
	private final ThreadFactory factory = 
			Executors.defaultThreadFactory();
	
	@Override
	public Thread newThread(Runnable r) {
		numCreated.incrementAndGet();
		return factory.newThread(r);
	}
}
