package me.hao0.basic.concurrent.jmm;

/**
 * 双重检查加锁, 不安全,
 * 线程可能看到无效的值
 */
public class DoubleCheckedLocking {
	private static Object resource;
	
	public static Object getInstance(){
		if (resource == null){
			synchronized (DoubleCheckedLocking.class){
				if (resource == null){
					resource = new Object(); 
				}
			}
		}
		return resource;
	}
}
