package me.hao0.basic.concurrent.threadsafe;

import java.math.BigInteger;

/**
 * 通过synchronized实现线程安全，但性能低下
 */
@ThreadSafe
public class CachingFactorizer implements Servlet {
	private BigInteger lastNumber = new BigInteger("");
	private BigInteger[] lastFactors = new BigInteger[]{};
	
	@Override
	public synchronized void service(ServletRequest req, ServletResponse repo) {
	   BigInteger i = extractFromRequest(req);
	   if (i.equals(lastNumber)){
		   reponseTo(i,lastFactors);
	   } else{ 
		   BigInteger[] factors = factor(i);
		   lastNumber = i;
		   lastFactors = factors;
		   reponseTo(i, factors);
	   }
	}

	private void reponseTo(BigInteger i, BigInteger[] factors) {
		// TODO Auto-generated method stub
		
	}

	private BigInteger[] factor(BigInteger i) {
		return null;
	}

	private BigInteger extractFromRequest(ServletRequest req) {
		return null;
	}
}
