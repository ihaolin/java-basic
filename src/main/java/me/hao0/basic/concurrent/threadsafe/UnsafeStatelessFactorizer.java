package me.hao0.basic.concurrent.threadsafe;

import java.util.Map;

/**
 * 竞态条件导致非线程安全
 */
@NotThreadSafe
public class UnsafeStatelessFactorizer implements Servlet {
	private long count = 0;
	
	@Override
	public void service(ServletRequest req, ServletResponse repo) {
		count++; //该操作并非原子(读取--自增--写入), 导致非线程安全
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
