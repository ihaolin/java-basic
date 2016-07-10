package me.hao0.basic.concurrent.objectcombine;

import me.hao0.concurrency.practice.threadsafe.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * NumberRange不足以保护它的不变性条件
 * 并发环境下不安全
 */
 @NotThreadSafe
public class NumberRange {
	//不变性条件: lower <= upper
	private final AtomicInteger lower = new AtomicInteger();
	private final AtomicInteger upper = new AtomicInteger();
	
	public void setLower(int i){
		if (i > upper.get()){ //不安全的检查
			throw new IllegalArgumentException("lower can't > upper");
		}
		lower.set(i);
	}
	
	public void setUpper(int i){
		if (i < lower.get()){ //不安全的检查
			throw new IllegalArgumentException("lower can't > upper");
		}
		upper.set(i);
	}
}
