package me.hao0.basic.concurrent.nonblocksync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于AtomicInteger实现的随机数生成器
 */
public class AtomicPseudoRandom extends PseudoRandom{
	private AtomicInteger seed;
	
	public AtomicPseudoRandom(int seed){
		this.seed = new AtomicInteger(seed);
	}
	
	public int nextInt(int n){
		while (true){
			int s = seed.get();
			int nextSeed = calculateNext(s);
			if (seed.compareAndSet(s, nextSeed)){
				int remainder = s % n;
				return remainder > 0 ? remainder: remainder + n;
			}
		}
	}

	private int calculateNext(int s) {
		// TODO Auto-generated method stub
		return 0;
	}
}
