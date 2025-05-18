package com.app.multi_thread.medium;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *     ThreadLocal for Thread-Specific Data
 *     Description: Use ThreadLocal to maintain thread-specific data without synchronization.
 *     Key Concept: Per-thread storage.
 *     Link: Offers an alternative to shared resource synchronization seen in Examples 3 and 4.
**/
public class Example08 {
    private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Initial Value");

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            threadLocal.set("Thread 1 Value");
            System.out.println("Thread 1: " + threadLocal.get());
        });
        executor.submit(() -> {
            System.out.println("Thread 2: " + threadLocal.get());
            threadLocal.set("Thread 2 Value");
            System.out.println("Thread 2: " + threadLocal.get());
        });
        executor.shutdown();
    }
}
