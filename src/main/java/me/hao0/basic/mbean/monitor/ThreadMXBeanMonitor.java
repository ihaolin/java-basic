package me.hao0.basic.mbean.monitor;

import java.lang.management.ManagementFactory;

/**
 * Author: haolin
 * Date:   7/6/16
 * Email:  haolin.h0@gmail.com
 */
public class ThreadMXBeanMonitor {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        while (true){
            System.out.println("I'm sleeping...");
            Thread.sleep(10000L);
        }
    }
}
