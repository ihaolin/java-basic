package me.hao0.basic.concurrent.synccontaners;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {

	@Override
	public BigInteger compute(String input) throws InterruptedException {
		// do long compute
		
		return new BigInteger(input);
	}
}
