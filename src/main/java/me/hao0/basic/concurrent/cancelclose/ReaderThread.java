package me.hao0.basic.concurrent.cancelclose;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 */
public class ReaderThread extends Thread {
	private final Socket socket;
	private final InputStream in;
	private int bufferSize;
	
	public ReaderThread(Socket socket, InputStream in) {
		this(socket, in, 1024);
	}
	
	public ReaderThread(Socket socket, InputStream in, int bufferSize) {
		this.socket = socket;
		this.in = in;
		this.bufferSize = bufferSize;
	}

	@Override
	public void interrupt() {
		try {
			socket.close(); //中断前关闭socket
		} catch (IOException e) {
			
		} finally{
			super.interrupt();
		}
	}

	@Override
	public void run() {
		try {
			byte[] buf = new byte[bufferSize];
			while (true) {
				int count = in.read(buf);
				if (count < 0) {
					break;
				} else if (count > 0) {
					processBuffer(buf, count);
				}
			}
		} catch (IOException e) {
			// 线程中断处理
		}
	}

	private void processBuffer(byte[] buf, int count) {
		
	}
}
