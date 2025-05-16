package com.app.multi_thread.hard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example 1: Thread Pool Basics
 *
 *     Description: Create a thread pool using ExecutorService to execute multiple tasks concurrently.
 *     Key Concept: Managing a pool of worker threads.
 *     Purpose: Serves as the foundation for subsequent examples that enhance thread pool usage.
 * **/
public class Example01 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}

