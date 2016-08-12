package me.hao0.basic.concurrent.objectcombine;


import me.hao0.basic.concurrent.threadsafe.ThreadSafe;
import me.hao0.basic.entities.Person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 委托的PersonSet
 * 将内部操作委托给线程安全的类SynchronizedSet
 * 从而自身也是线程安全的
 */
@ThreadSafe
public class DelegatingPersonSet {
	private final Set<Person> mySet =
			Collections.synchronizedSet(new HashSet<Person>());
	
	public void addPerson(Person p){
		mySet.add(p);
	}
	
	public boolean containsPerson(Person p){
		return mySet.contains(p);
	}
}
