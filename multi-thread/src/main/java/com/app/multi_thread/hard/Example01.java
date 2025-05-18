package com.app.multi_thread.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * In this example, we simulate a system where multiple worker threads must coordinate to complete phases of a task in a specific order. Imagine a scenario where a group of threads (workers) must prepare resources in parallel (Phase 1), wait for all preparations to complete, and then process those resources together (Phase 2). We’ll use CountDownLatch to ensure all threads complete Phase 1 before any proceed to Phase 2, and CyclicBarrier to synchronize the start of Phase 2 across all threads. This setup mimics real-world systems like distributed computing or parallel simulations, where timing and coordination are critical.
 * Key Challenges
 *
 *     Race Conditions: If synchronization isn’t perfect, threads might access shared resources prematurely, leading to inconsistent states.
 *     Deadlocks or Livelocks: Improper use of latches or barriers could cause threads to wait indefinitely.
 *     Debugging Difficulty: The non-deterministic nature of thread scheduling means issues like missed signals or premature progression might only appear sporadically, making them nearly impossible to trace without advanced tools or deep concurrency insight.
 *     Order and Visibility: Ensuring all threads see the same state after synchronization points requires understanding the Java Memory Model.
 *
 * Solution
 *
 * We’ll use:
 *
 *     CountDownLatch: To wait for all threads to finish Phase 1 (resource preparation).
 *     CyclicBarrier: To synchronize the start of Phase 2 (resource processing) across all threads.
 *     Shared State: A thread-safe list to simulate resource preparation and consumption.
 * **/

public class Example01 {
    private static final int NUM_WORKERS = 5;
    private final CountDownLatch prepLatch = new CountDownLatch(NUM_WORKERS);
    private final CyclicBarrier processBarrier = new CyclicBarrier(NUM_WORKERS, () ->
            System.out.println("All workers have reached Phase 2 - Processing begins."));
    private final List<Integer> sharedResources = Collections.synchronizedList(new ArrayList<>());

    class Worker implements Runnable {
        private final int workerId;

        Worker(int workerId) {
            this.workerId = workerId;
        }

        @Override
        public void run() {
            try {
                // Phase 1: Prepare resources
                System.out.println("Worker " + workerId + " preparing resources...");
                int resource = ThreadLocalRandom.current().nextInt(100);
                Thread.sleep(500 + ThreadLocalRandom.current().nextInt(500)); // Simulate work
                sharedResources.add(resource);
                System.out.println("Worker " + workerId + " prepared resource: " + resource);
                prepLatch.countDown();

                // Wait for all workers to finish Phase 1
                prepLatch.await();

                // Phase 2: Process resources (synchronized start)
                System.out.println("Worker " + workerId + " waiting at barrier...");
                processBarrier.await();
                System.out.println("Worker " + workerId + " processing resources: " + sharedResources);
                Thread.sleep(300); // Simulate processing
                synchronized (sharedResources) {
                    if (!sharedResources.isEmpty()) {
                        int consumed = sharedResources.removeFirst();
                        System.out.println("Worker " + workerId + " consumed: " + consumed);
                    }
                }
            } catch (Exception e) {
                System.err.println("Worker " + workerId + " encountered an error: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_WORKERS);
        for (int i = 0; i < NUM_WORKERS; i++) {
            executor.submit(new Worker(i));
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
       Example01 example01 = new Example01();
       example01.start();
    }
}
