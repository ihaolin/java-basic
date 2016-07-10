package me.hao0.basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * @author Hao Lin
 * @since 2013
 * @focus java core
 */
public class DynamicSubject implements InvocationHandler {
	private Object sub;
	
	public DynamicSubject(){}
	
	public DynamicSubject(Object obj){
		this.sub = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object res = null;
		System.out.println("调用前被代理类前..");
		res = method.invoke(sub, args);
		System.out.println("调用前被代理类后..");
		return res;
	}
}
