package me.hao0.basic.proxy.demo;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Author: haolin
 * Date  : 8/6/15.
 * Email : haolin.h0@gmail.com
 */
public class ProxyTest {

    @Test
    public void testDynamicProxy(){
        InvocationHandler handler = new ServiceAdvice();
        Service proxiedService = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class[]{Service.class},
                handler);

    }
}
