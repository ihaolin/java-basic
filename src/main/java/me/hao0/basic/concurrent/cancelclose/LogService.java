package me.hao0.basic.concurrent.cancelclose;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 为LoggerWriter添加可靠的取消操作
 */
public class LogService {
	private final BlockingQueue<String> queue;
	private final LoggerThread logger;
	private final PrintWriter writer;
	private boolean isShutdown; //用于终止生产者
	private int reservations; //队列中的消息数
	
	public LogService(PrintWriter writer){
		this.queue = new LinkedBlockingDeque<String>();
		this.logger = new LoggerThread();
		this.writer = writer;
	}
	
	/**
	 * 产生日志
	 * @param msg 日志内容
	 * @throws InterruptedException
	 */
	public void log(String msg) throws InterruptedException{
		synchronized (this) {
			if (isShutdown){
				throw new IllegalStateException("can't log, org.haolin.cxf.service has stopped.");
			}
			++reservations;
		}
		queue.put(msg);
	}
	
	/**
	 * 启动日志香妃
	 */
	public void start(){
		logger.start();
	}
	/**
	 * 停止日志服务
	 */
	public void stop(){
		synchronized(this){
			isShutdown = true;
		}
		logger.interrupt(); //中断日志线程
	}
	
	/**
	 * 消费日志线程
	 */
	private class LoggerThread extends Thread{
		@Override
		public void run() {
			try {
				while(true){
					try {
						synchronized (LogService.this) {
							if (isShutdown)
								break;
						}
						String msg = queue.take();
						synchronized (LogService.this) {
							--reservations;
						}
						writer.println(msg);
					} catch (InterruptedException e) {
						// retry
					}
				}
			}finally{
				writer.close();
			}
		}
	}
}
