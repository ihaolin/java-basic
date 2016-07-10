package me.hao0.basic.concurrent.objectcombine;

import me.hao0.concurrency.practice.threadsafe.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这段客户端看似线程安全，
 * 但其实并不安全，因为锁住的对象不一致，
 * 这里仅是锁住ListHelper对象，但list对象并没有被锁住，
 * 其他客户端仍可在不安全情况下对其进行操作
 */
@NotThreadSafe
public class ListHelper<E> {
	public List<E> list = 
			Collections.synchronizedList(new ArrayList<E>());
	
	public synchronized boolean putIfAbsent(E x){
		boolean absent = !list.contains(x);
		if (absent)
			list.add(x);
		return absent;
	}
}
