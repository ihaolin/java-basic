package me.hao0.basic.concurrent.avoidactiveness_danger;

/**
 * 容易因为获取锁的顺序导致死锁
 */
public class LeftRightDeadLock {
	private final Object left = new Object();
	private final Object right = new Object();
	
	public void leftRight(){
		synchronized(left){
			synchronized(right){
				// to do sth.
			}
		}
	}
	
	public void rightLeft(){
		synchronized(right){
			synchronized(left){
				// to do sth.
			}
		}
	}
}
