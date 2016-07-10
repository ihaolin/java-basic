package me.hao0.basic.concurrent.objectcombine;

import java.util.Vector;

/**
 * 通过扩展实现非重复Vector
 */
public class NoRepeatVector<E> extends Vector<E> {
	public synchronized boolean putIfAbsent(E e){
		boolean exist = contains(e);
		if (!exist) 
			add(e);
		return exist;
	}
}
