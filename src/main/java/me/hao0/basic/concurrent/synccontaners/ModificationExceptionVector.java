package me.hao0.basic.concurrent.synccontaners;

import me.hao0.basic.concurrent.threadsafe.NotThreadSafe;
import me.hao0.basic.entities.Person;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * 下面将会抛出:
 * Exception in thread "Thread-0" java.util.ConcurrentModificationException
	at java.util.Vector$Itr.checkForComodification(Vector.java:1156)
	at java.util.Vector$Itr.next(Vector.java:1133)
	at practice.synccontaners.ModificationExceptionVector$IterateThread.run(ModificationExceptionVector.java:56)
	at java.lang.Thread.run(Thread.java:744)
	可通过在迭代前锁住vector, 但这样会损失并发性能
 */
@NotThreadSafe
public class ModificationExceptionVector {
	public static void main(String[] args) {
		Vector<Person> vector = new Vector<>();
		for (int i=0; i<10; i++){
			vector.add(new Person((long)i, "person" + i));
		}
		new Thread(new IterateThread(vector)).start();
		new Thread(new RemoveThread(vector)).start();
	}
	
	private static class RemoveThread implements Runnable{
		private Vector<Person> v;
		private Random ran = new Random();
		public RemoveThread(Vector<Person> v) {
			this.v = v;
		}
		
		@Override
		public void run() {
			try {
				// do 100 times' remove
				for (int i=0 ;i<5; i++){
					v.remove(ran.nextInt(v.size()));
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
			}
		}
	}
	
	private static class IterateThread implements Runnable{
		private Vector<Person> v;
		
		public IterateThread(Vector<Person> v) {
			this.v = v;
		}
		
		@Override
		public void run() {
			try {
				Iterator<Person> it = v.iterator();
				while (it.hasNext()){
					System.out.println(it.next());
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
			}
		}
	}
}
