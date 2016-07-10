package me.hao0.basic.concurrent.performance_retractility;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 不必要的长时间持有锁
 */
public class AttrbuteStore {
	private final Map<String, String> attributes 
								= new HashMap<String, String>();
	
	/**
	 * synchronized锁住当前对象
	 */
	public synchronized boolean userLocationMatcher(String name, String regexp){
		String key = "users." + name + ".location";
		String location = attributes.get(key);
		if (location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
}
