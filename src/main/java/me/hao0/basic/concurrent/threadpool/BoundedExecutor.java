package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore来控制任务的提交速率
 */
public class BoundedExecutor {
	private final Executor exec;
	private final Semaphore semaphore;
	
	public BoundedExecutor(Executor exec, int bound) {
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}
	
	public void submitTask(final Runnable command){
		try {
			semaphore.acquire(); //提交任务前请求信号量
			exec.execute(new Runnable() {
				@Override
				public void run() {
					try{
						command.run();
					} finally{
						semaphore.release(); //执行完释放信号
					}
				}
			});
		} catch (InterruptedException e) {
			// handle exception
		}
	}
}
