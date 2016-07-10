package me.hao0.basic.concurrent.taskexecute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 为每一个用户请求创建一个线程为其服务
 */
public class TaskExecutionWebServer {
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
			new Thread(task).start();
		}
		server.close();
	}

	private static void handlerRequest(Socket connection) {
		
	}
}
