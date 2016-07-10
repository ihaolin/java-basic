package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * 携带结果的闭锁
 */
public class ValueLatch<T> {
	private T value = null;
	private final CountDownLatch done = new CountDownLatch(1);
	
	public T getValue() throws InterruptedException {
		done.await(); //阻塞直到设置了值
		synchronized (this) {
			return value;
		}
	}

	public boolean isSet() {
		return done.getCount() == 0;
	}

	public synchronized void setValue(T newValue) {
		if (!isSet()){
			value = newValue;
			done.countDown();
		}
	}
}
