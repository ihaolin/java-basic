package me.hao0.basic.concurrent.performance_retractility;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 不必要的长时间持有锁
 */
public class BetterAttrbuteStore {
	private final Map<String, String> attributes 
								= new HashMap<String, String>();
	public boolean userLocationMatcher(String name, String regexp){
		String key = "users." + name + ".location";
		String location = null;
		synchronized(this){
			location = attributes.get(key); //仅锁住共享对象
		}
		if (location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
}
