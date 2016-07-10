package me.hao0.basic.concurrent.cancelclose;

import java.util.concurrent.*;

/**
 * 通过newTaskFor将非标准的取消操作封装在任务中
 */
public class CancellingExecutor extends ThreadPoolExecutor {

	public CancellingExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask){ //若是我们定制的可取消任务
			return ((CancellableTask<T>)callable).newTask();
		}
		return super.newTaskFor(callable);
	}
}
