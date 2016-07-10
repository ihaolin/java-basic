package me.hao0.basic.concurrent.syncutils;

/**
 * 当不满足前提条件时，有界缓存不会执行相应的操作
 */
public class GrumyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	public GrumyBoundedBuffer(int size){
		super(size);
	}
	
	public synchronized void put(V v) throws BufferFullException{
		if (isFull()){
			throw new BufferFullException();
		}
		doPut(v);
	}
	
	public synchronized V take() throws BufferEmptyExeption{
		if (isEmpty())
			throw new BufferEmptyExeption();
		return doTake();
	}
}
