package me.hao0.basic.concurrent.threadsafe;

import java.util.Map;

/**
 * 该类无任务属性域，且不包含任何其他类中域的引用
 * 即计算中的状态都为临时状态，保存在线程栈中
 * 由JMM知线程栈线程私有，仅由当前线程可访问
 * 因此线程安全
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
	@Override
	public void service(ServletRequest req, ServletResponse repo) {
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
