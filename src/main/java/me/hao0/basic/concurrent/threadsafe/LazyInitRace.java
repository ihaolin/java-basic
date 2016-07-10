package me.hao0.basic.concurrent.threadsafe;

/**
 * 延迟初始化的竞态条件
 * 由于多个线程可能同时执行到instance == null,
 * 或者由于实例初始化过程比较耗费时间,
 * 这样有可能所谓的"单例"不再单例
 */
@NotThreadSafe
public class LazyInitRace {
	private ExpensiveObject instance;
	
	private LazyInitRace(){}
	
	public ExpensiveObject getInstance() {
		if (instance == null) {
			instance = new ExpensiveObject();
		}
		return instance;
	}
}
