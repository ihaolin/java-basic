package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 增加日志和记时等功能的线程池
 */
public class TimingThreadPoolExecutor extends ThreadPoolExecutor {
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();//任务执行开始时间
	private final Logger log = Logger.getAnonymousLogger();
	private final AtomicLong numTasks = new AtomicLong(); //统计任务数
	private final AtomicLong totalTime = new AtomicLong(); //线程池运行总时间

	public TimingThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		log.fine(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try{
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.fine(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
		} finally{
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void terminated() {
		try{
			//任务执行平均时间
			log.info(String.format("Terminated: average time=%dns", totalTime.get() / numTasks.get()));
		}finally{
			super.terminated();
		}
	}
}
