package me.hao0.basic.concurrent.syncutils;

/**
 * 使用简单阻塞实现的有界缓存
 */
public class SleepyBounedBuffer<V> extends BaseBoundedBuffer<V> {
	private static long SLEEP_TIME;
	
	public SleepyBounedBuffer(int size) {
		super(size);
	}

	public void put(V v) throws InterruptedException{
		while (true){
			synchronized(this){
				if (!isFull()){
					doPut(v);
					return;
				}
			}
			Thread.sleep(SLEEP_TIME);
		}
	}
	
	public V take() throws InterruptedException{
		while (true){
			synchronized(this){
				if (!isEmpty()){
					return doTake();
				}
			}
			Thread.sleep(SLEEP_TIME);
		}
	}
}
