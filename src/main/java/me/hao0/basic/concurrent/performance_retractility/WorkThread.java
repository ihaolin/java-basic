package me.hao0.basic.concurrent.performance_retractility;

import java.util.concurrent.BlockingQueue;

/**
 * 对任务队列的串行访问
 */
public class WorkThread extends Thread {
	private final BlockingQueue<Runnable> queue;
	
	public WorkThread(BlockingQueue<Runnable> queue){
		this.queue = queue;
	}
	
	public void run(){
		while (true){
			try {
				Runnable task = queue.take(); //此处为程序的串行部分
				task.run();
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
