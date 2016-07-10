package me.hao0.basic.concurrent.objectshare;

import java.awt.*;

/**
 * 通过发布类的内部实例
 * this引用被逸出
 */
public class ThisEscape {
	public ThisEscape(EventSource source){
		source.registerListener(new EventListener() {
			@Override
			public void onEvent(Event e) {
				// ThisEscape.this is published
			}
		});
	}
}
