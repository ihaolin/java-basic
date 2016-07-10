package me.hao0.basic.concurrent.locks.demo;

/**
 * 锁
 * Author: haolin
 * Date  : 8/7/15.
 * Email : haolin.h0@gmail.com
 */
public class ReentrantLock {

    /**
     * 是否已被加锁
     */
    private Boolean locked = Boolean.FALSE;

    /**
     * 当前加锁线程
     */
    private Thread locking = null;

    /**
     * 记录被同一线程加锁几次
     */
    private Integer locks = 0;

    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked && (Thread.currentThread() != locking)){
                this.wait();
            }
            locked = Boolean.TRUE;
            locking = Thread.currentThread();
            ++ locks;
        }
    }

    public void unlock(){
        synchronized (this){
            if (Thread.currentThread() != locking){
                throw new IllegalMonitorStateException("current thread not locked me.");
            }
            if (--locks == 0){
                locked = Boolean.FALSE;
                locking = null;
                this.notify();
            }
        }
    }
}
