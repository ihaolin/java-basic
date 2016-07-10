package me.hao0.basic.concurrent.cancelclose;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 不支持关闭的生产者-消费者日志服务
 */
public class LogWriter {
	private final BlockingQueue<String> queue;
	private final LoggerThread logger;
	
	public LogWriter(Writer writer){
		this.queue = new LinkedBlockingDeque<String>();
		this.logger = new LoggerThread(writer);
	}
	
	public void start(){
		logger.start();
	}
	
	public void log(String msg) throws InterruptedException{
		queue.put(msg);
	}

	private class LoggerThread extends Thread{
		private final Writer writer;
		
		public LoggerThread(Writer writer) {
			this.writer = writer;
		}

		@Override
		public void run() {
			try {
				while(true){
					writer.write(queue.take());
				}
			} catch (IOException e) {
				// io exception handle
			} catch (InterruptedException e) {
				// interrupt exceptino handle
			} finally{
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
