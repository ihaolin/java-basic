package me.hao0.basic.concurrent.synccontaners;

import java.util.concurrent.*;

/**
 * 计算缓存器
 * 通过ConcurrentHashMap.putIfAbsent避免重复任务
 */
public class Memoizer<A, V> implements Computable<A, V> {
	private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A, V> c;
	
	public Memoizer(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public V compute(final A key) throws InterruptedException {
		while(true){
			Future<V> f = cache.get(key);
			if (f == null){
				Callable<V> computeTask = new Callable<V>() {
					@Override
					public V call() throws Exception {
						return c.compute(key);
					}
				};
				FutureTask<V> ft = new FutureTask<V>(computeTask);
				f = cache.putIfAbsent(key, ft); //该方法不会对相同key的值进行覆盖，这样避免了相同key的任务被计算
				if (f == null) ft.run(); //执行计算
			}
			try {
				return f.get(); //获取计算结果
			} catch (CancellationException e){ 
				cache.remove(key); //计算取消，则移除对应计算任务key
			} catch (ExecutionException e) {
				//do exception handle
			}
		}
	}
}
