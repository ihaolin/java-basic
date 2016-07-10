package me.hao0.basic.concurrent.objectshare;

import me.hao0.concurrency.practice.threadsafe.Servlet;
import me.hao0.concurrency.practice.threadsafe.ServletRequest;
import me.hao0.concurrency.practice.threadsafe.ServletResponse;
import me.hao0.concurrency.practice.threadsafe.ThreadSafe;

import java.math.BigInteger;

/**
 * 使用Volatile类型发布不可变对象
 */
@ThreadSafe
public class VolatileCachedFactorizer implements Servlet {
	private volatile OneValueCache cache = new OneValueCache(null, null);
	@Override
	public void service(ServletRequest req, ServletResponse repo) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
		reponseTo(i, factors);
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
