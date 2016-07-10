package me.hao0.basic.concurrent.locks.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 公平锁
 * Author: haolin
 * Date  : 8/7/15.
 * Email : haolin.h0@gmail.com
 */
public class FairLock2 {

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

    /**
     * 等待线程队列
     */
    private List<Object> waitings = new ArrayList<>();

    public void lock() throws InterruptedException {
        // 使用占位符来进行线程等待和唤醒
        Object placeholder = new Object();

        synchronized (this){
            // 占位(优先进入等待队列的线程，优先被执行)
            waitings.add(placeholder);
        }

        synchronized (this) {
            while ((locked && Thread.currentThread() != locking)    // 是否已经被锁且可重入
                    || waitings.get(0) != placeholder){             // 是否为最先请求锁的线程
                System.out.println(Thread.currentThread().getName() + " is waiting.");
                this.wait();
            }
            // 线程不需要等待
            System.out.println(Thread.currentThread().getName() + " is locking.");
            locked = Boolean.TRUE;
            locking = Thread.currentThread();
            ++ locks;
            // 加锁成功后，移除占位符
            waitings.remove(placeholder);
        }
    }

    public void unlock(){
        synchronized (this){
            if (Thread.currentThread() != locking){
                throw new IllegalMonitorStateException("current thread not locked me.");
            }

            if (--locks == 0){
                System.out.println(Thread.currentThread().getName() + " is unlocking.");
                locked = Boolean.FALSE;
                locking = null;
                this.notifyAll();
            }
        }
    }
}
