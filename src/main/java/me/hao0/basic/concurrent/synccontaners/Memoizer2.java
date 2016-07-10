package me.hao0.basic.concurrent.synccontaners;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 计算缓存器
 * 通过ConcurrentHashMap代替HashMap, 提升并发性能
 * 但这样有可能多个线程同时调用compute方法，
 * 由于计算过程中还没有结果，有可能导致多个线程计算同样的值
 */
public class Memoizer2<A, V> implements Computable<A, V> {
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	private final Computable<A, V> c;
	
	public Memoizer2(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public V compute(A key) throws InterruptedException {
		V result = cache.get(key);
		if (result == null){
			result = c.compute(key); //计算
			cache.put(key, result);
		}
		return result;
	}
}
