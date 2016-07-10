package me.hao0.basic.concurrent.cancelclose;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断来实现取消
 * 不采用boolean变量，
 * 防止在queue.put()时由于阻塞，不能检查到boolean变量而无法取消
 * 但使用interrupt就可以,
 * 即使queue.put()阻塞, 也会检查到interrupt信号，从而抛出IntteruptedException
 * 从而达到取消的目的
 */
public class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	
	public PrimeProducer(BlockingQueue<BigInteger> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted()){
				queue.put(p = p.nextProbablePrime());
			}
		} catch (InterruptedException e) {
			// thread exit
		}
	}
	/**
	 * 取消
	 */
	public void cancel(){
		interrupt(); //中断当前线程
	}
}
