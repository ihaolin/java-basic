package me.hao0.basic.concurrent.nonblocksync;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过CAS来维持包含多个变量的不变性条件
 */
public class CasNumberRange {
	private static class IntPair{
		// 不变性条件: lower <= upper
		final int lower;
		final int upper;
		
		public IntPair(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	}
	
	private AtomicReference<IntPair> values = new AtomicReference<>();
	
	public int getLower(){
		return values.get().lower;
	}
	
	public int getUpper(){
		return values.get().upper;
	}
	
	public void setLower(int i){
		while (true){
			IntPair oldv = values.get();
			if (i > oldv.upper){
				throw new IllegalArgumentException("lower can't > upper");
			}
			IntPair newv = new IntPair(i, oldv.upper);
			if (values.compareAndSet(oldv, newv)){
				return;
			}
		}
	}
}
