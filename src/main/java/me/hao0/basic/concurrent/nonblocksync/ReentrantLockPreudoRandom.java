package me.hao0.basic.concurrent.nonblocksync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于ReentrantLock实现的随机数生成器
 */
public class ReentrantLockPreudoRandom extends PseudoRandom{
	private final Lock lock = new ReentrantLock(false);
	private int seed;
	
	public ReentrantLockPreudoRandom(int seed){
		this.seed = seed;
	}
	
	public int nextInt(int n){
		lock.lock();
		try{
			int  s = seed;
			seed = calculateNext(s);
			int remainder = s % n;
			return remainder > 0 ? remainder : remainder + n;
		} finally{
			lock.unlock();
		}
	}

	private int calculateNext(int s) {
		return 0;
	}
}
