package com.app.multi_thread.medium;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Description: Use Condition with a lock to make a thread wait until a counter reaches a specific value.
 * Key Concept: Advanced synchronization with condition signaling.
 * Link: Enhances Example 3 by adding conditional waiting and thread coordination.
 * **/

public class Example04 {
    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            lock.lock();
            try {
                while (counter < 100) {
                    condition.await();
                }
                System.out.println("Counter reached 100!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                lock.lock();
                try {
                    counter++;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            });
        }

        executor.shutdown();
    }
}
