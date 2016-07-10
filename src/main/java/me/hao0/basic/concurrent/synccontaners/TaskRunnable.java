package me.hao0.basic.concurrent.synccontaners;

import com.sun.jmx.snmp.tasks.Task;

import java.util.concurrent.BlockingQueue;

/**
 * 恢复中断状态以避免屏蔽中断
 */
public class TaskRunnable implements Runnable {
	private final BlockingQueue<Task> queue;
	public TaskRunnable(BlockingQueue<Task> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			doTask(queue.take());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void doTask(Task task) {
		// do sth.
	}
}
