package me.hao0.basic.concurrent.synccontaners;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier测试
 */
public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		int threadCount = 3;
		CyclicBarrier barrier =
				 new CyclicBarrier(threadCount, new Runnable() {
					@Override
					public void run() { //最后一个线程到达栅栏时触发
						System.out.println("all have finished.");
					}
		});
		
		for (int i=0 ;i<threadCount; i++){
			new Thread(new WorkThread(barrier)).start();
		}
	}
	
	private static class WorkThread implements Runnable{
		private CyclicBarrier barrier;
		
		public WorkThread(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
			System.out.println(
					Thread.currentThread().getId() + " Working...");
			try {
				barrier.await(); //当前线程阻塞直到最后一个线程到达
				System.out.println(Thread.currentThread().getId() + " awaiting finished.");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}


