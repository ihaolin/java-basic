package me.hao0.basic.concurrent.cancelclose;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 封装ExecutorService实现日志服务
 */
public class LogService2 {
	private final ExecutorService exec = Executors.newSingleThreadExecutor();
	private final PrintWriter writer;
	
	public LogService2(PrintWriter writer){
		this.writer = writer;
	}
	
	/**
	 * 产生日志
	 * @param msg 日志内容
	 * @throws InterruptedException
	 */
	public void log(String msg) throws InterruptedException{
		exec.execute(new WriteTask(msg));
	}
	
	/**
	 * 停止日志服务
	 * @throws InterruptedException 
	 */
	public void stop(long timeout, TimeUnit unit) throws InterruptedException{
		try {
			exec.shutdown(); //平缓关闭服务
			//关闭服务后, 阻塞到所有任务被执行完毕或者超时发生，或当前线程被中断
			exec.awaitTermination(timeout, unit); 
		} finally{
			writer.close();
		}
	}
	
	private class WriteTask implements Runnable{
		private final String msg;
		
		public WriteTask(String msg){
			this.msg = msg;
		}
		@Override
		public void run() {
			
		}
	}
}
