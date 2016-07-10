package me.hao0.basic.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestPing {
	public static void main(String[] args) throws IOException {
		System.getProperties().list(System.out);
		Runtime runtime = Runtime.getRuntime();
		String cmd = "ping www.baidu.com";
		Process prop = runtime.exec(cmd);
		BufferedReader reader = new BufferedReader(
					new InputStreamReader(prop.getInputStream(), "UTF-8"));
		String line = null;
		while ((line = reader.readLine()) != null){
			System.out.println(line);
		}
	}
}
