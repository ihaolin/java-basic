package me.hao0.basic.concurrent.cancelclose;

import me.hao0.concurrency.practice.tests.BoundedBuffer;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoundedBufferTests {
	
	@Test
	public void testIsEmptyWhenConstructed(){
		BoundedBuffer<String> bf = new BoundedBuffer<String>(10);
		assertTrue(bf.isEmpty());
	}
	
	@Test
	public void testIsFullAfterPuts() throws InterruptedException{
		BoundedBuffer<String> bf = new BoundedBuffer<String>(10);
		for (int i=0; i<10; i++){
			bf.put("" + i);
		}
		assertTrue(bf.isFull());
		assertTrue(bf.isEmpty());
	}
	
	@Test
	public void testTaskBlocksWhenEmpty(){
		final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		Thread taker = new Thread(){
			@Override
			public void run() {
				try {
					int unused =  bb.take();
					fail(); //不应执行到这里
				} catch (InterruptedException e) {
				}
			}
		};
		try {
			taker.start();
			Thread.sleep(1000);
			taker.interrupt();
			taker.join(2000); //保证即使taker永久阻塞也能返回
			assertFalse(taker.isAlive());
		} catch (InterruptedException e) {
			fail();
		}
	}
}
