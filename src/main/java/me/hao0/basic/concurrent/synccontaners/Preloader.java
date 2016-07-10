package me.hao0.basic.concurrent.synccontaners;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask来提前加载稍后需要的数据
 */
public class Preloader {
	private final FutureTask<ProductInfo> future = new FutureTask<>(
			new Callable<ProductInfo>() {
				@Override
				public ProductInfo call() throws Exception {
					return loadProductInfo();
				}
			});
	
	private final Thread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public ProductInfo get() throws InterruptedException {
		try {
			return future.get();
		} catch (ExecutionException e) {
			return null;
		}
	}
	
	private ProductInfo loadProductInfo() {
		return null;
	}
}
