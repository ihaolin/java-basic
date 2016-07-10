package me.hao0.basic.concurrent.cancelclose;

import java.util.concurrent.*;

/**
 * 通过Future来取消任务
 */
public class FutureCancel {
	ExecutorService taskExec = Executors.newCachedThreadPool();

	/**
	 * 超时运行任务
	 * 
	 * @param r
	 *            任务
	 * @param timeout
	 *            超时时间
	 * @param unit
	 *            时间单位
	 * @throws InterruptedException
	 */
	public void timedRun(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		Future<?> task = taskExec.submit(r);
		try {
			task.get(timeout, unit);
		} catch (ExecutionException e) {
			//任务执行中抛出异常
		} catch (TimeoutException e) {
			//任务超时处理
		} finally{
			//任务执行完毕,没有影响; 任务执行中会中断任务
			if (task != null) task.cancel(true);
		}
	}
}
