package com.app.multi_thread.hard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Example 2: Thread Pool with Future
 *
 *     Description: Submit tasks that return results using Callable and retrieve them in the Future.
 *     Key Concept: Asynchronous task execution with return values.
 *     Purpose: Demonstrates how to manage task results and exceptions in a thread pool.
 **/

public class Example02 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<Integer>[] futures = new Future[10];
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            futures[i] = executor.submit(() -> taskId * 2);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Result of task " + i + ": " + futures[i].get());
        }
        executor.shutdown();
    }
}
