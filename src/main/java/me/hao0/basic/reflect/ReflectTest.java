package me.hao0.basic.reflect;


import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: haolin
 * Date  : 8/6/15.
 * Email : haolin.h0@gmail.com
 */
public class ReflectTest {

    @Test
    public void testInvokeVoidMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person p = new Person();
        Method m = Person.class.getMethod("hello");
        System.out.println(m.invoke(p));
    }

    @Test
    public void testArrayType(){
        String[] strings = new String[3];
        Class stringArrayClass = strings.getClass();
        Class stringArrayComponentType = stringArrayClass.getComponentType();
        System.out.println(stringArrayComponentType.getName());
    }
}
