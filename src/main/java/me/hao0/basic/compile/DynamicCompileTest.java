package me.hao0.basic.compile;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class DynamicCompileTest {
	public static void main(String[] args) throws Exception {
		/*
		 * 编译内存中的java代码
		 */
		// 将代码写入内存中
		StringWriter writer = new StringWriter();// 内存字符串输出流
		PrintWriter out = new PrintWriter(writer);
		out.println("package com.flyoung.hello;");
		out.println("public class Hello{");
		out.println("public static void main(String[] args){");
		out.println("System.out.println(\"helloworld!\");");
		out.println("}");
		out.println("}");
		out.flush();
		out.close();
		// 开始编译
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		JavaFileObject fileObject = new JavaStringObject("Hello",
				writer.toString());
		CompilationTask task = javaCompiler.getTask(null, null, null,
				Arrays.asList("-d", "./bin"), null, Arrays.asList(fileObject));
		boolean success = task.call();
		if (!success) {
			System.out.println("编译失败!");
		} else {
			System.out.println("编译成功！");
			// 利用反射调用其中的main()方法
			// Class class1=Class.forName("com.flyoung.hello.Hello");
			// ClassLoader是自动去从当前工作目录下的classpath路径下去找 也就是bin目录下
			// Class
			// class1=ClassLoader.getSystemClassLoader().loadClass("com.flyoung.hello.Hello");

			// 利用URLClassLoader去实例化一个Class类 类文件可以放在任意位置，这样就很方便了
			URL[] urls = new URL[] { new URL("file:/" + "./bin/") };
			URLClassLoader classLoader = new URLClassLoader(urls);
			Class class1 = classLoader.loadClass("com.flyoung.hello.Hello");
			Method method = class1.getDeclaredMethod("main", String[].class);
			String[] args1 = { null };
			method.invoke(class1.newInstance(), args1);
		}
	}
}