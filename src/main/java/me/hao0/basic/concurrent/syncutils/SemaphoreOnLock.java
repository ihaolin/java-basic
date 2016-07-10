package me.hao0.basic.concurrent.syncutils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock来实现信号量
 * 不是java.util.concurrent.Semaphore的真实实现
 */
public class SemaphoreOnLock {
	private final Lock lock = new ReentrantLock();
	private final Condition permitsAvailable = lock.newCondition(); //等待可用条件
	private int permits; //允许请求的线程数
	
	public SemaphoreOnLock(int initialPermits) {
		lock.lock();
		try {
			permits = initialPermits;
		} finally{
			lock.unlock();
		}
	}
	
	public void acruire() throws InterruptedException{
		lock.lock();
		try {
			while (permits <= 0){
				permitsAvailable.await();
			}
			-- permits;
		} finally {
			lock.unlock();
		}
	}
	
	public void release(){
		lock.lock();
		try {
			++permits;
			permitsAvailable.signal(); //已可用信号通知
		} finally{
			lock.unlock();
		}
	}
}
