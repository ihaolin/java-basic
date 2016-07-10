package me.hao0.basic.concurrent.locks.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 公平锁
 * Author: haolin
 * Date  : 8/7/15.
 * Email : haolin.h0@gmail.com
 */
public class FairLock {

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
    private List<PlaceHolder> waitings = new ArrayList<>();

    public void lock() throws InterruptedException {
        // 使用占位符来进行线程等待和唤醒
        PlaceHolder placeholder = new PlaceHolder();

        synchronized (this){
            // 占位(优先进入等待队列的线程，优先被执行)
            waitings.add(placeholder);
        }

        Boolean needWait = Boolean.TRUE;
        while (needWait){
            synchronized (this) {
                needWait = (locked && Thread.currentThread() != locking)    // 是否已经被锁且可重入
                            || waitings.get(0) != placeholder;              // 是否为最先请求锁的线程
                if (!needWait){
                    // 线程不需要等待
                    System.out.println(Thread.currentThread().getName() + " is locking.");
                    locked = Boolean.TRUE;
                    locking = Thread.currentThread();
                    ++ locks;
                    // 加锁成功后，移除占位符
                    waitings.remove(placeholder);
                    return;
                }
            }
            System.out.println(Thread.currentThread().getName() + " is waiting.");
            // 线程等待在自己占位符上, 不是this
            placeholder.doWait();
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
                if (waitings.size() > 0){
                    // 通知最先等待的线程
                    waitings.get(0).doNotify();
                }
            }
        }
    }

    class PlaceHolder {

        private boolean isNotified = false;

        public synchronized void doWait() throws InterruptedException {
            while (!isNotified) {
                this.wait();
            }
            this.isNotified = false;
        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }

        public boolean equals(Object o) {
            return this == o;
        }
    }
}
