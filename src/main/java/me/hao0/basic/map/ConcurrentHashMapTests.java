package me.hao0.basic.map;

import me.hao0.basic.entities.Person;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * count	ConcurrentHashMap	HashTable	SynchronizedMap
 * 10000		72ms
 * 100000		300ms				
 * 1000000		9000ms				
 * 上午12:58:08
 */
public class ConcurrentHashMapTests {
	private static int count = 100000;
	
	public static void main(String[] args) {
		Map<Person, Integer> map  = new ConcurrentHashMap<>();
		final int threadsCount = 16;
		final long start = System.currentTimeMillis();
		for (int i=0; i<threadsCount; i++){
			new InsertThread(map).start();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println(threadsCount + " threads cost time: "
						+ (System.currentTimeMillis()-start) + "ms");
			}
		});
	}
	
	public static class InsertThread extends Thread{
		private Map<Person, Integer> map;
		
		public InsertThread(Map<Person, Integer> map){
			this.map = map;
		}

		@Override
		public void run() {
			for (int i=0; i<count; i++){
				//System.out.println(Thread.currentThread().getName() + "put"+i);
				//map.put(new Person(i, "linhao" + i), i);
			}
		}
	}
}
