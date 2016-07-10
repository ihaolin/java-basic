package me.hao0.basic.concurrent.jmm;

/**
 * 提前初始化
 */
public class EagerInitialization {
	private static Object resource = new Object();
	
	public static Object getInstance(){
		return resource;
	}
}
