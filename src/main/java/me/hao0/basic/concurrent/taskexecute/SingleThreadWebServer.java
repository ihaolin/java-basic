package me.hao0.basic.concurrent.taskexecute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 串行处理请求:
 * 简单正确，但性能低下
 */
public class SingleThreadWebServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(80);
		boolean listening = true;
		while (listening){
			Socket connection = server.accept(); //阻塞等待客户端连接请求
			handlerRequest(connection);
		}
		server.close();
	}

	private static void handlerRequest(Socket connection) {
		
	}
}
