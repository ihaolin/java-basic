package me.hao0.basic.concurrent.cancelclose;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在ExecutorService中跟踪在关闭之后被取消的任务
 */
public class TrackingExecutor extends AbstractExecutorService {
	private final ExecutorService exec;
	private final Set<Runnable> tasksCancelledAtShutdown = 
			Collections.synchronizedSet(new HashSet<Runnable>());
	
	public TrackingExecutor(ExecutorService exec) {
		this.exec = exec;
	}

	/**
	 * 获取关闭后取消的任务
	 */
	public List<Runnable> getCancelledTasks(){
		if (!exec.isTerminated()){
			throw new IllegalStateException("org.haolin.cxf.service doesn't stop");
		}
		return new ArrayList<>(tasksCancelledAtShutdown);
	}
	
	@Override
	public void execute(final Runnable command) {
		exec.execute(new Runnable() {
			@Override
			public void run() {
				try {
					command.run();
				} finally{ //有可能出现误报: 任务执行完毕了, 线程池
					if (isShutdown() && //若Executor已经关闭了
							Thread.currentThread().isInterrupted()){ //且当前线程被中断了
						tasksCancelledAtShutdown.add(command);
					}
				}
			}
		});
	}
	
	@Override
	public void shutdown() {

	}

	@Override
	public List<Runnable> shutdownNow() {
		return null;
	}

	@Override
	public boolean isShutdown() {
		return false;
	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return false;
	}
}
