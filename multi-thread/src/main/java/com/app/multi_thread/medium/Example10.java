package com.app.multi_thread.medium;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 *     Concurrent Collections
 *     Description: Use ConcurrentHashMap for thread-safe map operations.
 *     Key Concept: Thread-safe data structures.
 *     Link: Builds on shared resource concepts from earlier examples, offering a higher-level solution.
 * **/
public class Example10 {

    private static final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            final int value = i;
            executor.submit(() -> map.put("Key" + value, value));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Map size: " + map.size());
    }
}
