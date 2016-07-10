package me.hao0.basic.concurrent.syncutils;

/**
 * 使用wait和notifyAll来实现可重新关闭的阀门
 */
public class ThreadGate {
	private boolean isOpen;
	private int generation;
	
	public synchronized void close(){
		isOpen = false;
	}
	
	public synchronized void open(){
		++ generation;
		isOpen = true;
		notifyAll();
	}
	
	public synchronized void await() throws InterruptedException{
		int arrivalGeneration = generation;
		while (!isOpen && arrivalGeneration == generation){
			wait();
		}
	}
}

