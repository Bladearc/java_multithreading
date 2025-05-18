package com.app.multi_thread.medium;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * Semaphores for Resource Limiting
 *
 *     Description: Use a Semaphore to limit concurrent access to a simulated resource.
 *     Key Concept: Controlling access to a finite resource pool.
 *     Link: Introduces a new synchronization tool while reusing the thread pool from earlier examples.
 * **/

public class Example05 {

    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Acquired permit: " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                    System.out.println("Released permit: " + Thread.currentThread().getName());
                }
            });
        }
        executor.shutdown();
    }

}
