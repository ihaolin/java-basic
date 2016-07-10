package me.hao0.basic.concurrent.syncutils;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用AbstractQueuedSynchronizer实现的二元闭锁
 */
public class OneShotLatch {
	private Sync sync = new Sync();
	
	public void signal(){
		sync.releaseShared(0);
	}
	
	public void await() throws InterruptedException{
		sync.acquireSharedInterruptibly(0);
	}
	
	private class Sync extends AbstractQueuedSynchronizer{
		
		private static final long serialVersionUID = -4086289179080702705L;

		protected int tryAcquireShared(int ingored){
			// 如果闭锁是开的(state == 1), 那么这个操作符成功，否则将失败
			return (getState() == 1) ? 1 : -1;
		}
		
		protected boolean tryReleaseShared(int ignored){
			setState(1); // 打开闭锁
			return true; // 其他的线程可以获取该闭锁
		}
	}
}
