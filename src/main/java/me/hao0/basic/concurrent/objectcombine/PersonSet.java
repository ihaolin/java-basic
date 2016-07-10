package me.hao0.basic.concurrent.objectcombine;

import me.hao0.concurrency.practice.threadsafe.ThreadSafe;
import me.hao0.entities.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * 这里将mySet实例封闭在PersonSet中，
 * 尽管HashSet是非线程安全类，
 * 由于mySet是私有且不会逸出的,
 * 我们通过公共接口提供给外部访问，但加上了PersonSet内置锁保护synchronized,
 * 因而PersonSet是一个线程安全的类
 */
@ThreadSafe
public class PersonSet {
	private final Set<Person> mySet = new HashSet<>();
	
	public synchronized void addPerson(Person p){
		mySet.add(p);
	}
	
	public synchronized boolean containsPerson(Person p){
		return mySet.contains(p);
	}
}
