package com.app.multi_thread.medium;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch for Synchronization
 *     Description: Use CountDownLatch to wait for multiple tasks to complete before proceeding.
 *     Key Concept: One-time synchronization of thread completion.
 *     Link: Combines thread pools with latch-based coordination, building on earlier examples.
 * **/
public class Example06 {

    public static void main(String[] args) throws Exception {
        int numTasks = 5;
        CountDownLatch latch = new CountDownLatch(numTasks);
        ExecutorService executor = Executors.newFixedThreadPool(numTasks);

        for (int i = 0; i < numTasks; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        System.out.println("Waiting for tasks to complete...");
        latch.await();
        System.out.println("All tasks completed.");
        executor.shutdown();
    }
}
