package me.hao0.basic.concurrent.synccontaners;

import me.hao0.basic.concurrent.threadsafe.ThreadSafe;

import java.util.Vector;

/**
 * 通过客户端加锁实现线程安全
 */
@ThreadSafe
public class SafeVector<E> {
	private final Vector<E> v = new Vector<>();
	
	public E getLast(){
		synchronized (v) {
			int lastIndex = v.size()-1;
			return v.get(lastIndex);
		}
	}
	
	public E rmLast(){
		synchronized(v){
			int lastIndex = v.size()-1;
			return v.remove(lastIndex);
		}
	}
}
