package me.hao0.basic.other;

import org.junit.Test;

public class SystemTest {
	
	@Test
	public void testBin(){
		System.out.println(SystemTest.class.getClassLoader().getResource("").getPath());
	}
}
