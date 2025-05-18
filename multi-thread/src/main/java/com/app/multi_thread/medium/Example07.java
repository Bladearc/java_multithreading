package com.app.multi_thread.medium;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 CyclicBarrier for Iterative Computations

 Description: Use CyclicBarrier to synchronize threads at multiple points in an iterative process.
 Key Concept: Reusable barrier for phased thread coordination.
 Link: Introduces iterative synchronization, contrasting with the one-time latch in Example 6.
 **/

public class Example07 {

    private static final int NUM_THREADS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                try {
                    for (int step = 1; step <= 3; step++) {
                        System.out.println("Thread " + Thread.currentThread().getName() + " step " + step);
                        barrier.await();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
