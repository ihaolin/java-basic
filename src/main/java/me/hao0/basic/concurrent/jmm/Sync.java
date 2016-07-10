package me.hao0.basic.concurrent.jmm;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Sync extends AbstractQueuedSynchronizer {
	private static final int RUNNING = 1, RAN = 2, CANCELLED = 4;
	private Object result;
	private Exception exception;
	
	void innerSet(Object o){
		while (true){
			int s = getState();
			if (ranOrCancelled(s)){
				return;
			}
			if (compareAndSetState(s, RAN)){ //设置状态
				break;
			}
		}
		result = o;
		releaseShared(0); //共享模式下释放锁
		done();
	}
	
	Object innerGet() throws InterruptedException, ExecutionException{
		acquireSharedInterruptibly(0); //共享模式下请求锁
		if (getState() == CANCELLED){
			throw new CancellationException();
		}
		if (exception != null){
			throw new ExecutionException(exception);
		}
		return result;
	}

	private void done() {
		
	}

	private boolean ranOrCancelled(int s) {
		return false;
	}
}
