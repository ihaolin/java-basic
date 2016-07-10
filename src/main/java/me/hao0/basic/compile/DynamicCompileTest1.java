package me.hao0.basic.compile;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class DynamicCompileTest1 {
	public static void main(String[] args) throws Exception {
		// 1.创建需要动态编译的代码字符串
		String nr = "\r\n";// 回车换行
		String source = "package com.flyoung.hello;" + nr
				+ "    public class Hello{" + nr
				+ "     public static void main(String[] args){" + nr
				+ "     System.out.println(\"helloworld!\");" + nr + "}" + nr
				+ "}";
		// 2.将预动态编译的代码写入文件中1:创建临时目录 2:写入临时文件
		File dir = new File(System.getProperty("user.dir") + "/temp");// 临时目录
		// 如果/temp目录不存在创建temp目录
		if (!dir.exists()) {
			dir.mkdir();
		}
		FileWriter writer = new FileWriter(new File(dir, "Hello.java"));
		writer.write(source);// 将字符串写入文件中
		writer.flush();
		writer.close();
		// 3:取得当前系统java编译器
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		// 4:获取一个文件管理器StandardJavaFileManage
		StandardJavaFileManager javaFileManager = javaCompiler
				.getStandardFileManager(null, null, null);
		// 5.文件管理器根与文件连接起来
		Iterable it = javaFileManager.getJavaFileObjects(new File(dir,
				"Hello.java"));
		// 6.创建编译的任务
		CompilationTask task = javaCompiler.getTask(null, javaFileManager,
				null, Arrays.asList("-d", "./temp"), null, it);
		// 执行编译
		if(task.call()){
		}
		
		javaFileManager.close();
	}
}