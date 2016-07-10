package me.hao0.basic.concurrent.locks.demo;

/**
 * Author: haolin
 * Date  : 8/7/15.
 * Email : haolin.h0@gmail.com
 */
public class FairLock2Test {

    private FairLock2 lock = new FairLock2();

    public static void main(String[] args){
        final FairLock2Test lockTest = new FairLock2Test();
        for (int i = 0; i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockTest.doWork();
                    } catch (InterruptedException e) {
                    }
                }
            }, "Worker" + i).start();
        }
    }

    public void doWork() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " starting work.");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end work.");
        } finally {
            lock.unlock();
        }
    }
}
