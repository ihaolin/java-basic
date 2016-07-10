package me.hao0.basic.concurrent.threadsafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 多个关联线程安全的状态对象导致线程安全
 * 这里我们对lastNumber进行缓存，若请求的值与其相同，直接返回，反之计算
 */
@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
	private final AtomicReference<BigInteger> lastNumber = 
			new AtomicReference<>();
	private final AtomicReference<BigInteger[]> lastFactors = 
			new AtomicReference<>();
	
	@Override
	public void service(ServletRequest req, ServletResponse repo) {
	   BigInteger i = extractFromRequest(req);
	   //A线程发现不等，重新计算;(A未计算完)
	   //B线程进入也发现不等，也重新计算(但其实有可能经过A计算后是相等的)
	   //从而达不到缓存效果
	   if (i.equals(lastNumber.get())){
		   reponseTo(i);
	   } else{ 
		   BigInteger[] factors = factor(i);
		   lastNumber.set(i);
		   lastFactors.set(factors);
		   reponseTo(i);
	   }
	}

	private BigInteger[] factor(BigInteger i) {
		return null;
	}

	private void reponseTo(BigInteger res) {
		
	}

	private BigInteger extractFromRequest(ServletRequest req) {
		return null;
	}
}
