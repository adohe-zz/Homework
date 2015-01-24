package com.xqbase.java;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Sample example about how to use ReentrantLock
 */
public class ReentrantLockHowto {

    private final ReentrantLock lock = new ReentrantLock();
    private int count;

    // Locking using Lock and ReentrantLock
    public int getCount() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " gets count: " + count);
            return count ++;
        } finally {
            lock.unlock();
        }
    }

    public synchronized int getCountTwo() {
        System.out.println(Thread.currentThread().getName() + " gets count: " + count);
        return count++;
    }

    public static void main(String[] args) {
        final ReentrantLockHowto counter = new ReentrantLockHowto();
        Thread t1 = new Thread() {

            @Override
            public void run() {
                while (counter.getCount() < 6) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();                    }
                }
            }
        };

        Thread t2 = new Thread() {

            @Override
            public void run() {
                while (counter.getCount() < 6) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
