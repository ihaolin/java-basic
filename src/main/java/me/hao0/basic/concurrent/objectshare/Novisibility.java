package me.hao0.basic.concurrent.objectshare;

/**
 * 可见性问题导致，程序运行结果不正确
 * 有可能由于编译器，处理器及运行时做一些重排序
 */
public class Novisibility {
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread{
		@Override
		public void run() {
			while (!ready){
				Thread.yield(); //主动让出cpu, 进入就绪队列
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}
