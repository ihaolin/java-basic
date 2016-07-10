package me.hao0.basic.concurrent.nonblocksync;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ConcurrentLinkedQueue<E> {
	private static class Node<E>{
		private final E item;
		private volatile Node<E> next; // volatile变量
		
		public Node(E item){
			this.item = item;
		}
	}
	// 基于CAS的更新器
	private static AtomicReferenceFieldUpdater<Node, Node> nextUpdater
		= AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "next");
}
