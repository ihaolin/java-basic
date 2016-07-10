package me.hao0.basic.concurrent.locks.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: haolin
 * Date  : 8/8/15.
 * Email : haolin.h0@gmail.com
 */
public class Test {

    @org.junit.Test
    public void eq(){
        Object o = new Object();
        Object o1 = o;
        System.out.println(o == o1);
    }

    @org.junit.Test
    public void rm(){
        List<Object> os = new ArrayList<>();
        Object o1 = new Object();
        Object o2 = new Object();
        os.add(o1);
        os.add(o2);
        System.out.println(os);
        os.remove(o1);
        System.out.println(os);
    }
}
