package com.xqbase.java;

import java.util.Random;
import java.util.Vector;

/**
 * Producer&Consumer example in Java with wait&notify.
 *
 * @author Tony He
 */
public class ProducerConsumerSolution {

    public static void main(String[] args) {
        Vector<Integer> sharedQueue = new Vector<Integer>();
        int size = 10;
        Thread proThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread conThread = new Thread(new Consumer(sharedQueue), "Consumer");
        proThread.start();
        conThread.start();
    }

    static class Producer implements Runnable {

        private final Vector<Integer> sharedQueue;
        private final int size;

        public Producer(Vector<Integer> sharedQueue, int size) {
            this.sharedQueue = sharedQueue;
            this.size = size;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (sharedQueue) {
                    while (sharedQueue.size() == size) {
                        try {
                            System.out.println("Queue is full, " + Thread.currentThread().getName()
                                    + " is waiting, size is " + sharedQueue.size());

                            sharedQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Random r = new Random();
                    sharedQueue.add(r.nextInt());
                    sharedQueue.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private final Vector sharedQueue;

        public Consumer(Vector sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (sharedQueue) {
                    while (sharedQueue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty, " + Thread.currentThread().getName()
                                    + " is waiting, size is " + sharedQueue.size());

                            sharedQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Consumed " + sharedQueue.remove(0));
                    sharedQueue.notifyAll();
                }
            }
        }
    }
}
