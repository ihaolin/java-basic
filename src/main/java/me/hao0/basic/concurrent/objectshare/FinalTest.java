package me.hao0.basic.concurrent.objectshare;

import java.util.Arrays;

public class FinalTest {
	private static final int[] arr = new int[]{12, 22};
	public static void main(String[] args) {
		int[] a = Arrays.copyOf(arr, arr.length);
		arr[0] = 11;
		System.out.println(a[0]);
	}
}
