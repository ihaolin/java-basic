package me.hao0.basic.concurrent.objectshare;

/**
 * 多线程访问下，有可能出错
 */
public class Holder {
	private int n;
	
	public Holder(int n){
		this.n = n;
	}
	
	public void assertSanity(){
		if (n != n){ //两次读取有可能不一致
			throw new AssertionError("");
		}
	}
}
