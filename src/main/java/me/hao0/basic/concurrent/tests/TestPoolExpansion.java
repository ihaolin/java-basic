package me.hao0.basic.concurrent.tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestPoolExpansion {
	/**
	 * 验证线程扩展能力
	 * @throws InterruptedException
	 */
	public void testPoolExpansion() throws InterruptedException{
		int MAX_SIZE = 10;
		TestingThreadFactory threadFactory = new TestingThreadFactory();
		ExecutorService exec = Executors.newFixedThreadPool(MAX_SIZE, threadFactory);
		
		for (int i=0; i<10*MAX_SIZE; i++){
			exec.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(Long.MAX_VALUE);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			});
		}
		
		for (int i=0; i<20 && 
				threadFactory.numCreated.get() < MAX_SIZE; i++){
			Thread.sleep(100);
		}
		assertEquals(threadFactory.numCreated.get(), MAX_SIZE);
		exec.shutdownNow();
	}
}
