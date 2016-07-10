package me.hao0.basic.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁的标准用法
 */
public class LockUsing {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		lock.lock();
		try{
			// to do sth.
		} finally{
			lock.unlock();
		}
	}
}
