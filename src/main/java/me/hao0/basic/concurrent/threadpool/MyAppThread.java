package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 扩展线程类
 */
public class MyAppThread extends Thread {
	public static final String DEFAULT_NAME="MyAppThread";
	private static volatile boolean debugLifecycle = false;
	private static final AtomicInteger created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	private static final Logger log = Logger.getAnonymousLogger();
	
	public MyAppThread(Runnable r) {
		this(r, DEFAULT_NAME);
	}

	public MyAppThread(Runnable r, String name) {
		super(r, name+ "-" + created.incrementAndGet());
		setUncaughtExceptionHandler( //设置未捕获的异常发生时的处理器
				new UncaughtExceptionHandler() {
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
					}
				});
	}

	@Override
	public void run() {
		boolean debug = debugLifecycle;
		if (debug) 
			log.log(Level.FINE, "running thread " + getName());
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if (debug) 
				log.log(Level.FINE, "existing thread " + getName());
		}
	}
}
