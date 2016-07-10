package me.hao0.basic.concurrent.jmm;

/**
 * 延长初始化占位类模式
 */
public class ResourceFactory {
	private static class ResourceHolder{
		public static Object resource = new Object();
	}
	
	public static Object getInstance(){
		return ResourceHolder.resource;
	}
}
