package me.hao0.basic.concurrent.jmm;

/**
 * 通过同步安全发布
 */
public class UnsafeLazyInitialization {
	private static Object resource;
	
	public synchronized static Object getInstance(){
		if (resource == null){
			resource = new Object(); //不安全的发布
		}
		return resource;
	}
}
