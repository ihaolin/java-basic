package me.hao0.basic.concurrent.tests;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试BoundedBuffer的生产者-消费者程序
 */
public class PutTakeTest {
	private static final ExecutorService pool = 
			Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;
	private final BoundedBuffer<Integer> bb;
	private final int nTrials, nPairs;
	
	public PutTakeTest(int capacity, int npairs, int ntrials) {
		this.bb = new BoundedBuffer<Integer>(capacity);
		this.nTrials = ntrials;
		this.nPairs = npairs;
		this.barrier = new CyclicBarrier(npairs * 2 + 1);
	}
	
	void test(){
		try {
			for (int i=0; i<nPairs; i++){
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			barrier.await(); // 等待所有的线程就绪
			barrier.await(); // 等待所有的线程执行完成
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	class Producer implements Runnable{
		@Override
		public void run() {
			try {
				int seed = hashCode() ^ (int) System.nanoTime();
				int sum = 0;
				barrier.await(); //等待所有线程都准备好了，再执行
				for (int i=nTrials; i>0; --i){
					bb.put(seed);
					sum += seed;
					seed = xorShift(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	class Consumer implements Runnable{
		@Override
		public void run() {
			try {
				barrier.await(); //等待所有线程都准备好了，再执行
				int sum = 0;
				for (int i=nTrials; i>0; --i){
					sum += bb.take();
				}
				takeSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public int xorShift(int seed) {
		return 0;
	}
	
}
