package me.hao0.basic.concurrent.objectcombine;

public class PrivateLock {
	private final Object lock = new Object();
	
	public void methodOne(){
		synchronized(lock){
			// do sth.
		}
	}
}
