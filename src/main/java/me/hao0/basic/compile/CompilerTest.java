package me.hao0.basic.compile;

import javax.tools.ToolProvider;
import java.util.Map.Entry;
import java.util.Properties;

public class CompilerTest {
	public static void main(String[] args) {
		Properties ps = System.getProperties();
		for (Entry<Object, Object> en : ps.entrySet()){
			System.out.println(en.getKey() + "=" + en.getValue());
		}
		System.out.println("java compiler: " + ToolProvider.getSystemJavaCompiler());
	}
}	
