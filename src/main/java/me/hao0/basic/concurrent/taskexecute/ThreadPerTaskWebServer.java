package me.hao0.basic.concurrent.taskexecute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的Web服务器
 */
public class ThreadPerTaskWebServer {
	private static final int NTHREADS = 100;
	/**
	 * 创建固定线程数量的线程池
	 */
	private static final Executor exec = 
			Executors.newFixedThreadPool(NTHREADS);
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(80);
		boolean listening = true;
		while (listening){
			final Socket connection = server.accept(); //阻塞等待客户端连接请求
			Runnable task = new Runnable() {
				@Override
				public void run() {
					handlerRequest(connection);
				}
			};
			exec.execute(task);
		}
		server.close();
	}

	private static void handlerRequest(Socket connection) {
		
	}
}
