package me.hao0.basic.code4j.methodhandle;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * java7 中用方法句柄代替反射
 * @author haolin
 *
 */
public class MethodHandleTests {
	
	@Test
	public void testInvokeMehtod() throws Throwable{
		// 这里假设我们调用String类的toUpperCase()方法
		// 返回类型为String.class, 参数类型为void.class
		MethodType type = MethodType.methodType(String.class);
		
		MethodHandle handle = MethodHandles.lookup().findVirtual(String.class, "toUpperCase", type);
		System.out.println(handle.invoke("This is test for method handle."));
	}
}
