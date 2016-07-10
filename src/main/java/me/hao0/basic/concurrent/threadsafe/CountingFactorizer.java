package me.hao0.basic.concurrent.threadsafe;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 可通过java提供的原子变量(Atomic*)
 * 来解决竞态条件引起的非线程安全
 */
@ThreadSafe
public class CountingFactorizer implements Servlet {
	private AtomicLong count = new AtomicLong(0);
	
	@Override
	public void service(ServletRequest req, ServletResponse repo) {
		count.incrementAndGet();
		Map<String, Object> params = extractFromRequest(req);
		Map<String, Object> res = doBussiness(params);
		reponseTo(res);
	}

	private void reponseTo(Map<String, Object> res) {
		
	}

	private Map<String, Object> doBussiness(Map<String, Object> params) {
		return null;
	}

	private Map<String, Object> extractFromRequest(ServletRequest req) {
		return null;
	}
}
