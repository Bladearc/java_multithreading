package com.app.multi_thread.medium;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *     Atomic Variables for Lock-Free Operations
 *
 *     Description: Use AtomicInteger for thread-safe operations without locks.
 *     Key Concept: Lock-free concurrency.
 *     Link: Provides an alternative to the locking approach in Example 3.
 * **/
public class Example09 {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> counter.incrementAndGet());
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Final counter value: " + counter.get());
    }
}
