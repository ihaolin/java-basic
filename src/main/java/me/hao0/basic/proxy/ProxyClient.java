package me.hao0.basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 客户端
 * @author Hao Lin
 * @since 2013
 * @focus java core
 */
public class ProxyClient {
	public static void main(String[] args) {
		//被代理类
		RealSubject  rs = new RealSubject();
		//代理处理器
		InvocationHandler handler = new DynamicSubject(rs);
		//代理对象
		Subject sub = (Subject) Proxy.newProxyInstance(
							rs.getClass().getClassLoader(), 
							rs.getClass().getInterfaces(), handler);
		sub.request();
	}
}
