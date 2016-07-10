package me.hao0.basic.reflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodTests {
	
	@Test
	public void testAnnoations() throws NoSuchMethodException, SecurityException{
		Method m = MethodTests.class.getMethod("fun1", String.class, Integer.class);
		Annotation[][] annos = m.getParameterAnnotations();
		for (int i=0; i<annos.length; i++){
			System.out.println(annos[i].length);
			for (int j=0; j<annos[i].length; j++){
				System.out.println(annos[i][j].annotationType());
			}
			System.out.println();
		}
	}

}	
