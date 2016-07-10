package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.Callable;

public class LoadFileTask implements Callable<String> {
	private String page;
	
	public LoadFileTask(String page){
		this.page = page;
	}
	
	@Override
	public String call() throws Exception {
		return null;
	}
}
