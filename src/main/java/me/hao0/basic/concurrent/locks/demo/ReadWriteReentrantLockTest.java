package me.hao0.basic.concurrent.locks.demo;

/**
 * Author: haolin
 * Date  : 8/7/15.
 * Email : haolin.h0@gmail.com
 */
public class ReadWriteReentrantLockTest {

    private ReadWriteReentrantLock lock = new ReadWriteReentrantLock();

    public static void main(String[] args){
        final ReadWriteReentrantLockTest lockTest = new ReadWriteReentrantLockTest();

        for (int i = 0; i<2; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockTest.doRead();
                    } catch (InterruptedException e) {
                    }
                }
            }, "Reader" + i).start();
        }

        for (int i = 0; i<3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockTest.doWrite();
                    } catch (InterruptedException e) {
                    }
                }
            }, "Writer" + i).start();
        }

        for (int i = 2; i<5; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockTest.doRead();
                    } catch (InterruptedException e) {
                    }
                }
            }, "Reader" + i).start();
        }

        for (int i = 3; i<5; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockTest.doWrite();
                    } catch (InterruptedException e) {
                    }
                }
            }, "Writer" + i).start();
        }
    }

    public void doRead() throws InterruptedException {
        lock.lockRead();
        lock.lockRead();
        try {
            System.out.println(Thread.currentThread().getName() + " starting read.");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end read.");
        } finally {
            lock.unlockRead();
            lock.unlockRead();
        }
    }

    public void doWrite() throws InterruptedException {
        lock.lockWrite();
        lock.lockWrite();
        try {
            System.out.println(Thread.currentThread().getName() + " starting write.");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end write.");
        } finally {
            lock.unlockWrite();
            lock.unlockWrite();
        }
    }
}
