package me.hao0.basic.nio;

import java.util.Arrays;
import java.util.Comparator;

public class TestString2find {

	public static void main(String[] args) {
		String[] arr = {"map", "markle", "ok", "hello"};
		Arrays.sort(arr);
		for (String a : arr){
			System.out.println(a);
		}
		System.out.println(Arrays.binarySearch(arr, "ok"));;
	}
	
	public class MyComparator implements Comparator<String>{

		@Override
		public int compare(String a, String b) {
			return b.compareTo(a);
		}
	}
}
