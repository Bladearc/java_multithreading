package com.app.multi_thread.baisc;

public class Example05 {
    public static void main(String[] args) throws InterruptedException {
        // Create a new thread using a lambda expression
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread is working...");
            try {
                // Simulate some work with sleep
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Worker interrupted!");
            }
            System.out.println("Worker thread done!");
        });
        // Start the worker thread
        worker.start();
        // Main thread waits for worker to finish
        worker.join();
        System.out.println("Main thread continues after worker is done.");
    }
}