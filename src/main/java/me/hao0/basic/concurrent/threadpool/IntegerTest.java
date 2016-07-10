package me.hao0.basic.concurrent.threadpool;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 
 */
public class IntegerTest {
	private static Unsafe UNSAFE;
	static {
		try {
			Class<?> clazz = Class.forName("sun.misc.Unsafe");
			Field field = clazz.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			UNSAFE = (Unsafe) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Integer i = 500;
		change(i);
		System.out.println(i);
	}

	private static void change(Integer i) {
		i = 200;
	}
}
