package me.hao0.basic.concurrent.jmm;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象的初始化安全性
 */
public class SafeStates {
	private final Map<String, String> states;
	
	public SafeStates(){
		states = new HashMap<String, String>();
		states.put("one", "One");
		//...
		states.put("two", "Two");
		//...
	}
	
	public String getAbbreviation(String s){
		return states.get(s);
	}
}
