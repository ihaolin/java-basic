package me.hao0.basic.concurrent.objectshare;


import me.hao0.basic.concurrent.threadsafe.NotThreadSafe;

/**
 * get操作可能与最近set值不一致，产生数据失效
 */
@NotThreadSafe
public class SynchronizedInteger {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}