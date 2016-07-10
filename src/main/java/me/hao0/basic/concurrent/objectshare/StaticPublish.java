package me.hao0.basic.concurrent.objectshare;

import java.util.HashSet;
import java.util.Set;

public class StaticPublish {
	public static Set<Object> publishedObject;
	public void init(){
		publishedObject = new HashSet<>();
	}
}
