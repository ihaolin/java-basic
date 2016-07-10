package me.hao0.basic.concurrent.objectshare;

public class VolatileTests {
	private static volatile boolean asleep = false;
	
	public static void main(String[] args) {
		while (!asleep){
			// do sth.
		}
	}
}
