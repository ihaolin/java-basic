package me.hao0.basic.concurrent.cancelclose;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 可取消的任务接口
 */
public interface CancellableTask<T> extends Callable<T> {
	void cancel();
	RunnableFuture<T> newTask();
}
