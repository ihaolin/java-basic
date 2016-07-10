package me.hao0.basic.concurrent.synccontaners;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 计算缓存器
 * 通过FutureTask代替map中的Value
 * 这样可以在计算结果计算完成，就立即返回，
 * 但仍然有可能重复计算，因为存在非原子的复合操作"若没有则添加": if (f == null){...}
 */
public class Memoizer3<A, V> implements Computable<A, V> {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A, V> c;
	
	public Memoizer3(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public V compute(final A key) throws InterruptedException {
		Future<V> f = cache.get(key);
		if (f == null){
			Callable<V> computeTask = new Callable<V>() {
				@Override
				public V call() throws Exception {
					return c.compute(key);
				}
			};
			FutureTask<V> ft = new FutureTask<>(computeTask);
			f = ft;
			cache.put(key, ft);
			ft.run(); //执行计算
		}
		try {
			return f.get(); //获取计算结果
		} catch (ExecutionException e) {
			//do exception handle
		}
		return null;
	}
}
