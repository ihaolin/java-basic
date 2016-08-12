package me.hao0.basic.concurrent.synccontaners;

import me.hao0.basic.concurrent.threadsafe.NotThreadSafe;

import java.util.Vector;

/**
 * getLast, rmLast没有同步，可能导致lastIndex错乱
 */
@NotThreadSafe
public class UnsafeVector<E> {
	private final Vector<E> v = new Vector<>();
	
	public E getLast(){
		int lastIndex = v.size()-1;
		return v.get(lastIndex);
	}
	
	public E rmLast(){
		int lastIndex = v.size()-1;
		return v.remove(lastIndex);
	}
}
