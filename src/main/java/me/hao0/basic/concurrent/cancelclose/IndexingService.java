package me.hao0.basic.concurrent.cancelclose;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 索引服务
 * 通过一个毒丸对象来关闭服务
 */
public class IndexingService {
	private static final File POISON = new File(""); //毒丸对象
	private final IndexerThread consumer = new IndexerThread(); //消费者
	private final CrawlerThread producer = new CrawlerThread(); //生产者
	private final BlockingQueue<File> queue = new LinkedBlockingDeque<File>();
	private final File root;
	
	public IndexingService(File root) {
		this.root = root;
	}

	/**
	 * 启动索引服务
	 */
	public void start(){
		producer.start();
		consumer.start();
	}
	
	public void stop(){
		producer.interrupt(); //中断爬虫线程
	}
	
	public void awaitTermination() throws InterruptedException{
		consumer.join(); //等待索引线程结束
	}
	
	/**
	 * 爬虫线程
	 */
	private class CrawlerThread extends Thread{
		@Override
		public void run() {
			try {
				crawl(root);
			} catch (InterruptedException e) {
				// handle the exception
			} 
			try {
				while(true){
					queue.put(POISON);
					break;
				}
			} catch (InterruptedException e) {
				// retry
			}
		}

		private void crawl(File root) throws InterruptedException{
			// crawl from web
		}
	}
	
	/**
	 * 建立索引的线程
	 */
	private class IndexerThread extends Thread{
		@Override
		public void run() {
			try {
				while (true){
					File file = queue.take();
					if (file == POISON){ //若是毒丸对象
						break;
					} else{
						indexFile(file); //建立索引文件
					}
				}
			} catch (InterruptedException e) {
				// handle exception
			}
		}

		private void indexFile(File file) {
			
		}
	}
}
