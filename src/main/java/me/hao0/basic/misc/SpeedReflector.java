package me.hao0.basic.misc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 基于Unsafe类实现的反射器
 */
public abstract class SpeedReflector {
	private static Unsafe UNSAFE;
	
	static {
		try {
			Class<?> clazz = Class.forName("sun.misc.Unsafe");
			Field field = clazz.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			UNSAFE = (Unsafe) field.get(null);
		} catch (Exception e) {
			System.err.println("failed to init UNSAFE: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> t){
		try {
			return (T)(UNSAFE.allocateInstance(t));
		} catch (InstantiationException e) {
			throw new ReflectException("failed to new instance of " + t.getClass());
		}
	}
	
	/**
	 * 反射异常
	 */
	private static class ReflectException extends RuntimeException{
		private static final long serialVersionUID = 8494454638027261870L;
		
		private String msg;
		
		public ReflectException(String msg) {
			this.msg = msg;
		}
	}
}
