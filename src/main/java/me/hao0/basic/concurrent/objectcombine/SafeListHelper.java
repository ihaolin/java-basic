package me.hao0.basic.concurrent.objectcombine;

import me.hao0.concurrency.practice.threadsafe.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ThreadSafe
public class SafeListHelper<E> {
	public List<E> list = 
			Collections.synchronizedList(new ArrayList<E>());
	
	public boolean putIfAbsent(E x){
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent)
				list.add(x);
			return absent;
		}
	}
}
