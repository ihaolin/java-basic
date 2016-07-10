package me.hao0.basic.concurrent.synccontaners;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 */
public class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore sem;
	
	public BoundedHashSet(int bound){
		this.set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound); //非公平
	}
	
	public boolean add(T t) throws InterruptedException{
		sem.acquire(); //请求semaphore, permits-1或阻塞到permits > 0
		boolean wasAdded = false;
		
		try {
			wasAdded = set.add(t);
			return wasAdded;
		} finally{
			if (!wasAdded) //未添加成功则释放semaphore
				sem.release();
		}
	}
	
	public boolean remove(T t){
		boolean wasRemoved = set.remove(t);
		if (wasRemoved) //删除成功permits+1;
			sem.release();
		return wasRemoved;
	}
}
