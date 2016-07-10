package me.hao0.basic.concurrent.objectshare;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 不可变类：
 * 所有域都是final
 */
public class OneValueCache {
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;
	
	public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
		this.lastNumber = lastNumber;
		this.lastFactors = lastFactors;
	}
	
	public BigInteger[] getFactors(BigInteger i){
		if (lastNumber == null || ! lastNumber.equals(i)){
			return null;
		} else{
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}
 