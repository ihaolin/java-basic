package me.hao0.basic.concurrent.jmm;

/**
 * 如果在程序中没有包含足够的同步, 那么可能产生奇怪的结果
 */
public class PossibleReordering {
	static int x = 0, y = 0;
	static int a = 0, b = 0;
	
	public static void main(String[] args) throws InterruptedException {
		Thread one = new Thread(new Runnable(){
			@Override
			public void run() {
				a = 1;
				x = b;
			}
		});
		
		Thread other = new Thread(new Runnable(){
			@Override
			public void run() {
				b = 1;
				y = a;
			}
		});
		
		one.start();
		other.start();
		one.join();
		other.join();
		
		System.out.println("x = " + x + ", y = " + y);
	}
}
