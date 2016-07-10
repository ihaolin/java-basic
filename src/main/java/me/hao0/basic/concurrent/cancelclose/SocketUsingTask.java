package me.hao0.basic.concurrent.cancelclose;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 使用了Socket的任务
 * 在取消时需要关闭Socket
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {
	private Socket socket;

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public T call() throws Exception {
		//do working
		return null;
	}

	@Override
	public synchronized void cancel() {
		try {
			if (socket != null){
				socket.close();
			}
		} catch (IOException ignored) {
		}
	}

	@Override
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this){
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					SocketUsingTask.this.cancel();
				} catch (Exception ignored) {
				}
				return super.cancel(mayInterruptIfRunning);
			}
		};
	}
}
