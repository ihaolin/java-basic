package me.hao0.basic.concurrent.locks.demo;

/**
 * 锁
 * Author: haolin
 * Date  : 8/7/15.
 * Email : haolin.h0@gmail.com
 */
public class Lock {

    private Boolean locked = Boolean.FALSE;

    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){ // 自旋转，防止假唤醒
                System.out.println(Thread.currentThread().getName() + " : Lock is locked, need waiting.");
                this.wait();
            }
            locked = Boolean.TRUE;
        }
    }

    public void unlock(){
        synchronized (this){
            if (!Thread.holdsLock(this)){
                throw new IllegalMonitorStateException("current thread not locked me.");
            }
            locked = Boolean.FALSE;
            this.notify();
        }
    }
}
