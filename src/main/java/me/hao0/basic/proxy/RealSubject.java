package me.hao0.basic.proxy;

/**
 * 被代理类
 * @author Hao Lin
 * @since 2013
 * @focus java core
 */
public class RealSubject implements Subject {

	@Override
	public void request() {
		System.out.println("我是被代理类..");
	}
}
