package me.hao0.basic.concurrent.nonblocksync;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 链表中非阻塞算法中的插入排序，来自Michael-Scott
 */
public class LinkedQueue<E> {
	private static class Node<E>{
		final E item;
		final AtomicReference<Node<E>> next;
		
		public Node(E item, Node<E> next){ 
			this.item = item;
			this.next = new AtomicReference<>(next);
		}
	}
	
	private final Node<E> dummy = new Node<E>(null, null);
	private final AtomicReference<Node<E>> head = 
						new AtomicReference<>(dummy);
	private final AtomicReference<Node<E>> tail = 
						new AtomicReference<>(dummy);
	
	public boolean put(E item){
		Node<E> newNode = new Node<E>(item, null);
		while (true){
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();
			if (curTail == tail.get()){		//尾部还未修改
				if (tailNext != null){
					// 队列处于中间状态(即新节点已经接上，尾节点还未更新)，推进尾节点
					tail.compareAndSet(curTail, tailNext);
				} else{
					// 处于稳定状态, 尝试插入新节点
					if (curTail.next.compareAndSet(null, newNode)){
						// 插入成功后，推进尾节点
						tail.compareAndSet(curTail, tailNext);
						return true;
					}
				}
			}
		}
	}
}
