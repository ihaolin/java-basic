package me.hao0.basic.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptableLock {
	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		
		lock.lockInterruptibly();
		try{
			// maybe throws InterruptedException
			doSomething();
		} finally{
			lock.unlock();
		}
	}

	private static void doSomething() throws InterruptedException{
		
	}
}
