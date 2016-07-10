package me.hao0.basic.concurrent.nonblocksync;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用Treiber算法构造的非阻塞栈
 */
public class ConcurrentStack<E> {
	private AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();
	
	public void push(E item){
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		
		do{
			oldHead = top.get();
			newHead.next = oldHead;
		} while (!top.compareAndSet(oldHead, newHead));
	}
	
	public E pop(){
		Node<E> oldHead;
		Node<E> newHead;
		
		do {
			oldHead = top.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!top.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}
	
	private static class Node<E>{
		public final E item;
		public Node<E> next;
		
		public Node(E item){
			this.item = item;
		}
	}
}
