package me.hao0.basic.concurrent.tests;

public class TestResourceLeak {
	private static final int CAPACITY = 1000;
	void testLeak() throws InterruptedException{
		BoundedBuffer<Big> bb = new BoundedBuffer<Big>(CAPACITY);
		// 生成堆快照
		for (int i=0; i<CAPACITY; i++){
			bb.put(new Big());
		}
		for (int i=0; i<CAPACITY; i++){
			bb.take(); //这里必须会通过doExtract(): items[i] = null;来释放资源
		}
		// 生成堆快照
	}
	
	class Big {
		double[] data = new double[100000];
	}
}
