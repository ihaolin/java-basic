package me.hao0.basic.concurrent.locks.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: haolin
 * Date  : 8/9/15.
 * Email : haolin.h0@gmail.com
 */
public class ReadWriteReentrantLock {

    /**
     * <读线程, 读线程请求数>
     */
    private final Map<Thread, Integer> readings = new HashMap<>();

    /**
     * 写请求数
     */
    private Integer writeRequests = 0;

    /**
     * 当前写线程
     */
    private Thread writing;

    /**
     * 当前写线程的重入数
     */
    private Integer writeCount = 0;

    public void lockRead() throws InterruptedException {
        synchronized (this){
            Thread current = Thread.currentThread();
            while (needWaitRead()){
                // 有写线程或写请求都应该等待
                System.out.println(current.getName() + " waiting read");
                wait();
            }
            System.out.println(current.getName() + " locking read");
            if (readings.get(current) != null){
                readings.put(current, readings.get(current) + 1);
            } else {
                readings.put(current, 1);
            }
        }
    }

    /**
     * 是否需要等待读锁
     */
    private Boolean needWaitRead(){
        if(writeCount > 0) {
            // 有写线程已加锁
            return Boolean.TRUE;
        }
        if(readings.get(Thread.currentThread()) != null) {
            // 当前线程已经获取过读锁
            return Boolean.FALSE;
        }
        if(writeRequests > 0) {
            // 有写请求
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void unlockRead(){
        synchronized (this){
            Thread current = Thread.currentThread();
            System.out.println(current.getName() + " unlock read");
            Integer readCount = readings.get(current);
            if (readCount == 1){
                readings.remove(current);
                notifyAll();
            } else {
                readings.put(current, --readCount);
            }
        }
    }

    public void lockWrite() throws InterruptedException {
        synchronized (this){
            Thread current = Thread.currentThread();
            ++ writeRequests; // 记录写请求, 保证写请求优先
            while (needWaitWrite()){
                System.out.println(current.getName() + " waiting write");
                wait();
            }
            System.out.println(current.getName() + " locking write");
            -- writeRequests;
            ++ writeCount;
            writing = current;
        }
    }

    private Boolean needWaitWrite(){
        if (readings.size() > 0){
            // 已经有读线程
            return Boolean.TRUE;
        }
        if (writing == null){
            // 还没有写线程
            return Boolean.FALSE;
        }
        if (Thread.currentThread() == writing){
            // 当前线程已经获得写锁
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public void unlockWrite(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " unlock write");
            if(-- writeCount == 0){
                writing = null;
                notifyAll();
            }
        }
    }
}
