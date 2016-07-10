package me.hao0.basic.concurrent.jmm;

/**
 * 不安全的延迟初始化
 */
public class SafeLazyInitialization {
	private static Object resource;
	
	public static Object getInstance(){
		if (resource == null){
			resource = new Object(); //不安全的发布
		}
		return resource;
	}
}
