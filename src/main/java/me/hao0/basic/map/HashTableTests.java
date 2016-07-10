package me.hao0.basic.map;

import me.hao0.entities.Person;

import java.util.Hashtable;

/**
 * count	ConcurrentHashMap	HashTable	SynchronizedMap
 * 10000		72ms				
 * 上午12:58:08
 */
public class HashTableTests {
	private static int count = 1000000;
	
	public static void main(String[] args) {
		Hashtable<Person, Integer> map  = new Hashtable<>();
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
		private Hashtable<Person, Integer> map;
		
		public InsertThread(Hashtable<Person, Integer> map){
			this.map = map;
		}

		@Override
		public void run() {
			for (int i=0; i<count; i++){
				//System.out.println(Thread.currentThread().getName() + "put"+i);
				map.put(new Person((long)i, "linhao"+i), i);
			}
		}
	}
}
