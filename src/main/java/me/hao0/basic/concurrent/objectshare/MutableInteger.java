package me.hao0.basic.concurrent.objectshare;


import me.hao0.concurrency.practice.threadsafe.ThreadSafe;

/**
 * 将get, set同步化，可防止数据失效
 */
@ThreadSafe
public class MutableInteger {
	private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}
}