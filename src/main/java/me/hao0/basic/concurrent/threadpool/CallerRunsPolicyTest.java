package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallerRunsPolicyTest {
	private static int N_THREADS=10;
	private static int CAPACITY = 100;
	
	/**
	 * 创建一个固定大小的线程池,
	 * 并采用有界队列与"调用者运行"饱和策略
	 */
	public static void main(String[] args) {
		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(N_THREADS, N_THREADS,
						0L, TimeUnit.MILLISECONDS, 
						new LinkedBlockingQueue<Runnable>(CAPACITY));
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	}
}
