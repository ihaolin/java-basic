package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义的线程工厂
 */
public class MyThreadFactory implements ThreadFactory {
	private final String poolName;
	
	public MyThreadFactory(String poolName) {
		super();
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new MyAppThread(r);
	}
}
