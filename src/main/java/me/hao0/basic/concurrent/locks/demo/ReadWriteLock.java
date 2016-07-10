package me.hao0.basic.concurrent.locks.demo;

/**
 * Author: haolin
 * Date  : 8/9/15.
 * Email : haolin.h0@gmail.com
 */
public class ReadWriteLock {

    /**
     * 读线程数
     */
    private Integer readCount = 0;

    /**
     * 写线程数
     */
    private Integer writeCount = 0;

    /**
     * 写请求数
     */
    private Integer writeRequests = 0;

    public void lockRead() throws InterruptedException {
        synchronized (this){
            while (writeRequests > 0 || writeCount > 0){
                // 有写线程或写请求都应该等待
                System.out.println(Thread.currentThread().getName() + " waiting read");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " locking read");
            ++ readCount;
        }
    }

    public void unlockRead(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " unlock read");
            -- readCount;
            notifyAll();
        }
    }

    public void lockWrite() throws InterruptedException {
        synchronized (this){
            ++ writeRequests; // 记录写请求, 保证写请求优先
            while (writeCount > 0 || readCount > 0){
                System.out.println(Thread.currentThread().getName() + " waiting write");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " locking write");
            -- writeRequests;
            ++ writeCount;
        }
    }

    public void unlockWrite(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " unlock write");
            -- writeCount;
            notifyAll();
        }
    }
}
