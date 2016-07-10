package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在单线程Executor中任务发生死锁 
 */
public class ThreadDeadLock {
	ExecutorService exec = Executors.newSingleThreadExecutor();
	
	public class RenderPageTask implements Callable<String>{
		@Override
		public String call() throws Exception {
			Future<String> header, footer; //页眉, 页脚
			header = exec.submit(new LoadFileTask("header.html"));
			footer = exec.submit(new LoadFileTask("footer.html"));
			String body = renderBody();
			//有可能发生死锁---任务等待子任务完成
			return header.get() + body + footer.get();
		}

		private String renderBody() {
			return null;
		}
	}
}
