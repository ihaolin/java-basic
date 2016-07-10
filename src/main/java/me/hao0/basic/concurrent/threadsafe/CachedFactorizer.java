package me.hao0.basic.concurrent.threadsafe;

import java.math.BigInteger;

/**
 * 通过分段synchronized提升性能
 */
@ThreadSafe
public class CachedFactorizer implements Servlet {
	private BigInteger lastNumber = new BigInteger("");
	private BigInteger[] lastFactors = new BigInteger[] {};
	private long hits;
	private long cacheHits;

	@Override
	public void service(ServletRequest req, ServletResponse repo) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = null;
		synchronized (this) {
			++hits;
			if (i.equals(lastNumber)) {
				++cacheHits;
				factors = lastFactors;
			}
		}
		if (factors == null) {
			factors = factor(i);
			synchronized (this) {
				lastNumber = i;
				lastFactors = factors;
			}
		}
		reponseTo(i, lastFactors);
	}

	private void reponseTo(BigInteger i, BigInteger[] factors) {

	}

	private BigInteger[] factor(BigInteger i) {
		return null;
	}

	private BigInteger extractFromRequest(ServletRequest req) {
		return null;
	}
}
