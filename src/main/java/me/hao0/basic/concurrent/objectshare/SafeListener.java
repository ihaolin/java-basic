package me.hao0.basic.concurrent.objectshare;

import java.awt.*;

/**
 * 通过工厂方法防止this逸出
 */
public class SafeListener {
	private final EventListener listener;
	private SafeListener(){
		listener = new EventListener() {
			@Override
			public void onEvent(Event e) {
				//do sth.
			}
		};
	}
	
	public static SafeListener newInstance(EventSource source){
		SafeListener safe = new SafeListener();
		source.registerListener(safe.listener);
		return safe;
	}
}
