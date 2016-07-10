package me.hao0.basic.misc;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;


/**
 * @author haitao.yao Dec 14, 2010
 */
public class ReflectionCompare {
	private static final int count = 10000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long duration = testIntCommon();
		System.out.println("int common test for  " + count
				+ " times, duration: " + duration);
		duration = testUnsafe();
		System.out.println("int unsafe test for  " + count
				+ " times, duration: " + duration);
	}

	private static long testUnsafe() {
		long start = System.currentTimeMillis();
		Unsafe unsafe = getUnsafe();
		int temp = count;
		Field field = getIntField();
		long offset = unsafe.objectFieldOffset(field);
		while (temp-- > 0) {
			unsafe.getInt(new TestBean(), offset);
		}
		return System.currentTimeMillis() - start;
	}

	private static long testIntCommon() {
		long start = System.currentTimeMillis();
		int temp = count;
		getIntField().setAccessible(true);
		try {
			while (temp-- > 0) {
				TestBean bean = new TestBean();
				getIntField().get(bean);
			}
			return System.currentTimeMillis() - start;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	private static final Unsafe unsafe;
	static {
		Unsafe value = null;
		try {
			Class<?> clazz = Class.forName("sun.misc.Unsafe");
			Field field = clazz.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			value = (Unsafe) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error to get theUnsafe", e);
		}
		unsafe = value;
	}

	public static final Unsafe getUnsafe() {
		return unsafe;
	}

	private static final Field intField;
	private static final Field stringField;
	static {
		try {
			intField = TestBean.class.getDeclaredField("age");
			stringField = TestBean.class.getDeclaredField("name");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("failed to init testbean field", e);
		}
	}

	public static final Field getIntField() {
		return intField;
	}

	public static final Field getStringField() {
		return stringField;
	}

	/**
	 * @author haitao.yao Dec 14, 2010
	 */
	static class TestBean implements Serializable {
		/**
     * 
     */
		private static final long serialVersionUID = -5994966479456252766L;

		private String name;
		private int age;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the age
		 */
		public int getAge() {
			return age;
		}

		/**
		 * @param age
		 *            the age to set
		 */
		public void setAge(int age) {
			this.age = age;
		}
	}
}