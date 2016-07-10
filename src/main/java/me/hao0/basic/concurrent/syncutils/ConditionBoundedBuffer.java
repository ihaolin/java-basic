package me.hao0.basic.concurrent.syncutils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示条件变量的有界缓存
 */
public class ConditionBoundedBuffer<T> {
	private static final int BUFFER_SIZE = 10;
	
	protected final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	private final T[] items = (T[])new Object[BUFFER_SIZE];
	private int tail, head, count;
	
	public void put(T x) throws InterruptedException{
		lock.lock();
		try {
			while (count == items.length){
				notFull.await(); //队列已满等待。
			}
			items[tail] = x;
			if (++ tail == items.length){
				tail = 0;
			}
			++ count;
			notEmpty.signal(); //队列非空，唤醒其他等待条件
		} finally{
			lock.unlock();
		}
	}
	
	public T take() throws InterruptedException{
		lock.lock();
		try {
			while (count == 0){
				notEmpty.await();
			}
			T x = items[head];
			items[head] = null; // let gc collect
			--count;
			notFull.signal();
			return x;
		} finally{
			lock.unlock();
		}
	}
}	
