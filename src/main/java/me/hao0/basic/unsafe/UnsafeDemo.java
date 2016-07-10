package me.hao0.basic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

	public static void main(String[] args) {
		long sum = 0L;
		long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
		SuperArray array = new SuperArray(SUPER_SIZE);
		System.out.println("Array size:" + array.size()); // 4294967294
		for (int i = 0; i < 100; i++) {
			array.set((long) Integer.MAX_VALUE + i, (byte) 3);
			sum += array.get((long) Integer.MAX_VALUE + i);
		}
		System.out.println("Sum of 100 elements:" + sum); // 300
	}

	public static Unsafe getUnsafe() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal
			f.setAccessible(true);
			Unsafe unsafe = (Unsafe) f.get(null);
			return unsafe;
		} catch (Exception e) {
			return null;
		}
	}
}

class SuperArray {
	private final static int BYTE = 1;
	private long size;
	private long address;

	public SuperArray(long size) {
		this.size = size;
		// 得到分配内存的起始地址
		address = UnsafeDemo.getUnsafe().allocateMemory(size * BYTE);
	}

	public void set(long i, byte value) {
		UnsafeDemo.getUnsafe().putByte(address + i * BYTE, value);
	}

	public int get(long idx) {
		return UnsafeDemo.getUnsafe().getByte(address + idx * BYTE);
	}

	public long size() {
		return size;
	}
}
