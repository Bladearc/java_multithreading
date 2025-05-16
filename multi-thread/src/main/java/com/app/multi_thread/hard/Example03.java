package com.app.multi_thread.hard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Example 3: Synchronizing Access with Locks
 * Description: Use ReentrantLock to synchronize access to a shared counter across multiple threads.
 * Key Concept: Explicit locking for thread safety.
 * **/
public class Example03 {
    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5000; i++) {
            executor.submit(() -> {
                lock.lock();
                try {
                    counter++;
                } finally {
                    lock.unlock();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
        System.out.println("Final counter value: " + counter);
    }
}
