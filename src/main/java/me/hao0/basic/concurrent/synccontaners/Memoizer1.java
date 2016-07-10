package me.hao0.basic.concurrent.synccontaners;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算缓存器
 * 内部使用HashMap实现计算结果的缓存
 * 通过外部接口同步操作实现线程安全
 * 但有可能由于计算时间过长导致性能低下
 */
public class Memoizer1<A, V> implements Computable<A, V> {
	private final Map<A, V> cache = new HashMap<A, V>();
	private final Computable<A, V> c;
	
	public Memoizer1(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public synchronized V compute(A key) throws InterruptedException {
		V result = cache.get(key);
		if (result == null){
			result = c.compute(key); //计算
			cache.put(key, result);
		}
		return result;
	}
}
