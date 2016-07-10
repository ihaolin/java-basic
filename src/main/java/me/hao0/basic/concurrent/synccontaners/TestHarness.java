package me.hao0.basic.concurrent.synccontaners;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用CountDownLatch来启动和停止线程
 */
public class TestHarness {
	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
		final CountDownLatch startGate = new CountDownLatch(1); //所有线程开始执行task的阀门
		final CountDownLatch endGate = new CountDownLatch(nThreads); //所有线程结束执行task的阀门
		
		for (int i=0; i<nThreads; i++){
			Thread t = new Thread(){
				@Override
				public void run() {
					try {
						startGate.await(); //等待startGate值减为0
						try {
							task.run();
						} finally{
							endGate.countDown(); //一个线程运行结束，值减1
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long start = System.nanoTime();
		startGate.countDown(); //所有线程开始执行task
		endGate.await(); //等待所有线程执行结束
		long end = System.nanoTime();
		return end - start;
	}
}
