package me.hao0.basic.concurrent.taskexecute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 对线程池进行生命周期管理
 */
public class LifecycleWebServer {
	private static final int NTHREADS = 100;
	private final ServerSocket server;
	
	public LifecycleWebServer() throws IOException{
		server = new ServerSocket(80);
	}
	
	/**
	 * 创建固定线程数量的线程池
	 */
	private static final ExecutorService exec = 
			Executors.newFixedThreadPool(NTHREADS);
	
	public void start() throws IOException{
		while (!exec.isShutdown()){
			try {
				final Socket connection = server.accept(); //阻塞等待客户端连接请求
				Runnable task = new Runnable() {
					@Override
					public void run() {
						handlerRequest(connection);
					}
				};
				exec.execute(task);
			} catch (RejectedExecutionException e) {
				if (!exec.isShutdown()){
					//task submission is rejected
				}
			}
		}
	}
	
	public void stap() throws IOException{
		exec.shutdown(); //平缓关闭线程池
		server.close();
	}

	private static void handlerRequest(Socket connection) {
		// handle request
	}
}
