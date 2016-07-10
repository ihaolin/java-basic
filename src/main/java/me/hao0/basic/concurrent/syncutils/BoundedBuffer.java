package me.hao0.basic.concurrent.syncutils;

/**
 * 使用条件队列实现的有界缓存
 * 每个对象都可看作一个条件队列
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

	public BoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v) throws InterruptedException{
		while (isFull()){
			wait();
		}
		doPut(v);
		notifyAll();
	}
	
	public synchronized V take() throws InterruptedException{
		while (isEmpty()){
			wait();
		}
		V v = doTake();
		notifyAll();
		return v;
	}
}
