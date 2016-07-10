package me.hao0.basic.concurrent.syncutils;

/**
 * 基于非公平的ReentrantLock实现tryAcquire
 * 上午12:59:00
 */
public class NotFairReentrantLock {
	private Thread owner;
	
	protected boolean tryAcquire(int ignored){
		final Thread current = Thread.currentThread();
		int c = getState();
		if (c == 0){ //若为被锁住
			if (compareAndSetState(0, 1)){ //设置锁住
				owner = current;
				return true;
			}
		} else if(current == owner){
			setState(c + 1);
			return true;
		}
		
		return false;
	}

	private void setState(int i) {
		
	}

	private boolean compareAndSetState(int i, int j) {
		return false;
	}

	private int getState() {
		return 0;
	}
	
}
